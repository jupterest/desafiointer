package br.com.di.desafiointer.aplicacao.usuario;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;

public interface AssociarResultadoAoUsuarioService {
	
	public void associar(Usuario usuario, DigitoUnico digitoUnico) throws UsuarioException;

}
