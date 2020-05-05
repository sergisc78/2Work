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
import towork.domain.Oferta;
import towork.repository.OfertaRepository;
import towork.service.impl.OfertaServiceImpl;

/**
 *
 * @author gonem
 */
@Repository
public class OfertaDAO implements OfertaRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    
    /**
     * Constructor
     * @param dBConnection 
     */
    public OfertaDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    
    /**
     * Constructor sense paràmetres
     */
    public OfertaDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(OfertaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
     * Crear oferta 
     * @param preparedStatement
     * @return result
     * @throws Exception 
     */ 
    private int createOferta(PreparedStatement preparedStatement) throws Exception {
           
        int result=executeUpdateQuery(preparedStatement);
        
        return result;
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
     * 
     * @param preparedStatement
     * @return result
     * @throws Exception 
     */
    private int create(PreparedStatement preparedStatement) throws Exception {

        int result=executeUpdateQuery(preparedStatement);
        return result;

    }
    /**
     * Afegir Oferta
     * @param oferta 
     */
    @Override
    public void addOferta(Oferta oferta){
        
        try{
            
            String qry="INSERT INTO ofertes(codiEmpresa,titolOferta,ocupacio,poblacio,provincia,sou,horari,tipusContracte,formacio,estat,descripcio) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement=getPreparedStatement(qry);
           
            preparedStatement.setInt(1,oferta.getCodiEmpresa()); //Aquí hem d'aconseguir posar el mètode que dona el codi d'empresa a partir de l'email que s'ha registrat per exemple?
            preparedStatement.setString(2,oferta.getTitolOferta());           
            preparedStatement.setInt(3,oferta.getOcupacio());           
            preparedStatement.setString(4,oferta.getPoblacio());
            preparedStatement.setString(5,oferta.getProvincia());
            preparedStatement.setDouble(6,oferta.getSou());
            preparedStatement.setString(7,oferta.getHorari());
            preparedStatement.setString(8,oferta.getTipusContracte());            
            preparedStatement.setInt(9,oferta.getFormacio());
            preparedStatement.setString(10,oferta.getEstat());
            preparedStatement.setString(11,oferta.getDescripcio());
            
            int result=createOferta(preparedStatement);
            
             
            for(int i=0;i<oferta.getHabilitats().size();i++){
                String query="INSERT INTO habilitatsOfertes(codiOferta,codiHab) values ((select codiOferta from ofertes),?)";
                PreparedStatement prepStatement = getPreparedStatement(query);                
                prepStatement.setInt(1,oferta.getHabilitats().get(i));
                int resultat=create(prepStatement);
            }
        }catch (Exception ex) {
            Logger.getLogger(OfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
      /**
      * 
      * @param preparedStatement
      * @return result
      */
     private int executeUpdateQuery(PreparedStatement preparedStatement) {
        
        int result = 0;
        if (getConnection() == null) {
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        try {
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
     
      /**
      * S'encarrega de construir un objecte oferta a partir del resultat 
      * d'executar la consulta SQL
      * @param rs
      * @return oferta
      * @throws SQLException 
      */
      private Oferta buildOfertaFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codiOferta = rs.getInt("codiOferta");
        Integer codiEmpresa = rs.getInt("codiEmpresa");
        String titolOferta = rs.getString("titolOferta");
        Integer ocupacio = rs.getInt("ocupacio");
        String poblacio = rs.getString("poblacio");
        String provincia = rs.getString("provincia");
        Double sou = rs.getDouble("sou");
        String horari = rs.getString("horari");
        String tipusContracte = rs.getString("tipusContracte");
        Integer formacio= rs.getInt("formacio");
        String estat = rs.getString("estat");
        String descripcio = rs.getString("descripcio");
        
        Oferta oferta = new Oferta(codiOferta,codiEmpresa,titolOferta,ocupacio,poblacio,provincia,sou,horari,tipusContracte,formacio,estat,descripcio);
        return oferta;
    }
      
      /**
      * Afegir oferta a ofertes
      * @param preparedStatement
      * @return totes les ofertes que resultin d'executar la consulta
      */
     private List<Oferta> executeQuery(PreparedStatement preparedStatement) {
            
       List<Oferta> ofertes=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Oferta oferta=buildOfertaFromResultSet(rs);
                ofertes.add(oferta);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return ofertes;
    }
     
      /**
      * Examina la llista retornada per executeQuery
      * @param preparedStatement
      * @return un objecte null si no hi ha cap resultat per la consulta i
      * un objecte Oferta, si hi és, troba un resultat
      * @throws Exception 
      */
      private Oferta findUniqueResult(PreparedStatement preparedStatement) throws Exception {
             
        List<Oferta> ofertes=executeQuery(preparedStatement);
        if(ofertes.isEmpty()){
            return null;
        }
        if(ofertes.size()>1){
            throw new Exception("Només s'espera 1 resultat");
        }
        return ofertes.get(0);
    }
      
      /**
      * Obtenir oferta a partir del codi
      * @param codi
      * @return oferta o null
      */
    @Override
    public Oferta getOfertaByCodi(Integer codi) {
              
        String qry = "select * from ofertes where codiOferta = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codi);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(OfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      /**
     * Actualitzar oferta a partir del codi
     * @param codi
     * @param preparedStatement
     * @return l'oferta a partir del codi
     * @throws Exception 
     */ 
    private Oferta updateOfer(Integer codi, PreparedStatement preparedStatement) throws Exception {
           
        int result=executeUpdateQuery(preparedStatement);
        
        return getOfertaByCodi(codi);
    }
    
    /**
     * Actualitzar les dades de l'oferta
     * @param oferta 
     */
           
    @Override
    public void updateOferta(Oferta oferta){
        String qry = "DELETE FROM ofertes WHERE codiOferta = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, oferta.getCodiOferta());
            updateOfer(oferta.getCodiOferta(), preparedStatement);
            System.out.println("--- Codi de l'empresa que ha creat l'oferta: "+oferta.getCodiEmpresa());
            addOferta(oferta);
        } catch (Exception ex) {
            Logger.getLogger(OfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Esborrar oferta a partir del codi
     * @param codi 
     */
    @Override
    public Boolean esborrarOferta(Integer codi){
        Oferta oferEsborrada=new Oferta();
        String qry = "DELETE FROM ofertes WHERE codiOferta = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codi); 
            oferEsborrada=updateOfer(codi, preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(OfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (oferEsborrada==null);
    }   
     
     /**
      * Obtenir totes les ofertes
      * @return totes les ofertes o null
      */   
    @Override
    public List<Oferta> getAllOfertes(){
        String qry = "select * from ofertes";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            List<Oferta> ofertes = executeQuery(preparedStatement);
            return ofertes;
        } catch (SQLException ex) {
            Logger.getLogger(OfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Obtenir totes les ofertes a partir del codi de l'empresa
     * @param codiEmpresa
     * @return totes les ofertes de l'empresa o null
     */
    @Override
    public List<Oferta> getOfertaByCodiEmpresa(Integer codiEmpresa){
        String qry = "select * from ofertes WHERE codiEmpresa = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codiEmpresa);            
            List<Oferta> ofertes = executeQuery(preparedStatement);
            return ofertes;
        } catch (SQLException ex) {
            Logger.getLogger(OfertaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
