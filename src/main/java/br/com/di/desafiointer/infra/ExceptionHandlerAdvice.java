package br.com.di.desafiointer.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(ResourceException.class)
    public ResponseEntity<MensagemErro> handleException(ResourceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new MensagemErro(e.getMessage()));
    }     
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<MensagemErro> handle(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(e.getMostSpecificCause().getMessage()));
	}
	
}
