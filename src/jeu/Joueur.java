package jeu;

public class Joueur {
	private ZoneDeJeu zonedejeu;
	private String nom;
	
	public Joueur(String nom, ZoneDeJeu zonedejeu) {
		this.nom = nom;
		this.zonedejeu = zonedejeu;
	}
}
