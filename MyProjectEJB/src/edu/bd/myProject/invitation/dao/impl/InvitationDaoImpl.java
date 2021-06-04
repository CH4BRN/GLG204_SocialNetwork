// InvitationDaoImpl.java - Copyright pierr
package edu.bd.myProject.invitation.dao.impl;

import java.util.List;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.framework.dao.GenericDaoImpl;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.invitation.dao.InvitationDao;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.invitation.entity.impl.InvitationImpl;
import edu.bd.myProject.salons.entity.Salon;

/**
 * @author pierr
 *
 */
public class InvitationDaoImpl extends GenericDaoImpl implements InvitationDao {

	/**
	 * @see edu.bd.myProject.framework.dao.IGenericDao#inserer(java.lang.Object)
	 */
	@Override
	public Invitation inserer(Invitation t) throws InCognitoDaoException {
		try {
			this.getEm().persist(t);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur inserer", e);
		}
		return t;
	}

	/**
	 * @see edu.bd.myProject.framework.dao.IGenericDao#supprimer(java.lang.Object)
	 */
	@Override
	public Invitation supprimer(Invitation t) throws InCognitoDaoException {
		try {
			this.getEm().remove(t);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur supprimer", e);
		}
		return t;
	}

	/**
	 * @see edu.bd.myProject.framework.dao.IGenericDao#modifier(java.lang.Object)
	 */
	@Override
	public Invitation modifier(Invitation t) throws InCognitoDaoException {
		t = this.getEm().merge(t);
		return t;
	}

	/**
	 * @see edu.bd.myProject.framework.dao.IGenericDao#obtenir(java.lang.Object)
	 */
	@Override
	public Invitation obtenir(String id) throws InCognitoDaoException {
		try {
			return this.getEm().find(InvitationImpl.class, id);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	@Override
	public Invitation obtenirNouvelleEntite() throws InCognitoDaoException {
		return new InvitationImpl();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invitation> obtenirTous() throws InCognitoDaoException {
		try {
			return (List<Invitation>) this.getEm().createQuery("SELECT i FROM InvitationImpl i");
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir tous", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invitation> obtenirTousPourUnCompte(Compte compte) throws InCognitoDaoException {
		try {
			return (List<Invitation>) this.getEm()
					.createQuery("SELECT i FROM InvitationImpl i WHERE i.destinataire = :compte")
					.setParameter("compte", compte).getResultList();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir pour un compte", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invitation> obtenirTousPourUnSalon(Salon salon) throws InCognitoDaoException {
		try {
			return (List<Invitation>) this.getEm().createQuery("SELECT i FROM InvitationImpl i WHERE i.salon = :salon")
					.setParameter("salon", salon);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir pour un salon", e);
		}
	}

	@Override
	public Invitation obtenirPourUnSalonEtUnCompte(Salon salon, Compte compte) throws InCognitoDaoException {
		try {
			return (Invitation) this.getEm()
					.createQuery("SELECT i FROM InvitationImpl i WHERE i.salon = :salon AND i.destinataire = :compte")
					.setParameter("salonId", salon).setParameter("compteId", compte).getSingleResult();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir pour un salon", e);
		}
	}

}
