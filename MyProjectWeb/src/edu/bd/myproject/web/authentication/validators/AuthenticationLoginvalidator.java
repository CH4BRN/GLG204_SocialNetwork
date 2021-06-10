// AuthenticationLoginvalidator.java - Copyright pierr
package edu.bd.myproject.web.authentication.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import edu.bd.myproject.web.framework.validators.StringValidator;

/**
 * @author pierr
 *
 */
@FacesValidator(value = "authenticationLoginValidator")
public class AuthenticationLoginvalidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, String value) throws ValidatorException {
		String login = value;
		checkForNull(login);
		checkForEmpty(login);

	}

}
