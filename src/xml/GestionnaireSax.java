package xml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import utils.StringUtils;
import xml.annotations.XmlEntity;
import xml.annotations.XmlField;

/**
 * @see org.xml.sax.helpers.DefaultHandler À faire: Ajouter journalisation
 */
public class GestionnaireSax<T> implements ContentHandler, ErrorHandler {

	public GestionnaireSax(final Class<T> c) throws Exception {
		final var entity = c.getAnnotation(XmlEntity.class);
		if (entity == null) {
			throw new Exception("AAAAAAAAAAAAAAAAAAAAh");
		}
		this.tagName = StringUtils.defaultString(entity.tagName(), c.getSimpleName().toLowerCase());
		this.con = c.getConstructor(entity.constuctor());
		this.accesseurs = new HashMap<>();
		for (final var field : c.getFields()) {
			final var xmlField = field.getAnnotation(XmlField.class);
			if (xmlField == null) {
				continue;
			}

			this.accesseurs.put(StringUtils.defaultString(xmlField.tagName(), field.getName()), c.getDeclaredMethod(
					StringUtils.defaultString(xmlField.accesseur(), "set" + StringUtils.toTitleCase(field.getName())),
					field.getType()));
		}
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
		if (qName.equals(this.tagName)) {
			try {
				obj = con.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new SAXException(e);
			}
		} else {
			accesseurCourant = accesseurs.get(qName);
		}
		System.out.println(new StringBuilder("Début du tag { uri: ").append(uri).append(", localName: ")
				.append(localName).append(", qName: ").append(qName).append(" }").toString());
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println(new StringBuilder("Fin du tag { uri: ").append(uri).append(", localName: ").append(localName)
				.append(", qName: ").append(qName).append(" }").toString());
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		final var donnees = new String(ch, start, length);
		System.out.println("  valeur = *" + donnees + "*");
		if (accesseurCourant == null) {
			return;
		}
		try {
			accesseurCourant.invoke(obj, donnees);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new SAXException(e);
		}
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

	public T getObj() {
		return obj;
	}

	protected String tagName;
	protected Constructor<T> con;
	protected Map<String, Method> accesseurs;
	protected Method accesseurCourant;
	protected T obj;
}
