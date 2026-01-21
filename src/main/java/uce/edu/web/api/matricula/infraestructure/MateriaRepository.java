package uce.edu.web.api.matricula.infraestructure;



import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {



    public List<Materia> buscarPorCarrera(String carrera) {
        return list("carrera", carrera);
    }

public List<Materia> buscarPorSemestre(Integer semestre) {
    return list("semestre", semestre);
}

}
