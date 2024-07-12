package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalaTest {
	
	Sala s;

	@BeforeEach
	void setUp() throws Exception {
		s = new Sala();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}


	@Test
	void testSetSalaID() {
		s.setSalaID(20);
		assertEquals(20, s.getSalaID());
	}

	@Test
	void testSetScena() {
		s.setScena("mala");
		assertEquals("mala", s.getScena());
	}

	@Test
	void testSetKapacitet() {
		s.setKapacitet(100);
		assertEquals(100, s.getKapacitet());
	}

	@Test
	void testToString() {
		s.setScena("mala");
		s.setKapacitet(80);
		String st = s.toString();
		assertTrue(st.contains("mala"));
		assertTrue(st.contains("80"));
	}

}
