package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RepertoarTest {
	
	Repertoar r;

	@BeforeEach
	void setUp() throws Exception {
		r = new Repertoar();
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	void testSetGodina() {
		r.setGodina(2024);
		assertEquals(2024, r.getGodina());
	}

	@Test
	void testSetRepertoarID() {
		r.setRepertoarID(20);
		assertEquals(20, r.getRepertoarID());
	}

	@Test
	void testSetMesec() {
		r.setMesec(5);
		assertEquals(5, r.getMesec());
	}
	
	@Test
	void testSetMesecVan() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> r.setMesec(15));
	}

	@Test
	void testSetStavke() {
		List<StavkaRepertoara> stavke = new ArrayList<>();
		StavkaRepertoara stavka = new StavkaRepertoara();
		stavka.setStavkaRepertoaraID(20);
		stavke.add(stavka);
		r.setStavke(stavke);
		assertEquals(stavke, r.getStavke());
	}

}
