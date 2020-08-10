package br.com.di.desafiointer.dominio.usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.di.desafiointer.infra.ResourceBundleWrapper;

public class Email {
	
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public Email(String endereco) throws EmailInvalidoException {
		
		if( !isValidEmailAddressRegex(endereco) ){
			throw new EmailInvalidoException(ResourceBundleWrapper.getMessage("msg.erro.email.invalido", endereco));
		}
		
		this.endereco = endereco;
	}
	
	private boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

}
