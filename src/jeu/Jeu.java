package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	Sabot sabot;
	public Jeu() {
		 JeuDeCartes jeuDeCartes = new JeuDeCartes();
		 List<Carte> listeCartes = new ArrayList<>();
		 
		 Carte[] tableauCartes = jeuDeCartes.donnerCartes();
		 
		 Collections.addAll(listeCartes,tableauCartes);
		 
		 GestionCartes.melanger(listeCartes);
		 
		 this.sabot = new Sabot(listeCartes.toArray(new Carte[tableauCartes.length]));
	}
}
