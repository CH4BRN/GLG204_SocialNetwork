package edu.bd.inCognito.authentification.service;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;

/**
 * Service réalisant l'authentification des utilisateurs.
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface AuthentificationService {

	/**
	 * Réaliser l'authentification.
	 * 
	 * @param login
	 * @param motDePasse
	 * @return
	 * @throws InCognitoException
	 */
	public Compte authentifier(String login, String motDePasse) throws InCognitoException;

}
