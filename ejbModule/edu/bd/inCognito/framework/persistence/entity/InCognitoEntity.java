package edu.bd.inCognito.framework.persistence.entity;

import java.io.Serializable;

/**
 * Interface pour les EntityBean de l'application InCognito
 * 
 * @author Brique DECKARD
 *
 * @param <IDENTIFIER_TYPE>
 */
public interface InCognitoEntity<IDENTIFIER_TYPE> extends Serializable {

	/**
	 * Retourne l'identifiant de l'entit�.
	 * 
	 * @return
	 */
	public IDENTIFIER_TYPE getId();

	/**
	 * D�finir l'Id
	 * 
	 * @param id
	 */
	public void setId(IDENTIFIER_TYPE id);

}
