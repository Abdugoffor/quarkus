package org.acme.service;

import java.util.List;

import org.acme.dto.ProductCreateDTO;
import org.acme.entity.Category;
import org.acme.entity.Product;
import org.acme.repository.CategoryRepository;
import org.acme.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAll(String search, Long categoryId, Double minPrice, Double maxPrice) {
        String query = "1 = 1";
        if (search != null && !search.isEmpty()) {
            query += " and name like '%" + search + "%'";
        }
        if (categoryId != null) {
            query += " and category.id = " + categoryId;
        }
        if (minPrice != null) {
            query += " and price >= " + minPrice;
        }
        if (maxPrice != null) {
            query += " and price <= " + maxPrice;
        }
        return productRepository.list(query);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Product topilmadi. id=" + id));
    }

    @Override
    @Transactional
    public Product create(ProductCreateDTO  dto) {
        Category category = categoryRepository.findByIdOptional(dto.categoryId)
            .orElseThrow(() ->
                new NotFoundException("Category topilmadi. id=" + dto.categoryId)
            );

        Product product = new Product();
        product.name = dto.name;
        product.price = dto.price;
        product.category = category;

        productRepository.persist(product);
        return product;
    }

    @Override
    @Transactional
    public Product update(Long id, Product data) {
        Product product = getById(id);
        product.name = data.name;
        product.price = data.price;

        if (data.category != null && data.category.id != null) {
            Category category = categoryRepository.findByIdOptional(data.category.id)
                    .orElseThrow(() -> new NotFoundException("Category topilmadi. id=" + data.category.id));
            product.category = category;
        }

        return product;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.delete(getById(id));
    }
}
