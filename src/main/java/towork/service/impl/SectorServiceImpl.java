/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import towork.domain.Sector;
import towork.repository.SectorRepository;
import towork.service.SectorService;

/**
 *
 * @author gonem
 */
@Service
public class SectorServiceImpl implements SectorService{
    
    @Autowired
    private SectorRepository sectorRepository;
            
    public Sector getSector(Integer codiSector){
        return sectorRepository.getSector(codiSector);
    }
    public List<Sector> getAllSectors(){
        return sectorRepository.getAllSectors();
    }
     public String getNomSector(Integer codiSector){
        return sectorRepository.getNomSector(codiSector);
    }
}
