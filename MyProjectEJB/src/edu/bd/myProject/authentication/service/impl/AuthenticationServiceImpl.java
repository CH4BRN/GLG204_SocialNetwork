// AuthenticationServiceImpl.java - Copyright pierr
package edu.bd.myProject.authentication.service.impl;

import javax.inject.Inject;

import edu.bd.myProject.authentication.service.AuthenticationService;
import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;

/**
 * @author pierr
 *
 */
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Inject
	CompteDao comptesDao;
	

	/**
	 * @see edu.bd.myProject.authentication.service.AuthenticationService#authentifier(java.lang.String, java.lang.String)
	 */
	@Override
	public Compte authentifier(String login, String motDePasse) throws Exception {
		Compte compte = comptesDao.obtenirParLogin(login);
		if ( compte.getMotDePasse().equals(motDePasse)) {
			return compte;
		}
		return null;
	}

}
