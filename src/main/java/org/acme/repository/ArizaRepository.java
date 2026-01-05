package org.acme.repository;

import java.util.Map;

import org.acme.entity.Ariza;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ArizaRepository implements PanacheRepository<Ariza> {

    @PersistenceContext
    EntityManager em;

    public Map<String, Object> findArizaFull(Long arizaId) {
        String sql = """
            SELECT a.id, a.fio, a.passport, a.address, a.email, a.izoh, a.phone,
                COALESCE(
                    json_agg(DISTINCT jsonb_build_object(
                        'id', mh.id,
                        'nomi', mh.nomi,
                        'turi', mh.xonaturi,
                        'xonaRaqami', mh.xonaRaqami,
                        'nechKun', mh.kunSoni
                    )) FILTER (WHERE mh.id IS NOT NULL), '[]'
                ) AS mehmonhonalar,
                COALESCE(
                    json_agg(DISTINCT jsonb_build_object(
                        'id', s.id,
                        'kelishVaqti', s.kelishVaqti,
                        'kelishAeroporti', s.kelishAeroporti,
                        'ketishVaqti', s.ketishVaqti,
                        'ketishAeroporti', s.ketishAeroporti
                    )) FILTER (WHERE s.id IS NOT NULL), '[]'
                ) AS safarlar
            FROM arizalar a
            LEFT JOIN mehmonxonalar mh ON mh.arizaid = a.id
            LEFT JOIN safarlar s ON s.arizaid = a.id
            WHERE a.id = :id
            GROUP BY a.id
        """;

        return (Map<String, Object>) em.createNativeQuery(sql)
                .setParameter("id", arizaId)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(org.hibernate.transform.AliasToEntityMapResultTransformer.INSTANCE)
                .getSingleResult();
    }
}
