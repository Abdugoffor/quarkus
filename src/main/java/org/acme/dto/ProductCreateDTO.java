package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    public String name;

    public Double price;

    @NotNull(message = "Category id majburiy")
    public Long categoryId;
}
