package xml;

import static org.junit.jupiter.api.Assertions.*;
import javax.xml.parsers.SAXParserFactory;

import org.junit.jupiter.api.Test;

import model.Carnet;

class GestionnaireSaxTest {

	@Test
	void test() {
		try {
			final var usine = SAXParserFactory.newInstance();
			final var sp = usine.newSAXParser();
			final var xr = sp.getXMLReader();
			final GestionnaireSax<Carnet> gestionnaire = new GestionnaireSax(Carnet.class);
			xr.setContentHandler(gestionnaire);
			xr.parse("tests/test2.xml");
			final var obj = gestionnaire.getObj();
			System.out.println("Succès, type = " + obj.getType());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
