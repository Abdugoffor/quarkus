package org.acme.service;

import java.util.List;
import java.util.Map;

import org.acme.dto.MonitoryDTO;
import org.acme.entity.Monitory;
import org.acme.repository.MonitoryRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MonitoryService {

    @Inject
    MonitoryRepository repository;

    public List<Monitory> getAll(MonitoryDTO dto) {
        PanacheQuery<Monitory> query = repository.query();

        if (dto.name != null && !dto.name.isEmpty()) {
            query.filter("name like :name", Map.of("name", "%" + dto.name + "%"));
        }
        if (dto.minPrice != null) {
            query.filter("price >= :minPrice", Map.of("minPrice", dto.minPrice));
        }
        if (dto.maxPrice != null) {
            query.filter("price <= :maxPrice", Map.of("maxPrice", dto.maxPrice));
        }
        if (dto.categoryId != null) {
            query.filter("categoryid = :categoryId", Map.of("categoryId", dto.categoryId));
        }
        if (dto.inStock != null) {
            query.filter("instock = :inStock", Map.of("inStock", dto.inStock));
        }
        if (dto.brand != null && !dto.brand.isEmpty()) {
            query.filter("brand like :brand", Map.of("brand", "%" + dto.brand + "%"));
        }

        // Pagination
        query.page(dto.page, dto.size);

        return query.list();
    }
}
