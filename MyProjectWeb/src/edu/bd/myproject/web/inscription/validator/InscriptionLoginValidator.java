package edu.bd.myproject.web.inscription.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator(value = "inscriptionLoginValidator")
public class InscriptionLoginValidator extends StringValidator {

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
			return;
		}
		throw new ValidatorException(new FacesMessage("Echec validation login", "Le login existe déjà."));
	}

}
