package com.dam.mvc.empresa.servicios;

import com.dam.mvc.empresa.entidades.Proyecto;

import java.util.Date;
import java.util.List;


/**
 * Servicio de Proyecto
 *
 */
public interface ProyectoServiceI {

	/**
	 * Obtener todos los proyectos.
	 * @return lista de proyectos.
	 */
	public List<Proyecto> obtenerProyectos();
	
	/**
	 * Obtiene el proyecto que tenga el id pasado por argumento.
	 * @param id la id a buscar.
	 * @return Objeto Proyecto.
	 */
	public Proyecto obtenerPorId(Long id);
	
	/**
	 * Obtiene el proyecto que tenga el titulo pasado por argumento.
	 * @param titulo el titulo del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorTitulo(String titulo);
	
	/**
	 * Obtiene el proyecto que tenga la fecha de inicio pasado por argumento.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorFechaInicio(Date fechaInicio);
	
	/**
	 * Obtiene el proyecto que tenga la fecha de fin pasado por argumento.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorFechaFin(Date fechaFin);
	
	/**
	 * Obtiene el proyecto que tenga el titulo y fecha de inicio pasados por argumento.
	 * @param titulo el titulo del proyecto.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorTituloAndFechaInicio(String titulo, Date fechaInicio);
	
	/**
	 * Obtiene el proyecto que tenga el titulo y fecha de fin pasados por argumento.
	 * @param titulo el titulo del proyecto.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorTituloAndFechaFin(String titulo, Date fechaFin);
	
	/**
	 * Obtiene el proyecto que tenga el fecha de inicio y fecha de fin pasados por argumento.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin);
	
	/**
	 * Obtiene el proyecto que tenga el titulo, fecha de inicio y fecha de fin pasados por argumento.
	 * @param titulo el titulo del proyecto.
	 * @param fechaInicio la fecha de inicio del proyecto.
	 * @param fechaFin la fecha de fin del proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	public List<Proyecto> obtenerPorTituloAndFechaInicioAndFechaFin(String titulo, Date fechaInicio, Date fechaFin);
	
	/**
	 * Inserta un proyecto en la base de datos.
	 * @param p el proyecto a insertar.
	 */
	public void insertarProyecto(Proyecto p);
	
	/**
	 * Actualiza un proyecto ya existente en la base de datos.
	 * @param p El proyecto a actualizar.
	 */
	public void actualizarProyecto(Proyecto p);
	
	/**
	 * Elimina un proyecto en la base de datos.
	 * @param id la id del proyecto.
	 */
	public void eliminarProyectoPorId(Long id);
	
}
