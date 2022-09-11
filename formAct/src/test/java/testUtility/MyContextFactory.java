package testUtility;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;


public class MyContextFactory  implements javax.naming.spi.InitialContextFactory{

	@Override
	public Context getInitialContext(java.util.Hashtable environment) throws NamingException {
		// TODO Auto-generated method stub
		return new MyContext();
	}

}

