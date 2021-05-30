package edu.bd.myProject.compte.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;

@Local
public interface CompteService {

	public Compte creerCompte(String login, String email, String motDePasse, Boolean isActif, Date dateCreation,
			Boolean isAdmin) throws Exception;

	public List<Compte> obtenirTousLesComptes();

}
