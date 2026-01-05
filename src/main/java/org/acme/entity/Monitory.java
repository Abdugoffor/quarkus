package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "monitory")
public class Monitory extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public Double price;

    public String description;
    public Long categoryId;
    public Boolean inStock;
    public String sku;
    public String brand;
}
