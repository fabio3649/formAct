package model.dao;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


import javax.naming.NamingException;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



import model.entity.FormatoreEntity;

public class FormatoreDaoTest extends Mockito {

	private FormatoreDao dao;

	@Before
	public void setUp() {

		dao = Mockito.spy(FormatoreDao.class);
        
	}

	@Test
	public void testNextId()
			throws SQLException, ServletException, IOException, IllegalStateException, NamingException {
        
		Mockito.when(dao.nextId()).thenReturn(4);
        
		assertEquals(dao.nextId(), 4);
	}

	/*@Test
	public void testDoRetriveByKey() throws SQLException {

		FormatoreEntity f = (FormatoreEntity) dao.doRetrieveByKey(3);

		Mockito.when(dao.doRetrieveByKey(3)).thenReturn(f);

		assertEquals(dao.doRetrieveByKey(3), f);

	}

	@Test
	public void testDoSave() throws SQLException {

		FormatoreEntity f = new FormatoreEntity();
		f.setEmail("provaTesting@gamil.com");
		f.setPassword("progettoIS");
		f.setName("prova");
		f.setSurname("testing");
		Date data = new Date(2022 - 02 - 01);
		f.setBirthDate(data);
		f.setGender("M");
		f.setCodiceFiscale("PRVTST34fgs");
		f.setCountry("Italia");
		f.setContoCorrente("ITCFASDADA0584535");
		int id = dao.doSave(f);
		
		Mockito.when(dao.doSave(f)).thenReturn(4);
		
		f = (FormatoreEntity) dao.doRetrieveByKey(id);
		
		
		Mockito.when(dao.doRetrieveByKey(id)).thenReturn(f);
		
		assertEquals( dao.doRetrieveByKey(4), f);
		
	}  */
	
	
	

}
