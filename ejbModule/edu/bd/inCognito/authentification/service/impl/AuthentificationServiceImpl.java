/**
 * 
 */
package edu.bd.inCognito.authentification.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.inCognito.authentification.service.AuthentificationService;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.service.ComptesService;
import edu.bd.inCognito.exceptions.InCognitoException;

/**
 * Implémentation pour le service {@link AuthentificationService}
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class AuthentificationServiceImpl implements AuthentificationService {

	/**
	 * Instance de {@link ComptesService}
	 */
	@Inject
	ComptesService compteService;

	@Override
	public Compte authentifier(String login, String motDePasse) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " AUTHENTIFIER.");
		try {
			Compte compte = compteService.obtenirCompteParLogin(login);
			if (compte == null) {
				return null;
			}
			if (!compte.getMotDePasse().contentEquals(motDePasse)) {
				return null;
			}
			return compte;
		} catch (InCognitoException e) {
			throw new InCognitoException(e);
		}
	}

}
