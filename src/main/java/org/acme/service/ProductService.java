package org.acme.service;

import java.util.List;

import org.acme.entity.Product;

public interface ProductService {
    List<Product> getAll(String search, Long categoryId, Double minPrice, Double maxPrice);
    Product getById(Long id);
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
