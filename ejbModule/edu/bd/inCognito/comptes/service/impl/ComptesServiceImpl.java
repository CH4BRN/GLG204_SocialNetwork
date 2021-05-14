/**
 * 
 */
package edu.bd.inCognito.comptes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.inCognito.comptes.dao.ComptesDao;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.service.ComptesService;
import edu.bd.inCognito.exceptions.InCognitoCompteException;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.exceptions.InCognitoMailException;
import edu.bd.inCognito.exceptions.InCognitoPasswordException;

/**
 * @author Brique DECKARD
 *
 */
@Stateless
public class ComptesServiceImpl implements ComptesService {

	@Inject
	ComptesDao comptesDao;

	private Boolean verifierEmail(String email) throws InCognitoException {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new InCognitoMailException();
		}
		return matcher.matches();
	}

	private Boolean verifierMotDePasse(String motDePasse) throws InCognitoException {
		String regex = "^" // Debut de la ligne
				+ "(?=.*[0-9])" // Chiffre[0-9]
				+ "(?=.*[a-z])" // Minuscule
				+ "(?=.*[A-Z])" // Majuscule
				+ "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" // Caractère spécial
				+ "." // N'importe quoi
				+ "{8,20}" // au moins 8 maxi 20
				+ "$"; // Fin de la ligne
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(motDePasse);
		if (!matcher.matches()) {
			throw new InCognitoPasswordException();
		}
		return matcher.matches();
	}

	@Override
	public Compte creerCompte(String login, String email, String motDePasse) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " CREER COMPTE.");
		Compte compte = comptesDao.obtenirNouvelleEntite();
		compte.setLogin(login);
		compte.setDateCreation(new Date());

		// Verification de l'email
		try {
			verifierEmail(email);
		} catch (InCognitoMailException e) {
			throw new InCognitoException(e);
		}

		// Verification du mot de passe
		try {
			verifierMotDePasse(motDePasse);
		} catch (InCognitoPasswordException e) {
			throw new InCognitoException(e);
		}

		compte.setEmail(email);

		compte.setIsActif(false);
		compte.setMotDePasse(motDePasse);
		try {
			comptesDao.creer(compte);
			return compte;
		} catch (InCognitoException e) {
			e.printStackTrace();
			throw new InCognitoException(e);
		}

	}

	@Override
	public Compte supprimerCompte(Compte compte) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " SUPPRIMER COMPTE.");
		try {
			comptesDao.supprimer(compte);
			return compte;
		} catch (InCognitoException e) {
			e.printStackTrace();
			throw new InCognitoException(e);
		}
	}

	@Override
	public List<Compte> obtenirComptesInactifs() throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR COMPTES INACTIFS.");
		try {
			return comptesDao.obtenirComptesInactifs();
		} catch (InCognitoException e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public Compte obtenirCompte(String identifier) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR COMPTE.");
		try {
			return comptesDao.obtenir(identifier);
		} catch (InCognitoException e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public Compte obtenirCompteParLogin(String login) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR COMPTE POUR LOGIN " + login);
		try {
			Compte compte = comptesDao.obtenirCompteParLogin(login);
			if (compte != null) {
				System.out.println(compte.toString());
				return compte;
			} else {
				System.out.println(this.getClass().getSimpleName() + "Compte null");
				throw new InCognitoException("Compte null");
			}
		} catch (InCognitoCompteException e) {
			throw new InCognitoException(e);
		}

	}

	@Override
	public Compte obtenirCompeParLoginEtEmail(String login, String email) throws InCognitoException {
		System.out.println(
				this.getClass().getSimpleName() + " OBTENIR COMPTE POUR LOGIN " + login + " ET EMAIL " + email);
		try {
			return comptesDao.obtenirCompteParLoginEtEmail(login, email);
		} catch (InCognitoException e) {
			throw new InCognitoException(e);
		}
	}

}
