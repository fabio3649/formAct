package pianoformativo.geneticalgorithm;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import model.entity.PercorsoFormativoEntity;
import model.entity.PreferenzaStudenteEntity;

/**
 * 
 * Classe che definisce il problema del piano formativo personalizzato
 * 
 */
public class PianoFormativoProblem<S> extends AbstractDoubleProblem {
	
	private ArrayList<Stato> spazioStati;
	private int sizeIndividui;
	private ArrayList<PreferenzaStudenteEntity> giorniLiberi;
	private ArrayList<String> interessi;
	private Map<Integer, String> categorie;
	
	public PianoFormativoProblem(ArrayList<Stato> spazioStati, int sizeIndividui, 
			ArrayList<PreferenzaStudenteEntity> giorniLiberi, ArrayList<String> interessi, 
			Map<Integer, String> categorie) {
		
		this.spazioStati = spazioStati;
		this.sizeIndividui = sizeIndividui;
		this.giorniLiberi = giorniLiberi;
		this.interessi = interessi; 
		this.categorie = categorie;
		
		// assegno un nome al problema
		setName("Piano formativo personalizzato problem");
		
		// setto il numero di variabili
		setNumberOfVariables(spazioStati.size());
		
		// inserisco i limiti inferiori e i limiti superiori 
		List<Double> lowerBounds = new ArrayList<>();
		List<Double> upperBounds = new ArrayList<>();
		for (int i = 0; i < this.sizeIndividui; i++) {
			lowerBounds.add(0.0D);
			upperBounds.add(spazioStati.size() + 0.0D);
		}
		setVariableBounds(lowerBounds, upperBounds);
		
		// problema mono-obiettivo
		setNumberOfObjectives(1);
	}
	
	
	
	
	public ArrayList<Stato> getSpazioStati() {
		return spazioStati;
	}

	public void setSpazioStati(ArrayList<Stato> spazioStati) {
		this.spazioStati = spazioStati;
	}

	public int getSizeIndividui() {
		return sizeIndividui;
	}

	public void setSizeIndividui(int sizeIndividui) {
		this.sizeIndividui = sizeIndividui;
	}

	public ArrayList<PreferenzaStudenteEntity> getGiorniLiberi() {
		return giorniLiberi;
	}

	public void setGiorniLiberi(ArrayList<PreferenzaStudenteEntity> giorniLiberi) {
		this.giorniLiberi = giorniLiberi;
	}

	public ArrayList<String> getInteressi() {
		return interessi;
	}

	public void setInteressi(ArrayList<String> interessi) {
		this.interessi = interessi;
	}

	public Map<Integer, String> getCategorie() {
		return categorie;
	}

	public void setCategorie(Map<Integer, String> categorie) {
		this.categorie = categorie;
	}
	
	@Override
	public DoubleSolution evaluate(DoubleSolution solution) {
		
		// Otteniamo la Codifica dell'individuo da valutare
		ArrayList<Double> codifica = (ArrayList<Double>) solution.variables();
		ArrayList<String> nomiAccettati = new ArrayList<>();
		Map<String, HashSet<LocalTime>> giorniConOrari = new HashMap<>();
		int punteggio = 0;
		
		for (Double el : codifica) {
			
			int punteggioGene = 0;
			Integer gene = el.intValue();
			// otteniamo il nome del percorso formativo dello stato
			String nomePercorso = this.getSpazioStati().get(gene).getPercorsoFormativo().getNome().toLowerCase();
			String giorno = this.getSpazioStati().get(gene).getGiorno().toLowerCase();
			LocalTime orario = this.getSpazioStati().get(gene).getOrario();
			
			if (!giorniConOrari.containsKey(giorno)) {
				giorniConOrari.put(giorno, new HashSet<LocalTime>());
			}
			if (!nomiAccettati.contains(nomePercorso) && !giorniConOrari.get(giorno).contains(orario)) {
				nomiAccettati.add(nomePercorso);
				giorniConOrari.get(giorno).add(orario);
				if (orario.getHour() < 23) {
					giorniConOrari.get(giorno).add(orario.plusHours(1));
				}
				if (orario.getHour() > 0) {
					giorniConOrari.get(giorno).add(orario.plusHours(-1));
				}
				
				punteggioGene = this.calcolaPunteggioGene(gene);
				
				punteggio += punteggioGene;
			}
			
			// assegnamo il punteggio del gene allo stato collegato
			this.getSpazioStati().get(gene).setPunteggio(punteggio);
			
		}
		
		solution.objectives()[0] = -punteggio;
		return solution;
		
	}
	
	
	
	
	/**
	 * Metodo che calcola il punteggio del gene.
	 * @param gene: gene da valutare
	 * @return il punteggio del gene
	 */
	private int calcolaPunteggioGene (Integer gene) {
		// all'inizio punteggio = 10
		int punteggio = 10;
		
		// otteniamo il contenuto del gene (uno stato)
		Stato contenutoGene = this.getSpazioStati().get(gene);
		
		// otteniamo il percorsoFormativo dello stato
		PercorsoFormativoEntity percorsoFormativo = contenutoGene.getPercorsoFormativo();
		
		// otteniamo il nome, l'indice dei contenuti, l'id della categoria e il nome della categoria del percorso formativo
		String nome = percorsoFormativo.getNome().toLowerCase();
		String indiceContenuti = percorsoFormativo.getIndice_contenuti().toLowerCase();
		Integer idCategoria = percorsoFormativo.getCategoria();
		String nomeCategoria = categorie.get(idCategoria).toLowerCase();
		
		// Scorro tutti gli interessi dello studente
		for (String interesse : this.getInteressi()) {
			int posizioneParentesi = interesse.indexOf("(");
			String categoriaInteresse = "";
			if (posizioneParentesi >= 0) {
				categoriaInteresse = interesse.substring(posizioneParentesi + 1, interesse.length() - 1).toLowerCase();
				interesse = interesse.substring(0, posizioneParentesi-1).toLowerCase();
			}
			
			
			
			int p = (nome.contains(interesse.toLowerCase()) || nome.contains(categoriaInteresse.toLowerCase())) ? 10 : 5;
			punteggio += p;
			//System.out.println(nome + "  |  " + interesse.toLowerCase() + "  |  " + p + "  |  " + punteggio);
			
			p = (indiceContenuti.contains(interesse)) ? 10 : 5;
			punteggio += p;
			//System.out.println(indiceContenuti + "  |  " + interesse.toLowerCase() + "  |  " + p + "  |  " + punteggio);
			
			p = (nomeCategoria.contains(categoriaInteresse)) ? 10 : 5;
			punteggio += p;
			//System.out.println(nomeCategoria + "  |  " + interesse.toLowerCase() + "  |  " + p + "  |  " + punteggio);
		}
				
		// ritorno il punteggio del gene
		return punteggio;
	}
	
}









