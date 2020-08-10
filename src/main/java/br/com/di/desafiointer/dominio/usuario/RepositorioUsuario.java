package br.com.di.desafiointer.dominio.usuario;

public interface RepositorioUsuario {
	
	public void cadastrar(Usuario usuario) throws UsuarioException; 
	
	public void editar(Usuario usuario) throws UsuarioException;
	
	public void excluir(Usuario usuario) throws UsuarioException;
	
	public Usuario pesquisar(Email email) throws UsuarioException;

}
