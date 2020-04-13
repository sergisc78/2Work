/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository;

import towork.domain.Empresa;

public interface EmpresaRepository {
        Empresa getEmpresaByCodi(Integer codi);
        void addEmpresa(Empresa empresa);
}
