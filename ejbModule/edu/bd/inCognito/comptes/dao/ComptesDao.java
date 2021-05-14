/**
 * 
 */
package edu.bd.inCognito.comptes.dao;

import java.util.List;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.framework.persistence.dao.GenericDao;

/**
 * @author Brique DECKARD
 *
 */
@Local
public interface ComptesDao extends GenericDao<Compte, String> {

	/**
	 * Obtenir la liste des comptes inactifs.
	 * 
	 * @return
	 * @throws InCognitoException
	 */
	public List<Compte> obtenirComptesInactifs() throws InCognitoException;

	/**
	 * Obtenir un compte par son login.
	 * 
	 * @return
	 * @throws InCognitoException
	 */
	public Compte obtenirCompteParLogin(String login) throws InCognitoException;

	/**
	 * Obtenir un compte par son login et son email. Utilisé pour la récupération de
	 * compte.
	 * 
	 * @param login
	 * @param email
	 * @return
	 * @throws InCognitoException
	 */
	public Compte obtenirCompteParLoginEtEmail(String login, String email) throws InCognitoException;

}
