package rs.ac.bg.fon.ai.server_mvn.so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;
import rs.ac.bg.fon.ai.common_mvn.domain.StavkaRepertoara;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;

class SOZapamtiStavkuRepertoaraTest {
	
	SOZapamtiStavkuRepertoara so;
	DBBroker dbb;

	@BeforeEach
	void setUp() throws Exception {
		so = new SOZapamtiStavkuRepertoara();
		dbb = mock(DBBroker.class);
		so.dbb = dbb;
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testExecuteOperation() {
		StavkaRepertoara s = new StavkaRepertoara();
		s.setDatum(LocalDate.of(2024, 7, 12));
		s.setPredstava(new Predstava(1, "Sumnjivo lice", "Opis", null));
		s.setRepertoar(new Repertoar(1, 7, 2024, null));
		
		when(dbb.UbaciNoviObjekat(s)).thenReturn(true);

        try {
			so.execute(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        verify(dbb).UbaciNoviObjekat(s);
        assertEquals("Stavka repertoara je uspešno sacuvana.", so.getPoruka());
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
