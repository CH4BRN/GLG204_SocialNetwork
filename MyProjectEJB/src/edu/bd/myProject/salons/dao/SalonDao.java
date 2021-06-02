package edu.bd.myProject.salons.dao;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface SalonDao {

	public Salon inserer(Salon salon) throws InCognitoDaoException;

	public Salon obtenir(String id) throws InCognitoDaoException;

	public Salon supprimer(Salon salon) throws InCognitoDaoException;

	public Salon obtenirNouvelleEntite() throws InCognitoDaoException;

	public Salon obtenirParNom(String nom) throws InCognitoDaoException;

	public List<Salon> obtenirSalonsParCreateur(Compte createur) throws InCognitoDaoException;

}
