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
import towork.domain.Experiencia;
import towork.domain.HabilitatPersonal;
import towork.repository.ExperienciaRepository;
import towork.service.impl.ExperienciaServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class ExperienciaDAO implements ExperienciaRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    
    /**
     * Constructor
     * @param dBConnection 
     */
    public ExperienciaDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
     /**
     * Constructor sense paràmetres
     */
    public ExperienciaDAO() {
         try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(ExperienciaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
      * S'encarrega de construir un objecte Experiencia a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return Experiencia
      * @throws SQLException 
      */
      private Experiencia buildExperienciaFromResultSet(ResultSet rs) throws SQLException {
        
        Integer codiExperiencia = rs.getInt("codiExperiencia");          
        Integer anys = rs.getInt("anys");       
        String nomEmpresa=rs.getString("nomEmpresa");
        Integer codiCandidat=rs.getInt("codiCandidat");
        String descripcio=rs.getString("descripcio");
        
        
        Experiencia experiencia = new Experiencia(codiExperiencia,anys,nomEmpresa,codiCandidat,descripcio);
        return experiencia;
    }
     /**
      * Afegir experiencia a experiencies
      * @param preparedStatement
      * @return totes les experiències que resultin d'executar la consulta
      */
     private List<Experiencia> executeQuery(PreparedStatement preparedStatement) {
            
       List<Experiencia> experiencies=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Experiencia experiencia=buildExperienciaFromResultSet(rs);
                experiencies.add(experiencia);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return experiencies;
    }
    /**
     * Mètode per obtenir les experiències a partir del codiCandidat
     * @param codiCandidat
     * @return experiencies o null
     */
    public List<Experiencia> getExperiencies(Integer codiCandidat){
        String qry = "select * from experiencies WHERE codiCandidat = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiCandidat);            
            List<Experiencia> experiencies = executeQuery(preparedStatement);
            return experiencies;
        } catch (SQLException ex) {
            Logger.getLogger(ExperienciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
