package edu.bd.myProject.services;

import javax.ejb.Local;

@Local
public interface TestService {

	public String doSomething(String test);

}
