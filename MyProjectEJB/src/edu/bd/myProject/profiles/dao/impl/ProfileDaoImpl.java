// ProfileDaoImpl.java - Copyright pierr
package edu.bd.myProject.profiles.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.GenericDaoImpl;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.entity.impl.ProfileImpl;
import edu.bd.myProject.salons.entity.Salon;

/**
 * @author pierr
 *
 */
@Stateless
public class ProfileDaoImpl extends GenericDaoImpl implements ProfileDao {

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#inserer(edu.bd.myProject.profiles.entity.Profile)
	 */
	@Override
	public Profile inserer(Profile profile) throws InCognitoDaoException {
		try {
			System.out.println("INSERER " + profile.toString());
			this.getEm().persist(profile);
			return profile;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur insérer", e);
		}

	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#supprimer(edu.bd.myProject.profiles.entity.Profile)
	 */
	@Override
	public Profile supprimer(Profile profile) throws InCognitoDaoException {
		try {
			this.getEm().remove(profile);
			return profile;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur supprimer", e);
		}
	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#modifier(edu.bd.myProject.profiles.entity.Profile)
	 */
	@Override
	public Profile modifier(Profile profile) throws InCognitoDaoException {
		profile = this.getEm().merge(profile);
		return profile;
	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenir(java.lang.String)
	 */
	@Override
	public Profile obtenir(String id) throws InCognitoDaoException {
		try {
			return this.getEm().find(ProfileImpl.class, id);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenirPourUnCompte(edu.bd.myProject.compte.entity.Compte)
	 */
	@Override
	public List<Profile> obtenirPourUnCompte(String compteId) throws InCognitoDaoException {
		try {
			return (List<Profile>) this.getEm().createQuery("FROM ProfileImpl p WHERE p.user.id = :compteId")
					.setParameter("compteId", compteId).getResultList();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}

	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenirPourUnSalon(edu.bd.myProject.salons.entity.Salon)
	 */
	@Override
	public List<Profile> obtenirPourUnSalon(Salon salon) throws InCognitoDaoException {
		try {
			return (List<Profile>) this.getEm().createQuery("FROM ProfileImpl p WHERE p.salon = :salon")
					.setParameter("salon", salon).getResultList();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenirPourUnCompteEtUnSalon(edu.bd.myProject.compte.entity.Compte,
	 *      edu.bd.myProject.salons.entity.Salon)
	 */
	@Override
	public Profile obtenirPourUnCompteEtUnSalon(String compteId, String salonId) throws InCognitoDaoException {
		try {
			return (Profile) this.getEm()
					.createQuery("FROM ProfileImpl p WHERE p.salon.id = :salonId AND p.user.id = :compteId")
					.setParameter("salonId", salonId).setParameter("compteId", compteId).getSingleResult();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	@Override
	public Profile obtenirNouvelleEntite() {
		return new ProfileImpl();
	}

}
