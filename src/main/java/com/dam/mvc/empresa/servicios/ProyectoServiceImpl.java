package com.dam.mvc.empresa.servicios;

import java.util.Date;
import java.util.List;

import com.dam.mvc.empresa.entidades.Proyecto;
import com.dam.mvc.empresa.repositorios.ProyectoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementacion del servicio (ProyectoServiceI)
 *
 */
@Service
public class ProyectoServiceImpl implements ProyectoServiceI{

	@Autowired
	private ProyectoRepo proyectoRepositorio;
	
	/**
	 * Obtiene una lista con todos los proyecto.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerProyectos() {
		return proyectoRepositorio.findAll();
	}

	/**
	 * Obtiene el proyecto que tenga el id pasado por argumento.
	 * @return Objeto Proyecto.
	 */
	@Override
	public Proyecto obtenerPorId(Long id) {
		return proyectoRepositorio.findById(id).get();
	}
	
	/**
	 * Obtiene el proyecto que tenga el titulo pasado por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorTitulo(String titulo) {
		return proyectoRepositorio.findByTitulo(titulo);
	}

	/**
	 * Obtiene el proyecto que tenga la fecha de inicio pasado por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorFechaInicio(Date fechaInicio) {
		return proyectoRepositorio.findByFechaInicio(fechaInicio);
	}

	/**
	 * Obtiene el proyecto que tenga la fecha de fin pasado por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorFechaFin(Date fechaFin) {
		return proyectoRepositorio.findByFechaFin(fechaFin);
	}

	/**
	 * Inserta un proyecto en la base de datos.
	 */
	@Override
	public void insertarProyecto(Proyecto p) {
		proyectoRepositorio.save(p);
	}

	/**
	 * Actualiza un proyecto ya existente en la base de datos.
	 */
	@Override
	public void actualizarProyecto(Proyecto p) {
		proyectoRepositorio.save(p);
	}

	/**
	 * Elimina un proyecto en la base de datos.
	 */
	@Override
	public void eliminarProyectoPorId(Long id) {
		proyectoRepositorio.deleteById(id);
	}

	/**
	 * Obtiene el proyecto que tenga el titulo y fecha de inicio pasados por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorTituloAndFechaInicio(String titulo, Date fechaInicio) {
		return proyectoRepositorio.findByTituloAndFechaInicio(titulo, fechaInicio);
	}

	/**
	 * Obtiene el proyecto que tenga el titulo y fecha de fin pasados por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorTituloAndFechaFin(String titulo, Date fechaFin) {
		return proyectoRepositorio.findByTituloAndFechaFin(titulo, fechaFin);
	}

	/**
	 * Obtiene el proyecto que tenga el fecha de inicio y fecha de fin pasados por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin) {
		return proyectoRepositorio.findByFechaInicioAndFechaFin(fechaInicio, fechaFin);
	}

	/**
	 * Obtiene el proyecto que tenga el titulo, fecha de inicio y fecha de fin pasados por argumento.
	 * @return Lista de objetos Proyecto.
	 */
	@Override
	public List<Proyecto> obtenerPorTituloAndFechaInicioAndFechaFin(String titulo, Date fechaInicio, Date fechaFin) {
		return proyectoRepositorio.findByTituloAndFechaInicioAndFechaFin(titulo, fechaInicio, fechaFin);
	}

}
