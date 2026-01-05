package org.acme.service;

import java.util.List;

import org.acme.dto.SafarCreateDTO;
import org.acme.entity.Safar;
import org.acme.repository.SafarRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class SafarServiceImpl implements SafarService {
    private final SafarRepository repository;

    public SafarServiceImpl(SafarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Safar> getAll(Long arizaId) {
        if (arizaId != null) {
            return repository.list("arizaId", arizaId);
        }
        return repository.listAll();
    }

    @Override
    public Safar getById(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() ->
                        new NotFoundException("Safar topilmadi. id=" + id));
    }

    @Override
    @Transactional
    public Safar create(SafarCreateDTO dto) {
        Safar s = new Safar();
        s.arizaId = dto.arizaId;
        s.kelishVaqti = dto.kelishVaqti;
        s.kelishAeroporti = dto.kelishAeroporti;
        s.ketishVaqti = dto.ketishVaqti;
        s.ketishAeroporti = dto.ketishAeroporti;

        repository.persist(s);
        return s;
    }

    @Override
    @Transactional
    public Safar update(Long id, SafarCreateDTO dto) {
        Safar s = getById(id);
        s.arizaId = dto.arizaId;
        s.kelishVaqti = dto.kelishVaqti;
        s.kelishAeroporti = dto.kelishAeroporti;
        s.ketishVaqti = dto.ketishVaqti;
        s.ketishAeroporti = dto.ketishAeroporti;
        return s;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
