package edu.bd.myproject.web.inscription.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import edu.bd.myproject.web.framework.validators.StringValidator;

@Named("passwordValidator")
@RequestScoped
public class PasswordValidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Pattern VALID_PASSWORD_ADDRESS_REGEX = Pattern
			.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$", Pattern.CASE_INSENSITIVE);

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
		checkForFormat(value);

	}

	private void checkForFormat(String value) {
		Matcher matcher = VALID_PASSWORD_ADDRESS_REGEX.matcher(value);
		if (!matcher.find()) {
			throw new ValidatorException(
					new FacesMessage("Echec validation mot de passe", "Le mot de passe ne r�pond pas au crit�res."));
		}
	}

}
