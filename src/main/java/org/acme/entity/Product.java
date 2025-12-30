package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Product name bosh bolmasligi kerak")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    public String name;

    @Column(nullable = true)
    public Double price;

    // Many products → One category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    public Category category;
}
