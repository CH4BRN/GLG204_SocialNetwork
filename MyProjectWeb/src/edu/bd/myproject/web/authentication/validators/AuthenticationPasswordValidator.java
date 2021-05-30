package edu.bd.myproject.web.authentication.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myproject.web.authentication.beans.AuthenticationBean;
import edu.bd.myproject.web.framework.validators.StringValidator;

@Named("authenticationPasswordValidator")
@RequestScoped
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

		String login = authenticationBean.getLogin();
		String password = authenticationBean.getPassword();

		checkForNull(login);
		checkForEmpty(login);

		checkForNull(password);
		checkForEmpty(password);

		checkForAuthent(login, password);
	}

	private void checkForAuthent(String login, String password) {
		try {
			Compte compte = comptesDao.obtenirParLogin(login);
			if (!compte.getMotDePasse().equals(password)) {
				throw new ValidatorException(new FacesMessage("Echec authentification"));
			}
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("ECHEC"));

		}
	}

}
