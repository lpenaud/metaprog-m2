package xml;

public interface SerialisableXml {

	public static final String EN_TETE_V1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE carnet PUBLIC \"CarnetId\" \"Carnet.dtd\">";

	public static final String EN_TETE_V2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE carnet PUBLIC \"CarnetId\" \"CarnetAttr.dtd\">";

	String toXmlStringV1();

	String toXmlStringV2();
}
