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
import towork.domain.HabilitatPersonal;
import towork.repository.HabilitatPersonalRepository;
import towork.service.impl.HabilitatPersonalServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class HabilitatPersonalDAO implements HabilitatPersonalRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    
    /**
     * Constructor
     * @param dBConnection 
     */
    public HabilitatPersonalDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
     /**
     * Constructor sense paràmetres
     */
    public HabilitatPersonalDAO() {
         try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(HabilitatPersonalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
      * S'encarrega de construir un objecte habilitatPersonal a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return habilitatPersonal
      * @throws SQLException 
      */
      private HabilitatPersonal buildHabilitatPersonalFromResultSet(ResultSet rs) throws SQLException {
        
        Integer codiCandidat = rs.getInt("codiCandidat");          
        Integer codiHab = rs.getInt("codiHab");       
        
        HabilitatPersonal habilitatPersonal = new HabilitatPersonal(codiCandidat,codiHab);
        return habilitatPersonal;
    }
      /**
      * Afegir habilitatPersonal a habilitatsPersonals
      * @param preparedStatement
      * @return totes les habilitatsPersonals que resultin d'executar la consulta
      */
     private List<HabilitatPersonal> executeQuery(PreparedStatement preparedStatement) {
            
       List<HabilitatPersonal> habilitatsPersonals=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                HabilitatPersonal habilitatPersonal=buildHabilitatPersonalFromResultSet(rs);
                habilitatsPersonals.add(habilitatPersonal);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return habilitatsPersonals;
    }
    /**
     * Mètode per obtenir la llista d'habilitatsPersonals a partir del codiCandidat
     * @param codiCandidat
     * @return llista d'habilitatsPersonals o null
     */
    public List<HabilitatPersonal> getHabilitatsPerCandidat(Integer codiCandidat){
        String qry = "select * from habilitatspersonals WHERE codiCandidat = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiCandidat);            
            List<HabilitatPersonal> habilitatsPersonals = executeQuery(preparedStatement);
            return habilitatsPersonals;
        } catch (SQLException ex) {
            Logger.getLogger(HabilitatPersonalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
