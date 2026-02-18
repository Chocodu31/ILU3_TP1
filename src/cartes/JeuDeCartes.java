package cartes;

public class JeuDeCartes {
	Configuration config[];
	
	public String affichageJeuCartes() {
		StringBuilder text = null;
		for(int i = 0; i<config.length; i++) {
			text.append(config[i].getNbExemplaires()).append(" ").append(config[i].getCarte().toString());
		}
		return text.toString();
	}
	
	public Carte[] donnerCartes() {
		return null;
		
	}
	
	private class Configuration extends Carte {
		private int nbExemplaires;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
			super.carte = carte;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
		
		
	}
	
}
