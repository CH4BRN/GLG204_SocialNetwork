package edu.bd.myproject.web.posts.beans;

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
import edu.bd.myproject.web.salons.beans.CurrentSalonBean;
import edu.bd.myproject.web.url.beans.UrlCheckerBean;
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

	@Named
	@Inject
	UrlCheckerBean urlCheckerBean;

	private String newPostTitle;

	private String newPostBody;

	private boolean isALink = false;

	private boolean isAYoutubeLink = false;

	private String youtubeLink = "";

	public String getYoutubeLink() {
		String youtubeLink = urlCheckerBean.getYoutubeUrl(newPostBody);
		if (youtubeLink != null) {
			this.youtubeLink = youtubeLink;
		}
		return this.youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public boolean getIsALink() {

		isALink = urlCheckerBean.verifierUrl(newPostBody);
		if (isALink) {
			System.out.println("is " + newPostBody + " is a LINK ");
		}
		return isALink;
	}

	public boolean getIsAYoutubeLink() {
		if (this.newPostBody == null) {
			isAYoutubeLink = false;
			return isAYoutubeLink;
		}

		System.out.println("New post body : " + newPostBody);
		this.youtubeLink = urlCheckerBean.getYoutubeUrl(newPostBody);
		System.out.println("YT Link : " + this.youtubeLink);
		if (this.youtubeLink != null) {
			System.out.println( youtubeLink + " is a YOUTUBE LINK ");
			isAYoutubeLink = true;
		} else {
			isAYoutubeLink = false;
		}
		return isAYoutubeLink;
	}

	public void setALink(boolean isALink) {
		this.isALink = isALink;
	}

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
			profile = profileDao.obtenirPourUnCompteEtUnSalon(compte, salon);
			System.out.println("SALON : " + salon.toString());
			System.out.println("PROFILE : " + profile.getPseudo());
			System.out.println("TITLE : " + this.newPostTitle);
			System.out.println("BODY : " + this.newPostBody);

			Post post = postService.creerNouveauPost(salon, profile, this.newPostTitle, this.newPostBody,
					getIsAYoutubeLink() ? youtubeLink : null);
			this.newPostTitle = "";
			this.newPostBody = "";
			this.youtubeLink = "";
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
