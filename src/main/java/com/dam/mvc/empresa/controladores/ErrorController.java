package com.dam.mvc.empresa.controladores;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Clase para controlar las excepciones.
 *
 */
@ControllerAdvice
public class ErrorController {
	/**
	 * Controla cualquier excepción lanzada por la aplicación, y la muestra en un mensaje en un .html
	 * @param req HttpServletRequest
	 * @param e la excepci&oacute;n que ha saltado.
	 * @param model el modelo de la aplicación.
	 * @return error.html
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {
		// Respuesta
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}

}
