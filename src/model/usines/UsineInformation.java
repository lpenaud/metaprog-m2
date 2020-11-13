package model.usines;

import model.Information;
import xml.UsineXml;
import xml.sax.NoeudXml;

public class UsineInformation implements UsineXml<Information> {

	@Override
	public Information newInstance(NoeudXml noeud) {
		if (noeud == null) {
			return null;
		}
		return new Information(
				noeud.accessEnfant("libelle").accessContenue(),
				noeud.accessEnfant("texte").accessContenue());
	}

}
