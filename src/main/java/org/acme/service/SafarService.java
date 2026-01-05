package org.acme.service;

import java.util.List;

import org.acme.dto.SafarCreateDTO;
import org.acme.entity.Safar;

public interface SafarService {
    List<Safar> getAll(Long arizaId);

    Safar getById(Long id);

    Safar create(SafarCreateDTO dto);

    Safar update(Long id, SafarCreateDTO dto);

    void delete(Long id);
}
