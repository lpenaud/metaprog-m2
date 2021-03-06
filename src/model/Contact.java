package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Information getInformation(int index) {
		return this.informations.get(index);
	}
	
	public void addInformations(final Information info) {
		this.informations.add(info);
	}
	
	public int nbInformation() {
		return this.informations.size();
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
	
	public void pourChaqueInformation(Consumer<Information> consumer) {
		this.informations.forEach(consumer);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Contact) {
			final Contact contact = (Contact) obj;
			return contact.prenom.equals(prenom) 
					&& contact.nom.equals(nom)
					&& contact.telephone.equals(telephone)
					&& contact.informations.equals(informations);
		}
		return false;
	}
	
	public Information getInformation(final String libelle) {
		for (final Information information : informations) {
			if (information.libelle.equals(libelle)) {
				return information;
			}
		}
		return null;
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
