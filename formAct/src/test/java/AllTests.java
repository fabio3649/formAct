 import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import control.AdminServletTest;
import control.AutenticazioneServletTest;
import control.GestionePFServletTest;
import control.PercorsoFormativoServletTest;
import control.PianoFormativoServletTest;
import control.RegisterServletTest;
import control.ServiziUtenteServletTest;
import geneticalgorithm.GeneticAlgorithmTest;
import geneticalgorithm.PianoFormativoProblemTest;
import geneticalgorithm.SoluzioneTest;
import geneticalgorithm.StatoTest;
import geneticalgorithm.UniformCrossoverTest;
import model.CategoriaDaoTest;
import model.DisponibilitaDaoTest;
import model.FormatoreDaoTest;
import model.InteresseDaoTest;
import model.InteresseStudenteDaoTest;
import model.IscrizioneDaoTest;
import model.PercorsoFormativoDaoTest;
import model.PreferenzaStudenteDaoTest;
import model.StudenteDaoTest;
import model.ValutazioneDaoTest;
import services.AutenticazioneServiceTest;
import services.CreatorServiceTest;
import services.ModificaProfiloServiceTest;
import services.PercorsoFormativoServiceTest;
import services.PianoFormativoServiceTest;
import services.RegisterServiceTest;
import testUtility.InserimentiTest;



@RunWith(Suite.class)
@SuiteClasses({
	RegisterServiceTest.class, RegisterServletTest.class ,  AutenticazioneServiceTest.class,  ModificaProfiloServiceTest.class,
	CategoriaDaoTest.class, CreatorServiceTest.class, DisponibilitaDaoTest.class,
	FormatoreDaoTest.class, StudenteDaoTest.class, 
	InteresseDaoTest.class, PercorsoFormativoDaoTest.class, GestionePFServletTest.class,
	PercorsoFormativoServiceTest.class,  IscrizioneDaoTest.class,
	PercorsoFormativoServletTest.class, PreferenzaStudenteDaoTest.class, AutenticazioneServletTest.class, 
	AdminServletTest.class, ServiziUtenteServletTest.class, InteresseStudenteDaoTest.class, ValutazioneDaoTest.class,
	InserimentiTest.class, UniformCrossoverTest.class, StatoTest.class, SoluzioneTest.class, GeneticAlgorithmTest.class,
	PianoFormativoProblemTest.class, PianoFormativoServiceTest.class, PianoFormativoServletTest.class
	
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


