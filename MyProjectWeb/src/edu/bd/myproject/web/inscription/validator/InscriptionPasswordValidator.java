package edu.bd.myproject.web.inscription.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator(value = "inscriptionPasswordValidator")
public class InscriptionPasswordValidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
		checkForFormat(value);

	}

	private void checkForFormat(String value) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(value);
		if (!matcher.find()) {
			throw new ValidatorException(
					new FacesMessage("Echec validation mot de passe", "Le mot de passe ne répond pas au critères."));
		}
	}

}
