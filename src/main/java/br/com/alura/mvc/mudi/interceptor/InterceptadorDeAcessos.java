package br.com.alura.mvc.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InterceptadorDeAcessos extends HandlerInterceptorAdapter{

	//Poderiamos salvar o acesso no banco de dados.
	//Vamos fazer essa lista para simular um banco de dados.
	public static List<Acesso> acessos = new ArrayList<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Acesso acesso = new Acesso();
		//Pegando o path acessado.
		acesso.path = request.getRequestURI();
		//Pegando a data de agora, ou seja, da requisicao.
		acesso.data = LocalDateTime.now();
		
		request.setAttribute("acesso", acesso);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		//Recuperando o Acesso, que foi criado no preHandle()
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.duracao = 	Duration.between(acesso.data, LocalDateTime.now());
		
		//"Salvando" o acesso.
		acessos.add(acesso);
	}
	
	public static class Acesso{
		
		private String path;
		private LocalDateTime data;
		private Duration duracao;
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public LocalDateTime getData() {
			return data;
		}
		public void setData(LocalDateTime data) {
			this.data = data;
		}
		public Duration getDuracao() {
			return duracao;
		}
		public void setDuracao(Duration duracao) {
			this.duracao = duracao;
		}
		
		
	}
	
}
