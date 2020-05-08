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
import towork.domain.Habilitat;
import towork.repository.HabilitatRepository;
import towork.service.impl.HabilitatServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class HabilitatDAO implements HabilitatRepository{
    private Dbconnection dBConnection;
    private Connection connection;

     /**
     * Constructor
     * @param dBConnection 
     */
    public HabilitatDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    /**
     * Constructor sense paràmetres
     */
    public HabilitatDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(HabilitatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
      * S'encarrega de construir un objecte habilitat a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return habilitat
      * @throws SQLException 
      */
      private Habilitat buildHabilitatFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codiHab = rs.getInt("codiHab");       
        String nomHab = rs.getString("nomHab");
        Integer ocupacio = rs.getInt("ocupacio"); 
        
        Habilitat habilitat = new Habilitat(codiHab,nomHab,ocupacio);
        return habilitat;
    }
     /**
      * Afegir habilitat a habilitats
      * @param preparedStatement
      * @return totes les habilitats que resultin d'executar la consulta
      */
     private List<Habilitat> executeQuery(PreparedStatement preparedStatement) {
            
       List<Habilitat> habilitats=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Habilitat habilitat=buildHabilitatFromResultSet(rs);
                habilitats.add(habilitat);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return habilitats;
    }
    /**
     * Mètode per obtenir totes les habilitats a partir de l'ocupació
     * @param ocupacio
     * @return habilitats
     */
    @Override
    public List<Habilitat> getHabilitatsPerOcupacio(Integer ocupacio){
        String qry = "select * from habilitats WHERE ocupacio = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, ocupacio);            
            List<Habilitat> habilitats = executeQuery(preparedStatement);
            return habilitats;
        } catch (SQLException ex) {
            Logger.getLogger(HabilitatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      /**
     * Mètode per obtenir totes les habilitats
     * @return totes les habilitats
     */
    public List<Habilitat> getAllHabilitats(){
        String qry = "select * from habilitats";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);            
            List<Habilitat> habilitats = executeQuery(preparedStatement);
            return habilitats;
        } catch (SQLException ex) {
            Logger.getLogger(HabilitatDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    private Habilitat findUniqueResult(PreparedStatement preparedStatement) throws Exception {
             
        List<Habilitat> habilitats=executeQuery(preparedStatement);
        if(habilitats.isEmpty()){
            return null;
        }
        if(habilitats.size()>1){
            throw new Exception("Només s'espera 1 resultat");
        }
        return habilitats.get(0);
    }
   /**
    * Mètode per obtenir l'habilitat a partir del codiHab
    * @param codiHab
    * @return Habilitat o null
    */ 
    public Habilitat getHabilitatByCodiHab(Integer codiHab){
         String qry = "select * from habilitats where codiHab = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiHab);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(HabilitatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Mètode per obtenir el nom d'una habilitat a partir del codiHab
     * @param codiHab
     * @return nom de l'habilitat 
     */
    @Override
    public String getNomHabilitat(Integer codiHab){
        Habilitat hab=getHabilitatByCodiHab(codiHab);
        return hab.getNomHab();
    }
}
