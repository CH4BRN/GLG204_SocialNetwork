package edu.bd.inCognito.utilisateur.service;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.profils.entity.Profil;

/**
 * Service utilisateur
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface UtilisateurService {

	/**
	 * D�finir l'utilisateur courant de la session.
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
	 * Se d�connecter.
	 * 
	 */
	void seDeconnecter();

	/**
	 * G�rer les invitations
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
	 * Creer un profil
	 */
	Profil creerProfil(String pseudo) throws InCognitoException;

	/**
	 * Supprimer son propre compte utilisateur.
	 * @throws InCognitoException 
	 */
	void supprimerSonCompte() throws InCognitoException;

}
