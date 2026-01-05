package org.acme.controller;

import java.util.List;

import org.acme.dto.MonitoryDTO;
import org.acme.entity.Monitory;
import org.acme.service.MonitoryService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/monitories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitoryController {

    @Inject
    MonitoryService monitoryService;

    @GET
    public List<Monitory> getProducts(
            @QueryParam("name") String name,
            @QueryParam("minPrice") Double minPrice,
            @QueryParam("maxPrice") Double maxPrice,
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("inStock") Boolean inStock,
            @QueryParam("brand") String brand,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("5") int size
    ) {
        MonitoryDTO filterDTO = new MonitoryDTO();
        filterDTO.name = name;
        filterDTO.minPrice = minPrice;
        filterDTO.maxPrice = maxPrice;
        filterDTO.categoryId = categoryId;
        filterDTO.inStock = inStock;
        filterDTO.brand = brand;
        filterDTO.page = page;
        filterDTO.size = size;

        return monitoryService.getAll(filterDTO);
    }
}
