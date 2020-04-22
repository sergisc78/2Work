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
import towork.domain.Empresa;
import towork.domain.Oferta;
import towork.repository.AdminRepository;
import towork.service.impl.AdminServiceImpl;

@Repository
public class AdminDAO implements AdminRepository {

    private Dbconnection dBConnection;
    private Connection connection;

    public AdminDAO(Dbconnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public AdminDAO() {

        try {
            dBConnection = (Dbconnection) new InitialContext().lookup("java:global/2work/Dbconnection");   // DADES NO OK??
            dBConnection.setConnectionFile("db.properties");
        } catch (NamingException ex) {
            Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Retorna el candidat quan es passa el DNI per paràmetre
    @Override
    public Candidat getCandidatByDniNif(String dniNif) {

        String query = "select * from user where dni = ?";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, dniNif);
            return findUniqueResult(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    // Retorna l´empresa quan es passa el DNI per paràmetre
    @Override
    public Empresa getEmpresaByDniNif(String dniNif) {

        String query = "select * from user where dniNif = ?";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, dniNif);
            return findUniqueResult2(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    // Retorna l´oferta quan es passa el codi per paràmetre
    @Override
    public Oferta getOfertaByCodi(int codiOferta) {

        String query = "select * from oferta where codiOferta= ?";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setInt(1, codiOferta);
            return findUniqueResult3(preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    // Retorna tots els candidats
    @Override
    public List<Candidat> getCandidats() {

        String query = "SELECT * from candidat";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            List<Candidat> candidats = executeQuery(preparedStatement);
            return candidats;
        } catch (SQLException ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Retorna totes les empreses
    @Override
    public List<Empresa> getEmpreses() {

        String query = "SELECT * from empreses";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            List<Empresa> empreses = executeQuery2(preparedStatement);
            return empreses;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Retorna totes les ofertes
    @Override
    public List<Oferta> getOfertes() {

        String query = "SELECT * from candidat";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            List<Oferta> ofertes = executeQuery3(preparedStatement);
            return ofertes;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Eliminar oferta
    @Override
    public void deleteOferta(Oferta oferta) {

        String query = "DELETE from oferta where codiOferta=?";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setInt(1, oferta.getCodiOferta());
            createOrUpdateOferta(oferta.getCodiOferta(), preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Eliminar candidat
    @Override
    public void deleteCandidat(Candidat candidat) {
        String query = "DELETE from candidat where dniNif=?";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, candidat.getDniNif());
            createOrUpdateCandidat(candidat.getDniNif(), preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(CandidatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar Empresa
    @Override
    public void deleteEmpresa(Empresa empresa) {

        String query = "DELETE from empresa where dniNif=?";

        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, empresa.getDniNif());
            createOrUpdateCandidat(empresa.getDniNif(), preparedStatement);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        Candidat candidat = new Candidat(codi,nom, cognoms, dniNif, dataNaix, adreca, poblacio, provincia, telefon, email, observacions, pass, cPass, formacio, ocupacio);

        return candidat;

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
        Integer tamany = rs.getInt("tamany");
        String email = rs.getString("email");
        String observacions = rs.getString("observacions");
        String pass = rs.getString("pass");
        String cPass = rs.getString("cPass");
        Integer sector = rs.getInt("sector");

        Empresa empresa = new Empresa(codi, nom, responsable, dniNif, adreca, poblacio, provincia, telefon, web, tamany, email, observacions, pass, cPass, sector);

        return empresa;

    }

    private Oferta buildOfertaFromResultSet(ResultSet rs) throws SQLException {

        Integer codiOferta = rs.getInt("codiOferta");
        String nifEmpresa = rs.getString("nifEmpresa");
        String titolOferta = rs.getString("titolOferta");
        Integer ocupacio = rs.getInt("ocupacio");
        String poblacio = rs.getString("poblacio");
        String provincia = rs.getString("provincia");
        Double sou = rs.getDouble("sou");
        String horari = rs.getString("horari");
        String tipusContracte = rs.getString("tipusContracte");
        Integer formacio = rs.getInt("formacio");
        String estat = rs.getString("estat");
        String descripcio = rs.getString("descripcio");

        Oferta oferta = new Oferta(codiOferta, nifEmpresa, titolOferta, ocupacio, poblacio, provincia, sou, horari, tipusContracte, formacio, estat, descripcio);

        return oferta;

    }

    private Oferta createOrUpdateOferta(int codiOferta, PreparedStatement preparedStatement) throws Exception {
        executeUpdateQuery(preparedStatement);
        return getOfertaByCodi(codiOferta);
    }

    private Empresa createOrUpdateEmpresa(String dniNif, PreparedStatement preparedStatement) throws Exception {
        executeUpdateQuery(preparedStatement);
        return getEmpresaByDniNif(dniNif);

    }

    private Candidat createOrUpdateCandidat(String dniNif, PreparedStatement preparedStatement) throws Exception {
        executeUpdateQuery(preparedStatement);
        return getCandidatByDniNif(dniNif);

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

    private Empresa findUniqueResult2(PreparedStatement preparedStatement) throws Exception {

        List<Empresa> empreses = executeQuery2(preparedStatement);
        if (empreses.isEmpty()) {
            return null;
        }
        if (empreses.size() > 1) {
            throw new Exception("Only one result expected");
        }

        return empreses.get(0);
    }

    private Oferta findUniqueResult3(PreparedStatement preparedStatement) throws Exception {

        List<Oferta> ofertes = executeQuery3(preparedStatement);
        if (ofertes.isEmpty()) {
            return null;
        }
        if (ofertes.size() > 1) {
            throw new Exception("Only one result expected");
        }

        return ofertes.get(0);
    }

    // Ececuta llista de candidats
    private List<Candidat> executeQuery(PreparedStatement preparedStatement) {

        List<Candidat> usuaris = new ArrayList<>();

        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Candidat candidat = buildCandidatFromResultSet(rs);
                if (candidat != null) {
                    usuaris.add(candidat);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuaris;
    }

    //Executa llista d´empreses
    private List<Empresa> executeQuery2(PreparedStatement preparedStatement) {

        List<Empresa> emp = new ArrayList<>();

        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Empresa empresa = buildEmpresaFromResultSet(rs);
                if (empresa != null) {
                    emp.add(empresa);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    // Executa llista d´ofertes
    private List<Oferta> executeQuery3(PreparedStatement preparedStatement) {

        List<Oferta> of = new ArrayList<>();

        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Oferta oferta = buildOfertaFromResultSet(rs);
                if (oferta != null) {
                    of.add(oferta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return of;
    }

    private int executeUpdateQuery(PreparedStatement preparedStatement) {

        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
