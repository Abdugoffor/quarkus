package org.acme.repository;

import java.util.List;

import org.acme.entity.Safar;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SafarRepository implements PanacheRepository<Safar>{
    public List<Safar> findByArizaId(Long arizaId) {
        return list("arizaId", arizaId);
    }
}
