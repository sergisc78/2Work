/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Empresa;
import towork.repository.EmpresaRepository;
import towork.service.EmpresaService;

/**
 *
 * @author gonem
 */
@Service
public class EmpresaServiceImpl implements EmpresaService{
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Override
    public Empresa getEmpresaByCodi(Integer codi) {        
        return empresaRepository.getEmpresaByCodi(codi);
    }

    @Override
    public void addEmpresa(Empresa empresa){
        empresaRepository.addEmpresa(empresa);
    }
     @Override
    public void updateEmpresa(Empresa empresa){
        empresaRepository.updateEmpresa(empresa);
    }
    @Override
    public Empresa getEmpresaBydniNif(String dniNif){
        return empresaRepository.getEmpresaBydniNif(dniNif);
    }
    @Override
    public List<Empresa> getAllEmpreses(){
        return empresaRepository.getAllEmpreses();
    }
    @Override
    public Empresa getEmpresaByEmail(String email){
        return empresaRepository.getEmpresaByEmail(email);
    }
    @Override
    public Integer getCodiByEmail(String email){
        return empresaRepository.getCodiByEmail(email);
    }
    
    @Override
    public void deleteEmpresa(String email){
        empresaRepository.deleteEmpresa(email);
    }
    @Override
    public void esborrarEmpresa(Integer codi){
        empresaRepository.esborrarEmpresa(codi);    
    }
}
