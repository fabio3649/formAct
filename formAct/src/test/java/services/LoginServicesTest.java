package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import javax.tools.JavaCompiler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import autenticazione.service.LoginServices;
import model.dao.FormatoreDao;

public class LoginServicesTest extends Mockito{
	
	private LoginServices ls;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;
	private FormatoreDao dao;
	private Connection conn;
	private MockHttpSession mySess;
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		setupNaming();
		
		System.out.println("NAMING CREATO");
		req = new MockHttpServletRequest();
		System.out.println("REQUEST CREATO");
		mySess = new MockHttpSession();
		
		ls = new LoginServices();
		
	}
	
	@Test
	public void testCheckTrainerLoginCorrect() throws SQLException {
		
		req.addParameter("email","tesing@prova.it");
		req.addParameter("password","testing");
		
		assertEquals(ls.checkTrainerLogin(req),true);
		
		
		
	}
	
	@Test
	public void testCheckTrainerLoginError() throws SQLException {
		
		req.addParameter("email","ciao");
		req.addParameter("password","t");
		
		assertNotEquals(ls.checkTrainerLogin(req),true);
		
		
		
	}
	
	
	@Test
	public void testCheckStudentLoginCorrect() throws SQLException {
		
		req.addParameter("email","fabio.pica10@gmail.com");
		req.addParameter("password","testing");
		
		assertEquals(ls.checkStudentLogin(req),true);
	}
	
	
	@Test
	public void testCheckStudentLoginError() throws SQLException {
		
		req.addParameter("email","ciao");
		req.addParameter("password","t");
		
		assertNotEquals(ls.checkStudentLogin(req),true);
	}
	
	
	
	
	

	
}
