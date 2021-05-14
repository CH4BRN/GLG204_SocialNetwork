package edu.bd.inCognito.comptes.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import edu.bd.inCognito.comptes.dao.ComptesDao;
import edu.bd.inCognito.comptes.entity.Compte;
import edu.bd.inCognito.comptes.entity.impl.CompteImpl;
import edu.bd.inCognito.exceptions.InCognitoCompteException;
import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.framework.persistence.dao.impl.GenericDaoImpl;

/**
 * Implémentation du {@link ComptesDao}
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class ComptesDaoImpl extends GenericDaoImpl<Compte, String> implements ComptesDao {
	@Override
	public Compte obtenir(String identifier) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR " + Compte.class.getSimpleName());
		if (identifier.isEmpty()) {
			throw new InCognitoException("L'identifiant du compte est nul.");
		}
		try {
			return this.getEm().find(CompteImpl.class, identifier);
		} catch (Exception e) {
			throw new InCognitoCompteException();
		}
	}

	@Override
	public List<Compte> obtenirTous() throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR_TOUS " + Compte.class.getSimpleName());
		Query query = this.getEm().createNamedQuery("all_accounts", Compte.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public Compte obtenirNouvelleEntite() {
		return new CompteImpl();
	}

	@Override
	public List<Compte> obtenirComptesInactifs() throws InCognitoException {
		System.out
				.println(this.getClass().getSimpleName() + " OBTENIR_COMPTES_INACTIFS " + Compte.class.getSimpleName());
		Query query = this.getEm().createNamedQuery("all_inactive_accounts", Compte.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
	}

	@Override
	public Compte obtenirCompteParLogin(String login) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " OBTENIR COMPTE POUR LOGIN " + login);
		Query query = this.getEm().createNamedQuery("compte_by_login", Compte.class);
		query.setParameter("login", login);
		try {

			return (Compte) query.getSingleResult();
		} catch (Exception e) {
			throw new InCognitoCompteException();
		}
	}

	@Override
	public Compte obtenirCompteParLoginEtEmail(String login, String email) throws InCognitoException {
		System.out.println(
				this.getClass().getSimpleName() + " OBTENIR COMPTE POUR LOGIN " + login + " ET EMAIL " + email);
		Query query = this.getEm().createNamedQuery("compte_by_login_and_mail", Compte.class);
		query.setParameter("login", login);
		query.setParameter("email", email);
		try {
			return (Compte) query.getSingleResult();
		} catch (Exception e) {
			throw new InCognitoCompteException();
		}
	}

}
