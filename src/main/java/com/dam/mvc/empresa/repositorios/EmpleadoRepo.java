package com.dam.mvc.empresa.repositorios;

import java.util.Date;
import java.util.List;

import com.dam.mvc.empresa.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio de Empleado
 *
 */
@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, String> {
	
	/**
	 * Encontrar por nombre.
	 * @param nombre el nombre del empleado.
	 * @return lista de empleados.
	 */
	List<Empleado> findByNombre(String nombre);
	
	/**
	 * Encontrar por apellido.
	 * @param apellido el apellido del empleado.
	 * @return lista de empleados.
	 */
	List<Empleado> findByApellido(String apellido);
	
	/**
	 * Encontrar por fecha de nacimiento
	 * @param fecha la fecha de nacimiento del empleado.
	 * @return lista de empleados.
	 */
	List<Empleado> findByFechaNacimiento(Date fecha);
	
	/**
	 * Encontrar por nombre y apellido.
	 * @param nombre el nombre del empleado.
	 * @param apellido el apellido del empleado.
	 * @return lista de empleados.
	 */
	List<Empleado> findByNombreAndApellido(String nombre, String apellido);
	
	/**
	 * Encontrar por nombre y fecha de nacimiento.
	 * @param nombre el nombre del empleado.
	 * @param fechaNacimiento la fecha de nacimiento del empleado.
	 * @return lista de empleados.
	 */
	List<Empleado> findByNombreAndFechaNacimiento(String nombre, Date fechaNacimiento);
	
	/**
	 * Encontrar por apellido y fecha de nacimiento.
	 * @param apellido el apellido del empleado.
	 * @param fechaNacimiento la fecha de nacimiento del empleado.
	 * @return lista de empleados.
	 */
	List<Empleado> findByApellidoAndFechaNacimiento(String apellido, Date fechaNacimiento);
	
	/**
	 * Encontrar por nombre, apellido y fecha de nacimiento.
	 * @param nombre el nombre a buscar.
	 * @param apellido el apellido a buscar.
	 * @param fechaNacimiento la fecha de nacimiento a buscar.
	 * @return lista de empleados.
	 */
	List<Empleado> findByNombreAndApellidoAndFechaNacimiento(String nombre, String apellido, Date fechaNacimiento);
	
}
