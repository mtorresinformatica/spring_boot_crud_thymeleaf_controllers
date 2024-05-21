package com.dam.mvc.empresa.controladores;

import java.util.ArrayList;
import java.util.List;


import com.dam.mvc.empresa.entidades.Empleado;
import com.dam.mvc.empresa.entidades.Proyecto;
import com.dam.mvc.empresa.servicios.EmpleadoServiceI;
import com.dam.mvc.empresa.servicios.ProyectoServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controlador referente a las vistas y acciones de Empleado.
 *
 */
@Controller
public class EmpleadoController {

	/**
	 * El servicio de Empleado.
	 */
	@Autowired
	private EmpleadoServiceI empleadoServiceI;
	
	/**
	 * El servicio de Proyecto.
	 */
	@Autowired
	private ProyectoServiceI proyectoServiceI;

	/**
	 * Método predeterminado
	 * @return hello world
	 */
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	/*
	 * VISTAS
	 */

	/**
	 * La lista de empleados completa.
	 * @param model el modelo de la aplicación.
	 * @return empleado.html
	 */
	@GetMapping("/showEmpleadosView")
	public String mostrarEmpleados(Model model) {

		// Obtención de empleados
		final List<Empleado> listaEmpleados = empleadoServiceI.obtenerEmpleados();

		// Carga de datos al modelo
		model.addAttribute("empleadosListView", listaEmpleados);

		return "empleado";
	}
	
	/**
	 * Vista de modificación de un empleado.
	 * @param empleadoDni el DNI del empleado a modificar/actualizar.
	 * @param model el modelo de la aplicación.
	 * @return empleadoModificar.html
	 */
	@GetMapping("/showEmpleadoMod")
	public String mostrarModEmpleado(@RequestParam String empleadoDni, Model model) {

		// Obtención de empleado a modificar
		final Empleado empleadoToMod = empleadoServiceI.obtenerPorDni(empleadoDni);

		// Carga de datos al modelo
		model.addAttribute("empleadoToMod", empleadoToMod);

		return "empleadoModificar";
	}
	
	/**
	 * Muestra una lista de los proyectos donde esta integrado el empleado.
	 * @param empleadoDni el DNI del empleado a mostrar.
	 * @param model el modelo de la aplicación.
	 * @return empleadoProyectos.html
	 */
	@GetMapping("/showEmpleadoProyectos")
	public String mostrarEmpleadoProyectos(@RequestParam String empleadoDni, Model model) {

		// Obtención de empleado a modificar
		final Empleado empleado = empleadoServiceI.obtenerPorDni(empleadoDni);
		List<Proyecto> proyectosElegibles = proyectoServiceI.obtenerProyectos();
		proyectosElegibles.removeAll(empleado.getProyectos());

		// Carga de datos al modelo
		model.addAttribute("empleado", empleado);
		model.addAttribute("proyectosElegibles", proyectosElegibles);

		return "empleadoProyectos";
	}
	
	/**
	 * Se realizar la búsqueda por parámetros y se muestra una lista de empleados filtrados.
	 * @param searchedEmpleado el empleado buscado, sacado del formulario de búsqueda.
	 * @param model el modelo de la aplicación.
	 * @return empleado.html
	 * @throws Exception en caso de que no se haya introducido parámetros o sean incorrectos.
	 */
	@PostMapping("/actSearchEmpleado")
	public String submitBuscarEmpleadoForm(@ModelAttribute Empleado searchedEmpleado, Model model) throws Exception {
		List<Empleado> listaEmpleados = new ArrayList<>();
		if (searchedEmpleado.getDni().isBlank() && searchedEmpleado.getFechaNacimiento() == null && searchedEmpleado.getApellido().isBlank() && searchedEmpleado.getNombre().isBlank()) {
			new Exception("Debes de inidicar (mínimo) un parámetro de búsqueda.");
		} else if (!searchedEmpleado.getDni().isBlank()) {
			//Por DNI, resultado único
			final Empleado empleadoEncontrado = empleadoServiceI.obtenerPorDni(searchedEmpleado.getDni());
			listaEmpleados.add(empleadoEncontrado);
			model.addAttribute("empleadosListView", listaEmpleados);
			return "empleado";
		}
		if (!searchedEmpleado.getNombre().isBlank() && !searchedEmpleado.getApellido().isBlank() && searchedEmpleado.getFechaNacimiento() != null) {
			//Todos los parámetros
			listaEmpleados = empleadoServiceI.obtenerPorNombreYApellidoYFechaNacimiento(searchedEmpleado.getNombre(), searchedEmpleado.getApellido(), searchedEmpleado.getFechaNacimiento());
		} else if (searchedEmpleado.getNombre().isBlank()) {
			//Nombre esta vacio: Apellido, FechaNacimiento o Apellido+FechaNacimiento
			if (!searchedEmpleado.getApellido().isBlank() && searchedEmpleado.getFechaNacimiento() != null) {
				//Apellido+FechaNacimiento
				listaEmpleados = empleadoServiceI.obtenerPorApellidoYFechaNacimiento(searchedEmpleado.getApellido(), searchedEmpleado.getFechaNacimiento());
			} else if (!searchedEmpleado.getApellido().isBlank()) {
				//Apellido
				listaEmpleados = empleadoServiceI.obtenerPorApellido(searchedEmpleado.getApellido());
			} else {
				//FechaNacimiento
				listaEmpleados = empleadoServiceI.obtenerPorFechaNacimiento(searchedEmpleado.getFechaNacimiento());
			}
		} else {
			//Nombre esta completo: Nombre, Nombre+Apellido o Nombre+FechaNacimiento
			if (!searchedEmpleado.getApellido().isBlank()) {
				//Nombre+Apellido
				listaEmpleados = empleadoServiceI.obtenerPorNombreYApellido(searchedEmpleado.getNombre(), searchedEmpleado.getApellido());
			} else if (searchedEmpleado.getFechaNacimiento() != null) {
				//Nombre+FechaNacimiento
				listaEmpleados = empleadoServiceI.obtenerPorNombreYFechaNacimiento(searchedEmpleado.getNombre(), searchedEmpleado.getFechaNacimiento());
			} else {
				//Nombre
				listaEmpleados = empleadoServiceI.obtenerPorNombre(searchedEmpleado.getNombre());
			}
		}
		model.addAttribute("empleadosListView", listaEmpleados);
		return "empleado";
	}
	
	/*
	 * ACCIONES
	 */

	/**
	 * Insertar un nuevo empleado.
	 * @param newEmpleado el empleado a añadir, sacado del formulario.
	 * @param result el resultado de la aplicación.
	 * @return empleado.html
	 * @throws Exception en caso de que los parámetros sean incorrectos.
	 */
	@PostMapping("/actAddEmpleado")
	private String insertarEmpleado(@Valid @ModelAttribute Empleado newEmpleado, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de empleado erróneos");
		} else {

			// Se añade el nuevo empleado
			empleadoServiceI.insertarEmpleado(newEmpleado);
		}

		return "redirect:showEmpleadosView";
	}
	
	/**
	 * Modificar un empleado ya existente.
	 * @param newEmpleado el empleado modificado, sacado del formulario.
	 * @param result el resultado de la aplicación.
	 * @return empleado.html
	 * @throws Exception en caso de que se pasen parámetros incorrectos.
	 */
	@PostMapping("/actModEmpleado")
	private String modificarEmpleado(@Valid @ModelAttribute Empleado newEmpleado, BindingResult result) throws Exception {
		Empleado empleadoToMod = empleadoServiceI.obtenerPorDni(newEmpleado.getDni());
		empleadoToMod.setNombre(newEmpleado.getNombre());
		empleadoToMod.setApellido(newEmpleado.getApellido());
		empleadoToMod.setFechaNacimiento(newEmpleado.getFechaNacimiento());
		if (result.hasErrors()) {
			throw new Exception("Parámetros de empleado erróneos");
		} else {

			// Se añade el nuevo empleado
			empleadoServiceI.actualizarEmpleado(newEmpleado);
		}

		return "redirect:showEmpleadosView";
	}

	/**
	 * Eliminar a un empleado.
	 * @param empleadoDni el DNI del empleado a eliminar.
	 * @param model el modelo de la aplicación.
	 * @return empleado.html
	 */
	@GetMapping("/actDropEmpleado")
	public String eliminarEmpleado(@RequestParam String empleadoDni, Model model) {

		// Eliminación de empleado
		empleadoServiceI.eliminarEmpleadoPorDni(empleadoDni);
		
		return "redirect:showEmpleadosView";

	}
	
	/**
	 * Eliminar a un proyecto de un empleado.
	 * @param empleadoDni el DNI del empleado.
	 * @param proyectoId el ID del proyecto a eliminarle.
	 * @param model el modelo de la aplicación.
	 * @return empleadoProyectos.html
	 */
	@GetMapping("/actDropProyectoEmpleado")
	public String eliminarProyectoDeEmpleado(@RequestParam String empleadoDni, @RequestParam long proyectoId, Model model) {
		Empleado empleado = empleadoServiceI.obtenerPorDni(empleadoDni);
		Proyecto proyectoAEliminar = proyectoServiceI.obtenerPorId(proyectoId);
		empleado.getProyectos().remove(proyectoAEliminar);
		
		// Se actualiza el empleado ahora sin el proyecto seleccionado
		empleadoServiceI.actualizarEmpleado(empleado);
		
		return "redirect:showEmpleadoProyectos?empleadoDni=" + empleado.getDni();

	}
	
	/**
	 * Añadir a un proyecto en un empleado.
	 * @param empleadoDni el DNI del empleado.
	 * @param proyectoId el ID del proyecto a añadirle.
	 * @param model el modelo de la aplicación.
	 * @return EmpleadoProyectos.html
	 */
	@GetMapping("/actAddProyectoEmpleado")
	public String insertarProyectoDeEmpleado(@RequestParam String empleadoDni, @RequestParam long proyectoId, Model model) {
		Empleado empleado = empleadoServiceI.obtenerPorDni(empleadoDni);
		Proyecto proyectoAInsertar = proyectoServiceI.obtenerPorId(proyectoId);
		empleado.getProyectos().add(proyectoAInsertar);
		
		// Se actualiza el empleado ahora con el proyecto seleccionado
		empleadoServiceI.actualizarEmpleado(empleado);
		
		return "redirect:showEmpleadoProyectos?empleadoDni=" + empleado.getDni();

	}
	
}
