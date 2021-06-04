package edu.bd.myproject.web.salons.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import edu.bd.myProject.salons.service.SalonService;
import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator("salonNameValidator")
public class SalonNameValidator extends StringValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	SalonService salonService;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, String value) throws ValidatorException {
		checkForNull(value);
		checkForEmpty(value);
	}

}
