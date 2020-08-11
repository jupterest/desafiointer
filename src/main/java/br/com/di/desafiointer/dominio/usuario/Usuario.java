package br.com.di.desafiointer.dominio.usuario;

import java.util.Arrays;
import java.util.List;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;

public class Usuario {

	private Integer id;
	
	private String nome;
	
	private Email email;
	private List<DigitoUnico> resultados;
	
	public Usuario() {
	}
	
	public Usuario(Integer id) {
		super();
		this.id = id;
	}
	
	public Usuario(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Usuario(Integer id, String nome, Email email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}



	public void addResultado(DigitoUnico resultado) throws ResultadoInvalidoException {
		if(resultado == null || 
			resultado.getValor() == null || 
			resultado.getValor().trim().length()==0) {
				throw new ResultadoInvalidoException();
		}
		
		if(resultados==null) {
			resultados = Arrays.asList(resultado);
		}else {
			resultados.add(resultado);
		}
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Email getEmail() {
		return email;
	}


	public void setEmail(Email email) {
		this.email = email;
	}


	public List<DigitoUnico> getResultados() {
		return resultados;
	}


	public void setResultados(List<DigitoUnico> resultados) {
		this.resultados = resultados;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	

}
