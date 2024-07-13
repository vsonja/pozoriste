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
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;

class SOVratiSveGlumceTest {
	
	SOVratiSveGlumce so;
	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
		so = new SOVratiSveGlumce();
		dbb = mock(DBBroker.class);
		so.dbb = dbb;
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testExecuteOperation() {
		Glumac g1 = new Glumac(5, "Ime", "Prezime", "321321321");
		Glumac g2 = new Glumac(8, "Ime", "Prezime", "543210123");
		List<OpstiDomenskiObjekat> glumci = new ArrayList<>();
		glumci.add(g1);
		glumci.add(g2);
		
		when(dbb.vratiSveObjekteBezUslova(g1)).thenReturn(glumci);

        try {
			so.execute(g1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        verify(dbb).vratiSveObjekteBezUslova(g1);
        assertEquals(glumci, so.vratiListu());
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
