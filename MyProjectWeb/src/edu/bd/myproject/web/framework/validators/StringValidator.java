package edu.bd.myproject.web.framework.validators;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public abstract class StringValidator implements Serializable, Validator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void checkForEmpty(String value) {
		if (value.isEmpty()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Vous devez entrer une valeur"));
		}
	}

	protected void checkForNull(String value) {
		if (value == null) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Vous devez entrer une valeur"));
		}
	}

}
