package edu.bd.myProject.profiles.entity;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.salons.entity.Salon;

public interface Profile {

	public String getPseudo();

	public void setPseudo(String pseudo);

	public String getId();

	public void setId(String id);

	void setUser(Compte user);

	Compte getUser();

	Salon getSalon();

	void setSalon(Salon salon);

	Boolean getConnected();

	void setConnected(Boolean connected);

}
