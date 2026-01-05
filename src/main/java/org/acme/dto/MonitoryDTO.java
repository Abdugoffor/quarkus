package org.acme.dto;

public class MonitoryDTO {
    public String name;
    public Double minPrice;
    public Double maxPrice;
    public Long categoryId;
    public Boolean inStock;
    public String brand;

    public int page = 0;
    public int size = 5; // default page size
}
