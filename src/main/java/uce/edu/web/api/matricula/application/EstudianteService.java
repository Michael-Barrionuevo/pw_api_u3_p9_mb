package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante estu : this.estudianteRepository.listAll()) {
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    
    public EstudianteRepresentation consultarPorId(Integer id){
    Estudiante est = this.estudianteRepository.findById(id.longValue());
    if(est == null) return null;
    return this.mapperToER(est);
}

    @Transactional
    public void crear(EstudianteRepresentation estu){
        this.estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

   @Transactional
public void actualizar(Integer id, EstudianteRepresentation estu){
    Estudiante estudiante = this.estudianteRepository.findById(id.longValue());
    if(estudiante != null) {
        estudiante.apellido=estu.apellido;
        estudiante.nombre=estu.nombre;
        estudiante.fechaNacimiento=estu.fechaNacimiento;
    }
}

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation estu){
        Estudiante est = estudianteRepository.findById(id.longValue());
        if (est == null) {
            return;
        }
        if (estu.nombre != null) {
            est.nombre = estu.nombre;
        }
        if (estu.apellido != null) {
            est.apellido = estu.apellido;
        }
        if (estu.fechaNacimiento != null) {
            est.fechaNacimiento = estu.fechaNacimiento;
        }
    }

    @Transactional
    public void eliminar(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia, String genero){
        //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }

     public List<Estudiante> buscarPorGenero(String provincia, String genero){
        return this.estudianteRepository.find("provincia", provincia).list();
        
    }

    private EstudianteRepresentation mapperToER (Estudiante est){
        EstudianteRepresentation estR = new EstudianteRepresentation();
        estR.id=est.id;
        estR.nombre=est.nombre;
        estR.apellido=est.apellido;
        estR.fechaNacimiento=est.fechaNacimiento;
        estR.provincia=est.provincia;
        estR.genero=est.genero;
        return estR;
    }



  private Estudiante mapperToEstudiante (EstudianteRepresentation est){
    Estudiante estR = new Estudiante();
    
    estR.nombre = est.nombre;
    estR.apellido = est.apellido;
    estR.fechaNacimiento = est.fechaNacimiento;
    estR.provincia = est.provincia;
    estR.genero = est.genero;
    return estR;
}




   
   

}
