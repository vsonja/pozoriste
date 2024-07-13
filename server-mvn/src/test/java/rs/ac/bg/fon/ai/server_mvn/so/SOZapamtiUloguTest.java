package rs.ac.bg.fon.ai.server_mvn.so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;
import rs.ac.bg.fon.ai.common_mvn.domain.Uloga;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;

class SOZapamtiUloguTest {
	
	SOZapamtiUlogu so;
	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
		so = new SOZapamtiUlogu();
		dbb = mock(DBBroker.class);
		so.dbb = dbb;
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testExecuteOperation() {
		Uloga u = new Uloga();
		u.setNazivUloge("Uloga 1");
		
		Glumac g = new Glumac("Ime", "Prezime", "123123123");
		Predstava p = new Predstava(1, "Sumnjivo lice", "Opis", null);
		
		u.setGlumac(g);
		u.setPredstava(p);
		
		when(dbb.UbaciNoviObjekat(u)).thenReturn(true);

        try {
			so.execute(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        verify(dbb).UbaciNoviObjekat(u);
        assertEquals("Uloga je uspesno sacuvana.", so.getPoruka());
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
