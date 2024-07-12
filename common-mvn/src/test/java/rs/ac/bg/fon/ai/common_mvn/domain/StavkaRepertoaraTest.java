package rs.ac.bg.fon.ai.common_mvn.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StavkaRepertoaraTest {
	
	StavkaRepertoara stavka;

	@BeforeEach
	void setUp() throws Exception {
		stavka = new StavkaRepertoara();
	}

	@AfterEach
	void tearDown() throws Exception {
		stavka = null;
	}

	@Test
	void testSetPredstava() {
		Predstava p = new Predstava();
		p.setNazivPredstave("P1");
		stavka.setPredstava(p);
		assertEquals(p, stavka.getPredstava());
	}

	@Test
	void testSetRepertoar() {
		Repertoar p = new Repertoar();
		p.setGodina(2024);
		stavka.setRepertoar(p);
		assertEquals(p, stavka.getRepertoar());
	}

	@Test
	void testSetSala() {
		Sala p = new Sala();
		p.setKapacitet(2024);
		stavka.setSala(p);
		assertEquals(p, stavka.getSala());
	}

	@Test
	void testSetStavkaRepertoaraID() {
		stavka.setStavkaRepertoaraID(20);
		assertEquals(20, stavka.getStavkaRepertoaraID());
	}

	@Test
	void testSetDatum() {
		LocalDate datum = LocalDate.of(2024, 1, 8);
		stavka.setDatum(datum);
		assertEquals(datum, stavka.getDatum());
	}

	@Test
	void testSetVremePocetka() {
		LocalTime time = LocalTime.of(18, 18);
		stavka.setVremePocetka(time);
		assertEquals(time, stavka.getVremePocetka());
	}

	@Test
	void testSetVremeZavrsetka() {
		LocalTime time = LocalTime.of(20, 20);
		stavka.setVremeZavrsetka(time);
		assertEquals(time, stavka.getVremeZavrsetka());
	}

}
