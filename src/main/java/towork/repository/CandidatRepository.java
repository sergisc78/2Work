/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository;

import java.util.List;
import towork.domain.Candidat;

/**
 *
 * @author Sergi
 */
public interface CandidatRepository {
    
   
    void addCandidat(Candidat candidat);

    List<Candidat> selectCandidat();

    void updateCandidat(Candidat candidat);
    
    void deletePerfil(Candidat candidat);
    
    Candidat getCodiByEmail(String email);
    
    void deleteByCodi (Candidat candidat);
    
    
}
