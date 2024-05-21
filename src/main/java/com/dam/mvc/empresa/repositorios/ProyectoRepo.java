package com.dam.mvc.empresa.repositorios;

import java.util.Date;
import java.util.List;

import com.dam.mvc.empresa.entidades.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio de proyecto.
 *
 */
@Repository
public interface ProyectoRepo extends JpaRepository<Proyecto, Long>{
	
	/**
	 * Encontrar por titulo.
	 * @param titulo el titulo del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByTitulo(String titulo);
	
	/**
	 * Encontrar por fecha de inico.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByFechaInicio(Date fechaInicio);
	
	/**
	 * Encontrar por fecha de fin.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByFechaFin(Date fechaFin);
	
	/**
	 * Encontrar por titulo y fecha de inicio.
	 * @param titulo el titulo del proyecto.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByTituloAndFechaInicio(String titulo, Date fechaInicio);
	
	/**
	 * Encontrar por titulo y fecha de inicio.
	 * @param titulo el titulo del proyecto.
	 * @param fechaFin la fecha de inicio del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByTituloAndFechaFin(String titulo, Date fechaFin);
	
	/**
	 * Encontrar por fecha de inicio y fecha de fin.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin);
	
	/**
	 * Encontrar por titulo, fecha de inicio y fecha fin.
	 * @param titulo el titulo del proyecto.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return lista de proyectos.
	 */
	List<Proyecto> findByTituloAndFechaInicioAndFechaFin(String titulo, Date fechaInicio, Date fechaFin);
	
}
