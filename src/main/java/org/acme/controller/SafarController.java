package org.acme.controller;

import java.util.List;

import org.acme.dto.SafarCreateDTO;
import org.acme.entity.Safar;
import org.acme.service.SafarService;

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

@Path("/safarlar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SafarController {
    private final SafarService service;

    public SafarController(SafarService service) {
        this.service = service;
    }

    @GET
    public List<Safar> list(@QueryParam("arizaId") Long arizaId) {
        return service.getAll(arizaId);
    }

    @GET
    @Path("/{id}")
    public Safar get(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    public Response create(@Valid SafarCreateDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Safar update(@PathParam("id") Long id,
                        @Valid SafarCreateDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}
