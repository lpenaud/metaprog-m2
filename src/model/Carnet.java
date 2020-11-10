package model;

import java.util.ArrayList;
import java.util.Iterator;

import xml.SerialisableXml;
import xml.annotations.XmlEntity;
import xml.annotations.XmlField;

@XmlEntity(tagName = "carnet")
public class Carnet implements SerialisableXml {
	public Carnet() {
		this.contacts = new ArrayList<>();
		type = "commun";
		
	}

	public void addContact(Contact c) {
		this.contacts.add(c);
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

	protected ArrayList<Contact> contacts;

	@XmlField
	protected String type;
}
