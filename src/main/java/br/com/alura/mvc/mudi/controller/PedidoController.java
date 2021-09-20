package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}

	//A classe RequisicaoNovoPedido será uma classe DTO.
	//Os atributos RequisicaoNovoPedido deve ter atributos com o mesmo nome dado ao name no formulário
	//@Valid é para ocorrer a validação das anotacoes do BeanValidation
	//O BindingResult é quem terá o resultado da validação (se hou ou não erro)
	@PostMapping("/novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		
		//Se tiver erro, volte para tela do formulario
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
			
		//Pegando Username do Usuário Logado. Só pegamos o username do mesmo!
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userRepository.findByUsername(userName);
		
		//Transformando um DTO em Entity
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
		repository.save(pedido);

		return "redirect:/home";
	}
}
