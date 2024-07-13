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

class SOVratiSvePredstaveTest {
	
	SOVratiSvePredstave so;
	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
		so = new SOVratiSvePredstave();
		dbb = mock(DBBroker.class);
		so.dbb = dbb;
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testExecuteOperation() {
		Predstava p1 = new Predstava(20, "Predstava 1", "Opis", null);
		Predstava p2 = new Predstava(30, "Predstava 2", "Opis", null);
		List<OpstiDomenskiObjekat> predstave = new ArrayList<>();
		predstave.add(p1);
		predstave.add(p2);
		
		when(dbb.vratiSveObjekteBezUslova(p1)).thenReturn(predstave);

        try {
			so.execute(p1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        verify(dbb).vratiSveObjekteBezUslova(p1);
        assertEquals(predstave, so.vratiListu());
        assertEquals(Status.SUCCESS, so.getStatus());
	}

	@Test
	void testValidateNull() {
		assertThrows(java.lang.NullPointerException.class, () -> so.execute(null));
	}
	
	@Test
	void testValidateInstanca() {
		Glumac g = new Glumac("Ime", "Prezime", "987654321");
		assertThrows(java.lang.IllegalArgumentException.class, () -> so.validate(g));
	}

}
