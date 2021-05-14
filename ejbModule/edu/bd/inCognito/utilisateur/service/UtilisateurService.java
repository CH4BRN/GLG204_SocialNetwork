package edu.bd.inCognito.utilisateur.service;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;

/**
 * Service utilisateur
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface UtilisateurService {

	/**
	 * Définir l'utilisateur courant de la session.
	 * 
	 * @param utilisateur
	 */
	public void setUtilisateurCourant(Compte utilisateur);

	/**
	 * Obtenir l'utilisateur courant de la session.
	 * 
	 * @return
	 */
	public Compte getUtilisateurCourant();

	/**
	 * Se déconnecter.
	 * 
	 */
	void seDeconnecter();

	/**
	 * Gérer les invitations
	 */
	void gererLesInvitations();

	/**
	 * Consulter le profil d'un utilisateur.
	 * 
	 * @param login
	 * @throws InCognitoException
	 */
	void consulterProfil(String login) throws InCognitoException;

	/**
	 * Supprimer son propre compte utilisateur.
	 * @throws InCognitoException 
	 */
	void supprimerSonCompte() throws InCognitoException;

}
