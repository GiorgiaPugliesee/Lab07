package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	List<PowerOutage> migliore;
	int numeroPersone = 0;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public String trovaSequenza(Nerc nerc, int anni, int ore) {
		List<PowerOutage> outages = this.podao.getPowerOutagesNerc(nerc);
		List<PowerOutage> parziale = new LinkedList<>();
		
		cerca(parziale, outages, anni, ore, 0);
		
		String s = "Numero di persone coinvolte: " + calcolaPersone(migliore) + "\n" + "Ore totali di malfunzionamento: " + controlloOre(migliore) + "\n";
		for(PowerOutage p : migliore) {
			s += p.getStartDate().getYear() + " " + p.getStartDate() + p.getEndDate() + " " + p.getCustomersAffected() + "\n";
		}
		
		return s;
	}
	
	private void cerca(List<PowerOutage> parziale, List<PowerOutage> outages, int anni, int ore, int livello) {
		//condizioni di uscita
		
		if(parziale.size()>0 && (controlloOre(parziale) > ore || !controlloAnni(parziale, anni))) {
			//non ho una soluzione
			return;
		}
		
		if (parziale.size()>0 && (controlloOre(parziale) <= ore && controlloAnni(parziale, anni))) {
			//posso avere una soluzione
			int tot = calcolaPersone(parziale);
			if(tot > numeroPersone) {
				numeroPersone = tot;
				migliore = new LinkedList<>(parziale);
			}
		}
		
		if(livello == outages.size()) {
			return;
		}
		
		//se sono arrivato a questo punto posso continuare con la ricorsione
		for(PowerOutage p : outages) {
			if(!parziale.contains(p)) {
				parziale.add(p);
				cerca(parziale, outages, anni, ore, livello+1);
				parziale.remove(p);
			}
		}
	}

	private int calcolaPersone(List<PowerOutage> parziale) {
		int tot = 0;
		
		for(PowerOutage p : parziale) {
			tot += p.getCustomersAffected();
		}
		
		return tot;
	}
	
	private double controlloOre(List<PowerOutage> parziale) {
		int oreTot = 0;
		
		for(PowerOutage p : parziale) {
			oreTot += ChronoUnit.HOURS.between(p.getStartDate(), p.getEndDate());	
		}

		return oreTot;
	}
	
	private boolean controlloAnni(List<PowerOutage> parziale, int anni) {
		long differenza = ChronoUnit.YEARS.between(parziale.get(0).getStartDate(), parziale.get(parziale.size()-1).getEndDate());
		if(Math.abs(differenza) > anni ) {
			return false;
		}
		
		return true;
	}
	

}
