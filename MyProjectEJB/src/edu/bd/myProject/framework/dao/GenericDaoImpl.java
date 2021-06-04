package edu.bd.myProject.framework.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDaoImpl {
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


}
