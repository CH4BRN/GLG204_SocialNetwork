package edu.bd.inCognito.framework.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.bd.inCognito.exceptions.InCognitoException;
import edu.bd.inCognito.framework.persistence.dao.GenericDao;
import edu.bd.inCognito.framework.persistence.entity.InCognitoEntity;

/**
 * Classe abstraite pour le Dao générique
 * 
 * @author Brique DECKARD
 *
 * @param <ENTITY_TYPE>
 * @param <IDENTIFIER_TYPE>
 */
public abstract class GenericDaoImpl<ENTITY_TYPE extends InCognitoEntity<IDENTIFIER_TYPE>, IDENTIFIER_TYPE>
		implements GenericDao<ENTITY_TYPE, IDENTIFIER_TYPE> {

	/**
	 * EntityManager pour la persistance.
	 */
	@PersistenceContext(unitName = "InCognito")
	EntityManager em;

	/**
	 * Retourne l'EntityManager de JPA
	 * 
	 * @return
	 */
	protected EntityManager getEm() {
		return this.em;
	}

	@Override
	public ENTITY_TYPE creer(ENTITY_TYPE entity) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " CREER ENTITE " + entity.toString());
		try {
			em.persist(entity);
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
		return entity;
	}

	@Override
	public ENTITY_TYPE modifier(ENTITY_TYPE entity) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " MODIFIER ENTITE " + entity.toString());
		try {
			em.merge(entity);
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
		return entity;
	}

	@Override
	public ENTITY_TYPE supprimer(ENTITY_TYPE entity) throws InCognitoException {
		System.out.println(this.getClass().getSimpleName() + " SUPPRIMER ENTITE " + entity.toString());
		try {
			this.em.remove(entity);
		} catch (Exception e) {
			throw new InCognitoException(e);
		}
		return entity;
	}

	@Override
	public abstract List<ENTITY_TYPE> obtenirTous() throws InCognitoException;

	@Override
	public abstract ENTITY_TYPE obtenirNouvelleEntite();

	@Override
	public abstract ENTITY_TYPE obtenir(IDENTIFIER_TYPE identifier) throws InCognitoException;

}
