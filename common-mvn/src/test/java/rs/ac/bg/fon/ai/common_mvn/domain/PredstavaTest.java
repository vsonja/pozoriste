package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PredstavaTest {
	
	Predstava p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Predstava();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	void testSetAutoIncrementPK() {
		p.setPredstavaID(20);
		assertEquals(20, p.getPredstavaID());
	}

	@Test
	void testSetUloge() {
		List<Uloga> uloge = new ArrayList<>();
		Uloga u = new Uloga();
		u.setGlumac(new Glumac("Petar", "Petrovic", "123456789"));
		uloge.add(u);
		assertTrue(uloge.contains(u));
	}

	@Test
	void testSetPredstavaID() {
		p.setPredstavaID(20);
		assertEquals(20, p.getPredstavaID());
	}

	@Test
	void testSetNazivPredstave() {
		p.setNazivPredstave("Predstava 1");
		assertEquals("Predstava 1", p.getNazivPredstave());
	}

	@Test
	void testSetOpis() {
		p.setOpis("Opis");
		assertEquals("Opis", p.getOpis());
	}

	@Test
	void testToString() {
		p.setNazivPredstave("Naziv");
		String s = p.toString();
		assertTrue(s.contains("Naziv"));
	}

}
