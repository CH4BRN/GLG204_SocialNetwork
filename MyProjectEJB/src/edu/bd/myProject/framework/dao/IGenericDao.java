package edu.bd.myProject.framework.dao;

import java.util.List;

public interface IGenericDao<T, ID> {

	public T inserer(T t) throws InCognitoDaoException;

	public T supprimer(T t) throws InCognitoDaoException;

	public T modifier(T t) throws InCognitoDaoException;

	public T obtenir(ID id) throws InCognitoDaoException;

	public T obtenirNouvelleEntite() throws InCognitoDaoException;

	public List<T> obtenirTous() throws InCognitoDaoException;

}
