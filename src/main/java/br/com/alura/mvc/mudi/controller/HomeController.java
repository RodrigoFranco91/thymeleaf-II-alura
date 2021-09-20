package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
//	Spring cria um EntityManager para nós.
//	@PersistenceContext
//	private EntityManager em;
	
	//Spring injetando um PedidoRepository para nós.
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public String home(Model model) {
		
		//Buscando no banco de dados todos pedidos.
//		Query query = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
//		List<Pedido> pedidos = query.getResultList();
		
		//O sort é para organizar/filtrar a busca
		Sort sort = Sort.by("dataDaEntrega").descending();
		
		//Criando uma paginacao. Primeiro argumento é a pagina inicia e o segundo é a quantidade de itens por pagina
		//Passamos o sort, pois queremos manter a organizacao/filtro ja feito
		PageRequest paginacao = PageRequest.of(0, 5, sort);
		
		//Buscando no banco agora com Repository
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
}
