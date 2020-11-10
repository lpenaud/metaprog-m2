package xml;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public interface SerialisableXml {

	public static final String EN_TETE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE Carnet PUBLIC \"CarnetId\" \"Carnet.dtd\">";

	public static void toXmlFile(final File file, final SerialisableXml... serialisables) {
		try {
			final var stream = new BufferedOutputStream(new FileOutputStream(file));
			stream.write(EN_TETE.getBytes());
			stream.flush();
			for (final var s : serialisables) {
				stream.write(s.toXmlStringV1().getBytes());
				stream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void toXmlFile(final String pathname, final SerialisableXml... serialisables) {
		final File file = new File(pathname);
		toXmlFile(file, serialisables);
	}

	String toXmlStringV1();

	String toXmlStringV2();
}
