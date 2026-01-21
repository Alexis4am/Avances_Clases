package ec.edu.ups.ppw.services;

import java.net.URI;
import java.util.List;
import ec.edu.ups.ppw.business.GestionNotificacion;
import ec.edu.ups.ppw.model.Notificacion;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("notificaciones")
public class NotificacionService {

    @Inject
    private GestionNotificacion gn;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaNotificaciones() {
        List<Notificacion> listado = gn.getNotificaciones();
        return Response.ok(listado).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotificacion(@PathParam("id") int id) {
        Notificacion n;
        try {
            n = gn.getNotificacionPorId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Error(500, "Error interno", e.getMessage())).build();
        }
        if (n == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new Error(404, "No encontrado", "ID " + id + " no existe")).build();
        }
        return Response.ok(n).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNotificacion(Notificacion notificacion, @Context UriInfo uriInfo) {
        try {
            gn.createNotificacion(notificacion);
            URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(notificacion.getId())).build();
            return Response.created(location).entity(notificacion).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Error(500, "Error al crear", e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNotificacion(@PathParam("id") int id, Notificacion notificacion) {
        Notificacion existente = gn.getNotificacionPorId(id);
        if (existente == null) return Response.status(Response.Status.NOT_FOUND).build();
        try {
            // TODO: Mapear campos (ej: existente.setMensaje(notificacion.getMensaje()))
            gn.updateNotificacion(existente);
            return Response.ok(existente).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteNotificacion(@PathParam("id") int id) {
        if (gn.getNotificacionPorId(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
        try {
            gn.deleteNotificacion(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}