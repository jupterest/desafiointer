package br.com.di.desafiointer.aplicacao.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.RepositorioUsuario;
import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.dominio.usuario.UsuarioException;
import br.com.di.desafiointer.infra.RepositorioUsuarioJPA;

@DataJpaTest
public class GerenciarUsuarioServiceTest {

	@Autowired
	private TestEntityManager entityManager;

	private RepositorioUsuario repositorioUsuario;
	
	@Test
	public void testCreate() {
		
		repositorioUsuario = new RepositorioUsuarioJPA();
		
		String nome = "Sidney Senna";
		String email = "sidney.senna@gmail.com";
		java.util.UUID dd = java.util.UUID.randomUUID();
		
		int i = dd.variant();
		
		try {
			Usuario usuario = new Usuario();
			usuario.setId(dd.variant());
			usuario.setNome(nome);
			usuario.setEmail(new Email(email));
			repositorioUsuario.cadastrar(usuario);
			
			Usuario usuarioPesquisado = repositorioUsuario.pesquisar(new Email(email));
			
			assertEquals(usuario.getNome(), usuarioPesquisado.getNome());
			
		} catch (EmailInvalidoException e) {
			fail(e.getMessage());
		} catch (UsuarioException e) {
			fail(e.getMessage());
		}

		//
	}
	

}
