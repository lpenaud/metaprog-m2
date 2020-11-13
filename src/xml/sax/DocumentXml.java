package xml.sax;

public class DocumentXml extends NoeudXml {

	public DocumentXml() {
		super("#document");
	}

	public NoeudXml ajouterEnfant(final String nom) {
		return super.ajouterEnfant(new NoeudXml(nom));
	}
	
	protected NoeudXml racine;
}
