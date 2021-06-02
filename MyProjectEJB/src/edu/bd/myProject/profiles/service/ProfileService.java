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

}
