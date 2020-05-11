/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.repository.FormacioRepository;
import towork.service.FormacioService;

/**
 *
 * @author gonem
 */
@Service
public class FormacioServiceImpl implements FormacioService{
    @Autowired
    private FormacioRepository formacioRepository;
    
    public  String getNomFormacio(Integer codiFormacio){
        return formacioRepository.getNomFormacio(codiFormacio);
    }
}
