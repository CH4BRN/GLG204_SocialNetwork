package edu.bd.myProject.post.dao;

import java.util.List;

import javax.ejb.Local;

import edu.bd.myProject.framework.dao.IGenericDao;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.salons.entity.Salon;

@Local
public interface PostsDao extends IGenericDao<Post, String> {

	public List<Post> obtenirPourUnSalon(Salon salon) throws InCognitoDaoException;

	public List<Post> obtenirPourUnProfil(String profileId) throws InCognitoDaoException;

}
