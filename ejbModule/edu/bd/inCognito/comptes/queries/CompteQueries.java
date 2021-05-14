package edu.bd.inCognito.comptes.queries;

import edu.bd.inCognito.comptes.entity.Compte;

/**
 * Requêtes JPA pour l'entié {@link Compte}
 * 
 * @author Brique DECKARD
 *
 */
public abstract class CompteQueries {
	/**
	 * Obtenir tous les comptes
	 */
	public static final String ALL_ACCOUNTS_QUERY = "SELECT a FROM CompteImpl a";
	/**
	 * Obtenir tous les comptes inactifs
	 */
	public static final String ALL_INACTIVE_ACCOUNTS_QUERY = "SELECT a FROM CompteImpl a WHERE a.isActif = FALSE";
	/**
	 * Obtenir un compte par son login.
	 */
	public static final String COMPTE_BY_LOGIN_QUERY = "SELECT a FROM CompteImpl a WHERE a.login = :login";
	/**
	 * Obtenir un compte par son login et son email.
	 */
	public static final String ACCOUNT_BY_LOGIN_AND_EMAIL = "SELECT a FROM CompteImpl a WHERE a.login = :login AND a.email = :email";

}
