package br.com.di.desafiointer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.di.desafiointer.aplicacao.usuario.GerenciarUsuarioService;
import br.com.di.desafiointer.aplicacao.usuario.UsuarioDTO;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;

@RestController
public class UsuarioController {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	private GerenciarUsuarioService usuarioService;
	
	@PostMapping("/cadastrar")
	public String cadastrar() {
		try {
			UsuarioDTO usuario = new UsuarioDTO("Sidney", "sidney@javabh.com");
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			usuarioService.cadastrar(usuario);
			return "Usuario cadastrado com sucesso.";
		} catch (UsuarioException e) {
			return e.getMessage();
		} catch (EmailInvalidoException e) {
			return e.getMessage();
		}
	}
	
	@GetMapping("/pesquisar")
	public UsuarioDTO pesquisar() {
		try {
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			return new UsuarioDTO(usuarioService.pesquisar("sidney@javabh.com"));
		} catch (UsuarioException e) {
			e.printStackTrace();
			return null;
		} catch (EmailInvalidoException e) {
			e.printStackTrace();
			return null;
		}
	}

}
