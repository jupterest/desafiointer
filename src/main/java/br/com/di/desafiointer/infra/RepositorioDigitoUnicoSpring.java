package br.com.di.desafiointer.infra;

import org.springframework.data.repository.CrudRepository;

import br.com.di.desafiointer.infra.entity.DigitoUnicoJPA;

public interface RepositorioDigitoUnicoSpring extends CrudRepository<DigitoUnicoJPA, Integer>{
	

}
