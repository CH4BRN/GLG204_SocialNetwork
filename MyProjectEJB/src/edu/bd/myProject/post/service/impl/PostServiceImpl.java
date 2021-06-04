// PostServiceImpl.java - Copyright pierr
package edu.bd.myProject.post.service.impl;

import javax.inject.Inject;

import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.service.PostService;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.salons.entity.Salon;

/**
 * @author pierr
 *
 */
public class PostServiceImpl implements PostService {

	@Inject
	PostsDao postDao;

	/**
	 * @see edu.bd.myProject.post.service.PostService#creerNouveauPost(edu.bd.myProject.salons.entity.Salon,
	 *      edu.bd.myProject.profiles.entity.Profile, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Post creerNouveauPost(Salon salon, Profile profile, String titre, String body) {
		Post post;
		try {
			post = postDao.obtenirNouvelleEntite();
			post.setSalon(salon);
			post.setProfile(profile);
			post.setTitre(titre);
			post.setBody(body);
			postDao.inserer(post);
		} catch (InCognitoDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
