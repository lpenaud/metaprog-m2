package xml.sax;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.xml.sax.Attributes;

public class NoeudXml {
	
	public NoeudXml(final String nom) {
		this.nom = nom;
		this.enfants = new ArrayDeque<>();
	}
	
	public NoeudXml(final String nom, final NoeudXml parent) {
		this(nom);
		this.parent.ajouterEnfant(this);
	}
	
	public NoeudXml ajouterEnfant(final NoeudXml noeud) {
		if (noeud != null) {
			noeud.parent = this;
			this.enfants.add(noeud);			
		}
		return noeud;
	}
	
	public NoeudXml supprimerEnfant(final NoeudXml noeud) {
		if (this.enfants.remove(noeud)) {
			noeud.parent = null;
		}
		return noeud;
	}
	
	public NoeudXml premierEnfant() {
		return this.enfants.getFirst();
	}
	
	public NoeudXml dernierEnfant() {
		return this.enfants.getLast();
	}
	
	public NoeudXml accessEnfant(final String nom) {
		return this.enfants.stream()
				.filter((e) -> e.nom.equals(nom))
				.findFirst()
				.orElse(null);		
	}
	
	public List<NoeudXml> accessEnfants(final String nom) {
		final List<NoeudXml> res = new ArrayList<>();
		this.enfants.stream()
				.filter((e) -> e.nom.equals(nom))
				.forEach(res::add);
		return res;
	}
	
	public String accesssNom() {
		return nom;
	}
	
	public NoeudXml accessParent() {
		return parent;
	}
	
	public String accessContenue() {
		return contenue;
	}
	
	public void muteContenue(String contenue) {
		this.contenue = contenue;
	}
	
	public Attributes accessAttributs() {
		return attributs;
	}
	
	public void muteAttributs(Attributes attributs) {
		this.attributs = attributs;
	}
	
	protected final String nom;
	protected final Deque<NoeudXml> enfants;
	protected Attributes attributs; 
	protected String contenue;
	protected NoeudXml parent;
}
