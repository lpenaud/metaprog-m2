package model.usines;

import model.Contact;
import xml.UsineXml;
import xml.sax.NoeudXml;

public class UsineContact implements UsineXml<Contact> {
	
	UsineContact() {}

	@Override
	public Contact newInstance(final NoeudXml noeud) {
		if (noeud == null) {
			return null;
		}
		final Contact contact = new Contact(
				noeud.accessEnfant("nom").accessContenue(),
				noeud.accessEnfant("prenom").accessContenue(),
				noeud.accessEnfant("telephone").accessContenue()
			);
		for (final var noeudInformation : noeud.accessEnfants("information")) {
			contact.addInformations(Usines.information.newInstance(noeudInformation));
		}
		return contact;
	}

}
