/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import towork.domain.Candidat;
import towork.repository.CandidatRepository;
import towork.service.impl.CandidatServiceImpl;

@Repository
public class CandidatDAO implements CandidatRepository {

    private Dbconnection dBConnection;
    private Connection connection;

    public CandidatDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public CandidatDAO() {

        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");   
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(CandidatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
     private Candidat buildUsuariFromResultSet(ResultSet rs) throws SQLException {

        Integer codi = rs.getInt("codi");
        String nom = rs.getString("nom");
        String cognoms = rs.getString("cognoms");
        String dniNif = rs.getString("dniNif");
        Date dataNaix = rs.getDate("dataNaix");
        String adreca = rs.getString("adreca");
        String poblacio = rs.getString("poblacio");
        String provincia = rs.getString("provincia");
        String telefon = rs.getString("telefon");
        String email = rs.getString("email");
        String observacions = rs.getString("observacions");
        String pass = rs.getString("pass");
        String cPass = rs.getString("cPass");
        Integer formacio = rs.getInt("formacio");
        Integer ocupacio = rs.getInt("ocupacio");

        Candidat candidat = new Candidat(codi,nom, cognoms, dniNif, dataNaix, adreca, poblacio, provincia, telefon, email, observacions, pass, cPass, formacio, ocupacio);

        return candidat;
    }
     private List<Candidat> executeQuery(PreparedStatement preparedStatement) {

        List<Candidat> usuaris = new ArrayList<>();

        try {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Candidat candidat = buildUsuariFromResultSet(rs);
                if (candidat != null) {
                    usuaris.add(candidat);
                }
            }

        } catch (SQLException e) {
        }

        return usuaris;
    }
    private Candidat findUniqueResult(PreparedStatement preparedStatement) throws Exception {

        List<Candidat> candidats = executeQuery(preparedStatement);
        if (candidats.isEmpty()) {
            return null;
        }
        if (candidats.size() > 1) {
            throw new Exception("Only one result expected");
        }

        return candidats.get(0);
    }

    public Candidat getCandidatByCodi(Integer codi) {

        String query = "select * from candidats where codi =? ";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setInt(1, codi);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
     // Retorna el candidat quan es passa el DNI per paràmetre
    public Candidat getCandidatByDniNif(String dniNif) {

        String query = "select * from candidats where dniNif =? ";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, dniNif);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

     private Candidat createOrUpdateCandidat(Integer codi, PreparedStatement preparedStatement) throws Exception {

        executeUpdateQuery(preparedStatement);
        return getCandidatByCodi(codi);

    }
    // Donar-se d´alta a l´aplicació
    @Override
    public void addCandidat(Candidat candidat) {

        String query = "INSERT INTO candidats (nom, cognoms, dniNif,dataNaix,adreca,poblacio,provincia, telefon, email,observacions, pass, cPass,formacio,ocupacio) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            
            preparedStatement.setString(1, candidat.getNom());
            preparedStatement.setString(2, candidat.getCognoms());
            preparedStatement.setString(3, candidat.getDniNif());
            preparedStatement.setDate(4, (Date) candidat.getDataNaix());
            preparedStatement.setString(5, candidat.getAdreca());
            preparedStatement.setString(6, candidat.getPoblacio());
            preparedStatement.setString(7, candidat.getProvincia());
            preparedStatement.setString(8, candidat.getTelefon());
            preparedStatement.setString(9, candidat.getEmail());
            preparedStatement.setString(10, candidat.getObservacions());
            preparedStatement.setString(11, candidat.getPass());
            preparedStatement.setString(12, candidat.getcPass());
            preparedStatement.setInt(13, candidat.getFormacio());
            preparedStatement.setInt(14, candidat.getOcupacio());
            createOrUpdateCandidat(candidat.getCodi(), preparedStatement);

        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 // Actualitzar perfil
    @Override
    public void updateCandidat(Candidat candidat) {
        String qry = "DELETE FROM candidats WHERE dniNif = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = getPreparedStatement(qry);
            preparedStatement.setString(1, candidat.getDniNif());
            createOrUpdateCandidat(candidat.getCodi(), preparedStatement);
            this.addCandidat(candidat);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Consultar dades perfil
    @Override
    public List<Candidat> selectCandidat() {

        String query = "Select * from candidat";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            List<Candidat> candidat = executeQuery(preparedStatement);
            return candidat;
        } catch (SQLException ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
   
    // Esborrar perfil a través de l´email
    @Override
    public void deletePerfil(Candidat candidat) {

        String query = "DELETE  from candidat where email=?";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, candidat.getEmail());
            createOrUpdateCandidat(candidat.getCodi(), preparedStatement);
            this.addCandidat(candidat);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Relaciona candidat amb ofertes
    public void selectOferta() {

    }

   

   
    
   

    


    
}
