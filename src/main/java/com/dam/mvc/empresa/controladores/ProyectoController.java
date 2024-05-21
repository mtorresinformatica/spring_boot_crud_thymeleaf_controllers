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
 * Controlador referente a las vistas y acciones de Proyecto
 *
 */
@Controller
public class ProyectoController {
	
	/**
	 * El servicio de Proyecto.
	 */
	@Autowired
	private ProyectoServiceI proyectoServiceI;
	
	/**
	 * El servicio de Empleado.
	 */
	@Autowired
	private EmpleadoServiceI empleadoServiceI;
	
	/**
	 * Método predeterminado
	 * @return hello world
	 */
	@RequestMapping("/homeProyecto")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	/*
	 * VISTAS
	 */
	
	/**
	 * La lista de todos los proyectos.
	 * @param model el modelo de la aplicación.
	 * @return proyecto.html
	 */
	@GetMapping("/showProyectosView")
	public String mostrarProyectos(Model model) {

		// Obtención de vehículos
		final List<Proyecto> listaProyectos = proyectoServiceI.obtenerProyectos();

		// Carga de datos al modelo
		model.addAttribute("proyectosListView", listaProyectos);

		return "proyecto";
	}
	
	/**
	 * La vista de modificación de un proyecto.
	 * @param proyectoId la ID del proyecto a modificar.
	 * @param model el modelo de la aplicación.
	 * @return proyectoModificar.html
	 */
	@GetMapping("/showProyectoMod")
	public String mostrarModProyecto(@RequestParam String proyectoId, Model model) {
		Long realId = Long.parseLong(proyectoId);
		// Obtención de empleado a modificar
		final Proyecto proyectoToMod = proyectoServiceI.obtenerPorId(realId);

		// Carga de datos al modelo
		model.addAttribute("proyectoToMod", proyectoToMod);

		return "proyectoModificar";
	}
	
	/**
	 * La vista de empleados pertenecientes a un proyecto.
	 * @param proyectoId el ID del proyecto del que visualizar los empleados miembro.
	 * @param model el modelo de la aplicación.
	 * @return proyectoIntegrantes.html
	 */
	@GetMapping("/showProyectoEmpleados")
	public String mostrarProyectoEmpleados(@RequestParam long proyectoId, Model model) {

		// Obtención de proyecto
		final Proyecto proyecto = proyectoServiceI.obtenerPorId(proyectoId);
		List<Empleado> empleadosElegibles = empleadoServiceI.obtenerEmpleados();
		empleadosElegibles.removeAll(proyecto.getEmpleados());
		
		// Carga de datos al modelo
		model.addAttribute("proyecto", proyecto);
		model.addAttribute("empleadosElegibles", empleadosElegibles);

		return "proyectoIntegrantes";
	}
	
	/**
	 * Búsqueda de proyectos por parámetros, para visualizarse en una lista.
	 * @param searchedProyecto el proyecto con los parámetros a buscar, sacado del formulario.
	 * @param model el modelo de la aplicación.
	 * @return proyecto.html
	 * @throws Exception en caso de falta de parámetros o parámetros incorrectos.
	 */
	@PostMapping("/actSearchProyecto")
	public String submitBuscarProyectoForm(@ModelAttribute Proyecto searchedProyecto, Model model) throws Exception {
		List<Proyecto> listaProyectos = new ArrayList<>();
		if (searchedProyecto.getFechaInicio() == null && searchedProyecto.getTitulo().isBlank() && searchedProyecto.getFechaFin() == null) {
			new Exception("Debes de inidicar (mínimo) un parámetro de búsqueda.");
		}
		if (searchedProyecto.getFechaFin() != null && !searchedProyecto.getTitulo().isBlank() && searchedProyecto.getFechaInicio() != null) {
			//Todos los parámetros
			listaProyectos = proyectoServiceI.obtenerPorTituloAndFechaInicioAndFechaFin(searchedProyecto.getTitulo(), searchedProyecto.getFechaInicio(), searchedProyecto.getFechaFin());
		} else if (searchedProyecto.getFechaFin() == null) {
			//FechaFin esta vacio: Titulo, FechaInicio o Titulo+FechaInicio
			if (!searchedProyecto.getTitulo().isBlank() && searchedProyecto.getFechaInicio() != null) {
				//Titulo+FechaInicio
				listaProyectos = proyectoServiceI.obtenerPorTituloAndFechaInicio(searchedProyecto.getTitulo(), searchedProyecto.getFechaInicio());
			} else if (!searchedProyecto.getTitulo().isBlank()) {
				//Titulo
				listaProyectos = proyectoServiceI.obtenerPorTitulo(searchedProyecto.getTitulo());
			} else {
				//FechaInicio
				listaProyectos = proyectoServiceI.obtenerPorFechaInicio(searchedProyecto.getFechaInicio());
			}
		} else {
			//FechaFin esta completo: FechaFin, FechaFin+Titulo o FechaFin+FechaInicio
			if (!searchedProyecto.getTitulo().isBlank()) {
				//FechaFin+Titulo
				listaProyectos = proyectoServiceI.obtenerPorTituloAndFechaFin(searchedProyecto.getTitulo(), searchedProyecto.getFechaFin());
			} else if (searchedProyecto.getFechaInicio() != null) {
				//FechaFin+FechaInicio
				listaProyectos = proyectoServiceI.obtenerPorFechaInicioAndFechaFin(searchedProyecto.getFechaInicio(), searchedProyecto.getFechaFin());
			} else {
				//FechaFin
				listaProyectos = proyectoServiceI.obtenerPorFechaFin(searchedProyecto.getFechaFin());
			}
		}
		model.addAttribute("proyectosListView", listaProyectos);
		return "proyecto";
	}
	
	/*
	 * ACCIONES
	 */
	
	/**
	 * Insertar un proyecto nuevo
	 * @param newProyecto el nuevo proyecto a añadir.
	 * @param result el resultado de la aplicación.
	 * @return proyecto.html
	 * @throws Exception en caso de falta de parámetros o parámetros incorrectos.
	 */
	@PostMapping("/actAddProyecto")
	private String insertarProyecto(@Valid @ModelAttribute Proyecto newProyecto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de proyecto erróneos");
		} else {

			// Se añade el nuevo proyecto
			proyectoServiceI.insertarProyecto(newProyecto);
		}

		return "redirect:showProyectosView";
	}
	
	/**
	 * Eliminar un proyecto.
	 * @param proyectoId la ID del proyecto a eliminar.
	 * @param model el modelo de la aplicación.
	 * @return proyecto.html
	 */
	@GetMapping("/actDropProyecto")
	public String eliminarProyecto(@RequestParam String proyectoId, Model model) {

		// Eliminación de proyecto
		proyectoServiceI.eliminarProyectoPorId(Long.valueOf(proyectoId));

		return "redirect:showProyectosView";

	}
	
	/**
	 * Modificación de un proyecto.
	 * @param newProyecto el proyecto a modificar con los datos nuevos.
	 * @param result el resultado de la aplicación.
	 * @return proyecto.html
	 * @throws Exception en caso de falta de parámetros o parámetros incorrectos.
	 */
	@PostMapping("/actModProyecto")
	private String modificarProyecto(@Valid @ModelAttribute Proyecto newProyecto, BindingResult result) throws Exception {
		Proyecto proyectoToMod = proyectoServiceI.obtenerPorId(newProyecto.getId());
		proyectoToMod.setTitulo(newProyecto.getTitulo());
		proyectoToMod.setFechaInicio(newProyecto.getFechaInicio());
		proyectoToMod.setFechaFin(newProyecto.getFechaFin());
		proyectoToMod.setDescripcion(newProyecto.getDescripcion());
		if (result.hasErrors()) {
			throw new Exception("Parámetros de proyecto erróneos");
		} else {

			// Se añade el nuevo proyecto
			proyectoServiceI.actualizarProyecto(newProyecto);
		}

		return "redirect:showProyectosView";
	}
	
	/**
	 * Eliminar un empleado de un proyecto.
	 * @param proyectoId la ID del proyecto.
	 * @param empleadoDni el DNI del empleado a eliminar del proyecto.
	 * @param model el modelo de la aplicación.
	 * @return proyectoIntegrantes.html
	 */
	@GetMapping("/actDropEmpleadoProyecto")
	public String eliminarEmpleadoDeProyecto(@RequestParam long proyectoId, @RequestParam String empleadoDni, Model model) {
		Proyecto proyecto = proyectoServiceI.obtenerPorId(proyectoId);
		Empleado empleadoAEliminar = empleadoServiceI.obtenerPorDni(empleadoDni);
		proyecto.getEmpleados().remove(empleadoAEliminar);
		
		// Se actualiza el empleado ahora sin el proyecto seleccionado
		proyectoServiceI.actualizarProyecto(proyecto);
		
		return "redirect:showProyectoEmpleados?proyectoId=" + proyecto.getId();

	}
	
	/**
	 * Agregar un empleado a un proyecto.
	 * @param proyectoId la ID del proyecto.
	 * @param empleadoDni el DNI del empleado a agregar al proyecto.
	 * @param model el modelo de la aplicación.
	 * @return proyectoIntegrantes.html
	 */
	@GetMapping("/actAddEmpleadoProyecto")
	public String insertarEmpleadoDeProyecto(@RequestParam long proyectoId, @RequestParam String empleadoDni, Model model) {
		Proyecto proyecto = proyectoServiceI.obtenerPorId(proyectoId);
		Empleado empleadoAInsertar = empleadoServiceI.obtenerPorDni(empleadoDni);
		proyecto.getEmpleados().add(empleadoAInsertar);
		
		// Se actualiza el proyecto ahora con el empleado seleccionado
		proyectoServiceI.actualizarProyecto(proyecto);
		
		return "redirect:showProyectoEmpleados?proyectoId=" + proyecto.getId();

	}
}
