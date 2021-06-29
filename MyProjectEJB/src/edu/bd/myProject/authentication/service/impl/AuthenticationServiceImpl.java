// AuthenticationServiceImpl.java - Copyright pierr
package edu.bd.myProject.authentication.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.authentication.service.AuthenticationService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
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
	CompteService compteService;

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
		// Cherche le compte
		Compte compte = compteService.obtenirCompteParLogin(login);
		
		CoreService service;
		
		// Si le compte est null, alors erreur connexion
		if (compte == null) {
			throw new Exception("Erreur connexion - Compte null");
		}
		// Si les mots de passe ne correspondent pas, alors erreur connexion
		if (!compte.getMotDePasse().equals(motDePasse)) {
			throw new Exception("Erreur connexion - Mot de passe");
		}
		// Si le compte n'est pas actif, alors erreur connexion
		if (!compte.getIsActif()) {
			throw new Exception("Erreur connexion - Inactif");
		}
		// Si le compte associé est admin, alors service admin
		if (compte.getIsAdmin()) {
			service = coreAdminService;
			// Check si le service est nul
			if(service == null) {
				throw new Exception("Erreur interne - Service null");	
			}			

		}// Sinon user 
		else {
			service = coreUserService;
			// Check si le service est nul
			if(service == null) {
				throw new Exception("Erreur interne - Service null");
			}			
		}
		
		service.setUser(compte);
		// Check si l'user est nul
		if(service.getUser() == null) {
			throw new Exception("Erreur interne - User null"); 
		}
		
		return service;
	}

}
