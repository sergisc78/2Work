/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.HabilitatOferta;
import towork.repository.HabilitatOfertaRepository;
import towork.service.HabilitatOfertaService;

/**
 *
 * @author gonem
 */
@Service
public class HabilitatOfertaServiceImpl implements HabilitatOfertaService{
    @Autowired
    private HabilitatOfertaRepository habilitatOfertaRepository;
    
    public List<HabilitatOferta> getHabilitatPerOferta(Integer codiOferta){
        return habilitatOfertaRepository.getHabilitatPerOferta(codiOferta);
    }
}
