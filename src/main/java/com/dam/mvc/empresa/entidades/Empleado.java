package com.dam.mvc.empresa.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa a un empleado.
 *
 */
@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado{

    /**
     * El DNI del empleado.
     */
    @Id
    @Column(name = "Dni", nullable = false, unique = true)
    private String dni;

    /**
     * El nombre del empleado.
     */
    @NotEmpty(message = "debe especificar un nombre")
    @Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
    @Column(name = "Nombre", nullable = false)
    private String nombre;

    /**
     * El apellido del empleado.
     */
    @NotEmpty(message = "debe especificar un apellido")
    @Column(name = "Apellido", nullable = false)
    private String apellido;

    /**
     * La fecha de nacimiento del empleado.
     */
    //@DateTimeFormat se usa para compatibilizar lo que se recibe del navegador web (<input type="date">) con la clase Date de Java.
    @NotNull(message = "debe registrar la fecha de nacimiento")
    @Column(name = "FechaNacimiento")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    /**
     * Lista de proyectos a los que pertenece el empleado.
     */
    @ManyToMany
    @JoinTable(name = "empleados_proyectos",
            joinColumns = @JoinColumn(name = "empleado_dni"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
    private List<Proyecto> proyectos;
}