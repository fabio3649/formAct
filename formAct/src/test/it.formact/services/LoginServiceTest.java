package services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import autenticazione.service.LoginServices;
import model.dao.FormatoreDao;

public class LoginServiceTest {
	
	private LoginServices ls;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private FormatoreDao dao;
	private Connection conn;
	
	@Before
	public void setUp() {
		
		req = Mockito.mock(HttpServletRequest.class);
		conn = Mockito.mock(Connection.class);
		dao=Mockito.mock(FormatoreDao.class);
		req.setAttribute("email","ciao");
		req.setAttribute("password", "ue");
		ls = Mockito.spy(new LoginServices());
	}
	
	@Test
	public void testCheckTrainerLogin() throws SQLException {
		
		
		doReturn(dao).when(ls).createDaoF();
		boolean result = ls.checkTrainerLogin(req);
		
		assertEquals(result,false);
		
	}
	
	

	
	
}
