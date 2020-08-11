package br.com.di.desafiointer.aplicacao.usuario;

import java.util.List;

import br.com.di.desafiointer.dominio.RegistroNaoEncontradoException;
import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;

public class GerenciarUsuarioService {
	
	private RepositorioUsuario repositorioUsuario;
	
	public GerenciarUsuarioService(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}
	
	public UsuarioDTO cadastrar(UsuarioDTO usuario) throws UsuarioException, EmailInvalidoException{
		
		UsuarioDTO usuDTO = new UsuarioDTO(
				repositorioUsuario.cadastrar(
						new Usuario(
							usuario.getId(),
							usuario.getNome(),
							new Email(usuario.getEmail())
						)
				)
		);
		return usuDTO;
	}
	
	public void editar(UsuarioDTO usuario) throws UsuarioException, EmailInvalidoException, RegistroNaoEncontradoException{
		
		repositorioUsuario.editar(new Usuario(
				usuario.getId(),
				usuario.getNome(),
				new Email(usuario.getEmail())
		));
	}
	
	public void excluir(UsuarioDTO usuario) throws UsuarioException, RegistroNaoEncontradoException{
		
		repositorioUsuario.excluir(new Usuario(
				usuario.getId()
		));
	}
	
	public Usuario pesquisar(String email) throws UsuarioException, EmailInvalidoException, RegistroNaoEncontradoException{
		Email emailObj = new Email(email);
		return repositorioUsuario.pesquisar(emailObj);
	}
	
	public List<Usuario> retornarTodos() throws UsuarioException{
		return repositorioUsuario.retornarTodos();
	}
	

}
