/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.repository;

import java.util.List;
import towork.domain.HabilitatPersonal;

/**
 *
 * @author gonem
 */
public interface HabilitatPersonalRepository {
    List<HabilitatPersonal> getHabilitatsPerCandidat(Integer codiCandidat);
}
