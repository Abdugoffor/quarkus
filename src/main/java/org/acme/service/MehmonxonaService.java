package org.acme.service;

import java.util.List;

import org.acme.dto.MehmonxonaCreateDTO;
import org.acme.entity.Mehmonxona;

public interface MehmonxonaService {
    List<Mehmonxona> getAll(Long arizaId);

    Mehmonxona getById(Long id);

    Mehmonxona create(MehmonxonaCreateDTO dto);

    Mehmonxona update(Long id, MehmonxonaCreateDTO dto);

    void delete(Long id);
}
