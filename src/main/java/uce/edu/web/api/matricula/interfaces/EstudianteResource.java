package uce.edu.web.api.matricula.interfaces;
 
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;
 
@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;
 
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user","docente"})
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("LISTAR TODOS XXXXX");

        List<EstudianteRepresentation> lista = new ArrayList<>();

            for(EstudianteRepresentation er : estudianteService.listarTodos()){
                this.construirLinks(er);
                lista.add(er);
            }
        return lista;
    }
 
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    //@PermitAll
    public Response consultarPorId(@PathParam("id") Integer iden) {
        EstudianteRepresentation er = this.estudianteService.consultarPorId(iden);
        if (er == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(null).build();
        }
        return Response.ok(this.construirLinks(er)).build();
    }
 
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estudianteRepresentation) {
        this.estudianteService.crear(estudianteRepresentation);
        return Response.status(Response.Status.CREATED).entity(estudianteRepresentation).build();
    }
 
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estudianteRepresentation) {
        this.estudianteService.actualizar(id, estudianteRepresentation);
        return Response.ok().build();

    }
 
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estudianteRepresentation) {
        this.estudianteService.actualizarParcial(id, estudianteRepresentation);
        return Response.status(209).entity(estudianteRepresentation).build();
    }
 
    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void borrar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }
 
    
    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("LISTAR TODOS POR PROVINCIA Y GENEROS XXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }
 
    
    @GET
    @Path("/{id}/hijos")
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        if (er == null) {
            return null;
        }
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id)).build().toString();

        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id)).path("hijos").build().toString();

        er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));

        return er;

    }
 
}