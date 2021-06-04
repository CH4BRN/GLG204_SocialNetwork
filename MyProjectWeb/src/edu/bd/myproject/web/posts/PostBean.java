package edu.bd.myproject.web.posts;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.framework.dao.InCognitoDaoException;
import edu.bd.myProject.post.dao.PostsDao;
import edu.bd.myProject.post.entity.Post;
import edu.bd.myProject.post.service.PostService;
import edu.bd.myProject.profiles.dao.ProfileDao;
import edu.bd.myProject.profiles.entity.Profile;
import edu.bd.myProject.profiles.service.ProfileService;
import edu.bd.myProject.salons.entity.Salon;
import edu.bd.myproject.web.salons.CurrentSalonBean;
import edu.bd.myproject.web.utilisateur.beans.CurrentUserBean;

@Named("postBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class PostBean {

	@Inject
	PostsDao postDao;

	@Inject
	PostService postService;

	@Inject
	ProfileService profileService;

	@Inject
	ProfileDao profileDao;

	@Named
	@Inject
	CurrentSalonBean currentSalonBean;

	@Named
	@Inject
	CurrentUserBean currentUserBean;

	private String newPostTitle;

	private String newPostBody;

	public String getNewPostBody() {
		return newPostBody;
	}

	public void setNewPostBody(String newPostBody) {
		this.newPostBody = newPostBody;
	}

	public String getNewPostTitle() {
		return newPostTitle;
	}

	public void setNewPostTitle(String newPostTitle) {
		this.newPostTitle = newPostTitle;
	}

	public String publishNewPost() throws Exception {
		Salon salon = currentSalonBean.getThisSalon();
		Compte compte = currentUserBean.getCurrentAccount();

		Profile profile;
		try {
			profile = profileDao.obtenirPourUnCompteEtUnSalon(compte.getId(), salon.getId());
			Post post = postService.creerNouveauPost(salon, profile, this.newPostTitle, this.newPostBody);
			currentSalonBean.rafraichirPosts();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(((HttpServletRequest) externalContext.getRequest()).getRequestURI());

			return "currentSalon";
		} catch (InCognitoDaoException e) {
			e.printStackTrace();
			return "";
		}

	}

}
