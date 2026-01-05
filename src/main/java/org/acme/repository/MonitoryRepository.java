package org.acme.repository;

import org.acme.entity.Monitory;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitoryRepository {

    public PanacheQuery<Monitory> query() {
        return Monitory.findAll(); // PanacheEntity findAll()
    }
}
