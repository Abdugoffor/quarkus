package org.acme.repository;

import java.util.List;

import org.acme.entity.Mehmonxona;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MehmonxonaRepository implements PanacheRepository<Mehmonxona> {
    public List<Mehmonxona> findByArizaId(Long arizaId) {
        return list("arizaId", arizaId);
    }
}
