package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

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
	
	public Boolean peutAvancer() {
		Bataille feuVert = new Parade(Type.FEU);
		return !pileBataille.isEmpty() && pileBataille.getLast().equals(feuVert);
	}
	
	private Boolean estDepotFeuVertAutorise() {
		Bataille feuRouge = new Attaque(Type.FEU);
		Bataille feuVert = new Parade(Type.FEU);
		if (pileBataille.isEmpty()) {
	        return true;
	    }
		Bataille sommetPile = pileBataille.getLast();
		return sommetPile.equals(feuRouge) || (sommetPile instanceof Parade && !sommetPile.equals(feuVert));
	}
	
	private Boolean estDepotBorneAutorise(Borne borne) {
		if (!pileBataille.isEmpty()) {
			return !(pileBataille.getLast() instanceof Attaque);
	    }
		return borne.getKm() < donnerLimitationVitesse() && (borne.getKm() + donnerKmParcourus() < 1000);
	}
	
	private Boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) return pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite;
		return pileLimite.getLast() instanceof DebutLimite;
	}
	
	private Boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque) {
			if (!pileBataille.isEmpty()) {
				return !(pileBataille.getLast() instanceof Attaque);
		    }
		} else {
			Bataille feuVert = new Parade(Type.FEU);
			if (bataille.equals(feuVert)){
				return estDepotFeuVertAutorise();
			}
			return !pileBataille.isEmpty() && pileBataille.getLast().getType().equals(bataille.getType());
		}
		return false;
	}
	
	public Boolean estDepotAutorise(Carte carte) {
	    if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
	    if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
	    if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
	    return false;
	}	
}

