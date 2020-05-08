/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Ocupacio;
import towork.repository.OcupacioRepository;
import towork.service.OcupacioService;

/**
 *
 * @author gonem
 */
@Service
public class OcupacioServiceImpl implements OcupacioService{
    @Autowired
    private OcupacioRepository ocupacioRepository;
    
    public List<Ocupacio> getAllOcupacions(){
        return ocupacioRepository.getAllOcupacions();
    }
    public String getNomOcupacio(Integer codiOcupacio){
        return ocupacioRepository.getNomOcupacio(codiOcupacio);
    }
}
