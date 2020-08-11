package br.com.di.desafiointer.aplicacao.digitoUnico;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.digitoUnico.NumeroForaDoLimiteException;
import br.com.di.desafiointer.dominio.digitoUnico.ValorNaoNumericoException;

public class CalcularDigitoUnicoService {
	
	public DigitoUnicoDTO calcular(DigitoUnicoDTO digitoUnicoDTO) throws ValorNaoNumericoException, NumeroForaDoLimiteException{
		DigitoUnico digitoUnico = new DigitoUnico();
		digitoUnico.calcular(digitoUnicoDTO.getValor(), digitoUnicoDTO.getNumeroDeVezes());
		return new DigitoUnicoDTO(digitoUnico);
	}

}
