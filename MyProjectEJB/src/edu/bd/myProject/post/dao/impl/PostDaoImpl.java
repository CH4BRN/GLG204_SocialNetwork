package edu.bd.myProject.post.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import edu.bd.myProject.framework.dao.GenericDaoImpl;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.entity.impl.PostImpl;
import edu.bd.myProject.salons.entity.Salon;

@Stateless
public class PostDaoImpl extends GenericDaoImpl implements PostsDao {

	@Override
	public Post inserer(Post t) throws InCognitoDaoException {
		try {
			System.out.println("INSERER " + t.toString());
			this.getEm().persist(t);
			return t;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur insérer", e);
		}
	}

	@Override
	public Post supprimer(Post t) throws InCognitoDaoException {
		try {
			this.getEm().remove(t);
			return t;
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur supprimer", e);
		}
	}

	@Override
	public Post modifier(Post t) throws InCognitoDaoException {
		t = this.getEm().merge(t);
		return t;
	}

	@Override
	public Post obtenir(String id) throws InCognitoDaoException {
		try {
			return this.getEm().find(PostImpl.class, id);
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir", e);
		}
	}

	@Override
	public Post obtenirNouvelleEntite() throws InCognitoDaoException {
		return new PostImpl();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> obtenirTous() throws InCognitoDaoException {
		try {
			return (List<Post>) this.getEm().createQuery("SELECT p FROM PostImpl p").getResultList();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir tous", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> obtenirPourUnSalon(Salon salon) throws InCognitoDaoException {
		try {
			return (List<Post>) this.getEm().createQuery("SELECT p FROM PostImpl p WHERE p.salon = :salon")
					.setParameter("salon", salon).getResultList();
		} catch (Exception e) {
			throw new InCognitoDaoException("Erreur obtenir pour un salon", e);
		}
	}

}
