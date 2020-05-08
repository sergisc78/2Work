/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Experiencia;
import towork.repository.ExperienciaRepository;
import towork.service.ExperienciaService;

/**
 *
 * @author gonem
 */
@Service
public class ExperienciaServiceImpl implements ExperienciaService{
    @Autowired
    private ExperienciaRepository experienciaRepository;
    
    public List<Experiencia> getExperiencies(Integer codiCandidat){
        return experienciaRepository.getExperiencies(codiCandidat);
    }
}
