// SalonDaoImpl.java - Copyright pierr
package edu.bd.myProject.salons.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.framework.dao.GenericDao;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.entity.impl.SalonImpl;

/**
 * @author pierr
 *
 */
@Stateless
public class SalonDaoImpl extends GenericDao implements SalonDao {

	@Override
	public Salon inserer(Salon salon) throws InCognitoDaoException {
		try {
			this.getEm().persist(salon);
			return salon;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur inserer", e);
		}
	}

	@Override
	public Salon obtenir(String id) throws InCognitoDaoException {
		try {
			return this.getEm().find(SalonImpl.class, id);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	@Override
	public Salon supprimer(Salon salon) throws InCognitoDaoException {
		try {
			this.getEm().remove(salon);
			return salon;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InCognitoDaoException("Erreur suppression", e);
		}
	}

	@Override
	public Salon obtenirNouvelleEntite() throws InCognitoDaoException {
		Salon salon = new SalonImpl();
		salon.setDateCreation(new Date());
		return salon;
	}

	@Override
	public Salon obtenirParNom(String nom) throws InCognitoDaoException {
		try {
			return (Salon) this.getEm().createNamedQuery("getSalon_byName").setParameter("name", nom).getSingleResult();
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public List<Salon> obtenirSalonsParCreateur(Compte createur) throws InCognitoDaoException {
		try {
			return (List<Salon>) this.getEm().createQuery("FROM SalonImpl s WHERE s.createur.id = :id")
					.setParameter("id", createur.getId()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new InCognitoDaoException("erreur obtention salon", e);
		}
	}

}
