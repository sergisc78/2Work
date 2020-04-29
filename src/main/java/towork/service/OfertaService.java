/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service;

import java.util.List;
import towork.domain.Oferta;

/**
 *
 * @author gonem
 */
public interface OfertaService {
    
    Oferta getOfertaByCodi(Integer codi);
        
    void addOferta(Oferta oferta);
        
    void updateOferta(Oferta oferta); 
        
    List<Oferta> getAllOfertes();
        
    List<Oferta> getOfertaByCodiEmpresa(Integer codiEmpresa);       
        
    void esborrarOferta(Integer codi); 
}
