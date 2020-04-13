/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service;

import towork.domain.Empresa;

/**
 *
 * @author gonem
 */
public interface EmpresaService {
    Empresa getEmpresaByCodi(Integer codi);
    void addEmpresa(Empresa empresa);
}
