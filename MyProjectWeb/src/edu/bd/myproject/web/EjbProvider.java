package edu.bd.myproject.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class EjbProvider {

	// Constants
	// ----------------------------------------------------------------------------------

	private static final String EJB_CONTEXT;

	static {
		try {
			EJB_CONTEXT = "java:global/" + new InitialContext().lookup("java:app/AppName") + "/";
		} catch (NamingException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	// Constructors
	// -------------------------------------------------------------------------------

	private EjbProvider() {
		// Utility class, so hide default constructor.
	}

	@SuppressWarnings("unchecked") // Because of forced cast on (T).
	public static <T> T lookup(Class<T> ejbClass, String jndi) {

		try {
			// Do not use ejbClass.cast(). It will fail on local/remote interfaces.
			return (T) new InitialContext().lookup(jndi);
		} catch (NamingException e) {
			throw new IllegalArgumentException(String.format("Cannot find EJB class %s in JNDI %s", ejbClass, jndi), e);
		}
	}

}
