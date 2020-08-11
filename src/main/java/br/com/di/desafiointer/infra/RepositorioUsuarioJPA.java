package br.com.di.desafiointer.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.di.desafiointer.dominio.RegistroNaoEncontradoException;
import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;
import br.com.di.desafiointer.infra.entity.DigitoUnicoJPA;
import br.com.di.desafiointer.infra.entity.UsuarioJPA;

@Component("repositorioUsuarioJPA")
@Service
public class RepositorioUsuarioJPA implements RepositorioUsuario{

	@Autowired
	private RepositorioUsuarioSpring repositorioUsuarioSpring;
	
	@Autowired
	private RepositorioDigitoUnicoSpring repositorioDigitoUnicoSpring;

	public RepositorioUsuarioJPA() {
	}

	@Override
	public Usuario cadastrar(Usuario usuario) throws UsuarioException {

		UsuarioJPA usuJPA = new UsuarioJPA(usuario);
		List<DigitoUnicoJPA> resultados = usuJPA.getResultados();
		usuJPA.setResultados(null);
		
		usuJPA = repositorioUsuarioSpring.save(usuJPA);
		
		preencherUsuarioDosResultados(usuJPA, resultados);
		
		repositorioDigitoUnicoSpring.saveAll(resultados);
		
		usuario.setId(usuJPA.getId());
		
		return usuario;
	}

	@Override
	public void editar(Usuario usuario) throws UsuarioException, RegistroNaoEncontradoException {

		Optional<UsuarioJPA> optional = repositorioUsuarioSpring.findById(usuario.getId());

		if (optional.isPresent()) {
			UsuarioJPA usuUpdate = optional.get();
			usuUpdate.setEmail(usuario.getEmail().getEndereco());
			usuUpdate.setNome(usuario.getNome());
		}else {
			throw new RegistroNaoEncontradoException(ResourceBundleWrapper.getMessage("msg.erro.registro.naoencontrado"));
		}
	}

	@Override
	public void excluir(Usuario usuario) throws UsuarioException, RegistroNaoEncontradoException {

		Optional<UsuarioJPA> optional = repositorioUsuarioSpring.findById(usuario.getId());

		if (optional.isPresent()) {
			UsuarioJPA usuUpdate = optional.get();
			repositorioUsuarioSpring.delete(usuUpdate);
		}else {
			throw new RegistroNaoEncontradoException(ResourceBundleWrapper.getMessage("msg.erro.registro.naoencontrado"));
		}

	}

	@Override
	public Usuario pesquisar(Email email) throws UsuarioException, RegistroNaoEncontradoException  {
		UsuarioJPA usuarioJPA = repositorioUsuarioSpring.findByEmail(email.getEndereco());
		
		if(usuarioJPA == null) {
			throw new RegistroNaoEncontradoException(ResourceBundleWrapper.getMessage("msg.erro.registro.naoencontrado"));
		}
		
		try {
			return usuarioJPA.toUsuario();
		} catch (EmailInvalidoException e) {
			throw new UsuarioException(e);
		}
	}

	@Override
	public List<Usuario> retornarTodos() throws UsuarioException {
		return UsuarioJPA.converter(repositorioUsuarioSpring.findAll());
	}
	
	private void preencherUsuarioDosResultados(UsuarioJPA usuJPA, List<DigitoUnicoJPA> resultados) {
		resultados.forEach(e -> e.setUsuario(usuJPA));
	}

}
