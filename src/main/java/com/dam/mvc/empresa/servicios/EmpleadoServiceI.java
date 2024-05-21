package com.dam.mvc.empresa.servicios;

import com.dam.mvc.empresa.entidades.Empleado;

import java.util.Date;
import java.util.List;



/**
 * Servicio de empleado
 *
 */
public interface EmpleadoServiceI {
	
	/**
	 * Obtiene una lista con todos los empleados.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerEmpleados();
	
	/**
	 * Obtiene el empleado que tenga el dni pasado por argumento.
	 * @param dni el DNI del empleado a buscar.
	 * @return Objeto Empleado.
	 */
	public Empleado obtenerPorDni(String dni);

	/**
	 * Obtiene los empleados que tengan el nombre pasado por argumento.
	 * @param nombre el nombre a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorNombre(String nombre);

	/**
	 * Obtiene los empleados que tengan el apellido pasado por argumento.
	 * @param apellido el apellido a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorApellido(String apellido);
	
	/**
	 * Obtiene los empleados que tengan la fecha de nacimiento pasada por argumento.
	 * @param fechaNacimiento la fecha de nacimiento a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorFechaNacimiento(Date fechaNacimiento);

	/**
	 * Obtiene los empleados que tengan el nombre y apellido pasados por argumento.
	 * @param nombre el nombre a buscar.
	 * @param apellido el apellido a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorNombreYApellido(String nombre, String apellido);

	/**
	 * Obtiene los empleados que tengan el nombre y fecha de nacimiento pasados por argumento.
	 * @param nombre el nombre a buscar.
	 * @param fechaNacimiento la fecha de nacimiento a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorNombreYFechaNacimiento(String nombre, Date fechaNacimiento);

	/**
	 * Obtiene los empleados que tengan el apellido y fecha de nacimiento pasados por argumento.
	 * @param apellido el apellido a buscar.
	 * @param fechaNacimiento la fecha de nacimiento a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorApellidoYFechaNacimiento(String apellido, Date fechaNacimiento);

	/**
	 * Obtiene los empleados que tengan el nombre, apellido y fecha de nacimiento pasados por argumento.
	 * @param nombre el nombre a buscar.
	 * @param apellido el apellido a buscar.
	 * @param fechaNacimiento la fecha de nacimiento a buscar.
	 * @return Lista de objetos Empleado.
	 */
	public List<Empleado> obtenerPorNombreYApellidoYFechaNacimiento(String nombre, String apellido, Date fechaNacimiento);

	/**
	 * Inserta un empleado en la base de datos.
	 * @param e el objeto empleado a insertar.
	 */
	public void insertarEmpleado(Empleado e);
	
	/**
	 * Actualiza un empleado ya existente en la base de datos.
	 * @param e el objeto empleado a actualizar.
	 */
	public void actualizarEmpleado (Empleado e);
	
	/**
	 * Elimina un empleado en la base de datos.
	 * @param dni el DNI del empleado a eliminar.
	 */
	public void eliminarEmpleadoPorDni(String dni);
	
}
