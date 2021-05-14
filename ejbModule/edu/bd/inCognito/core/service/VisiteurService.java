package edu.bd.inCognito.core.service;

import javax.ejb.Local;

import edu.bd.inCognito.administrateur.service.AdministrateurService;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.utilisateur.service.UtilisateurService;

/**
 * Point d'entrée de l'application
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface VisiteurService {

	/**
	 * Créer un compte à partir des informations utilisateur
	 * 
	 * @param login
	 * @param email
	 * @param motDePasse
	 * @return
	 * @throws InCognitoException
	 */
	public Compte creerUnCompte(String login, String email, String motDePasse) throws InCognitoException;

	/**
	 * Récupérer un compte à partir des informations de connexion
	 * 
	 * @param login
	 * @param email
	 * @throws InCognitoException
	 */
	public void récupérerCompte(String login, String email) throws InCognitoException;

	/**
	 * Se connecter en tant qu'adminsitrateur.
	 * 
	 * @param login
	 * @param motDePasse
	 * @return
	 * @throws InCognitoException
	 */
	public AdministrateurService connexionAdmin(String login, String motDePasse) throws InCognitoException;

	/**
	 * Se connecter en tant qu'utilisateur.
	 * 
	 * @param login
	 * @param motDePasse
	 * @return
	 * @throws InCognitoException
	 */
	public UtilisateurService connexionUtilisateur(String login, String motDePasse) throws InCognitoException;

}
