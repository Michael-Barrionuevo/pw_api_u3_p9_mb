package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;
import java.util.List;

import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;

@ApplicationScoped
public class HijoService {


    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorEstudiante(Integer idEstudiante){
        List<HijoRepresentation> list = new java.util.ArrayList<>();
        for(Hijo h : this.hijoRepository.buscarPorEstudiante(idEstudiante)){
            list.add(this.mapperToHijoR(h));
        }
        return list;
    }

    private HijoRepresentation mapperToHijoR(Hijo hijo){
        HijoRepresentation hr = new HijoRepresentation();
        hr.id=hijo.id;
        hr.nombre=hijo.nombre;
        hr.apellido=hijo.apellido;
        return hr;
    }

}
