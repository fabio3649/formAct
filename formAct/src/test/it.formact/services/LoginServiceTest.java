package services;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import autenticazione.service.LoginServices;

public class LoginServiceTest {
	
	private LoginServices ls;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	
	@Before
	public void setUp() {
		
		req = Mockito.mock(HttpServletRequest.class);
		req.setAttribute("email","ciao");
		req.setAttribute("password", "ue");
		ls = Mockito.mock(LoginServices.class);
	}
	
	@Test
	public void testCheckTrainerLogin() throws SQLException {
		
		Mockito.when(ls.checkTrainerLogin(req)).thenReturn(true);		
		assertEquals( ls.checkTrainerLogin(req) , true  );
		
	}

}
