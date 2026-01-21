package ec.edu.ups.ppw.services;

import java.net.URI;
import java.util.List;
import ec.edu.ups.ppw.business.GestionDisponibilidad;
import ec.edu.ups.ppw.model.Disponibilidad;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("disponibilidades")
public class DisponibilidadService {

    @Inject
    private GestionDisponibilidad gd;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaDisponibilidades() {
        return Response.ok(gd.getDisponibilidades()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisponibilidad(@PathParam("id") int id) {
        Disponibilidad d = gd.getDisponibilidadPorId(id);
        if (d == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(d).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisponibilidad(Disponibilidad disponibilidad, @Context UriInfo uriInfo) {
        try {
            gd.createDisponibilidad(disponibilidad);
            URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(disponibilidad.getId())).build();
            return Response.created(location).entity(disponibilidad).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisponibilidad(@PathParam("id") int id, Disponibilidad disponibilidad) {
        Disponibilidad existente = gd.getDisponibilidadPorId(id);
        if (existente == null) return Response.status(Response.Status.NOT_FOUND).build();
        try {
            // TODO: Mapear campos (ej: existente.setFecha(disponibilidad.getFecha()))
            gd.updateDisponibilidad(existente);
            return Response.ok(existente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDisponibilidad(@PathParam("id") int id) {
        try {
            gd.deleteDisponibilidad(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}