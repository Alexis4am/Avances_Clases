package ec.edu.ups.ppw.services;

import java.net.URI;
import java.util.List;
import ec.edu.ups.ppw.business.GestionAsesoria;
import ec.edu.ups.ppw.model.Asesoria;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("asesorias")
public class AsesoriaService {

    @Inject
    private GestionAsesoria ga;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaAsesorias() {
        return Response.ok(ga.getAsesorias()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsesoria(@PathParam("id") int id) {
        Asesoria a = ga.getAsesoriaPorId(id);
        if (a == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(a).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAsesoria(Asesoria asesoria, @Context UriInfo uriInfo) {
        try {
            ga.createAsesoria(asesoria);
            URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(asesoria.getId())).build();
            return Response.created(location).entity(asesoria).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAsesoria(@PathParam("id") int id, Asesoria asesoria) {
        Asesoria existente = ga.getAsesoriaPorId(id);
        if (existente == null) return Response.status(Response.Status.NOT_FOUND).build();
        try {
            // TODO: Mapear campos de Asesoria
            ga.updateAsesoria(existente);
            return Response.ok(existente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAsesoria(@PathParam("id") int id) {
        try {
            ga.deleteAsesoria(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}