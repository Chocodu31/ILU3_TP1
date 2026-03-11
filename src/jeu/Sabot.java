package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes = new Carte[106];
	private int nbCartes = 106;
	private int nbModif = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte nouvCarte) {
		if (nbCartes < 106) {
			cartes[nbCartes] = nouvCarte;
			nbModif++;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public Iterator<Carte> iterator(){
		return new Iterator<Carte>() {
			private int iterateur = 0;
			private int dernierRenvoye = -1;
			private boolean nextEffectue = false;
			private int nbModifAttendu = nbModif;

			@Override
			public Carte next() { 
				if (nbModif != nbModifAttendu) {
					throw new ConcurrentModificationException();
				}
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				nextEffectue = true;
				dernierRenvoye = iterateur;
				return cartes[iterateur++];
			}
			
			@Override
			public boolean hasNext() {
				return iterateur < 106;
			}
			
			@Override
			public void remove() {
				if(!nextEffectue) {
					throw new IllegalStateException();
				} if (nbModif != nbModifAttendu) {
					throw new ConcurrentModificationException();
				}
				for(int i = dernierRenvoye; i < nbCartes-1; i++) {
//					System.out.println(i + " et " + nbCartes);
					cartes[i] = cartes[i+1];
				}
				cartes[nbCartes-1] = null;
				nbCartes--;
				iterateur--;
				dernierRenvoye = -1;
				nextEffectue = false;
				nbModif++;
				nbModifAttendu++;
			}
		};
	}
	
	public Carte piocher() {
		if(estVide()) {
			throw new NoSuchElementException();
		}
		Iterator<Carte> iterateur = this.iterator();
		Carte premiereCarte = iterateur.next();
		iterateur.remove();
//		System.out.println("il reste en " + nbCartes);
		return premiereCarte;
	}
}
