// CompteDaoImpl.java - Copyright pierr
package edu.bd.myProject.compte.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import edu.bd.myProject.compte.dao.CompteDao;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.entity.impl.CompteImpl;
import edu.bd.myProject.framework.dao.GenericDao;
import edu.bd.myProject.framework.dao.InCognitoDaoException;

/**
 * @author pierr
 *
 */
@Stateless
public class CompteDaoImpl extends GenericDao implements CompteDao {

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
	public Compte obtenirNouvelleEntité() {
		Compte compte = new CompteImpl();
		compte.setDateCreation(new Date());
		return compte;
	}

	@Override
	public Compte obtenirParLogin(String login) throws InCognitoDaoException {
		try {
			return (Compte) this.getEm().createNamedQuery("getAccount_byLogin").setParameter("login", login)
					.getSingleResult();

		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Compte obtenirParEmail(String email) throws InCognitoDaoException {
		try {
			return (Compte) this.getEm().createNamedQuery("getAccount_byEmail").setParameter("email", email)
					.getSingleResult();

		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public List<Compte> obtenirTousLesComptes() {
		try {
			return this.getEm().createNamedQuery("getAccount_all").getResultList();
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Compte modifier(Compte compte) {
		compte = this.getEm().merge(compte);
		return compte;

	}

	@Override
	public void supprimerCompte(Compte compte) {
		try {
			this.getEm().remove(compte);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
