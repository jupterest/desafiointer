package br.com.di.desafiointer.aplicacao.usuario;

import java.util.ArrayList;
import java.util.List;

import br.com.di.desafiointer.dominio.RegistroNaoEncontradoException;
import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;

public class AssociarResultadoAoUsuarioService {

	private RepositorioUsuario repositorioUsuario;

	public AssociarResultadoAoUsuarioService(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	public void associar(UsuarioDTO usuario) throws UsuarioException, EmailInvalidoException, RegistroNaoEncontradoException{

		List<DigitoUnico> resultados =  new ArrayList<DigitoUnico>();

		if(usuario.getResultados()!=null) {
			usuario.getResultados().forEach( d -> resultados.add( d.converter() ));
		}

		repositorioUsuario.adicionarResultados(
				new Usuario(
						usuario.getId(),
						usuario.getNome(),
						new Email(usuario.getEmail()),
						resultados
						)
				);
	}

}
