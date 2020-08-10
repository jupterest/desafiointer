package br.com.di.desafiointer.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;
import br.com.di.desafiointer.infra.entity.UsuarioJPA;

@Component("repositorioUsuarioJPA")
@Service
public class RepositorioUsuarioJPA implements RepositorioUsuario{

	@Autowired
	private RepositorioUsuarioSpring repositorioUsuarioSpring;
	
	public RepositorioUsuarioJPA() {
	}

	@Override
	public void cadastrar(Usuario usuario) throws UsuarioException {
		repositorioUsuarioSpring.save(new UsuarioJPA(usuario));
	}

	@Override
	public void editar(Usuario usuario) throws UsuarioException {
		cadastrar(usuario);
	}

	@Override
	public void excluir(Usuario usuario) throws UsuarioException {
		repositorioUsuarioSpring.delete(new UsuarioJPA(usuario));
	}

	@Override
	public Usuario pesquisar(Email email) throws UsuarioException {
		UsuarioJPA usuarioJPA = repositorioUsuarioSpring.findByEmail(email.getEndereco());
		try {
			return usuarioJPA.toUsuario();
		} catch (EmailInvalidoException e) {
			throw new UsuarioException(e);
		}
	}

}
