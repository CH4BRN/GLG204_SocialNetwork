// EmailValidator.java - Copyright pierr
package edu.bd.myproject.web.inscription.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myproject.web.framework.validators.StringValidator;

/**
 * @author pierr
 *
 */
@Named("emailValidator")
@RequestScoped
public class EmailValidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	CompteDao comptesDao;

	private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
		checkForFormat(value);
		checkForExisting(value);

	}

	private void checkForFormat(String value) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		if (!matcher.find()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le mail n'est pas valide.", null));
		}
	}

	private void checkForExisting(String value) {
		try {
			comptesDao.obtenirParEmail(value);
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("Echec validation email", "Le login existe déjà."));

		}
	}
}
