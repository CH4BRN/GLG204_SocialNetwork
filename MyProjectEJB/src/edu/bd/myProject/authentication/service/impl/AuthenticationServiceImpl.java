// AuthenticationServiceImpl.java - Copyright pierr
package edu.bd.myProject.authentication.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.authentication.service.AuthenticationService;
import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceA;
import edu.bd.myProject.core.service.ServiceU;

/**
 * @author pierr
 *
 */
@Stateless
public class AuthenticationServiceImpl implements AuthenticationService {

	@Inject
	CompteDao comptesDao;

	@Inject
	@ServiceA
	CoreService coreAdminService;

	@Inject
	@ServiceU
	CoreService coreUserService;

	/**
	 * @see edu.bd.myProject.authentication.service.AuthenticationService#authentifier(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public CoreService authentifier(String login, String motDePasse) throws Exception {
		Compte compte = comptesDao.obtenirParLogin(login);
		if (compte == null) {
			throw new Exception("Erreur connexion");
		}
		if (!compte.getMotDePasse().equals(motDePasse)) {
			throw new Exception("Erreur connexion");
		}
		if (!compte.getIsActif()) {
			throw new Exception("Erreur connexion");
		}
		if (compte.getIsAdmin()) {
			coreAdminService.setUser(compte);
			return coreAdminService;
		} else {
			coreUserService.setUser(compte);
			return coreUserService;
		}
	}

}
