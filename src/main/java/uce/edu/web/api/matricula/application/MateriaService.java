package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    MateriaRepository materiaRepository;

    

    public List<Materia> obtenerTodas() {
        return materiaRepository.listAll();
    }

    public Materia obtenerPorId(Integer id) {
        return materiaRepository.findByIdOptional(id.longValue())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    public List<Materia> buscarPorCarrera(String carrera) {
        return materiaRepository.buscarPorCarrera(carrera);
    }

    public List<Materia> buscarPorSemestre(Integer semestre) {
        return materiaRepository.buscarPorSemestre(semestre);
    }

    
    @Transactional
    public Materia crear(Materia materia) {
        materiaRepository.persist(materia);
        return materia;
    }

    @Transactional
    public Materia actualizar(Integer id, Materia datos) {
        Materia materia = obtenerPorId(id);
        materia.setNombre(datos.getNombre());
        materia.setCarrera(datos.getCarrera());
        materia.setSemestre(datos.getSemestre());
        materia.setCosto(datos.getCosto());
        return materia;
    }

    @Transactional
    public Materia actualizarParcial(Integer id, Materia datos) {
        Materia materia = obtenerPorId(id);

        if (datos.getNombre() != null) {
            materia.setNombre(datos.getNombre());
        }
        if (datos.getCarrera() != null) {
            materia.setCarrera(datos.getCarrera());
        }
        if (datos.getSemestre() != null) {
            materia.setSemestre(datos.getSemestre());
        }
        if (datos.getCosto() != null) {
            materia.setCosto(datos.getCosto());
        }

        return materia;
    }

    @Transactional
    public void eliminar(Integer id) {
        obtenerPorId(id); 
        materiaRepository.deleteById(id.longValue());
    }
}
