package br.com.alura.mvc.mudi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
//	Enviando dados para HTML via HttpServletRequest
//	@GetMapping("/hello")
//	public String hello(HttpServletRequest request) {
//		
//		//O primeiro atributo é o identificador do valor enviado para HTML. É através dele que vamos
//		// acessar o valor.
//		request.setAttribute("nome", "Rodrigo");
//		
//		return "hello";
//	}
	
	
//	Enviando dados para HTML via Model
	@GetMapping("/hello")
	public String hello(Model model) {
		
		//O primeiro atributo é o identificador do valor enviado para HTML. É através dele que vamos
		// acessar o valor.
		model.addAttribute("nome", "Rodrigo Franco");
		
		return "hello";
	}
	

}
