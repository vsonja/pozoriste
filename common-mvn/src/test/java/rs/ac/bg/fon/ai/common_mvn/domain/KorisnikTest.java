package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KorisnikTest {

	Korisnik k;

	@BeforeEach
	void setUp() throws Exception {
		k = new Korisnik();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	@Test
	void testSetAutoIncrementPK() {
		k.setKorisnikID(20);
		assertEquals(20, k.getKorisnikID());
	}

	@Test
	void testSetKorisnikID() {
		k.setKorisnikID(20);
		assertEquals(20, k.getKorisnikID());
	}

	@Test
	void testSetIme() {
		k.setIme("Ime");
		assertEquals("Ime", k.getIme());
	}

	@Test
	void testSetImeBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setIme("Ime5"));
	}

	@Test
	void testSetPrezime() {
		k.setPrezime("Prezime");
		assertEquals("Prezime", k.getPrezime());
	}

	@Test
	void testSetPrezimeBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setPrezime("Prezime5"));
	}

	@Test
	void testSetKorisnickoIme() {
		k.setKorisnickoIme("korisnickoime");
		assertEquals("korisnickoime", k.getKorisnickoIme());
	}

	@Test
	void testSetKorisnickoImeManje() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setKorisnickoIme("ime"));
	}

	@Test
	void testSetSifra() {
		k.setSifra("sifra");
		assertEquals("sifra", k.getSifra());
	}

	@Test
	void testSetSifraManje() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> k.setSifra("sif"));
	}

}
