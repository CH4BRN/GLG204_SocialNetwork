package edu.bd.inCognito.framework.persistence.dao;

import java.util.List;

import edu.bd.inCognito.exceptions.InCognitoException;

/**
 * Interface pour un dao g�n�rique
 * 
 * @author Brique DECKARD
 *
 * @param <ENTITY_TYPE>
 * @param <IDENTIFIER_TYPE>
 */
public interface GenericDao<ENTITY_TYPE, IDENTIFIER_TYPE> {
	/**
	 * Creer une entit�
	 * 
	 * @param entity
	 * @return
	 * @throws InCognitoException
	 */
	public ENTITY_TYPE creer(ENTITY_TYPE entity) throws InCognitoException;

	/**
	 * Obtenir une enti� grace a son identifiant.
	 * 
	 * @param identifier
	 * @return
	 * @throws InCognitoException
	 */
	public ENTITY_TYPE obtenir(IDENTIFIER_TYPE identifier) throws InCognitoException;

	/**
	 * Modifier une entit�
	 * 
	 * @param entity
	 * @return
	 * @throws InCognitoException 
	 */
	public ENTITY_TYPE modifier(ENTITY_TYPE entity) throws InCognitoException;

	/**
	 * Supprimer une entit�
	 * 
	 * @param entity
	 * @return
	 * @throws InCognitoException 
	 */
	public ENTITY_TYPE supprimer(ENTITY_TYPE entity) throws InCognitoException;

	/**
	 * Obtenir toutes les entit�s.
	 * 
	 * @return
	 * @throws InCognitoException 
	 */
	public List<ENTITY_TYPE> obtenirTous() throws InCognitoException;

	/**
	 * Obtenir une nouvelle entit�.
	 * 
	 * @return
	 */
	public ENTITY_TYPE obtenirNouvelleEntite();

}
