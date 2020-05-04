
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
import towork.domain.Empresa;
import towork.repository.EmpresaRepository;
import towork.service.impl.EmpresaServiceImpl;

@Repository
public class EmpresaDAO implements EmpresaRepository{
    private Dbconnection dBConnection;
    private Connection connection;
    
     /**
     * Constructor 
     * @param dBConnection 
     */
    public EmpresaDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    
    /**
     * Constructor sense paràmetres 
     */
    public EmpresaDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(EmpresaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
     * Crear empresa 
     * @param preparedStatement
     * @return result
     * @throws Exception 
     */
    private int createEmpresa(PreparedStatement preparedStatement) throws Exception {
           
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
     * Afegir empresa 
     * @param empresa 
     */
    @Override
    public void addEmpresa(Empresa empresa) {
        
        try{
            
            String qry="INSERT INTO empreses(nom,responsable,dniNif,adreca,poblacio,provincia,telefon,web,tamany,email,observacions,pass,cPass,sector) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement=getPreparedStatement(qry);
            
            preparedStatement.setString(1,empresa.getNom());
            preparedStatement.setString(2,empresa.getResponsable());
            preparedStatement.setString(3,empresa.getDniNif());           
            preparedStatement.setString(4,empresa.getAdreca());
            preparedStatement.setString(5,empresa.getPoblacio());
            preparedStatement.setString(6,empresa.getProvincia());
            preparedStatement.setString(7,empresa.getTelefon());
            preparedStatement.setString(8,empresa.getWeb());            
            preparedStatement.setInt(9,empresa.getTamany());
            preparedStatement.setString(10,empresa.getEmail());
            preparedStatement.setString(11,empresa.getObservacions());
            preparedStatement.setString(12,empresa.getPass());
            preparedStatement.setString(13,empresa.getcPass());
            preparedStatement.setInt(14,empresa.getSector());
             
             
            int result=createEmpresa(preparedStatement);
        }catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
     * S'encarrega de construir un objecte empresa a partir del resultat 
     * d'executar la consulta SQL
     * @param rs
     * @return empresa
     * @throws SQLException 
     */
     private Empresa buildEmpresaFromResultSet(ResultSet rs) throws SQLException {
                 
        Integer codi = rs.getInt("codi");
        String nom = rs.getString("nom");
        String responsable = rs.getString("responsable");
        String dniNif = rs.getString("dniNif");
        String adreca = rs.getString("adreca");
        String poblacio = rs.getString("poblacio");
        String provincia = rs.getString("provincia");
        String telefon = rs.getString("telefon");
        String web = rs.getString("web");
        Integer tamany  = rs.getInt("tamany");
        String email = rs.getString("email");
        String observacions= rs.getString("observacions");
        String pass = rs.getString("pass");
        String cPass = rs.getString("cPass");
        Integer sector  = rs.getInt("sector");
        
        
        Empresa empresa = new Empresa(codi,nom,responsable,dniNif,adreca,poblacio,provincia,telefon,web,tamany,email,observacions,pass,cPass,sector);
        return empresa;
    }
     
     /**
      * Afegir empresa a empreses
      * @param preparedStatement
      * @return totes les empreses que resultin d'executar la consulta
      */
    private List<Empresa> executeQuery(PreparedStatement preparedStatement) {
            
       List<Empresa> empreses=new ArrayList<>();
       try(
           ResultSet rs=preparedStatement.executeQuery()){
            while (rs.next()){
                Empresa empresa=buildEmpresaFromResultSet(rs);
                empreses.add(empresa);
            }
            
       }catch(SQLException e){
           e.printStackTrace();
       }
        return empreses;
    }
    
    /**
     * Examina la llista retornada per executeQuery
     * @param preparedStatement
     * @return un objecte null si no hi ha cap resultat per a la consulta i 
     * un objecte Empresa,si hi és, troba un resultat
     * @throws Exception 
     */
    private Empresa findUniqueResult(PreparedStatement preparedStatement) throws Exception {
             
        List<Empresa> empreses=executeQuery(preparedStatement);
        if(empreses.isEmpty()){
            return null;
        }
        if(empreses.size()>1){
            throw new Exception("Només s'espera 1 resultat");
        }
        return empreses.get(0);
    }
    
    /**
     * Obtenir empresa a partir del codi
     * @param codi
     * @return empresa o null
     */
    @Override
    public Empresa getEmpresaByCodi(Integer codi) {
              
        String qry = "select * from empreses where codi = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codi);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Obtenir empresa per nif
     * @param dniNif
     * @return un objecte null si no hi ha cap resultat per a la consulta o
     * un objecte Empresa 
     */
    @Override
    public Empresa getEmpresaBydniNif(String dniNif){
        String qry = "select * from empreses where dniNif = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setString(1, dniNif);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Actualitzar empresa a partir del codi
     * @param codi
     * @param preparedStatement
     * @return l'empresa a partir del codi
     * @throws Exception 
     */
    private Empresa updateEmp(Integer codi, PreparedStatement preparedStatement) throws Exception {
           
        int result=executeUpdateQuery(preparedStatement);
        
        return getEmpresaByCodi(codi);
    }
    
    
    
    /**
     * Actualitzar les dades de l'empresa
     * @param empresa 
     */
    @Override
    public void updateEmpresa(Empresa empresa) {
               
        String qry = "DELETE FROM empreses WHERE dniNif = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setString(1, empresa.getDniNif());
            updateEmp(empresa.getCodi(), preparedStatement);
            this.addEmpresa(empresa);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtenir totes les empreses
     * @return totes les empreses o null
     */
    @Override
    public List<Empresa> getAllEmpreses() {
        
        String qry = "select * from empreses";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            List<Empresa> empreses = executeQuery(preparedStatement);
            return empreses;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    /**
     * Obtenir Empresa a partir de l'email
     * @param email
     * @return empresa o null
     */
    
    @Override
    public Empresa getEmpresaByEmail(String email){
        String qry = "select * from empreses where email = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setString(1, email);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Obtenir el codi a partir de l'email
     * @param email
     * @return el codi de l'empresa
     */
    @Override
    public Integer getCodiByEmail(String email){
        Empresa empCodi=getEmpresaByEmail(email);
        return empCodi.getCodi();
    }
     /**
     * Mètode per esborrar empresa retornant si s'ha fet l'eliminació a la bd 
     * @param codi
     * @return null si no s'ha fet l'eliminació
     */
      @Override
    public Boolean esborrarEmpresaBoolean(Integer codi){
        Empresa empEsborrada=new Empresa();
        String qry = "DELETE FROM empreses WHERE codi = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setInt(1, codi); 
            empEsborrada=updateEmp(codi, preparedStatement);
            
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return (empEsborrada==null);
    }
}
