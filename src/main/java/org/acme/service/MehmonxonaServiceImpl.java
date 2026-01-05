package org.acme.service;

import java.util.List;

import org.acme.dto.MehmonxonaCreateDTO;
import org.acme.entity.Mehmonxona;
import org.acme.repository.MehmonxonaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MehmonxonaServiceImpl implements MehmonxonaService {
    private final MehmonxonaRepository repository;

    public MehmonxonaServiceImpl(MehmonxonaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Mehmonxona> getAll(Long arizaId) {
        if (arizaId != null) {
            return repository.list("arizaId", arizaId);
        }
        return repository.listAll();
    }

    @Override
    public Mehmonxona getById(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() ->
                        new NotFoundException("Mehmonxona topilmadi. id=" + id));
    }

    @Override
    @Transactional
    public Mehmonxona create(MehmonxonaCreateDTO dto) {
        Mehmonxona m = new Mehmonxona();
        m.arizaId = dto.arizaId;
        m.nomi = dto.nomi;
        m.xonaTuri = dto.xonaTuri;
        m.xonaRaqami = dto.xonaRaqami;
        m.kunSoni = dto.kunSoni;

        repository.persist(m);
        return m;
    }

    @Override
    @Transactional
    public Mehmonxona update(Long id, MehmonxonaCreateDTO dto) {
        Mehmonxona m = getById(id);
        m.arizaId = dto.arizaId;
        m.nomi = dto.nomi;
        m.xonaTuri = dto.xonaTuri;
        m.xonaRaqami = dto.xonaRaqami;
        m.kunSoni = dto.kunSoni;
        return m;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
