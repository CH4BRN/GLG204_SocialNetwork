package edu.bd.myProject.services.impl;

import javax.ejb.Stateless;

import edu.bd.myProject.services.TestService;

@Stateless
public class TestServiceImpl implements TestService {

	@Override
	public String doSomething(String test) {
		return ">>" + test + "<<";
	}

}
