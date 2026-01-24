package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")

public class MateriaResource {

    @Inject
    MateriaService materiaService;

  

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> obtenerTodas() {
        List<Materia> test = this.materiaService.obtenerTodas();
        return test;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response obtenerPorId(@PathParam("id") Integer id) {
        return Response.ok(materiaService.obtenerPorId(id)).build();
    }

    @GET
    @Path("/{carrera}")
    public Response buscarPorCarrera(@PathParam("carrera") String carrera) {
        return Response.ok(materiaService.buscarPorCarrera(carrera)).build();
    }

    @GET
    @Path("/{semestre}")
    public Response buscarPorSemestre(@PathParam("semestre") Integer semestre) {
        return Response.ok(materiaService.buscarPorSemestre(semestre)).build();
    }

   

    @POST
    @Path("")
    public Response crear(Materia materia) {
        Materia creada = materiaService.crear(materia);
        return Response.status(Response.Status.CREATED)
                       .entity(creada)
                       .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
        return Response.status(209).entity(null).build();
    }

     @PATCH
    @Path("/{id}")
    public Response actualizarParcial(@PathParam("id") Integer id, Materia materia) {
        Materia actualizada = materiaService.actualizarParcial(id, materia);
        return Response.ok(actualizada).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        materiaService.eliminar(id);
        return Response.noContent().build();
    }
}
