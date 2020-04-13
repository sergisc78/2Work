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
import towork.domain.Empresa;
import towork.repository.EmpresaRepository;
import towork.service.impl.EmpresaServiceImpl;

@Repository
public class EmpresaDAO implements EmpresaRepository{
    private Dbconnection dBConnection;
    private Connection connection;
  
    public EmpresaDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    public EmpresaDAO() {
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(EmpresaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
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
    private Empresa createOrUpdateEmpresa(Integer codi, PreparedStatement preparedStatement) throws Exception {
           
        int result=executeUpdateQuery(preparedStatement);
        
        return getEmpresaByCodi(codi);
    }
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
             
             
            createOrUpdateEmpresa(empresa.getCodi(),preparedStatement);
        }catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
