package rs.ac.bg.fon.ai.server_mvn.so;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;

class OpstaSOTest {

	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testOpstaSO() {
		dbb = new DBBroker();
		assertNotNull(dbb);
	}

}
