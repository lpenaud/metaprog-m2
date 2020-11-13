package factory;

import javax.xml.parsers.SAXParserFactory;

import model.Carnet;
import xml.GestionnaireSax;

public class CarnetFactory implements FactoryXml<Carnet> {
	
	CarnetFactory() {
		parserFactory = SAXParserFactory.newInstance();
		parserFactory.setValidating(true);
	}

	@Override
	public Carnet fromXml(String filename) {
		try {
			final var sp = parserFactory.newSAXParser();
			final var xr = sp.getXMLReader();
			final GestionnaireSax<Carnet> gestionnaire = new GestionnaireSax<>(Carnet.class);
			xr.setContentHandler(gestionnaire);
			xr.parse(filename);
			return gestionnaire.getObj();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private final SAXParserFactory parserFactory;
}
