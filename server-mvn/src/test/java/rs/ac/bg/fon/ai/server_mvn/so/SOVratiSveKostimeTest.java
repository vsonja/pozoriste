package rs.ac.bg.fon.ai.server_mvn.so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Kostim;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;

class SOVratiSveKostimeTest {
	
	SOVratiSveKostime so;
	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
		so = new SOVratiSveKostime();
		dbb = mock(DBBroker.class);
		so.dbb = dbb;
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testExecuteOperation() {
		Kostim k1 = new Kostim(2, "Haljina", "S", false, null);
		Kostim k2 = new Kostim(2, "Odelo", "L", false, null);
		List<OpstiDomenskiObjekat> kostimi = new ArrayList<>();
		kostimi.add(k1);
		kostimi.add(k2);
		
		when(dbb.vratiSveObjekteBezUslova(k1)).thenReturn(kostimi);

        try {
			so.execute(k1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        verify(dbb).vratiSveObjekteBezUslova(k1);
        assertEquals(kostimi, so.vratiListu());
        assertEquals(Status.SUCCESS, so.getStatus());
	}

	@Test
	void testValidateNull() {
		assertThrows(java.lang.NullPointerException.class, () -> so.execute(null));
	}
	
	@Test
	void testValidateInstanca() {
		Predstava p = new Predstava(20, "Predstava 1", null, null);
		assertThrows(java.lang.IllegalArgumentException.class, () -> so.validate(p));
	}

}
