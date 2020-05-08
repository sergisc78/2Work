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
import towork.domain.HabilitatOferta;
import towork.domain.HabilitatPersonal;
import towork.repository.HabilitatOfertaRepository;
import towork.service.impl.HabilitatOfertaServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class HabilitatOfertaDAO implements HabilitatOfertaRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    
     /**
     * Constructor
     * @param dBConnection 
     */
    public HabilitatOfertaDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    /**
     * Constructor sense paràmetres
     */
    public HabilitatOfertaDAO() {
         try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(HabilitatOfertaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
      * S'encarrega de construir un objecte habilitatOferta a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return habilitatOferta
      * @throws SQLException 
      */
      private HabilitatOferta buildHabilitatOfertaFromResultSet(ResultSet rs) throws SQLException {
        
        Integer codiOferta = rs.getInt("codiOferta");          
        Integer codiHab = rs.getInt("codiHab");       
        
        HabilitatOferta habilitatOferta = new HabilitatOferta(codiOferta,codiHab);
        return habilitatOferta;
    }
     /**
      * Afegir habilitatOferta a habilitatsOfertes
      * @param preparedStatement
      * @return totes les habilitatsOfertes que resultin d'executar la consulta
      */
     private List<HabilitatOferta> executeQuery(PreparedStatement preparedStatement) {
            
       List<HabilitatOferta> habilitatsOfertes=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                HabilitatOferta habilitatOferta=buildHabilitatOfertaFromResultSet(rs);
                habilitatsOfertes.add(habilitatOferta);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return habilitatsOfertes;
    } 
    /**
     * Mètode per obtenir totes les habilitOferta a partir del codiOferta
     * @param codiOferta
     * @return llista d'habilitatOferta o null
     */
    public List<HabilitatOferta> getHabilitatPerOferta(Integer codiOferta){
        String qry = "select * from habilitatsofertes WHERE codiOferta = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiOferta);            
            List<HabilitatOferta> habilitatsOfertes = executeQuery(preparedStatement);
            return habilitatsOfertes;
        } catch (SQLException ex) {
            Logger.getLogger(HabilitatOfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
