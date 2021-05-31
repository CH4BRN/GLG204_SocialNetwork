package edu.bd.myproject.web.authentication.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myproject.web.authentication.beans.AuthenticationBean;
import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator(value = "authenticationPasswordValidator")
public class AuthenticationPasswordValidator extends StringValidator {

	@Inject
	CompteDao comptesDao;
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
		checkForNull(password);
		checkForEmpty(password);
	}

}
