/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository;

import java.util.List;
import towork.domain.Empresa;

public interface EmpresaRepository {
    
        Empresa getEmpresaByCodi(Integer codi);
        
        void addEmpresa(Empresa empresa);  
        
        void updateEmpresa(Empresa empresa);
        
        Empresa getEmpresaBydniNif(String dniNif);
        
        List<Empresa> getAllEmpreses();
        
        Empresa getEmpresaByEmail(String email);
        
        Integer getCodiByEmail(String email);
        
        Boolean esborrarEmpresaBoolean(Integer codi);
}
