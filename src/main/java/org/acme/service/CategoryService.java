package org.acme.service;

import java.util.List;

import org.acme.entity.Category;

public interface CategoryService {
    List<Category> getAll(String search);

    Category getById(Long id);

    Category create(Category category);

    Category update(Long id, Category category);

    void delete(Long id);
}
