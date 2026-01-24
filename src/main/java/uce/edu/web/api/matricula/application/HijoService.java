package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;
import java.util.List;
import uce.edu.web.api.matricula.domain.Hijo;

@ApplicationScoped
public class HijoService {


    @Inject
    private HijoRepository hijoRepository;

    public List<Hijo> buscarPorEstudiante(Integer idEstudiante){
        return this.hijoRepository.buscarPorEstudiante(idEstudiante);
    }

}
