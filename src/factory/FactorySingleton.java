package factory;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

public class FactorySingleton {
	
	public static CarnetFactory getCarnetFactory() {
		return carnetFactory;
	}
	
	public static DocumentBuilderFactory getDocumentBuilderFactory() {
		return documentBuilderFactory;
	}
	
	public static TransformerFactory getTransformerFactory() {
		return transformerFactory;
	}
	
	private FactorySingleton() {}

	private static final CarnetFactory carnetFactory = new CarnetFactory();
	
	private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
	
	private static final TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
	
}
