/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Candidatura;
import towork.repository.CandidaturaRepository;
import towork.service.CandidaturaService;

/**
 *
 * @author gonem
 */
@Service
public class CandidaturaServiceImpl implements CandidaturaService{
    @Autowired
    private CandidaturaRepository candidaturaRepository;
    
    @Override
    public List<Candidatura> getAllCandidatures(){
        return candidaturaRepository.getAllCandidatures();
    }
    
    @Override
    public List<Candidatura> getCandidaturesPerOferta(Integer codiOferta){
        return candidaturaRepository.getCandidaturesPerOferta(codiOferta);
    }
    
    @Override
    public List<Candidatura> getCandidaturesPerCandidat(Integer codiCandidat){
        return candidaturaRepository.getCandidaturesPerCandidat(codiCandidat);
    }
}
