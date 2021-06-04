package edu.bd.myProject.post.entity;

import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;

public interface Post {

	public String getId();

	public String getTitre();

	public void setTitre(String titre);

	public Salon getSalon();

	public void setSalon(Salon salon);

	public Profile getProfile();

	public void setProfile(Profile profile);

	public String getBody();

	public void setBody(String body);

	void setId(String id);

}
