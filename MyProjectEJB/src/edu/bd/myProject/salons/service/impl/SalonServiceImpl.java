// SalonServiceImpl.java - Copyright pierr
package edu.bd.myProject.salons.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.service.SalonService;

/**
 * @author pierr
 *
 */
@Stateless
public class SalonServiceImpl implements SalonService {

	@Inject
	SalonDao salonDao;

	/**
	 * @throws InCognitoDaoException
	 * @see edu.bd.myProject.salons.service.SalonService#creerSalon(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public Salon creerSalon(String name, Compte createur, List<String> emails) throws InCognitoDaoException {
		try {
			Salon salon = this.salonDao.obtenirNouvelleEntite();
			salon.setNom(name);
			salon.setCreateur(createur);
			this.salonDao.inserer(salon);
			return salon;
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Salon> obtenirSalonsPourUtilisateur(Compte utilisateur) throws InCognitoDaoException {
		try {
			List<Salon> salons = this.salonDao.obtenirSalonsParCreateur(utilisateur);
			return salons;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public Salon supprimerSalon(String id) throws InCognitoDaoException {
		try {
			Salon salon = salonDao.obtenir(id);
			salonDao.supprimer(salon);
			return salon;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Salon obtenirSalonParId(String id) throws InCognitoDaoException {
		try {
			Salon salon = salonDao.obtenir(id);
			return salon;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public Salon obtenirSalonParNom(String nom) throws Exception {
		try {
			Salon salon = salonDao.obtenirParNom(nom);
			return salon;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erreur obtention",e);
		}
	}

}
