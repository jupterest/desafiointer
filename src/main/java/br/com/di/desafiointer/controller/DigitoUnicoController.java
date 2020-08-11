package br.com.di.desafiointer.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.di.desafiointer.aplicacao.digitoUnico.CalcularDigitoUnicoService;
import br.com.di.desafiointer.aplicacao.digitoUnico.DigitoUnicoDTO;
import br.com.di.desafiointer.infra.ResourceException;

@RestController
@RequestMapping("/digitoUnico")
public class DigitoUnicoController {

	
	@PostMapping
	public ResponseEntity<DigitoUnicoDTO> calcular(@RequestBody @Valid DigitoUnicoDTO digito) {
		try {
			DigitoUnicoDTO digitoUnico = new CalcularDigitoUnicoService().calcular(digito);
			return ResponseEntity.ok(digitoUnico);
		} catch (Exception e) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
}
