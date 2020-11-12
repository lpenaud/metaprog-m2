package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import xml.SerialisableXml;

class CarnetTest {

	void testXmlStringV1(final SerialisableXml serialisable, final String pathname) {
		try (final var reader = new BufferedReader(new FileReader(pathname))) {
			final var actual = new StringBuilder(SerialisableXml.EN_TETE_V1).append(serialisable.toXmlStringV1())
					.toString();
			System.out.println(actual);
			assertEquals(reader.readLine(), actual);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	void testXmlStringV2(final SerialisableXml serialisable, final String pathname) {
		try (final var reader = new BufferedReader(new FileReader(pathname))) {
			final var actual = new StringBuilder(SerialisableXml.EN_TETE_V2).append(serialisable.toXmlStringV2())
					.toString();
			System.out.println(actual);
			assertEquals(reader.readLine(), actual);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void test1() {
		testXmlStringV1(new Carnet(), "tests/test1.xml");
	}

	@Test
	void test2() {
		final var carnet = new Carnet();
		final var contact = new Contact("Bessac", "Bilbo", "0298989898");
		contact.addInformations(new Information("Hobby", "Musique"));
		contact.addInformations(new Information("Loisir", "Kayak Polo"));
		carnet.addContact(contact);
		testXmlStringV1(carnet, "tests/test2.xml");
	}

	@Test
	void test3() {
		testXmlStringV2(new Carnet(), "tests/test3.xml");
	}

	@Test
	void test4() {
		final var carnet = new Carnet();
		final var contact = new Contact("Bessac", "Bilbo", "0298989898");
		contact.addInformations(new Information("Hobby", "Musique"));
		contact.addInformations(new Information("Loisir", "Kayak Polo"));
		carnet.addContact(contact);
		testXmlStringV2(carnet, "tests/test4.xml");
	}

}
