package model;

import java.util.ArrayList;
import java.util.List;

import xml.SerialisableXml;
import xml.annotations.XmlEntity;
import xml.annotations.XmlField;

@XmlEntity
public class Contact implements SerialisableXml {
	
	public Contact() {
		this.informations = new ArrayList<>();
	}

	public Contact(final String nom, final String prenom, final String telephone) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
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
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void addInformations(final Information info) {
		this.informations.add(info);
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

	@XmlField
	protected String nom;

	@XmlField
	protected String prenom;

	@XmlField
	protected String telephone;

	@XmlField(accesseur = "addInformations", tagName = "information", type = Information.class)
	protected List<Information> informations;
}
