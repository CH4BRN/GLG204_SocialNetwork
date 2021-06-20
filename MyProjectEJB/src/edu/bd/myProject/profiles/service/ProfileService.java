package edu.bd.myProject.profiles.service;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface ProfileService {

	public Profile createProfile(String pseudo, Compte compte, Salon salon) throws Exception;

	public List<Profile> getProfilesForSalon(Salon salon) throws Exception;

	public Profile mettreAJour(Profile yourProfile, String newProfileName) throws Exception;

	public Profile supprimer(Profile profile) throws Exception;

	public Profile passerHorsLigne(Profile yourProfile);

	public List<Profile> obtenirActifsPourUnSalon(Salon thisSalon);

	public List<Profile> obtenirPourUnSalon(Salon thisSalon);

	public Profile obtenirPourUnCompteEtUnSalon(Compte currentAccount, Salon salon);

	public Profile activerConnexion(Profile profile) throws Exception;

	public Profile mettreAJour(Profile newProfile);

	public List<String> exclureProfile(Salon salon, String profileId) throws Exception;

}
