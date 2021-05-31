package edu.bd.myProject.core.service.impl;


import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceU;

@ServiceU
public class CoreUserService implements CoreService {

	private Compte user;

	public void setUser(Compte user) {
		this.user = user;
	}

	@Override
	public String getDashboard() {
		return "userDahboard";
	}

	@Override
	public Compte getUser() {
		return this.user;
	}

}
