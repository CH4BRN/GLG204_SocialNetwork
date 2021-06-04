package edu.bd.myProject.salons.service;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface SalonService {

	public Salon creerSalon(String name, Compte createur, List<String> emails) throws InCognitoDaoException;

	public List<Salon> obtenirSalonsCreesParUtilisateur(Compte utilisateur) throws InCognitoDaoException;

	public List<Salon> obtenirSalonsAuxquelsUtilisateurParticipe(Compte utilisateur) throws InCognitoDaoException;

	public Salon obtenirSalonParId(String id) throws InCognitoDaoException;

	public Salon obtenirSalonParNom(String nom) throws Exception;

	public Salon supprimerSalon(Salon id) throws InCognitoDaoException;

}
