package com.dam.mvc.empresa.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proyectos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {

    /**
     * La ID del proyecto. Se genera por el motor de la base de datos.
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * El titulo del proyecto.
     */
    @NotEmpty(message = "debe especificar un titulo")
    @Column(name = "Titulo")
    private String titulo;

    /**
     * La fecha de inicio del proyecto.
     */
    @NotNull(message = "debe registrar la fecha de inicio")
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    /**
     * La fecha de fin del proyecto.
     */
    @NotNull(message = "debe registrar la fecha de fin")
    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    /**
     * La descripción del proyecto.
     */
    @Column(name = "Descripcion")
    private String descripcion;

    /**
     * Los empleados pertenecientes al proyectos.
     */
    @ManyToMany
    @JoinTable(name = "empleados_proyectos",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_dni"))
    private List<Empleado> empleados;
}