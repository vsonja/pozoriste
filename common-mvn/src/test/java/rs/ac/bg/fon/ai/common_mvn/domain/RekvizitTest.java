package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RekvizitTest {
	
	Rekvizit r;

	@BeforeEach
	void setUp() throws Exception {
		r = new Rekvizit();
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	void testSetPredstava() {
		Predstava p = new Predstava();
		p.setNazivPredstave("Predstava");
		r.setPredstava(p);
		assertEquals(p, r.getPredstava());
	}

	@Test
	void testSetRekvizitID() {
		r.setRekvizitID(20);
		assertEquals(20, r.getRekvizitID());
	}

	@Test
	void testSetTip() {
		r.setTip("sto");;
		assertEquals("sto", r.getTip());
	}

	@Test
	void testSetZauzet() {
		r.setZauzet(true);
		assertTrue(r.isZauzet());
	}

}
