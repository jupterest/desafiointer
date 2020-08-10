package br.com.di.desafiointer.infra;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleWrapper {

	private static ResourceBundle resourceBundle;
	
	static {
		resourceBundle =
			      ResourceBundle.getBundle("messages_pt_BR", new Locale("pt", "BR"));
	}
	
	public static String getMessage(String key, Object... params) {
		try {
			
			String pattern = resourceBundle.getString(key);
			String message = MessageFormat.format(pattern, params);
			
			return message;
		} catch (MissingResourceException e) {
			return "?".concat(key).concat("?");
		}
	}

}
