package visteur;

import model.Carnet;
import model.Contact;
import model.Information;

public interface VisiteurModele {
	default void visite(Carnet carnet) {
		throw new UnsupportedOperationException();
	}
	
	default void visite(Contact contact) {
		throw new UnsupportedOperationException();
	}
	
	default void visite(Information information) {
		throw new UnsupportedOperationException();
	}
}
