package edu.bd.myproject.web.inscription.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator(value = "inscriptionLoginValidator")
public class InscriptionLoginValidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
	}

}
