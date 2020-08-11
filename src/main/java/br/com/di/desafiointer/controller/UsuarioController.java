package br.com.di.desafiointer.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.di.desafiointer.aplicacao.usuario.AssociarResultadoAoUsuarioService;
import br.com.di.desafiointer.aplicacao.usuario.GerenciarUsuarioService;
import br.com.di.desafiointer.aplicacao.usuario.UsuarioDTO;
import br.com.di.desafiointer.dominio.RegistroNaoEncontradoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;
import br.com.di.desafiointer.infra.ResourceException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	private GerenciarUsuarioService usuarioService;
	
	private AssociarResultadoAoUsuarioService associarResultadoAoUsuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioDTO usuario, UriComponentsBuilder uriBuilder) {
		try {
			
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			usuario = usuarioService.cadastrar(usuario);
			
			URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
			
			return ResponseEntity.created(uri).body(usuario);
		} catch (Exception e) {
			throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}
	
	@PostMapping("/addResultado")
	@Transactional
	public ResponseEntity<UsuarioDTO> addResultado(@RequestBody @Valid UsuarioDTO usuario) {
		try {
			
			preencherUsuarioDosResultados(usuario);			
			associarResultadoAoUsuarioService = new AssociarResultadoAoUsuarioService(repositorioUsuario);
			associarResultadoAoUsuarioService.associar(usuario);
			removerUsuarioDosResultados(usuario);
			
			return ResponseEntity.ok(usuario);
			
		} catch (RegistroNaoEncontradoException e) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}
	
	private void preencherUsuarioDosResultados(@Valid UsuarioDTO usuario) {
		usuario.getResultados().forEach(e -> e.setUsuario(usuario));
	}

	private void removerUsuarioDosResultados(@Valid UsuarioDTO usuario) {
		usuario.getResultados().forEach(e -> e.setUsuario(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail())));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> editar(@PathVariable Integer id, @RequestBody @Valid UsuarioDTO usuario) {
		try {
						
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			usuario.setId(id);
			usuarioService.editar(usuario);
			
			return ResponseEntity.ok(usuario);
		} catch (RegistroNaoEncontradoException e) {
			throw new ResourceException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Integer id) {
		try {
			
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setId(id);
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			usuarioService.excluir(usuario);
			
			return ResponseEntity.ok().build();
		} catch (RegistroNaoEncontradoException e) {
			throw new ResourceException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}
	
	@GetMapping("/{email}")
	public UsuarioDTO pesquisar(@PathVariable String email) {
		try {
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			return new UsuarioDTO(usuarioService.pesquisar(email));
		} catch (RegistroNaoEncontradoException e) {
			throw new ResourceException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@GetMapping("/todos")
	public List<UsuarioDTO> listarTodos(){
		try {
			usuarioService = new GerenciarUsuarioService(repositorioUsuario);
			return UsuarioDTO.converter(usuarioService.retornarTodos());
		} catch (UsuarioException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
