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
import towork.domain.Sector;
import towork.repository.SectorRepository;
import towork.service.impl.SectorServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class SectorDAO implements SectorRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    
    /**
     * Constructor
     * @param dBConnection 
     */
    public SectorDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    /**
     * Constructor sense paràmetres
     */
    public SectorDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(SectorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
      * S'encarrega de construir un objecte sector a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return sector
      * @throws SQLException 
      */
      private Sector buildSectorFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codiSector = rs.getInt("codiSector");
        String nomSector = rs.getString("nomSector");
        
        Sector sector = new Sector(codiSector,nomSector);
        return sector;
    }   
      /**
      * Afegir sector a sectors
      * @param preparedStatement
      * @return tots els sectors que resultin d'executar la consulta
      */
     private List<Sector> executeQuery(PreparedStatement preparedStatement) {
            
       List<Sector> sectors=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Sector sector=buildSectorFromResultSet(rs);
                sectors.add(sector);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return sectors;
    }
     /**
      * Examina la llista retornada per executeQuery
      * @param preparedStatement
      * @return un objecte null si no hi ha cap resultat per la consulta i
      * un objecte Sector, si hi és, troba un resultat
      * @throws Exception 
      */
      private Sector findUniqueResult(PreparedStatement preparedStatement) throws Exception {
             
        List<Sector> sectors=executeQuery(preparedStatement);
        if(sectors.isEmpty()){
            return null;
        }
        if(sectors.size()>1){
            throw new Exception("Només s'espera 1 resultat");
        }
        return sectors.get(0);
    }
    public Sector getSector(Integer codiSector){
        String qry = "select * from sectors where codiSector = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiSector);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(SectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Mètode per obtenir tots els sectors
     * @return tots els sectors o null
     */
    @Override
    public List<Sector> getAllSectors(){
        String qry = "select * from sectors";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            List<Sector> sectors = executeQuery(preparedStatement);
            return sectors;
        } catch (SQLException ex) {
            Logger.getLogger(SectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
