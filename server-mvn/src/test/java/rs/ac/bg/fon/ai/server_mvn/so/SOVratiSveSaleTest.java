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
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;

class SOVratiSveSaleTest {
	
	SOVratiSveSale so;
	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
		so = new SOVratiSveSale();
		dbb = mock(DBBroker.class);
		so.dbb = dbb;
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testExecuteOperation() {
		Sala s1 = new Sala(1, "mala", 35);
		Sala s2 = new Sala(2, "velika", 80);
		List<OpstiDomenskiObjekat> sale = new ArrayList<>();
		sale.add(s1);
		sale.add(s2);
		
		when(dbb.vratiSveObjekteBezUslova(s1)).thenReturn(sale);

        try {
			so.execute(s1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        verify(dbb).vratiSveObjekteBezUslova(s1);
        assertEquals(sale, so.vratiListu());
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
