package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

import visteur.Visitable;
import visteur.VisiteurModele;
import xml.SerialisableXml;
import xml.annotations.XmlEntity;
import xml.annotations.XmlField;

@XmlEntity
public class Carnet implements SerialisableXml, Visitable {
	public Carnet() {
		this.contacts = new ArrayList<>();
		type = "commun";
	}

	public void addContact(Contact c) {
		this.contacts.add(c);
	}
	
	public Contact getContact(int index) {
		return this.contacts.get(index);
	}
	
	public int nbContact() {
		return this.contacts.size();
	}

	@Override
	public String toXmlStringV1() {
		final StringBuilder builder = new StringBuilder("<carnet><type>")
				.append(this.type)
				.append("</type>");
		Iterator<Contact> itor = contacts.iterator();
		while(itor.hasNext()) {
			builder.append(itor.next().toXmlStringV1());
		}
		return builder
				.append("</carnet>")
				.toString();
	}
	
	@Override
	public String toXmlStringV2() {
		final StringBuilder builder = new StringBuilder("<carnet type=\"")
				.append(this.type)
				.append("\">");
		Iterator<Contact> itor = contacts.iterator();
		while(itor.hasNext()) {
			builder.append(itor.next().toXmlStringV2());
		}
		return builder
				.append("</carnet>")
				.toString();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(getClass().getCanonicalName())
				.append("{ type: ")
				.append(type)
				.append(", contacts: ")
				.append(contacts)
				.append(" }")
				.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Carnet) {
			final Carnet carnet = (Carnet) obj;
			return carnet.type.equals(type) && carnet.contacts.equals(contacts);
		}
		return super.equals(obj);
	}

	@XmlField(accesseur = "addContact", tagName = "contact", type = Contact.class)
	protected ArrayList<Contact> contacts;

	@XmlField
	protected String type;

	@Override
	public void accepter(VisiteurModele visiteur) {
		visiteur.visite(this);
	}
	
	public void pourChaqueContact(final Consumer<Contact> consumer) {
		this.contacts.forEach(consumer);
	}
}
