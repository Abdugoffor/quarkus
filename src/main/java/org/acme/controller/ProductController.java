package org.acme.controller;

import java.util.List;

import org.acme.entity.Product;
import org.acme.service.ProductService;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {
     private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<Product> list(
        @QueryParam("search") String search,
        @QueryParam("categoryId") Long categoryId,
        @QueryParam("minPrice") Double minPrice,
        @QueryParam("maxPrice") Double maxPrice
    ) {
        return productService.getAll(search, categoryId, minPrice, maxPrice);
    }

    @GET
    @Path("/{id}")
    public Product get(@PathParam("id") Long id) {
        return productService.getById(id);
    }

    @POST
    public Response create(@Valid Product product) {
        return Response.status(Response.Status.CREATED)
                .entity(productService.create(product))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Product update(@PathParam("id") Long id, @Valid Product product) {
        return productService.update(id, product);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        productService.delete(id);
    }
}
