package edu.bd.inCognito.administrateur.service.impl;

import javax.ejb.Stateless;

import edu.bd.inCognito.administrateur.service.AdministrateurService;
import edu.bd.inCognito.comptes.entity.Compte;

/**
 * Implémentation pour le service {@link AdministrateurService}
 * 
 * @author Brique DECKARD
 *
 */
@Stateless
public class AdministrateurServiceImpl implements AdministrateurService {
	/**
	 * Admin de la session.
	 */
	private Compte admin;

	/**
	 * @return
	 */
	@Override
	public Compte getAdmin() {
		return admin;
	}

	@Override
	public void setAdmin(Compte admin) {
		this.admin = admin;
	}

	@Override
	public void seDeconnecter() {
		System.out.println(this.getClass().getSimpleName() + " SE DECONNECTER");
		this.setAdmin(null);

	}

}
