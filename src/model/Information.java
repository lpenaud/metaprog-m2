package model;

import xml.SerialisableXml;
import xml.annotations.XmlEntity;
import xml.annotations.XmlField;

@XmlEntity
public class Information implements SerialisableXml {
	
	public Information() {}

	public Information(final String libelle, final String texte) {
		this.libelle = libelle;
		this.texte = texte;
	}

	@Override
	public String toXmlStringV1() {
		return new StringBuilder("<information><libelle>")
				.append(libelle)
				.append("</libelle>")
				.append("<texte>")
				.append(texte)
				.append("</texte>")
				.append("</information>")
				.toString();
	}

	@Override
	public String toXmlStringV2() {
		return new StringBuilder("<information libelle=\"")
				.append(libelle)
				.append("\" texte=\"")
				.append(texte)
				.append("\"/>")
				.toString();
	}
	
	@Override
	public String toString() {
		return new StringBuilder(getClass().getCanonicalName())
				.append("{ libelle: ")
				.append(libelle)
				.append(", texte: ")
				.append(texte)
				.append(" }")
				.toString();
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}

	@XmlField
	protected String libelle;
	
	@XmlField
	protected String texte;
}
