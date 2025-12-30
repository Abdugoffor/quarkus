package org.acme.controller;

import java.util.List;

import org.acme.entity.Category;
import org.acme.service.CategoryService;

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

@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    public List<Category> list(@QueryParam("search") String search) {
        return categoryService.getAll(search);
    }

    @GET
    @Path("/{id}")
    public Category get(@PathParam("id") Long id) {
        return categoryService.getById(id);
    }

    @POST
    public Response create(@Valid Category category) {
        return Response.status(Response.Status.CREATED)
                .entity(categoryService.create(category))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Category update(
            @PathParam("id") Long id,
            @Valid Category category
    ) {
        return categoryService.update(id, category);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        categoryService.delete(id);
    }
}
