package edu.bd.myproject.web.posts.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import edu.bd.myproject.web.framework.validators.StringValidator;

@FacesValidator("postValidator")
public class PostValidator extends StringValidator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, String arg2) throws ValidatorException {
		checkForNull(arg2);
		checkForEmpty(arg2);		
	}

}
