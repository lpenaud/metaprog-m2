package visteur;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import model.Carnet;
import model.Contact;
import model.Information;

class VisiteurXmlTest {
	final VisiteurXml visiteur = new VisiteurXml();
	Carnet carnet;
	Document document;

	public void testUniqueTextContent(final String expected, final String tagName) {
		final var nodes = document.getElementsByTagName(tagName);
		assertEquals(1, nodes.getLength());
		testTextContent(expected, nodes.item(0));
	}
	
	public void testTextContent(final String expected, final Node node) {
		assertEquals(expected, node.getTextContent());
	}
	
	public Element getFirstElementByTagName(final String tagname) {
		final var nodes = document.getElementsByTagName(tagname);
		assertEquals(1, nodes.getLength());
		assertTrue(nodes.item(0) instanceof Element);
		return (Element) nodes.item(0);
	}

	@BeforeEach
	void setUp() {
		carnet = new Carnet();
		final var contact = new Contact("Bessac", "Bilbo", "0298989898");
		contact.addInformations(new Information("Hobby", "Musique"));
		contact.addInformations(new Information("Loisir", "Kayak Polo"));
		carnet.addContact(contact);
		carnet.accepter(visiteur);
	}

	@Test
	void test1() {
		final var contact = carnet.getContact(0);
		document = visiteur.getDocument();
		final var carnetElement = getFirstElementByTagName("carnet");
		final var contactElement = getFirstElementByTagName("contact");
		assertEquals(carnet.getType(), carnetElement.getAttribute("type"));
		assertEquals(contactElement.getAttribute("nom"), contact.getNom());
		assertEquals(contactElement.getAttribute("prenom"), contact.getPrenom());
		assertEquals(contactElement.getAttribute("telephone"), contact.getTelephone());
		final var contactChildren = contactElement.getChildNodes();
		for (int i = 0; i < contactChildren.getLength(); i++) {
			assertTrue(contactChildren.item(0) instanceof Element);
			final var informationElement = (Element) contactChildren.item(i);
			assertEquals(contact.getInformation(i).getLibelle(), informationElement.getAttribute("libelle"));
			assertEquals(contact.getInformation(i).getTexte(), informationElement.getAttribute("texte"));
		}
	}
	
	@Test
	void test2() {
		final Path path = Paths.get("tests", "dom.xml");
		try {
			Files.deleteIfExists(path);
		} catch (IOException e1) {
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		try {
			visiteur.writeXml(path);
			assertTrue(Files.exists(path));
		} catch (TransformerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
