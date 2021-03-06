// AuthenticationLoginvalidator.java - Copyright pierr
package edu.bd.myproject.web.authentication.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myproject.web.authentication.beans.AuthenticationBean;
import edu.bd.myproject.web.framework.validators.StringValidator;

/**
 * @author pierr
 *
 */
@Named("authenticationLoginvalidator")
@RequestScoped
public class AuthenticationLoginvalidator extends StringValidator {

	@Inject
	CompteDao comptesDao;

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
