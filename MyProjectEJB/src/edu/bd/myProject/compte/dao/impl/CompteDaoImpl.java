// CompteDaoImpl.java - Copyright pierr
package edu.bd.myProject.compte.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.framework.dao.GenericDaoImpl;
import edu.bd.myProject.framework.dao.InCognitoDaoException;

/**
 * @author pierr
 *
 */
@Stateless
public class CompteDaoImpl extends GenericDaoImpl implements CompteDao {

	/**
	 * @throws InCognitoDaoException
	 * @see edu.bd.myProject.compte.dao.CompteDao#inserer(edu.bd.myProject.compte.entity.Compte)
	 */
	@Override
	public Compte inserer(Compte compte) throws InCognitoDaoException {
		try {
			this.getEm().persist(compte);
			return compte;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur inserer", e);
		}

	}

	/**
	 * @throws InCognitoDaoException
	 * @see edu.bd.myProject.compte.dao.CompteDao#obtenir(java.lang.String)
	 */
	@Override
	public Compte obtenir(String id) throws InCognitoDaoException {
		try {
			return this.getEm().find(CompteImpl.class, id);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	@Override
	public Compte obtenirNouvelleEntite() {
		Compte compte = new CompteImpl();
		compte.setDateCreation(new Date());
		return compte;
	}

	@Override
	public Compte obtenirParLogin(String login) throws InCognitoDaoException {
		try {
			return (Compte) this.getEm().createQuery("FROM CompteImpl c WHERE c.login = :login")
					.setParameter("login", login).getSingleResult();

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Compte obtenirParEmail(String email) throws InCognitoDaoException {
		try {
			return (Compte) this.getEm().createNamedQuery("getAccount_byEmail").setParameter("email", email)
					.getSingleResult();

		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> obtenirTous() throws InCognitoDaoException {
		try {
			return this.getEm().createNamedQuery("getAccount_all").getResultList();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir tous", e);
		}

	}

	@Override
	public Compte modifier(Compte compte) throws InCognitoDaoException {
		try {
			compte = this.getEm().merge(compte);
			return compte;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur modifier", e);
		}

	}

	@Override
	public Compte supprimer(Compte compte) throws InCognitoDaoException {
		try {
			compte = getEm().merge(compte);
			this.getEm().remove(compte);
			return compte;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur supprimer", e);
		}
	}

}
