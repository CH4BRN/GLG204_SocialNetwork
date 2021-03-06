package edu.bd.myproject.web.inscription.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myproject.web.framework.validators.StringValidator;

@Named("loginValidator")
@RequestScoped
public class LoginValidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	CompteDao comptesDao;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
		checkForExisting(value);
	}

	private void checkForExisting(String value) {
		try {
			comptesDao.obtenirParLogin(value);
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("Echec validation login", "Le login existe d�j�."));

		}
	}

}
