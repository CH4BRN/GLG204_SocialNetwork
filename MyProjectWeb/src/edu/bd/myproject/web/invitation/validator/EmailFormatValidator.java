package edu.bd.myproject.web.invitation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator(value = "emailFormatValidator")
public class EmailFormatValidator extends StringValidator {

	private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
		checkForFormat(value);

	}

	private void checkForFormat(String value) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		if (!matcher.find()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le mail n'est pas valide.", null));
		}
	}
}
