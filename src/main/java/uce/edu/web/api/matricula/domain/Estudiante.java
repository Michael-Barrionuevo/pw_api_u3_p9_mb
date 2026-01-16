package uce.edu.web.api.matricula.domain;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante extends PanacheEntity {

    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
}
