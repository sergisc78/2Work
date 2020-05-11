/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.stereotype.Repository;
import towork.domain.Formacio;
import towork.repository.FormacioRepository;
import towork.service.impl.FormacioServiceImpl;


/**
 *
 * @author gonem
 */
@Repository
public class FormacioDAO implements FormacioRepository{
    private Dbconnection dBConnection;
    private Connection connection;

    /**
     * Constructor 
     * @param dBConnection 
     */
    public FormacioDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    /**
     * Constructor sense paràmetres 
     */
    public FormacioDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(FormacioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Per obtenir la connexió
     * @return la connexió
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Establir connexió
     * @param connection 
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    /**
     * S'encarrega d'obtenir un objecte PreparedStatement ja sigui reutilitzant una connexió 
     * existent o creant-ne una nova
     * @param query
     * @return connexió
     * @throws SQLException 
     */
     private PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (getConnection() == null) {
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return getConnection().prepareStatement(query);
    }
     /**
      * S'encarrega de construir un objecte formacio a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return formacio
      * @throws SQLException 
      */
      private Formacio buildFormacioFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codiFormacio = rs.getInt("codiFormacio");
        String nomFormacio = rs.getString("nomFormacio");
        
        Formacio formacio = new Formacio(codiFormacio,nomFormacio);
        return formacio;
    }   
       /**
      * Afegir formacio a formacions
      * @param preparedStatement
      * @return tots les formacions que resultin d'executar la consulta
      */
     private List<Formacio> executeQuery(PreparedStatement preparedStatement) {
            
       List<Formacio> formacions=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Formacio formacio=buildFormacioFromResultSet(rs);
                formacions.add(formacio);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return formacions;
    }
     /**
      * Examina la llista retornada per executeQuery
      * @param preparedStatement
      * @return un objecte null si no hi ha cap resultat per la consulta i
      * un objecte Formacio, si hi és, troba un resultat
      * @throws Exception 
      */
      private Formacio findUniqueResult(PreparedStatement preparedStatement) throws Exception {
             
        List<Formacio> formacions=executeQuery(preparedStatement);
        if(formacions.isEmpty()){
            return null;
        }
        if(formacions.size()>1){
            throw new Exception("Només s'espera 1 resultat");
        }
        return formacions.get(0);
    }
    /**
     * Mètode per obtenir l'objecte Formacio a partir del codiFormacio
     * @param codiFormacio
     * @return 
     */
     public Formacio getFormacio(Integer codiFormacio){
        String qry = "select * from formacions where codiFormacio = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiFormacio);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(FormacioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Mètode per obtenir el nom de la formació a partir del codiFormacio
     * @param codiFormacio
     * @return 
     */
    @Override
    public String getNomFormacio(Integer codiFormacio){
        Formacio form=getFormacio(codiFormacio);
        return form.getNomFormacio();
    }
}
