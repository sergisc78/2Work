/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository.impl;

import java.io.IOException;
import java.sql.Array;
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
        // Constructor per utilitzar la connexió 'normal' a la base de dades
        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");   // DADES NO OK??
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(CandidatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Candidat getCandidatByDniNif(String dniNif) {

        String qry = "select * from user where dni = '" + dniNif + "'";
        System.out.println("--- Soc a getUsuariByNom i la query que vull executar és: " + qry);

        // La query és OK però no m'està trobant l'usuari :S
        try {

            PreparedStatement prepStat = getPreparedStatement(qry);
            List<Candidat> candidats = executeQuery(prepStat);

            // Podriem resoldre-ho utilitzant el mètode findUniqueResult
            if (candidats.isEmpty()) {
                throw new Exception("La query no ha retornat cap resultat.");
            }

            if (candidats.size() > 1) {
                throw new Exception("La query ha retornat més d'un resultat.");
            }

            return candidats.get(0);

        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    // Consultar  dades perfil
    public List<Candidat> consultCandidat() {

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

    // Donar-se d´alta a l´aplicació
    @Override
    public void addCandidat(Candidat candidat) {

        String query = "INSERT INTO candidat (nom, cognoms, dniNif,adreça,ciutat,provincia, telefon, email,observacions, password, confirmPass, sector,formacio) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, candidat.getNom());
            preparedStatement.setString(2, candidat.getCognoms());
            preparedStatement.setString(3, candidat.getDniNif());
            preparedStatement.setDate(4, (Date) candidat.getDataNaix());
            preparedStatement.setString(5, candidat.getAdreca());
            preparedStatement.setString(6, candidat.getCiutat());
            preparedStatement.setString(7, candidat.getProvincia());
            preparedStatement.setString(8, candidat.getTelefon());
            preparedStatement.setString(9, candidat.getEmail());
            preparedStatement.setString(10, candidat.getObservacions());
            preparedStatement.setString(11, candidat.getPass());
            preparedStatement.setString(12, candidat.getcPass());
            preparedStatement.setInt(13, candidat.getFormacio());
            preparedStatement.setInt(14, candidat.getOcupacio());
            createOrUpdateCandidat(candidat.getDniNif(), preparedStatement); 

        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Candidat buildUsuariFromResultSet(ResultSet rs) throws SQLException {

        String nom = rs.getString("nom");
        String cognoms = rs.getString("cognoms");
        String dniNif = rs.getString("dniNif");
        Date dataNaix = rs.getDate("dataNaix");
        String adreca = rs.getString("adreca");
        String ciutat = rs.getString("ciutat");
        String provincia = rs.getString("provincia");
        String telefon = rs.getString("telefon");
        String email = rs.getString("email");
        String observacions = rs.getString("observacions");
        String pass = rs.getString("pass");
        String cPass = rs.getString("cPass");
        int codi = rs.getInt("codi");
        int formacio = rs.getInt("formacio");
        int ocupacio = rs.getInt("ocupacio");

        Candidat candidat = new Candidat(nom, cognoms, dniNif, dataNaix, adreca, ciutat, provincia, telefon, email, observacions, pass, cPass, codi, formacio, ocupacio);

        return candidat;
    }

    private Candidat createOrUpdateCandidat(String dniNif, PreparedStatement preparedStatement) throws Exception {

        executeUpdateQuery(preparedStatement);
        System.out.println("--- He entrat a createOrUpdateUsuari i retorno l'usuari amb nom '" + getCandidatByDniNif(dniNif).getDniNif() + "' després d'executar la funció");
        return getCandidatByDniNif(dniNif);

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

    private int executeUpdateQuery(PreparedStatement preparedStatement) {

        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    private PreparedStatement getPreparedStatement(String query) throws SQLException {

        System.out.println("--- He entrat a getPreparedStatement");

        if (getConnection() == null) {
            System.out.println("--- getConnection ha estat null");
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                System.out.println("--- Ha petat el setConnection");
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
