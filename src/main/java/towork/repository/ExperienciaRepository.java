/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository;

import java.util.List;
import towork.domain.Experiencia;

/**
 *
 * @author gonem
 */
public interface ExperienciaRepository {
    List<Experiencia> getExperiencies(Integer codiCandidat);
}
