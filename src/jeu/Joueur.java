package jeu;

import java.util.HashSet;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private ZoneDeJeu zonedejeu;
	private String nom;
	private MainJoueur mainjoueur;
	
	public Joueur(String nom, ZoneDeJeu zonedejeu, MainJoueur mainjoueur) {
		this.nom = nom;
		this.zonedejeu = zonedejeu;
		this.mainjoueur = mainjoueur;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Object joueur) {
			return toString().equals(joueur.toString());
		}
		return false;
	}
	
	public void donner(Carte carte) {
		mainjoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		Carte carte = null;
		if(sabot.estVide()) {
			return null;
		}
		carte = sabot.piocher();
		donner(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zonedejeu.donnerKmParcourus();
	}
	
	public Boolean estDepotAutorise(Carte carte) {
		return zonedejeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupPossibles (Set<Joueur> participants) {
		Set<Coup> ensembleCoupsValide = new HashSet<>();
		
		for (Joueur cible : participants) {
			for (Carte carte : mainjoueur) {
				Coup coup = new Coup(this, carte, cible);
	            if (coup.estValide()) ensembleCoupsValide.add(coup);
			}
		}
		return ensembleCoupsValide;
	}
	
}
