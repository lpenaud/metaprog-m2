package model;

import xml.SerialisableXml;

public class Information implements SerialisableXml {

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

	protected String libelle;
	
	protected String texte;
}
