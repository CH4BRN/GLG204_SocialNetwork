// ProfileDaoImpl.java - Copyright pierr
package edu.bd.myProject.profiles.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.GenericDaoImpl;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
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

	@Inject
	InvitationDao invitationDao;

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
			profile = getEm().merge(profile);
			this.getEm().remove(profile);
			return profile;
		} catch (Exception e) {
			try {
				this.getEm().remove(profile);
				return profile;
			} catch (Exception e1) {
				throw new InCognitoDaoException("Erreur supprimer", e1);
			}

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
			return null;
		}
	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenirPourUnCompte(edu.bd.myProject.compte.entity.Compte)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> obtenirPourUnCompte(String compteId) throws InCognitoDaoException {
		try {
			return (List<Profile>) this.getEm().createQuery("FROM ProfileImpl p WHERE p.user.id = :compteId")
					.setParameter("compteId", compteId).getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenirPourUnSalon(edu.bd.myProject.salons.entity.Salon)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> obtenirPourUnSalon(Salon salon) throws InCognitoDaoException {
		try {
			return (List<Profile>) this.getEm().createQuery("FROM ProfileImpl p WHERE p.salon = :salon")
					.setParameter("salon", salon).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @see edu.bd.myProject.profiles.dao.ProfileDao#obtenirPourUnCompteEtUnSalon(edu.bd.myProject.compte.entity.Compte,
	 *      edu.bd.myProject.salons.entity.Salon)
	 */
	@Override
	public Profile obtenirPourUnCompteEtUnSalon(Compte compte, Salon salon) throws InCognitoDaoException {
		try {
			return (Profile) this.getEm().createQuery("FROM ProfileImpl p WHERE p.salon = :salon AND p.user = :compte")
					.setParameter("salon", salon).setParameter("compte", compte).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Profile obtenirNouvelleEntite() {
		return new ProfileImpl();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> obtenirTous() throws InCognitoDaoException {
		try {
			return (List<Profile>) this.getEm().createQuery("SELECT p FROM ProfileImpl p").getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> obtenirActifsPourUnSalon(Salon salon) throws InCognitoDaoException {
		try {
			return (List<Profile>) this.getEm()
					.createQuery("FROM ProfileImpl p WHERE p.salon = :salon AND p.connected = :connected")
					.setParameter("salon", salon).setParameter("connected", true).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
