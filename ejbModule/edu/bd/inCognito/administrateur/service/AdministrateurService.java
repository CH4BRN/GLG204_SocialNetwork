package edu.bd.inCognito.administrateur.service;

import javax.ejb.Local;

import edu.bd.inCognito.comptes.entity.Compte;

/**
 * Service administrateur.
 * 
 * @author Brique DECKARD
 *
 */
@Local
public interface AdministrateurService {
	/**
	 * Se déconnecter
	 * 
	 * @param login
	 * @param motDePasse
	 */
	public void seDeconnecter();

	/**
	 * Obtenir l'administrateur de la session.
	 * 
	 * @return
	 */
	Compte getAdmin();

	/**
	 * Definir l'administrateur de la session.
	 * 
	 * @param admin
	 */
	void setAdmin(Compte admin);

}
