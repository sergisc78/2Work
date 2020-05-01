/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Oferta;
import towork.repository.OfertaRepository;
import towork.service.OfertaService;

/**
 *
 * @author gonem
 */
@Service
public class OfertaServiceImpl implements OfertaService {
    @Autowired
    private OfertaRepository ofertaRepository;
    
    @Override
    public Oferta getOfertaByCodi(Integer codi){
        return ofertaRepository.getOfertaByCodi(codi);
    }
        
    @Override
    public void addOferta(Oferta oferta){
        ofertaRepository.addOferta(oferta);
    }
        
    @Override
    public void updateOferta(Oferta oferta){
        ofertaRepository.updateOferta(oferta);
    } 
        
    @Override
    public List<Oferta> getAllOfertes(){
        return ofertaRepository.getAllOfertes();
    }
        
    @Override
    public List<Oferta> getOfertaByCodiEmpresa(Integer codiEmpresa){
        return ofertaRepository.getOfertaByCodiEmpresa(codiEmpresa);
    }       
        
     @Override
    public Boolean esborrarOferta(Integer codi){
        return ofertaRepository.esborrarOferta(codi);
    }
}
