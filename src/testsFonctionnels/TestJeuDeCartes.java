package testsFonctionnels;

import cartes.JeuDeCartes;
import jeu.Jeu;

public class TestJeuDeCartes {
	public static void main(String[] args) {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Jeu jeu = new Jeu();
		System.out.println("JEU:\n" + jeuDeCartes.affichageJeuCartes());
		
		System.out.println("Test checkCount : " + jeuDeCartes.checkCount());
		
		
	}
}
