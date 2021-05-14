/**
 * 
 */
package edu.bd.inCognito.comptes.service;

import java.util.List;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;

/**
 * Service de maintenance des comptes.
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface ComptesService {
	/**
	 * Créer un compte.
	 * 
	 * @param login
	 * @param email
	 * @param motDePasse
	 * @return
	 * @throws InCognitoException
	 */
	Compte creerCompte(String login, String email, String motDePasse) throws InCognitoException;

	/**
	 * Supprimer un compte.
	 * 
	 * @param compte
	 * @return
	 * @throws InCognitoException
	 */
	Compte supprimerCompte(Compte compte) throws InCognitoException;

	/**
	 * Obtenir la liste des comptes inactifs
	 * 
	 * @return
	 * @throws InCognitoException
	 */
	List<Compte> obtenirComptesInactifs() throws InCognitoException;

	/**
	 * Obtenir un compte par son ID
	 * 
	 * @param identifier
	 * @return
	 * @throws InCognitoException
	 */
	Compte obtenirCompte(String identifier) throws InCognitoException;

	/**
	 * Obtenir un compte par son login
	 * 
	 * @param login
	 * @return
	 * @throws InCognitoException
	 */
	Compte obtenirCompteParLogin(String login) throws InCognitoException;

	/**
	 * Obtenir un compte par son login et son email.
	 * 
	 * @param login
	 * @param email
	 * @return
	 * @throws InCognitoException
	 */
	Compte obtenirCompeParLoginEtEmail(String login, String email) throws InCognitoException;

}
