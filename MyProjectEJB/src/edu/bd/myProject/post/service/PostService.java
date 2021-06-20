package edu.bd.myProject.post.service;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface PostService {

	public Post creerNouveauPost(Salon salon, Profile profile, String titre, String body);

	public List<Post> obtenirPourUnSalon(Salon thisSalon);

	public List<Post> obtenirPourUnProfil(String profileId);

}
