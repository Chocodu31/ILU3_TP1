package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileBataille;
	private Collection<Borne> collecBorne;
	
	public ZoneDeJeu() {
		this.pileLimite = new ArrayList<>();
		this.pileBataille = new ArrayList<>();
		this.collecBorne = new ArrayList<>();
	}
	
	public int donnerLimitationVitesse() {
		int limite = 50;
		Limite finLimite = new FinLimite();
		if(pileLimite.isEmpty() || pileLimite.getLast().equals(finLimite)){
			limite = 200;
		}

		return limite;
	}
	
	public int donnerKmParcourus() {
		int nbkm=0;
		for(Borne borne : collecBorne) nbkm+=borne.getKm();
		return nbkm;
	}
	
	public void deposer(Carte c) {
		if(c.toString().contains("Km")) {
			collecBorne.add((Borne) c);
		} else if (c.toString().contains("Début limite") || c.toString().contains("Fin limite")) {
			pileLimite.add((Limite) c);
		} else {
			pileBataille.add((Bataille) c);
		}
	}
}

