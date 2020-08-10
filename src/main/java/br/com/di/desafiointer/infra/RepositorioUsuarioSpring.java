package br.com.di.desafiointer.infra;

import org.springframework.data.repository.CrudRepository;

import br.com.di.desafiointer.infra.entity.UsuarioJPA;

public interface RepositorioUsuarioSpring extends CrudRepository<UsuarioJPA, Integer>{
	
	public UsuarioJPA findByEmail(String email);

}
