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
import towork.domain.Ocupacio;
import towork.repository.OcupacioRepository;
import towork.service.impl.OcupacioServiceImpl;


/**
 *
 * @author gonem
 */
@Repository
public class OcupacioDAO implements OcupacioRepository{
    private Dbconnection dBConnection;
    private Connection connection;

    /**
     * Constructor
     * @param dBConnection 
     */
    public OcupacioDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    
    /**
     * Constructor sense paràmetres
     */
    public OcupacioDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(OcupacioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
      * S'encarrega de construir un objecte ocupacio a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return ocupacio
      * @throws SQLException 
      */
      private Ocupacio buildOcupacioFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codiOcupacio = rs.getInt("codiOcupacio");       
        String nomOcupacio = rs.getString("nomOcupacio");
       
        Ocupacio ocupacio = new Ocupacio(codiOcupacio,nomOcupacio);
        return ocupacio;
    }
     /**
      * Afegir ocupacio a ocupacions
      * @param preparedStatement
      * @return totes les ocupacions que resultin d'executar la consulta
      */
     private List<Ocupacio> executeQuery(PreparedStatement preparedStatement) {
            
       List<Ocupacio> ocupacions=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Ocupacio ocupacio=buildOcupacioFromResultSet(rs);
                ocupacions.add(ocupacio);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return ocupacions;
    }
    /**
     * Mètode per obtenir totes les ocupacions
     * @return ocupacions
     */
    public List<Ocupacio> getAllOcupacions(){
        String qry = "select * from ocupacions";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            List<Ocupacio> ocupacions = executeQuery(preparedStatement);
            return ocupacions;
        } catch (SQLException ex) {
            Logger.getLogger(OcupacioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     /**
     * Examina la llista retornada per executeQuery
     * @param preparedStatement
     * @return un objecte null si no hi ha cap resultat per a la consulta i 
     * un objecte Habilitat,si hi és, troba un resultat
     * @throws Exception 
     */
    private Ocupacio findUniqueResult(PreparedStatement preparedStatement) throws Exception {
             
        List<Ocupacio> ocupacions=executeQuery(preparedStatement);
        if(ocupacions.isEmpty()){
            return null;
        }
        if(ocupacions.size()>1){
            throw new Exception("Només s'espera 1 resultat");
        }
        return ocupacions.get(0);
    }
    /**
    * Mètode per obtenir l'ocupació a partir del codiOcupacio
    * @param codiOcupacio
    * @return Ocupacio o null
    */ 
    public Ocupacio getOcupacioByCodiOcupacio(Integer codiOcupacio){
        String qry = "select * from ocupacions where codiOcupacio = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiOcupacio);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(OcupacioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     /**
     * Mètode per obtenir el nom d'una ocupació a partir del codiOcupacio
     * @param codiOcupacio
     * @return nom de l'ocupació 
     */
    @Override
    public String getNomOcupacio(Integer codiOcupacio){
        Ocupacio ocup=getOcupacioByCodiOcupacio(codiOcupacio);
        return ocup.getNomOcupacio();
    }
}
