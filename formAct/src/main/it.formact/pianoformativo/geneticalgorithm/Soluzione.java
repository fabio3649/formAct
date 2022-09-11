package pianoformativo.geneticalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import model.entity.PercorsoFormativoEntity;

/**
 * 
 * Classe che rappresenta una soluzione.
 * 
 */
public class Soluzione {
	
	private ArrayList<Stato> stati;
	
	/**
	 * Costruttore della classe Soluzione.
	 */
	public Soluzione () { 
		this.stati = new ArrayList<>(); 
	}
	
	/**
	 * Costruttore della classe Soluzione.
	 * 
	 * @param  stati gli stati della soluzione.
	 */
	public Soluzione (ArrayList<Stato> stati) {
		this.stati = stati;
	}
	
	/**
	 * Metodo che ritorna gli stati della soluzione.
	 * 
	 * @return  gli stati della soluzione.
	 */
	public ArrayList<Stato> getStati() {
		return stati;
	}
	
	/**
	 * Metodo che modifica gli stati della soluzione.
	 * 
	 * @param  stati nuovi stati della soluzione.
	 */
	public void setStati(ArrayList<Stato> stati) {
		this.stati = stati;
	}
	
	/**
	 * Metodo che restituisce uno stato della soluzione.
	 * 
	 * @param  i indice stato.
	 * 
	 * @return  lo stato i-esimo della soluzione.
	 */
	public Stato getStato(int i) {
		return this.getStati().get(i);
	}
	
	/**
	 * Metodo che modifica lo stato i-esimo della soluzione.
	 * 
	 * @param  i indice stato.
	 * @param  stato nuovo stato.
	 */
	public void setStato(int i, Stato stato) {
		this.getStati().set(i, stato);
	}
	
	/**
	 * Metodo che ritorna il numero di stati della soluzione.
	 * 
	 * @return  il numero di stati della soluzione.
	 */
	public int getSize() {
		return this.getStati().size();
	}
	
	/**
	 * Metodo che elimina i percorsi con i nomi duplicati tramite il nome.
	 */
	public void deletePercorsiDuplicatiByNome () {
		
		ArrayList<Stato> b = new ArrayList<>();
		
		for (int i = 0; i < this.getSize(); i++) {
			
			Stato el = this.getStato(i);
			
			b.add(el);
			
			int j = i + 1;
			
			while(j < this.getSize()) {
				
				String nome1 = el.getPercorsoFormativo().getNome();
				String nome2 = this.getStato(j).getPercorsoFormativo().getNome();
				
				if (nome1.equalsIgnoreCase(nome2)) {
					this.getStati().remove(j);
				}
				
				else {
					j = j + 1;
				}
				
			}
			
		}
		
		this.setStati(b);
		
	}
	
	/**
	 * Metodo che ordina gli stati della soluzione utilizzando l'algoritmo di ordinamento selectionSort.
	 * 
	 * @param  numeroStatiDaOrdinare il numero di stati da ordinare (i primi)
	 * @param  crescente valore booleano che indica se l'ordinamento è crescente oppure decrescente. E' uguale a:
	 *         <ul>
	 *           <li>true: se vogliamo effettuare un ordinamento crescente</li>
	 *           <li>false: se vogliamo effettuare un ordinamento decrescente.</li>
	 *         </ul>
	 * @param  cosaOrdinare stringa che indica cosa ordinare. E' uguale ad una delle seguenti stringhe:
	 *         <ul>
	 *           <li>"Giorno": se vogliamo ordinare gli stati tramite i loro giorni;</li>
	 *           <li>"Orario": se vogliamo ordinare gli stati tramite i loro orari;</li>
	 *           <li>"Punteggio": se vogliamo ordinare gli stati tramite i loro punteggi;</li>
	 *           <li>"GiornoAndOrario": se vogliamo ordinare gli stati tramite i loro giorni e i loro orari (insieme).</li>
	 *         </ul>
	 */
	public void selectionSort(int numeroStatiDaOrdinare, boolean crescente, String cosaOrdinare) {
		
		if (numeroStatiDaOrdinare < 0 || numeroStatiDaOrdinare > stati.size()) {
			throw new IllegalStateException("Il parametro numeroStatiDaOrdinare non è valido");
		}
		
		for(int i = 0; i < numeroStatiDaOrdinare - 1; i++) {
			
			int k = i;
			
			for (int j = i+1; j < numeroStatiDaOrdinare; j++) {
				
				Stato stato_k = this.getStato(k);
				Stato stato_j = this.getStato(j);
			    
				if (       (crescente && stato_k.compare(stato_j, cosaOrdinare) > 0)
						|| (!crescente && stato_k.compare(stato_j, cosaOrdinare) < 0)) {
					
					k = j;
					
				}
				
			}
			
			if (k != i) {
				
				Stato tmp = this.getStato(i);
				this.setStato(i, this.getStato(k));
				this.setStato(k, tmp);
				
			}
			
		}
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Soluzione)) {
			return false;
		}
		
		Soluzione soluzione2 = (Soluzione) o;
		
		if (this.getSize() != soluzione2.getSize()) {
			return false;
		}
		
		for (int i = 0; i < this.getSize(); i++) {
			
			Stato stato1 = this.getStato(i);
			Stato stato2 = soluzione2.getStato(i);
			
			if (
					   !stato1.getGiorno().equals(stato2.getGiorno())
					|| !stato1.getOrario().equals(stato2.getOrario())
					|| stato1.getPunteggio() != stato2.getPunteggio()
					|| !stato1.getPercorsoFormativo().equals(stato2.getPercorsoFormativo())
			) {
				return false;
			}
			
		}
		
		return true;
	}
	
}









