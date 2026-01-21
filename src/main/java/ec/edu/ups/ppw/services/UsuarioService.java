package ec.edu.ups.ppw.services;

import java.net.URI;
import java.util.List;

import ec.edu.ups.ppw.business.GestionUsuario;
import ec.edu.ups.ppw.model.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("usuarios")
public class UsuarioService {

    @Inject
    private GestionUsuario gu;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaUsuarios() {
        List<Usuario> listado = gu.getUsuarios();
        return Response.ok(listado).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") int id) {
        Usuario u;
        try {
            u = gu.getUsuarioPorId(id);
        } catch (Exception e) {
            Error error = new Error(
                    500,
                    "Error interno del servidor",
                    "Se produjo un error al procesar la solicitud: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        if (u == null) {
            Error error = new Error(
                    404,
                    "No encontrado",
                    "Usuario con id " + id + " no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }

        return Response.ok(u).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario, @Context UriInfo uriInfo) {
        try {
            gu.createUsuario(usuario);
        } catch (Exception e) {
            Error error = new Error(
                    500,
                    "Error interno del servidor",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(usuario.getId()))
                .build();

        return Response.created(location)
                .entity(usuario)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        Usuario existente = gu.getUsuarioPorId(id);

        if (existente == null) {
            Error error = new Error(
                    404,
                    "No encontrado",
                    "Usuario con id " + id + " no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }

        try {
            existente.setNombre(usuario.getNombre());
            existente.setEmail(usuario.getEmail());
            existente.setPassword(usuario.getPassword());
            gu.updateUsuario(existente);
        } catch (Exception e) {
            Error error = new Error(
                    500,
                    "Error interno del servidor",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        return Response.ok(existente).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("id") int id) {
        Usuario existente = gu.getUsuarioPorId(id);

        if (existente == null) {
            Error error = new Error(
                    404,
                    "No encontrado",
                    "Usuario con id " + id + " no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }

        try {
            gu.deleteUsuario(id);
        } catch (Exception e) {
            Error error = new Error(
                    500,
                    "Error interno del servidor",
                    e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        return Response.ok().build();
    }
}