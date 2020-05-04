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
import towork.domain.Candidatura;
import towork.repository.CandidaturaRepository;
import towork.service.impl.CandidaturaServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class CandidaturaDAO implements CandidaturaRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    /**
     * Constructor
     * @param dBConnection 
     */
    public CandidaturaDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    /**
     * Constructor sense paràmetres
     */
    public CandidaturaDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(CandidaturaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
     * Mètode per afegir cada candidatura a l'array candidatures 
     * @param preparedStatement
     * @return candidatures
     */
    private List<Candidatura> executeQueryCandidatura(PreparedStatement preparedStatement) {
            
       List<Candidatura> candidatures=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Candidatura candidatura=buildCandidaturaFromResultSet(rs);
                candidatures.add(candidatura);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return candidatures;
    }
    /**
     * Crear objecte Candidatura
     * @param rs
     * @return candidatura
     * @throws SQLException 
     */ 
    private Candidatura buildCandidaturaFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codiCandidatura = rs.getInt("codiCandidatura");
        Integer codiOferta = rs.getInt("codiOferta");        
        Integer codiCandidat = rs.getInt("codiCandidat");       
        Integer estat= rs.getInt("estat");      
        
        Candidatura candidatura = new Candidatura(codiCandidatura,codiOferta,codiCandidat,estat);
        return candidatura;
    }
      /**
      * Afegir candidatura a candidatures
      * @param preparedStatement
      * @return totes les candidatures que resultin d'executar la consulta
      */
     private List<Candidatura> executeQuery(PreparedStatement preparedStatement) {
            
       List<Candidatura> candidatures=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Candidatura candidatura=buildCandidaturaFromResultSet(rs);
                candidatures.add(candidatura);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return candidatures;
    }
    /**
     * Mètode per obtenir totes les candidatures
     * @return candidatures 
     */
    public List<Candidatura> getAllCandidatures(){
        String qry = "select * from candidatures";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            List<Candidatura> candidatures = executeQuery(preparedStatement);
            return candidatures;
        } catch (SQLException ex) {
            Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Totes les candidatures a partir del codiOferta
     * @param codiOferta
     * @return candidatures
     */
     @Override
    public List<Candidatura> getCandidaturesPerOferta(Integer codiOferta){
        String qry = "select * from candidatures where codiOferta = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiOferta);
            List<Candidatura> candidatures = executeQueryCandidatura(preparedStatement);
            return candidatures;
        } catch (Exception ex) {
            Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    /**
     * Totes les candidatures a partir del codiCandidat
     * @param codiCandidat
     * @return candidatures
     */
     @Override
    public List<Candidatura> getCandidaturesPerCandidat(Integer codiCandidat){
        String qry = "select * from candidatures where codiCandidat = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiCandidat);
            List<Candidatura> candidatures = executeQueryCandidatura(preparedStatement);
            return candidatures;
        } catch (Exception ex) {
            Logger.getLogger(CandidaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
