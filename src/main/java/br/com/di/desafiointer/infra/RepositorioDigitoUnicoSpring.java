package br.com.di.desafiointer.infra;

import org.springframework.data.repository.CrudRepository;

import br.com.di.desafiointer.infra.entity.DigitoUnicoJPA;
import br.com.di.desafiointer.infra.entity.UsuarioJPA;

public interface RepositorioDigitoUnicoSpring extends CrudRepository<DigitoUnicoJPA, Integer>{
	
	public void deleteByUsuario(UsuarioJPA usuario);

}
