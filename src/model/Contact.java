package model;

import java.util.ArrayList;
import java.util.List;

import xml.SerialisableXml;

public class Contact implements SerialisableXml {
	
	public Contact(final String nom, final String prenom, final String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.informations = new ArrayList<>();
	}
	
	public void addInformations(final Information info) {
		this.informations.add(info);
	}

	@Override
	public String toXmlStringV1() {
		final StringBuilder builder = new StringBuilder("<contact>")
				.append("<nom>")
				.append(nom)
				.append("</nom>")
				.append("<prenom>")
				.append(prenom)
				.append("</prenom>")
				.append("<telephone>")
				.append(telephone)
				.append("</telephone>");
		for (final var information : informations) {
			builder.append(information.toXmlStringV1());
		}
		return builder.append("</contact>").toString();
	}

	@Override
	public String toXmlStringV2() {
		final StringBuilder builder = new StringBuilder("<contact nom=\"")
				.append(nom)
				.append("\" prenom=\"")
				.append(prenom)
				.append("\" telephone=\"")
				.append(telephone)
				.append("\">");
		for (final var information : informations) {
			builder.append(information.toXmlStringV2());
		}
		return builder.append("</contact>").toString();
	}
	
	@Override
	public String toString() {
		return new StringBuilder(getClass().getCanonicalName())
				.append("{ nom: ")
				.append(nom)
				.append(", prenom: ")
				.append(prenom)
				.append(", telephone: ")
				.append(telephone)
				.append(", informations: ")
				.append(informations)
				.append(" }")
				.toString();
	}

	protected String nom;

	protected String prenom;

	protected String telephone;

	protected List<Information> informations;
}
