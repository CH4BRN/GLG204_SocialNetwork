package edu.bd.myProject.core.service.impl;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import edu.bd.myProject.compte.entity.Compte;
import edu.bd.myProject.core.service.ServiceA;
import edu.bd.myProject.core.service.CoreService;

@ServiceA
public class CoreAdminServiceImpl implements CoreService {

	private Compte user;

	@Override
	public String getDashboard() {
		return "adminDashboard";
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
