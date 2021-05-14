/**
 * 
 */
package edu.bd.inCognito.core.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.inCognito.administrateur.service.AdministrateurService;
import edu.bd.inCognito.administrateur.service.impl.AdministrateurServiceImpl;
import edu.bd.inCognito.authentification.service.AuthentificationService;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.service.ComptesService;
import edu.bd.inCognito.core.service.VisiteurService;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.utilisateur.service.UtilisateurService;
import edu.bd.inCognito.utilisateur.service.impl.UtilisateurServiceImpl;

/**
 * Implémentation pour le service {@link VisiteurService}
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class VisiteurServiceImpl implements VisiteurService {
	/**
	 * Instance de {@link ComptesService}
	 */
	@Inject
	ComptesService comptesService;

	/**
	 * Instance de {@link AdministrateurService}
	 */
	@Inject
	AdministrateurService adminService;

	/**
	 * Instance de {@link UtilisateurService}
	 */
	@Inject
	UtilisateurService userService;

	/**
	 * Instance de {@link AuthentificationService}
	 */
	@Inject
	AuthentificationService authentificationService;

	@Override
	public Compte creerUnCompte(String login, String email, String motDePasse) throws InCognitoException {
		try {
			return comptesService.creerCompte(login, email, motDePasse);
		} catch (InCognitoException e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public void récupérerCompte(String login, String email) throws InCognitoException {
		try {
			Compte compte = comptesService.obtenirCompeParLoginEtEmail(login, email);
			if (compte != null) {
				System.out.println("ENVOYER EMAIL RECUPERATION A : " + compte.getEmail());
			}

		} catch (Exception e) {
			throw new InCognitoException(e);
		}

	}

	@Override
	public AdministrateurService connexionAdmin(String login, String motDePasse) throws InCognitoException {
		Compte compte = authentificationService.authentifier(login, motDePasse);
		if (compte == null) {
			throw new InCognitoException("Erreur d'authentification.");
		}

		adminService.setAdmin(compte);

		return adminService;
	}

	@Override
	public UtilisateurService connexionUtilisateur(String login, String motDePasse) throws InCognitoException {
		Compte compte = authentificationService.authentifier(login, motDePasse);
		if (compte == null) {
			throw new InCognitoException("Erreur d'authentiication.");

		}
		userService.setUtilisateurCourant(compte);

		return userService;
	}

}
