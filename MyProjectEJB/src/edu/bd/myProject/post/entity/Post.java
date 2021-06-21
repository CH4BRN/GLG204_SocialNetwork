package edu.bd.myProject.post.entity;

import java.util.Date;

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

	public void setId(String id);

	public Date getDate();

	public void setDate(Date date);

	public String getStrDate();

	public void setStrDate(String date);

	public void setYoutubeLink(String youtubeLink);

	public String getYoutubeLink();

}
