/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.HabilitatPersonal;
import towork.repository.HabilitatPersonalRepository;
import towork.service.HabilitatPersonalService;

/**
 *
 * @author gonem
 */
@Service
public class HabilitatPersonalServiceImpl implements HabilitatPersonalService {
    @Autowired
    private HabilitatPersonalRepository habilitatPersonalRepository;
    
    public List<HabilitatPersonal> getHabilitatsPerCandidat(Integer codiCandidat){
        return habilitatPersonalRepository.getHabilitatsPerCandidat(codiCandidat);
    
    }
}
