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
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");   // DADES NO OK??
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(CandidatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Retorna tots els candidats de la bbdd
    @Override
    public List<Candidat> getAllCandidats() {
        String query = "SELECT * from candidats";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            List<Candidat> candidats = executeQuery(preparedStatement);
            return candidats;
        } catch (SQLException ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    // Retorna el candidat quan es passa el DNI per paràmetre
    public Candidat getCandidatByEmail(String email) {

        String query = "select * from candidats where email =? ";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, email);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    // Retorna el candidat quan es passa el DNI per paràmetre
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

    // Consultar dades perfil
    @Override
    public List<Candidat> selectCandidat() {

        String query = "Select * from candidats";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            List<Candidat> candidat = executeQuery(preparedStatement);
            return candidat;
        } catch (SQLException ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

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
            createOrUpdateCandidat(candidat.getDniNif(), preparedStatement);
            
            for(int i=0;i<candidat.getHabilitats().size();i++){
                String qry="INSERT INTO habilitatsPersonals(codiCandidat,codiHab) values ((select codi from candidats),?)";
                PreparedStatement prepStatement = getPreparedStatement(qry);                
                prepStatement.setInt(1,candidat.getHabilitats().get(i));
                int result=create(prepStatement);
            }
            for(int i=0;i<candidat.getExperiencies().size();i++){
                String qury="INSERT INTO experiencies(anys,nomEmpresa,codiCandidat,descripcio) values (?,?,(select codi from candidats),?)";
                PreparedStatement preStatement = getPreparedStatement(qury);               
                preStatement.setInt(1,candidat.getExperiencies().get(i).getAnys());
                preStatement.setString(2,candidat.getExperiencies().get(i).getNomEmpresa());
                preStatement.setString(3,candidat.getExperiencies().get(i).getDescripcio());
                int result=create(preStatement);
            }
           
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Actualitzar perfil a través del codi del candidat
    @Override
    public boolean updateCandidat(Integer codi, Candidat candidat) {

        String query = "UPDATE candidats SET codi=?";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setInt(1, codi);
            createOrUpdateCandidat3(candidat.getCodi(), preparedStatement);
            this.addCandidat(candidat);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidat.getCodi() != null;
    }

    // Esborrar perfil a través de l´email
    @Override
    public void deletePerfil(Candidat candidat
    ) {

        String query = "DELETE  from candidats where email=?";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, candidat.getEmail());
            createOrUpdateCandidat(candidat.getEmail(), preparedStatement);
            this.addCandidat(candidat);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Mostrar codi del candidat, a partir de l´email
    @Override
    public int getCodiByEmail(String email
    ) {
        Candidat codiCandidat = getCandidatByEmail(email);
        if (codiCandidat.getCodi() != null) {
            return 1;
        } else {
            return 0;
        }

    }

    // Esborrar un candidat a través de codi
    @Override
    public void deleteByCodi(Integer codi
    ) {

        String query = "DELETE from candidats where codi=?";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setInt(1, codi);
            createOrUpdateCandidat2(codi, preparedStatement);

        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Relaciona candidat amb ofertes

    public void selectOferta() {

        String query = "SELECT habilitats from candidats where habilitats=(SELECT habilitats from oferta)";

    }

    private Candidat buildCandidatFromResultSet(ResultSet rs) throws SQLException {

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

        Candidat candidat = new Candidat(codi, nom, cognoms, dniNif, dataNaix, adreca, poblacio, provincia, telefon, email, observacions, pass, cPass, formacio, ocupacio);

        return candidat;
    }

    private Candidat createOrUpdateCandidat(String email, PreparedStatement preparedStatement) throws Exception {

        executeUpdateQuery(preparedStatement);
        return getCandidatByEmail(email);

    }

    private Candidat createOrUpdateCandidat2(Integer codi, PreparedStatement preparedStatement) throws Exception {

        executeUpdateQuery(preparedStatement);
        return getCandidatByCodi(codi);

    }

    private Candidat createOrUpdateCandidat3(Integer codi, PreparedStatement preparedStatement) throws Exception {

        executeUpdateQuery(preparedStatement);
        return getCandidatByCodi(codi);

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

    private List<Candidat> executeQuery(PreparedStatement preparedStatement) {

        List<Candidat> candidats = new ArrayList<>();

        try {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Candidat candidat = buildCandidatFromResultSet(rs);
                candidats.add(candidat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidats;
    }

    private int executeUpdateQuery(PreparedStatement preparedStatement) {

        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
