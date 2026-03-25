package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import cartes.Carte;

public class GestionCartes {
	public static Carte extraire_v1(List<Carte> liste) {
		Random random = new Random();
		Carte element = liste.get(random.nextInt(liste.size()));
		liste.remove(element);
		return element;
	}
	
	public static Carte extraire_v2(List<Carte> liste) {
		Random random = new Random();
		Carte element = liste.get(random.nextInt(liste.size()));
		liste.remove(element);
		return element;
	}
	
	public static List<Carte> melanger(List<Carte> liste) {
		List<Carte> newliste = new ArrayList<>();
		
		while(!liste.isEmpty()) {
			newliste.add(extraire_v1(liste));
		}
		
		return newliste;
	}
	
	public static boolean verifierMelange(List<Carte> liste1,List<Carte> liste2) {
		if(liste1.size() != liste2.size()) {
			return false;
		}
		
		for(Object carte : liste1) {
			int freq1 = Collections.frequency(liste1, carte);
			int freq2 = Collections.frequency(liste2, carte);
			
			if (freq1 != freq2) {
				return false;
			}
		}
		return true;
	}

	public static List<Carte> rassemberV2(List<Carte> listeCartes) {
		List<Carte> Actuel = new ArrayList<>();
		List<Carte> DejaVu = new ArrayList<>();

		
		for(Carte carte : listeCartes) {
			if(!DejaVu.contains(carte)) {
				int freq = Collections.frequency(listeCartes, carte);
				for (int i = 0; i < freq ; i++) {
					Actuel.add(carte);
				}
				DejaVu.add(carte);
			}
		}

		return Actuel;
	}

	public static boolean verifierRassemblement(List<Carte> listeCartes) {
		if (listeCartes.isEmpty()) return true;
		
		ListIterator<Carte> iterateur1 = listeCartes.listIterator();
		Carte precedent = iterateur1.next();
		
		while (iterateur1.hasNext()) {
			Carte actuel = iterateur1.next();
			
			if(!actuel.equals(precedent)) {
				ListIterator<Carte> iterateur2 = listeCartes.listIterator(iterateur1.nextIndex());
				
				while (iterateur2.hasNext()) {
					Carte suivant = iterateur2.next();
					
					if(suivant.equals(precedent)) return false;
				}
				
				precedent = actuel;
			}
		}
		
		return true;
	}
}
