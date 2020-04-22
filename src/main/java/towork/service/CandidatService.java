/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.service;

import java.util.List;
import towork.domain.Candidat;

/**
 *
 * @author Sergi
 */
public interface CandidatService {
    
    public void addCandidat (Candidat candidat);
    List <Candidat> selectCandidat();
    void updateCandidat(Candidat candidat);
    void deletePerfil (Candidat candidat);
    
}
