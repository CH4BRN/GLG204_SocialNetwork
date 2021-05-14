package edu.bd.inCognito.test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ServiceImpl implements Service {

	@Override
	public String doSomething() {
		return "42";
	}
	
	 @PersistenceContext(unitName = "InCognito")
	    EntityManager em;
	 
	 

}
