package org.acme.service;

import java.util.List;

import org.acme.entity.Category;
import org.acme.repository.CategoryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll(String search) {
        String query = "1 = 1";
        if (search != null && !search.isEmpty()) {
            query += " and name like '%" + search + "%'";
        }
        return repository.list(query);
    }

    @Override
    public Category getById(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Category topilmadi"));
    }

    @Override
    @Transactional
    public Category create(Category category) {
        repository.persist(category);
        return category;
    }

    @Override
    @Transactional
    public Category update(Long id, Category data) {
        Category category = getById(id);
        category.name = data.name;
        return category;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}

