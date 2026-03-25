package cartes;

public abstract class Carte {
	public Carte carte;

	public Carte() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Carte carte) {
			return toString().equals(carte.toString());
		}
		return false;
	}
	
}
