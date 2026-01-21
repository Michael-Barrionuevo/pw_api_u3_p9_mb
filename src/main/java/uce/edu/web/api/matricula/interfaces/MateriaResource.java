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
    @Path("/todas")
    public Response obtenerTodas() {
        List<Materia> materias = materiaService.obtenerTodas();
        return Response.ok(materias).build();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id) {
        return Response.ok(materiaService.obtenerPorId(id)).build();
    }

    @GET
    @Path("/buscarPorCarrera/{carrera}")
    public Response buscarPorCarrera(@PathParam("carrera") String carrera) {
        return Response.ok(materiaService.buscarPorCarrera(carrera)).build();
    }

    @GET
    @Path("/buscarPorSemestre/{semestre}")
    public Response buscarPorSemestre(@PathParam("semestre") Integer semestre) {
        return Response.ok(materiaService.buscarPorSemestre(semestre)).build();
    }

   

    @POST
    @Path("/crear")
    public Response crear(Materia materia) {
        Materia creada = materiaService.crear(materia);
        return Response.status(Response.Status.CREATED)
                       .entity(creada)
                       .build();
    }

    @PUT
    @Path("/actualizar/{id}")
    public Response actualizar(@PathParam("id") Integer id, Materia materia) {
        Materia actualizada = materiaService.actualizar(id, materia);
        return Response.ok(actualizada).build();
    }

     @PATCH
    @Path("/actualizarParcial/{id}")
    public Response actualizarParcial(@PathParam("id") Integer id, Materia materia) {
        Materia actualizada = materiaService.actualizarParcial(id, materia);
        return Response.ok(actualizada).build();
    }

    @DELETE
    @Path("/borrar/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        materiaService.eliminar(id);
        return Response.noContent().build();
    }
}
