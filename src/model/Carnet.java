package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import xml.SerialisableXml;

public class Carnet implements SerialisableXml {
	protected ArrayList<Contact> contacts;
	protected String type;

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

}
