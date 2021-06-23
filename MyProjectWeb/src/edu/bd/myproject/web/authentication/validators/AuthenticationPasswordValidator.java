package edu.bd.myproject.web.authentication.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myproject.web.authentication.beans.AuthenticationBean;
import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator(value = "authenticationPasswordValidator")
public class AuthenticationPasswordValidator extends StringValidator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Named("authenticationBean")
	AuthenticationBean authenticationBean;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		String password = value;
		try {
			checkForNull(password);
			checkForEmpty(password);
		} catch (Exception e) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Vous devez entrer une valeur"));
		}

	}

}
