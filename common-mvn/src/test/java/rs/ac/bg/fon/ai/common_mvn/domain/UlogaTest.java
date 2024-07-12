package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UlogaTest {
	
	Uloga u;

	@BeforeEach
	void setUp() throws Exception {
		u = new Uloga();
	}

	@AfterEach
	void tearDown() throws Exception {
		u = null;
	}

	@Test
	void testSetNazivUloge() {
		u.setNazivUloge("Uloga 1");
		assertEquals("Uloga 1", u.getNazivUloge());
	}

	@Test
	void testSetGlumac() {
		Glumac g = new Glumac();
		g.setGlumacID(20);
		u.setGlumac(g);
		assertEquals(g, u.getGlumac());
	}

	@Test
	void testSetPredstava() {
		Predstava p = new Predstava();
		p.setPredstavaID(20);
		u.setPredstava(p);
		assertEquals(p, u.getPredstava());
	}

}
