package xml;

import static org.junit.jupiter.api.Assertions.*;
import javax.xml.parsers.SAXParserFactory;

import org.junit.jupiter.api.Test;

import factory.FactorySingleton;
import model.Carnet;

class GestionnaireSaxTest {

	Carnet creerCarnet(String filename) {
		try {
			return FactorySingleton.getCarnetFactory().fromXml("tests/" + filename);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		return null;
	}
	
	@Test
	void test1() {
		final var obj = creerCarnet("test1.xml");
		System.out.println(obj.toString());
		assertEquals(obj.getType(),"commun");
		assertEquals(obj.nbContact(), 0);
	}
	
	@Test
	void test2() {
		final var obj = creerCarnet("test2.xml");
		System.out.println(obj.toString());
		assertEquals(obj.getType(),"commun");
		assertEquals(obj.nbContact(), 1);
		final var c = obj.getContact(0);
		assertEquals(c.getNom(),"Bessac");
		assertEquals(c.getPrenom(),"Bilbo");
		assertEquals(c.getTelephone(),"0298989898");
		assertEquals(c.nbInformation(), 2);
		final var info1 = c.getInformation(0);
		assertEquals(info1.getLibelle(), "Hobby");
		assertEquals(info1.getTexte(), "Musique");
		final var info2 = c.getInformation(1);
		assertEquals(info2.getLibelle(), "Loisir");
		assertEquals(info2.getTexte(), "Kayak Polo");
	}
	
}
