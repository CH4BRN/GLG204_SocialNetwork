package edu.bd.myProject.compte.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

/**
 * Service pour la gestion des {@link Compte}
 * 
 * @author pierr
 *
 */
@Local
public interface CompteService {

	/**
	 * Créer un {@link Compte}
	 * 
	 * @param login
	 * @param email
	 * @param motDePasse
	 * @param isActif
	 * @param dateCreation
	 * @param isAdmin
	 * @return
	 * @throws Exception
	 */
	public Compte creerCompte(String login, String email, String motDePasse, Boolean isActif, Date dateCreation,
			Boolean isAdmin) throws Exception;

	/**
	 * Obtenir un compte par son ID
	 * 
	 * @param id
	 * @return
	 */
	public Compte obtenirUnCompte(String id);

	/**
	 * Obtenir tous les {@link Compte}
	 * 
	 * @return
	 */
	public List<Compte> obtenirTousLesComptes();

	/**
	 * Supprimer un {@link Compte}
	 * 
	 * @param compte
	 * @return
	 */
	public Compte supprimerCompte(Compte compte);

	/**
	 * Obtenir un {@linkplain Compte} par son email.
	 * 
	 * @param email
	 * @return
	 */
	public Compte obtenirCompteParEmail(String email);

	/**
	 * Obtenir un {@linkplain Compte} par son login.
	 * 
	 * @param login
	 * @return
	 */
	public Compte obtenirCompteParLogin(String login);

}
