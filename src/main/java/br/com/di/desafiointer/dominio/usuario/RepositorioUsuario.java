package br.com.di.desafiointer.dominio.usuario;

import java.util.List;

import br.com.di.desafiointer.dominio.RegistroNaoEncontradoException;

public interface RepositorioUsuario {
	
	public Usuario cadastrar(Usuario usuario) throws UsuarioException; 
	
	public Usuario adicionarResultados(Usuario usuario) throws UsuarioException, RegistroNaoEncontradoException;
	
	public void editar(Usuario usuario) throws UsuarioException, RegistroNaoEncontradoException;
	
	public void excluir(Usuario usuario) throws UsuarioException, RegistroNaoEncontradoException;
	
	public Usuario pesquisar(Email email) throws UsuarioException, RegistroNaoEncontradoException;
	
	public List<Usuario> retornarTodos() throws UsuarioException;

}
