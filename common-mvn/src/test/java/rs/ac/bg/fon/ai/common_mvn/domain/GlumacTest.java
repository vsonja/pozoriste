package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GlumacTest {

	Glumac g;

	@BeforeEach
	void setUp() throws Exception {
		g = new Glumac();
	}

	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	void testSetAutoIncrementPK() {
		g.setGlumacID(20);
		assertEquals(20, g.getGlumacID());
	}

	@Test
	void testSetGlumacID() {
		g.setGlumacID(20);
		assertEquals(20, g.getGlumacID());
	}

	@Test
	void testSetIme() {
		g.setIme("Ime");
		assertEquals("Ime", g.getIme());
	}

	@Test
	void testSetImeBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setIme("Ime5"));
	}

	@Test
	void testSetPrezime() {
		g.setPrezime("Prezime");
		assertEquals("Prezime", g.getPrezime());
	}

	@Test
	void testSetPrezimeBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setPrezime("Prezime5"));
	}

	@Test
	void testSetTelefon() {
		g.setTelefon("123456789");
		assertEquals("123456789", g.getTelefon());
	}

	@Test
	void testSetTelefonCifre() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setTelefon("123"));
	}

	@Test
	void testSetTelefonSlova() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setTelefon("123m"));
	}

	@Test
	void testToString() {
		g.setIme("Ime");
		g.setPrezime("Prezime");
		String s = g.toString();
		assertTrue(s.contains("Ime"));
		assertTrue(s.contains("Prezime"));
	}

}
