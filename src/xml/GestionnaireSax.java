package xml;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Deque;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import xml.annotations.XmlEntity;

/**
 * @see org.xml.sax.helpers.DefaultHandler À faire: Ajouter journalisation
 */
public class GestionnaireSax<T> implements ContentHandler, ErrorHandler {

	public GestionnaireSax(final Class<T> c) throws Exception {
		final var entity = c.getAnnotation(XmlEntity.class);
		if (entity == null) {
			throw new Exception("AAAAAAAAAAAAAAAAAAAAh");
		}
		this.c = c;
		this.pile = new ArrayDeque<>();
		this.pile.addFirst(new XmlObject<>(c));
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Début du document");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Fin du document");
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		System.out.println(new StringBuilder("Début du tag { uri: ").append(uri).append(", localName: ")
				.append(localName).append(", qName: ").append(qName).append(" }").toString());
		var xmlObject = this.pile.getFirst();
		if (qName.equals(xmlObject.tagName)) {
			try {
				xmlObject.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		xmlObject.setMutateurCourant(qName);
		if (xmlObject.mutateurCourant == null) {
			// Gestion d'erreur ?
			return;
		}
		final var parametre = xmlObject.mutateurCourant.getParameterTypes()[0];
		final var xmlEntity = parametre.getAnnotation(XmlEntity.class);
		if (xmlEntity == null) {
			return;
		}
		try {
			xmlObject = new XmlObject<>(parametre); 
			xmlObject.newInstance();
			pile.addFirst(xmlObject);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println(new StringBuilder("Fin du tag { uri: ").append(uri).append(", localName: ").append(localName)
				.append(", qName: ").append(qName).append(" }").toString());
		final var xmlObject = this.pile.getFirst();
		if (qName.equals(xmlObject.tagName)) {
			this.donnee = xmlObject.obj;
			if (this.pile.size() > 1) {
				this.pile.removeFirst();				
			}
		}
		try {
			this.pile.getFirst().mute(donnee);
			this.pile.getFirst().mutateurCourant = null;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		donnee = new String(ch, start, length);
		System.out.println("  valeur = *" + donnee + "*");
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		exception.printStackTrace();
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public T getObj() {
		return (T) this.pile.getFirst().obj;
	}

	protected Class<T> c;
	protected Deque<XmlObject<?>> pile;
	protected Object donnee;
}
