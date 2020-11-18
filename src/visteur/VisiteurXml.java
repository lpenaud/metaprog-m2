package visteur;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import factory.FactorySingleton;
import model.Carnet;
import model.Contact;
import model.Information;

public class VisiteurXml implements VisiteurModele {
	
	@Override
	public void visite(final Carnet carnet) {
		try {
			document = FactorySingleton.getDocumentBuilderFactory()
					.newDocumentBuilder()
					.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}
		racine = document.createElement("carnet");
		racine.setAttribute("type", carnet.getType());
		carnet.pourChaqueContact(this::visite);
		document.appendChild(racine);
	}
	
	@Override
	public void visite(final Contact contact) {
		courant = document.createElement("contact");
		courant.setAttribute("nom", contact.getNom());
		courant.setAttribute("prenom", contact.getPrenom());
		courant.setAttribute("telephone", contact.getTelephone());
		contact.pourChaqueInformation(this::visite);
		racine.appendChild(courant);
	}
	
	@Override
	public void visite(final Information information) {
		final var informationElement = document.createElement("information");
		informationElement.setAttribute("libelle", information.getLibelle());
		informationElement.setAttribute("texte", information.getTexte());
		courant.appendChild(informationElement);
	}
	
	public Document getDocument() {
		return document;
	}
	
	public void writeXml(final Path path) throws TransformerException {
		final var domSource = new DOMSource();
		final var transformer = FactorySingleton.getTransformerFactory()
				.newTransformer();
	    DOMImplementation domImpl = document.getImplementation();
	    DocumentType doctype = domImpl.createDocumentType("carnet", "CarnetId", "CarnetAttr.dtd");
	    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
	    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
		final var result = new StreamResult(path.toFile());
		transformer.transform(domSource, result);
	}
	
	public void writeXml(final String fileName) throws TransformerException {
		writeXml(Paths.get(fileName));
	}
	
	protected Document document;
	protected Element racine;
	protected Element courant;
}
