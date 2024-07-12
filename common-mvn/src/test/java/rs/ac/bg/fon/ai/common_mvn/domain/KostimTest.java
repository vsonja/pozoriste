package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KostimTest {

	Kostim k;

	@BeforeEach
	void setUp() throws Exception {
		k = new Kostim();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	@Test
	void testSetAutoIncrementPK() {
		k.setKostimID(20);
		assertEquals(20, k.getKostimID());
	}

	@Test
	void testSetPredstava() {
		Predstava p = new Predstava(10, "Predstava 1", null, null);
		k.setPredstava(p);
		assertEquals(p, k.getPredstava());
	}

	@Test
	void testSetKostimID() {
		k.setKostimID(20);
		assertEquals(20, k.getKostimID());
	}

	@Test
	void testSetTip() {
		k.setTip("Haljina");
		assertEquals("Haljina", k.getTip());
	}

	@Test
	void testSetVelicina() {
		k.setVelicina("S");
		assertEquals("S", k.getVelicina());
	}

	@Test
	void testSetZauzet() {
		k.setZauzet(true);
		assertEquals(true, k.isZauzet());
	}
	
	@Test
	void testToString() {
		k.setKostimID(5);
		k.setTip("Odelo");
		k.setVelicina("38");		
		String s = k.toString();
		assertTrue(s.contains("5"));
		assertTrue(s.contains("Odelo"));
		assertTrue(s.contains("38"));
	}

}
