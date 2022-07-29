import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import model.CategoriaDaoTest;
import model.CertificazioneDaoTest;
import model.DisponibilitaDaoTest;
import model.FormatoreDaoTest;
import model.InteresseDaoTest;
import model.PercorsoFormativoDaoTest;
import model.StudenteDaoTest;
import services.AutenticazioneServiceTest;
import services.CreatorServiceTest;
import services.ModificaProfiloServiceTest;
import services.RegisterServiceTest;


@RunWith(Suite.class)
@SuiteClasses({
	RegisterServiceTest.class, AutenticazioneServiceTest.class, ModificaProfiloServiceTest.class,
	CategoriaDaoTest.class, CreatorServiceTest.class, DisponibilitaDaoTest.class,
	FormatoreDaoTest.class, StudenteDaoTest.class, CertificazioneDaoTest.class,
	InteresseDaoTest.class, PercorsoFormativoDaoTest.class
})
public class AllTests {
	
	public AllTests () {
		
		// metodo init ()  
		//esecuzione script ( file.sql con istruzioni di delete delle tabelle )
		// FileInputStream in = new FileInputStream("./script.sql"); file nella cartella della classe che esegue lo script
		// AllTests.getClass().getResourceAsStream("file.sql")
		// StringBuffer r = lettura file;
		//
		////////////////////////////////////////////////////////////////////////
		super();
	}
    
}


