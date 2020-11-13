package xml;

import xml.sax.NoeudXml;

public interface UsineXml<T> {
	public T newInstance(NoeudXml noeud);
}
