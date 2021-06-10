package edu.bd.myproject.web.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

import edu.bd.myProject.admin.service.AdminService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.compte.service.CompteService;
import edu.bd.myproject.web.navigation.beans.NavigationBean;

@Named("adminBean")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class AdminBean implements Serializable {

	@Named
	@Inject
	NavigationBean navigationBean;

	private Compte user;

	public Compte getUser() {
		return user;
	}

	public void setUser(Compte user) {
		this.user = user;
		this.login = user.getLogin();
	}

	@Inject
	private AdminService adminService;

	public String seDeconnecter() {
		this.adminService.seDeconnecter();
		return navigationBean.getIndex();
	}

	public void rafraichirListe() {
		List<Compte> comptes;
		try {
			comptes = compteService.obtenirTousLesComptes();
			this.comptes = new ArrayList<Compte>();
			this.comptes.addAll(comptes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Compte> comptes = new ArrayList<Compte>();

	public ArrayList<Compte> getComptes() {
		rafraichirListe();
		return comptes;
	}

	public void setComptes(ArrayList<Compte> comptes) {
		this.comptes = comptes;
	}

	@Inject
	CompteService compteService;

	public void makeUserAdmin(String id) {
		this.adminService.makeUserAdmin(id);
		rafraichirListe();
	}

	public void makeUserActive(String id) {
		this.adminService.makeUserActif(id);
		rafraichirListe();

	}

	public void supprimer(String id) {
		try {
			this.adminService.supprimer(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rafraichirListe();

	}

}
