/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Habilitat;
import towork.repository.HabilitatRepository;
import towork.service.HabilitatService;

/**
 *
 * @author gonem
 */
@Service
public class HabilitatServiceImpl implements HabilitatService{
    @Autowired
    private HabilitatRepository habilitatRepository;
    
    public List<Habilitat> getHabilitatsPerOcupacio(Integer ocupacio){
        return habilitatRepository.getHabilitatsPerOcupacio(ocupacio);
    }
     
    public List<Habilitat> getAllHabilitats(){
        return habilitatRepository.getAllHabilitats();
    }
    
    public String getNomHabilitat(Integer codiHab){
        return habilitatRepository.getNomHabilitat(codiHab);
    }
}
