package model.usines;

import model.Carnet;
import utils.StringUtils;
import xml.UsineXml;
import xml.sax.DocumentXml;
import xml.sax.NoeudXml;

public class UsineCarnet implements UsineXml<Carnet> {
	
	UsineCarnet() {}

	@Override
	public Carnet newInstance(final NoeudXml noeud) {
		if (noeud == null) {
			return null;
		}
		final var type = noeud.accessEnfant("type");
		final var carnet = new Carnet();
		carnet.setType(type == null ? "commun" : type.accessContenue());
		for (final var noeudContact : noeud.accessEnfants("contact")) {
			carnet.addContact(Usines.contact.newInstance(noeudContact));
		}
		return carnet;
	}
}
