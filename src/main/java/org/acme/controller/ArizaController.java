package org.acme.controller;

import java.util.List;

import org.acme.dto.ArizaCreateDTO;
import org.acme.dto.ArizaFullDTO;
import org.acme.entity.Ariza;
import org.acme.service.ArizaService;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/arizalar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArizaController {
    private final ArizaService service;

    public ArizaController(ArizaService service) {
        this.service = service;
    }

    @GET
    public List<Ariza> list() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Ariza get(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    public Response create(@Valid ArizaCreateDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Ariza update(@PathParam("id") Long id, @Valid ArizaCreateDTO dto) {
        return service.update(id, dto);
    }
    // GET /arizalar/{id}/full
    @GET
    @Path("/{id}/full")
    public ArizaFullDTO getFull(@PathParam("id") Long id) {
        return service.getArizaFull(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}
