package org.acme.service;

import java.util.List;

import org.acme.dto.ArizaCreateDTO;
import org.acme.dto.ArizaFullDTO;
import org.acme.entity.Ariza;

public interface ArizaService {
    List<Ariza> getAll();
    Ariza getById(Long id);
    ArizaFullDTO getArizaFull(Long id);
    Ariza create(ArizaCreateDTO dto);
    Ariza update(Long id, ArizaCreateDTO dto);
    void delete(Long id);
}
