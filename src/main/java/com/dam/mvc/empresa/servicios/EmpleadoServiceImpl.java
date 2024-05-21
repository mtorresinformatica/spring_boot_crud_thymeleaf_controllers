package com.dam.mvc.empresa.servicios;

import java.util.Date;
import java.util.List;

import com.dam.mvc.empresa.entidades.Empleado;
import com.dam.mvc.empresa.repositorios.EmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de Empleado
 *
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoServiceI {

	@Autowired
	private EmpleadoRepo empleadoRepositorio;
	
	/**
	 * Obtiene una lista con todos los empleados.
	 *
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerEmpleados() {
		return empleadoRepositorio.findAll();
	}

	/**
	 * Obtiene el empleado que tenga el dni pasado por argumento.
	 * @return Objeto Empleado.
	 */
	@Override
	public Empleado obtenerPorDni(String dni) {
		return empleadoRepositorio.findById(dni).get();
	}

	/**
	 * Obtiene los empleados que tengan el nombre pasado por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorNombre(String nombre) {
		return empleadoRepositorio.findByNombre(nombre);
	}

	/**
	 * Obtiene los empleados que tengan el apellido pasado por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorApellido(String apellido) {
		return empleadoRepositorio.findByApellido(apellido);
	}
	
	/**
	 * Obtiene los empleados que tengan la fecha de nacimiento pasada por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorFechaNacimiento(Date fechaNacimiento) {
		return empleadoRepositorio.findByFechaNacimiento(fechaNacimiento);
	}

	/**
	 * Inserta un empleado en la base de datos.
	 */
	@Override
	public void insertarEmpleado(Empleado e) {
		empleadoRepositorio.save(e);
	}

	/**
	 * Actualiza un empleado ya existente en la base de datos.
	 */
	@Override
	public void actualizarEmpleado(Empleado e) {
		empleadoRepositorio.save(e);
	}

	/**
	 * Elimina un empleado en la base de datos.
	 */
	@Override
	public void eliminarEmpleadoPorDni(String dni) {
		empleadoRepositorio.deleteById(dni);
	}

	/**
	 * Obtiene los empleados que tengan el nombre y apellido pasados por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorNombreYApellido(String nombre, String apellido) {
		return empleadoRepositorio.findByNombreAndApellido(nombre, apellido);
	}

	/**
	 * Obtiene los empleados que tengan el nombre y fecha de nacimiento pasados por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorNombreYFechaNacimiento(String nombre, Date fechaNacimiento) {
		return empleadoRepositorio.findByNombreAndFechaNacimiento(nombre, fechaNacimiento);
	}

	/**
	 * Obtiene los empleados que tengan el apellido y fecha de nacimiento pasados por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorApellidoYFechaNacimiento(String apellido, Date fechaNacimiento) {
		return empleadoRepositorio.findByApellidoAndFechaNacimiento(apellido, fechaNacimiento);
	}

	/**
	 * Obtiene los empleados que tengan el nombre, apellido y fecha de nacimiento pasados por argumento.
	 * @return Lista de objetos Empleado.
	 */
	@Override
	public List<Empleado> obtenerPorNombreYApellidoYFechaNacimiento(String nombre, String apellido,
			Date fechaNacimiento) {
		return empleadoRepositorio.findByNombreAndApellidoAndFechaNacimiento(nombre, apellido, fechaNacimiento);
	}

}
