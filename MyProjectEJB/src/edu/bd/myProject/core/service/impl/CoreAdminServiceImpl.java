package edu.bd.myProject.core.service.impl;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.CoreService;
import edu.bd.myProject.core.service.ServiceA;

@ServiceA
public class CoreAdminServiceImpl implements CoreService {

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

}
