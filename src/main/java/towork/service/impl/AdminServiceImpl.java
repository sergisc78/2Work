/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Candidat;
import towork.domain.Empresa;
import towork.domain.Oferta;
import towork.repository.AdminRepository;
import towork.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Candidat getCandidatByDniNif(String dniNIf) {

        return adminRepository.getCandidatByDniNif(dniNIf);
    }

    @Override
    public Empresa getEmpresaByDniNif(String dniNIf) {
        return adminRepository.getEmpresaByDniNif(dniNIf);
    }

    @Override
    public Oferta getOfertaByCodi(int codiOferta) {
        return adminRepository.getOfertaByCodi(codiOferta);

    }

    @Override
    public List<Candidat> getCandidats() {

        return adminRepository.getCandidats();
    }

    @Override
    public List<Empresa> getEmpreses() {
        return adminRepository.getEmpreses();
    }

    @Override
    public List<Oferta> getOfertes() {
        return adminRepository.getOfertes();
    }

    @Override
    public void deleteOferta(Oferta oferta) {

    }

    public void deleteCandidat(Candidat candidat) {

    }

    public void deleteEmpresa(Empresa empresa) {

    }

}
