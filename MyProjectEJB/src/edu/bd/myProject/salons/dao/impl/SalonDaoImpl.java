// SalonDaoImpl.java - Copyright pierr
package edu.bd.myProject.salons.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.GenericDaoImpl;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.dao.SalonDao;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myProject.salons.entity.impl.SalonImpl;

/**
 * @author pierr
 *
 */
@Stateless
public class SalonDaoImpl extends GenericDaoImpl implements SalonDao {

	@Inject
	ProfileDao profileDao;

	@Inject
	InvitationDao invitationDao;

	@Inject
	PostsDao postDao;

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
			salon = this.getEm().merge(salon);
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
			throw new InCognitoDaoException("Pas de salon");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salon> obtenirSalonsParCreateur(Compte createur) throws InCognitoDaoException {
		try {
			return (List<Salon>) this.getEm().createQuery("FROM SalonImpl s WHERE s.createur.id = :id")
					.setParameter("id", createur.getId()).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salon> obtenirSalonsParticipe(Compte utilisateur) {
		String query = "SELECT p FROM ProfileImpl p WHERE p.user = :user";
		List<Profile> profiles = this.getEm().createQuery(query).setParameter("user", utilisateur).getResultList();
		List<Salon> salons = new ArrayList<Salon>();
		for (Profile profile : profiles) {
			salons.add(profile.getSalon());
		}
		return salons;
	}

	@Override
	public Salon modifier(Salon t) throws InCognitoDaoException {
		t = this.getEm().merge(t);
		return t;
	}

	@Override
	public List<Salon> obtenirTous() throws InCognitoDaoException {

		String query = "SELECT s FROM SalonImpl s";
		@SuppressWarnings("unchecked")
		List<Salon> salons = this.getEm().createQuery(query).getResultList();
		return salons;
	}

}
