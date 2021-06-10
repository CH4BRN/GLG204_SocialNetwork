package edu.bd.myProject.profiles.dao;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.IGenericDao;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface ProfileDao extends IGenericDao<Profile, String> {

	public Profile inserer(Profile profile) throws InCognitoDaoException;

	public Profile supprimer(Profile profile) throws InCognitoDaoException;

	public Profile modifier(Profile profile) throws InCognitoDaoException;

	public Profile obtenir(String id) throws InCognitoDaoException;

	public List<Profile> obtenirPourUnCompte(String compteId) throws InCognitoDaoException;

	public List<Profile> obtenirPourUnSalon(Salon salon) throws InCognitoDaoException;

	public List<Profile> obtenirActifsPourUnSalon(Salon salon) throws InCognitoDaoException;

	public Profile obtenirPourUnCompteEtUnSalon(Compte compte, Salon salon) throws InCognitoDaoException;

	public Profile obtenirNouvelleEntite();

}
