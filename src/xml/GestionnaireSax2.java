package xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import xml.sax.DocumentXml;
import xml.sax.NoeudXml;

public class GestionnaireSax2 implements ContentHandler {

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		this.courant = this.document = new DocumentXml();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
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
		this.courant = this.document.ajouterEnfant(qName);
		this.courant.muteAttributs(atts);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.courant = this.courant.accessParent();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		this.courant.muteContenue(new String(ch, start, length).trim());
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

	protected DocumentXml document;
	protected NoeudXml courant;
}
