package br.com.di.desafiointer.aplicacao.usuario;

import java.util.Optional;

import br.com.di.desafiointer.controller.NumberUtil;
import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;
import br.com.di.desafiointer.infra.ResourceBundleWrapper;

public class GerenciarUsuarioService {
	
	private RepositorioUsuario repositorioUsuario;
	
	public GerenciarUsuarioService(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}
	
	public void cadastrar(UsuarioDTO usuario) throws UsuarioException, EmailInvalidoException{
		
		if( Optional.ofNullable(usuario).isEmpty() ) {
			throw new UsuarioException(ResourceBundleWrapper.getMessage("msg.erro.objeto.obrigatorio", "Usuario"));
		}
		
		repositorioUsuario.cadastrar(new Usuario(
				//UUID.randomUUID().toString(),
				NumberUtil.generateId(),
				usuario.getNome(),
				new Email(usuario.getEmail())
		));
	}
	
	public void editar(UsuarioDTO usuario) throws UsuarioException, EmailInvalidoException{
		
		if( Optional.ofNullable(usuario).isEmpty() ) {
			throw new UsuarioException(ResourceBundleWrapper.getMessage("msg.erro.objeto.obrigatorio", "Usuario"));
		}
		
		repositorioUsuario.editar(new Usuario(
				usuario.getId(),
				usuario.getNome(),
				new Email(usuario.getEmail())
		));
	}
	
	public void excluir(UsuarioDTO usuario) throws UsuarioException{
		
		if( Optional.ofNullable(usuario).isEmpty() ) {
			throw new UsuarioException(ResourceBundleWrapper.getMessage("msg.erro.objeto.obrigatorio", "Usuario"));
		}
		
		repositorioUsuario.excluir(new Usuario(
				usuario.getId()
		));
	}
	
	public Usuario pesquisar(String email) throws UsuarioException, EmailInvalidoException{
		Email emailObj = new Email(email);
		return repositorioUsuario.pesquisar(emailObj);
	}
	

}
