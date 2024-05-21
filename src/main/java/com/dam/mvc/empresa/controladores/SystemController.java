package com.dam.mvc.empresa.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador principal de vistas.
 *
 */
@Controller
@RequestMapping("*")
public class SystemController {
	
	/**
	 * Redireccionamiento principal
	 * @return index.html
	 */
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	/*
	 * EMPLEADOS
	 */
	
	/**
	 * Va a la lista de empleados.
	 * @return empleado.html
	 */
	//Listado
	@GetMapping("/empleadosView")
	public String redirectToEmpleadoController() {
		return "redirect:showEmpleadosView";
	}
	
	/**
	 * Va a la vista de inserción de empleados.
	 * @return empleadoInsertar.html
	 */
	//Insertar
	@GetMapping("/newEmpleadoView")
	public String redirectToNewEmpleadoTemplate() {
		return "empleadoInsertar";
	}
	
	/*
	 * PROYECTOS
	 */
	
	/**
	 * Va a la lista de proyectos.
	 * @return proyecto.html
	 */
	//Listado
	@GetMapping("/proyectosView")
	public String redirectToProyectoController() {
		return "redirect:showProyectosView";
	}
	
	/**
	 * Va a la vista de insercción de proyectos.
	 * @return proyectoInsertar.html
	 */
	//Insertar
	@GetMapping("/newProyectoView")
	public String redirectToNewProyectoTemplate() {
		return "proyectoInsertar";
	}
	
}
