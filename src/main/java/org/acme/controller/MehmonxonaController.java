package org.acme.controller;

import java.util.List;

import org.acme.dto.MehmonxonaCreateDTO;
import org.acme.entity.Mehmonxona;
import org.acme.service.MehmonxonaService;

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

@Path("/mehmonxonalar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MehmonxonaController {
    private final MehmonxonaService service;

    public MehmonxonaController(MehmonxonaService service) {
        this.service = service;
    }

    @GET
    public List<Mehmonxona> list(@QueryParam("arizaId") Long arizaId) {
        return service.getAll(arizaId);
    }

    @GET
    @Path("/{id}")
    public Mehmonxona get(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    public Response create(@Valid MehmonxonaCreateDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Mehmonxona update(@PathParam("id") Long id,
                             @Valid MehmonxonaCreateDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}
