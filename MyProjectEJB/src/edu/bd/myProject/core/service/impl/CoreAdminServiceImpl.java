package edu.bd.myProject.core.service.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import edu.bd.myProject.admin.service.AdminService;
import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceA;
import edu.bd.myProject.invitation.entity.Invitation;

@ServiceA
@Stateful
public class CoreAdminServiceImpl implements CoreService {

	@Inject
	AdminService adminService;

	private Compte user;

	@Override
	public String getDashboard() {
		return "adminDashboard?faces-redirect=true";
	}

	@Override
	public Compte getUser() {
		return this.user;
	}

	@Override
	public void setUser(Compte user) {
		this.user = user;
	}

	@Override
	public void supprimerSonCompte() {
		try {
			adminService.supprimerUnCompteUtilisateur(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refuserInvitation(String id) throws Exception {
		throw new Exception("Connecté en tant qu'admin.");
	}

	@Override
	public List<Invitation> obtenirSesInvitations() throws Exception {
		throw new Exception("Connecté en tant qu'admin.");
	}

	@Override
	public void seDeconnecter() {
		this.user = null;

	}

}
