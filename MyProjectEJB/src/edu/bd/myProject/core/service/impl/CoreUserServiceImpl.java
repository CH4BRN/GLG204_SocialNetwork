package edu.bd.myProject.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceU;
import edu.bd.myProject.invitation.entity.Invitation;
import edu.bd.myProject.invitation.service.InvitationService;
import edu.bd.myProject.user.service.UserService;

@ServiceU
@Stateful
public class CoreUserServiceImpl implements CoreService {

	@Inject
	UserService userService;

	@Inject
	InvitationService invitationService;

	private Compte user;

	public void setUser(Compte user) {
		this.user = user;
	}

	@Override
	public String getDashboard() {
		return "userDashboard?faces-redirect=true";
	}

	@Override
	public Compte getUser() throws Exception {
		return this.user;
	}

	@Override
	public void supprimerSonCompte() {
		try {
			userService.supprimerSonCompte(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void refuserInvitation(String id) {
		try {
			invitationService.refuserInvitation(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Invitation> obtenirSesInvitations() throws Exception {
		if (user == null) {
			throw new Exception("User null ! ");
		}
		try {
			return invitationService.obtenirInvitationsPourCompte(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Invitation>();
		}
	}

	@Override
	public void seDeconnecter() {
		this.user = null;

	}

}
