package pianoformativo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.expression.spel.ast.Selection;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GenerationalGeneticAlgorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.example.AlgorithmRunner;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.crossover.impl.NPointCrossover;
import org.uma.jmetal.operator.crossover.impl.NullCrossover;
import org.uma.jmetal.operator.crossover.impl.TwoPointCrossover;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.NonUniformMutation;
import org.uma.jmetal.operator.mutation.impl.NullMutation;
import org.uma.jmetal.operator.mutation.impl.SimpleRandomMutation;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.operator.selection.impl.BestSolutionSelection;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.operator.selection.impl.DifferentialEvolutionSelection;
import org.uma.jmetal.operator.selection.impl.NaryRandomSelection;
import org.uma.jmetal.operator.selection.impl.RandomSelection;
import org.uma.jmetal.operator.selection.impl.RankingAndCrowdingSelection;
import org.uma.jmetal.operator.selection.impl.RankingAndDirScoreSelection;
import org.uma.jmetal.operator.selection.impl.SpatialSpreadDeviationSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.integersolution.IntegerSolution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.ObjectiveComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.evaluator.impl.MultiThreadedSolutionListEvaluator;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.IscrizioneDao;
import model.dao.PianoFormativoDao;
import model.dao.PreferenzaStudenteDao;
import model.entity.FormatoreEntity;
import model.entity.IscrizioneEntity;
import model.entity.PreferenzaStudenteEntity;
import model.entity.StudenteEntity;
import pianoformativo.geneticalgorithm.*;


public class PianoFormativoService implements Service {
	Action ottieniPianoPage = new Action("/formAct/view/pianoformativo/OttieniPianoFormativo.jsp", true, true);
	Action pianoPage = new Action("/formAct/view/pianoformativo/PianoFormativo.jsp", true, true);
	Action errorPage = new Action("/formAct/view/messagePages/Errori.jsp", true, true);

	public PianoFormativoService() {

	}

	@Override 
	public Action process(String serviceName , HttpServletRequest req, HttpServletResponse res) {
		if (serviceName.equalsIgnoreCase("PianoService")) {
			try {
				if(pianoFormativo(req)) {
					// Richiesta effettuata con successo
					return pianoPage;
				}
				else {
					req.getSession().setAttribute("errorePianoFormativo", "true");
					return errorPage;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (serviceName.equalsIgnoreCase("OttieniPianoService")) {
			try {
				if(ottieniPianoFormativo(req)) {
					// Richiesta effettuata con successo
					return ottieniPianoPage;
				}
				else {
					req.getSession().setAttribute("errorePianoFormativo", "true");
					return errorPage;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return errorPage; 
	}

	@Override
	public Action getErrorAction() {
		return errorPage;
	}
	
	public boolean pianoFormativo(HttpServletRequest req) throws SQLException {
		
		String ruolo = (String) req.getSession().getAttribute("role");
		
		if (ruolo == null || !ruolo.equalsIgnoreCase("Studente")) {
			return false;
		}
		
		PianoFormativoDao pianoFDao = new PianoFormativoDao();
		PreferenzaStudenteDao preferenzaSDao = new PreferenzaStudenteDao();
		int idStudente = (int) req.getSession().getAttribute("currentId");
		ArrayList<String> interessi = pianoFDao.doRetrieveInteressiStudente(idStudente);
		ArrayList<PreferenzaStudenteEntity> giorniLiberi = preferenzaSDao.doRetrieveByStudent(idStudente);
		
		req.getSession().setAttribute("interessi", interessi);
		req.getSession().setAttribute("giorniLiberi", giorniLiberi);
		
		return true;
		
	}
	
	public boolean ottieniPianoFormativo(HttpServletRequest req) throws SQLException {
		
		String ruolo = (String) req.getSession().getAttribute("role");
		
		if (ruolo == null || !ruolo.equalsIgnoreCase("Studente")) {
			return false;
		}
		
		PreferenzaStudenteDao preferenzaSDao = new PreferenzaStudenteDao();
		PianoFormativoDao pianoFDao = new PianoFormativoDao();		
		IscrizioneDao iscrizioneDao = new IscrizioneDao();
		
		Map<Integer, String> formatori = pianoFDao.doRetrieveFormatori();
		req.getSession().setAttribute("formatori", formatori);
		
		// Ottengo l'id dello studente della sessione
		int idStudente = (Integer) req.getSession().getAttribute("currentId");
		
		// ottengo i giorni liberi dello studente
		ArrayList<PreferenzaStudenteEntity> giorniLiberi = 
				(ArrayList<PreferenzaStudenteEntity>) preferenzaSDao.doRetrieveByStudent(idStudente);
		
	
		// ottengo le iscrizioni dello studente
		ArrayList<IscrizioneEntity> iscrizioni = null;
		try {
			iscrizioni = (ArrayList<IscrizioneEntity>) iscrizioneDao.doRetrieveByStudent(idStudente);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		} 
	
		// Ottengo lo spazio degli stati tramite i giorni liberi e le iscrizioni dello studente. 
		// Ogni stato avrà un percorso formativo tale che:
		// - è insegnato in un giorno libero dello studente;
		// - non fa parte delle sue iscrizioni.
		ArrayList<Stato> spazioStati = pianoFDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
	
		// Mi ricavo gli interessi dello studente
		ArrayList<String> interessi = pianoFDao.doRetrieveInteressiStudente(idStudente);

		// mi ricavo le categorie di percorsi formativi
		Map<Integer, String> categorie = pianoFDao.doRetrieveCategorie();
		
		// ottengo la soluzione.
		Soluzione soluzione = this.ottieniSoluzione(spazioStati, giorniLiberi, interessi, categorie);
		
		req.getSession().setAttribute("soluzione", soluzione);
		
		// elimino gli stati della soluzione con i percorsi formativi duplicati 
		soluzione.deletePercorsiDuplicatiByNome();
		
		// ordino la soluzione in base ai suoi punteggi
		soluzione.selectionSort(soluzione.getStati().size(), false, "Punteggio");
		
		int numeroStatiDaOrdinare = 0;
		// se invece |soluzione| >= 5 allora il numero dei primi stati da ordinare è uguale a 4
		// se 1 <= |soluzione| < 5 allora il numero dei primi stati da ordinare è uguale al |soluzione|
		numeroStatiDaOrdinare = (soluzione.getSize() >= 5) ? 4 : soluzione.getSize();
		
		// ordino i primi numeroStatiDaOrdinare stati della soluzione in base al giorno e all'orario
		soluzione.selectionSort(numeroStatiDaOrdinare, true, "GiornoAndOrario");
		
		// ritorno la soluzione
		req.getSession().setAttribute("soluzione", soluzione);
		
		return true;
	}
	
	/**
	 * Metodo che ritorna un piano formativo personalizzato.
	 * 
	 * @param spazioStati: spazio degli stati
	 * @param giorniLiberi: giorni liberi dello studente
	 * @param interessi: interessi dello studente
	 * @param categorie: categorie di percorsi formativi
	 * 
	 * @return un piano formativo personalizzato
	 */
	public Soluzione ottieniSoluzione(ArrayList<Stato> spazioStati,
			ArrayList<PreferenzaStudenteEntity> giorniLiberi, ArrayList<String> interessi, Map<Integer, String> categorie) {
				
		/********************  Inizializzo parametri algoritmo genetico  ********************/
		
		// Assegniamo una probabilità al crossover e alla mutazione
		double probabilitaCrossover = 0.8;
		double probabilitaMutazione = 0.15;
		
		// mi ricavo il numero di stati nel vettore spazio stati
		int sizeSpazioStati = (spazioStati == null) ? 0 : spazioStati.size();
		
		// se N < 1 allora ritorno un individuo vuoto
		// se 1 <= N < 11 allora ritorno un individuo con codifica = spazioStati 
		// se N >= 11 allora sizeIndividui = 10 ed eseguo l'algoritmo genetico.
		int sizeIndividui;
		if (sizeSpazioStati >= 11) { 
			sizeIndividui = 10; 
		}
		else {
			return (sizeSpazioStati < 1) ? new Soluzione() : new Soluzione(spazioStati);
		}
		
		// definisco la valutazione massima
		int valutazioneMassima = (10 + (10 * interessi.size() * 3)) * 1000;
		
		// definisco il numero totale di individui (popolazione): sizePopolazione = |popolazione|.
		int sizePopolazione = ( (sizeSpazioStati/3) % 2 == 0 ) ? sizeSpazioStati/3 : (sizeSpazioStati/3)+1;
		
		Problem<DoubleSolution> pianoFormativoPersonalizzatoProblem = new PianoFormativoProblem<>
				(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);////
		
		BinaryTournamentSelection<DoubleSolution> selection = new BinaryTournamentSelection<>();
		CrossoverOperator<DoubleSolution> crossover = new UniformCrossover<>(probabilitaCrossover);
		MutationOperator<DoubleSolution> mutation = new SimpleRandomMutation(probabilitaMutazione);
		
		// valutazione di tipo sequenziale
		SolutionListEvaluator<DoubleSolution> evaluator = new SequentialSolutionListEvaluator<>();
		long maxComputingTime = 4000;
		GeneticAlgorithm<DoubleSolution> algoritmoGenetico = 
				new GeneticAlgorithm<DoubleSolution> (pianoFormativoPersonalizzatoProblem, valutazioneMassima, sizePopolazione, 
						crossover, mutation, selection, evaluator, maxComputingTime);
        
		/************************************************************************************/
        
        /***************************  Eseguo l'algoritmo genetico  **************************/
        
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algoritmoGenetico).execute();
        // miglior individuo ottenuto dall'esecuzione dell'algoritmo genetico (non per forza l'ottimo)
        DoubleSolution bestIndividual = algoritmoGenetico.getResult();
        if (algoritmoGenetico.getBestIndividual().objectives()[0] < bestIndividual.objectives()[0]) {
        	bestIndividual = algoritmoGenetico.getBestIndividual();
        }
        
        // Stampo a video delle informazioni inerenti l'algoritmo genetico e la sua esecuzione
        JMetalLogger.logger.info(String.format("Problem: %s", pianoFormativoPersonalizzatoProblem.getName()));
        JMetalLogger.logger.info(String.format("Solution: %s", bestIndividual.variables()));
        JMetalLogger.logger.info(String.format("Evaluation: %s", Arrays.toString(bestIndividual.objectives())));
        JMetalLogger.logger.info(String.format("Total execution time: %s ms", algorithmRunner.getComputingTime()));
        JMetalLogger.logger.info(String.format("Description: %s", algoritmoGenetico.getDescription()));
        
        /************************************************************************************/
		
        /*******************  Ottengo e ritorno la soluzione del problema  ******************/
        
        // Ottengo gli indici dell'individuo migliore
        List<Double> indici = (ArrayList<Double>) bestIndividual.variables();
        
        // Ottengo gli stati degli indici
        ArrayList<Stato> stati = new ArrayList<>();
        for (int i = 0; i < indici.size(); i++) {
			stati.add(spazioStati.get(indici.get(i).intValue()));
		}
        
        // ritorno l'individuo migliore sotto forma di soluzione
        return new Soluzione(stati);
        
        /************************************************************************************/
	}
	
}









