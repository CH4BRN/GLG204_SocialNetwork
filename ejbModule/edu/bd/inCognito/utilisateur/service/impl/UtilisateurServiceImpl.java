package edu.bd.inCognito.utilisateur.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.service.ComptesService;
import edu.bd.inCognito.exceptions.InCognitoCompteException;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.profils.entity.Profil;
import edu.bd.inCognito.utilisateur.service.UtilisateurService;

/**
 * Implémentation pour le service {@link UtilisateurService} .
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class UtilisateurServiceImpl implements UtilisateurService {

	@Inject
	ComptesService comptesService;

	private Compte utilisateurCourant;

	@Override
	public void seDeconnecter() {
		this.utilisateurCourant = null;
	}

	@Override
	public void gererLesInvitations() {
		System.out.println("GERER LES INVITATIONS");
		// TODO implémenter la gestion des invitations

	}

	@Override
	public void consulterProfil(String login) throws InCognitoException {
		System.out.println("CONSULTER PROFIL : " + login);
		if (comptesService == null) {
			System.out.println("Comptes service is null !");
		}
		// TODO implémenter la consultation des profils.
		try {
			Compte compte = comptesService.obtenirCompteParLogin(login);
			if (compte == null) {
				System.out.println("N'EXISTE PAS");
			} else {
				System.out.println("COMPTE : " + compte.toString());
			}

		} catch (InCognitoException e) {
			throw new InCognitoException("Problème de profil", e);
		}
	}

	@Override
	public void setUtilisateurCourant(Compte utilisateur) {
		this.utilisateurCourant = utilisateur != null ? utilisateur : null;
	}

	@Override
	public Compte getUtilisateurCourant() {
		return this.utilisateurCourant;
	}

	@Override
	public void supprimerSonCompte() throws InCognitoException {
		try {
			this.comptesService.supprimerCompte(utilisateurCourant);
			this.setUtilisateurCourant(null);
		} catch (InCognitoException e) {
			throw new InCognitoException("Problème de supression", e);
		}

	}

	@Override
	public Profil creerProfil(String pseudo) throws InCognitoException {
		verifierPseudo(pseudo);
		this.profilDao.obtenirNou
		this.profilDao.creer()
		return null;
	}

}
