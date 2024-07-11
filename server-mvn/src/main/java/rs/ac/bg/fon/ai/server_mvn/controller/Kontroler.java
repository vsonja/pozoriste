package rs.ac.bg.fon.ai.server_mvn.controller;

import rs.ac.bg.fon.ai.common_mvn.communication.Odgovor;
import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.Korisnik;
import rs.ac.bg.fon.ai.common_mvn.domain.Kostim;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import rs.ac.bg.fon.ai.common_mvn.domain.StavkaRepertoara;
import rs.ac.bg.fon.ai.common_mvn.domain.Uloga;
import rs.ac.bg.fon.ai.server_mvn.form.FrmServer;
import rs.ac.bg.fon.ai.server_mvn.form.model.PrijavljeniKorisniciTableModel;
import java.awt.Color;
import rs.ac.bg.fon.ai.server_mvn.so.OpstaSO;
import rs.ac.bg.fon.ai.server_mvn.so.SOPrijavi;
import rs.ac.bg.fon.ai.server_mvn.so.SOObrisiGlumca;
import rs.ac.bg.fon.ai.server_mvn.so.SOObrisiPredstavu;
import rs.ac.bg.fon.ai.server_mvn.so.SOVratiGlumca;
import rs.ac.bg.fon.ai.server_mvn.so.SOVratiPredstavu;
import rs.ac.bg.fon.ai.server_mvn.so.SOVratiSveGlumce;
import rs.ac.bg.fon.ai.server_mvn.so.SOVratiSveKostime;
import rs.ac.bg.fon.ai.server_mvn.so.SOVratiSvePredstave;
import rs.ac.bg.fon.ai.server_mvn.so.SOVratiSveSale;
import rs.ac.bg.fon.ai.server_mvn.so.SOZapamtiGlumca;
import rs.ac.bg.fon.ai.server_mvn.so.SOZapamtiPredstavu;
import rs.ac.bg.fon.ai.server_mvn.so.SOZapamtiRepertoar;
import rs.ac.bg.fon.ai.server_mvn.so.SOZapamtiStavkuRepertoara;
import rs.ac.bg.fon.ai.server_mvn.so.SOZapamtiUlogu;
import rs.ac.bg.fon.ai.server_mvn.thread.ServerNit;

/**
 *
 * @author Sonja
 */
public class Kontroler {

    private static Kontroler instance;
    Odgovor odgovor;
    ServerNit serverNit;
    FrmServer forma;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void setForma(FrmServer forma) {
        this.forma = forma;
    }

    public boolean pokreniServer() {
        serverNit = new ServerNit();
        if (!serverNit.isAlive()) {
            serverNit.start();

            System.out.println("Server je pokrenut.");

            forma.getLblStatus().setText("POKRENUT");
            forma.getLblStatus().setBackground(Color.green);
            forma.getBtnPokreni().setEnabled(false);
            forma.getBtnZaustavi().setEnabled(true);

            return true;
        }

        return false;

    }

    public boolean zaustaviServer() {
        if (serverNit.getServerSocket() != null) {
            serverNit.zaustavi();

            System.out.println("Server je zaustavljen.");

            forma.getLblStatus().setText("ZAUSTAVLJEN");
            forma.getLblStatus().setBackground(Color.red);
            forma.getBtnZaustavi().setEnabled(false);
            forma.getBtnPokreni().setEnabled(true);

            return true;
        }

        return false;
    }

    public Odgovor ulogujKorisnika(Korisnik k) throws Exception {
        odgovor = new Odgovor();

        try {
            OpstaSO sop = new SOPrijavi();
            sop.execute(k);
            odgovor.setPoruka(sop.getPoruka());
            odgovor.setStatus(sop.getStatus());
        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }

        return odgovor;
    }

    public void dodajKorisnika(Korisnik korisnik) {
        PrijavljeniKorisniciTableModel pktm = (PrijavljeniKorisniciTableModel) forma.getTblPrijavljeniKorisnici().getModel();
        pktm.dodajKorisnika(korisnik);
    }

    public void izbaciKorisnika(Korisnik korisnik) {
        PrijavljeniKorisniciTableModel pktm = (PrijavljeniKorisniciTableModel) forma.getTblPrijavljeniKorisnici().getModel();
        pktm.izbaciKorisnika(korisnik);
    }

    public Odgovor sacuvajGlumca(Glumac glumac) {
        odgovor = new Odgovor();
        try {
            OpstaSO sozg = new SOZapamtiGlumca();
            sozg.execute(glumac);
            odgovor.setPoruka(sozg.getPoruka());
            odgovor.setStatus(sozg.getStatus());
            odgovor.setRezultat(sozg.vratiODO());

        } catch (Exception ex) {
            odgovor.setException(ex);
        }
        return odgovor;
    }

    public Odgovor sacuvajPredstavu(Predstava predstava) {
        odgovor = new Odgovor();

        try {
            OpstaSO sozp = new SOZapamtiPredstavu();
            sozp.execute(predstava);
            odgovor.setPoruka(sozp.getPoruka());
            odgovor.setStatus(sozp.getStatus());
            odgovor.setRezultat(sozp.vratiODO());

        } catch (Exception ex) {
            odgovor.setException(ex);
        }

        return odgovor;
    }

    public Odgovor vratiSveGlumce() throws Exception {
        odgovor = new Odgovor();

        try {
            OpstaSO sovsg = new SOVratiSveGlumce();
            sovsg.execute(new Glumac());
            odgovor.setPoruka(sovsg.getPoruka());
            odgovor.setStatus(sovsg.getStatus());
            odgovor.setRezultat(sovsg.vratiListu());

        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }

        return odgovor;
    }

    public Odgovor vratiGlumca(Glumac glumac, UslovWhere kriterijumWhere) throws Exception {
        odgovor = new Odgovor();

        try {
            OpstaSO sovg = new SOVratiGlumca(kriterijumWhere);
            sovg.execute(glumac);
            odgovor.setRezultat(sovg.vratiODO());
            odgovor.setPoruka(sovg.getPoruka());
            odgovor.setStatus(sovg.getStatus());
        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }

        return odgovor;
    }

    public Odgovor obrisiGlumca(Glumac glumac) throws Exception {
        odgovor = new Odgovor();

        try {
            OpstaSO soog = new SOObrisiGlumca();
            soog.execute(glumac);

            odgovor.setPoruka(soog.getPoruka());
            odgovor.setStatus(soog.getStatus());

        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }

        return odgovor;
    }

    public Odgovor vratiSvePredstave() throws Exception {
        odgovor = new Odgovor();

        try {
            OpstaSO sovsp = new SOVratiSvePredstave();
            sovsp.execute(new Predstava());
            odgovor.setPoruka(sovsp.getPoruka());
            odgovor.setStatus(sovsp.getStatus());
            odgovor.setRezultat(sovsp.vratiListu());

        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }

        return odgovor;
    }

    public Odgovor vratiPredstavu(Predstava predstava, UslovWhere kriterijumWhere) throws Exception {
        odgovor = new Odgovor();

        try {
            OpstaSO sovp = new SOVratiPredstavu(kriterijumWhere);
            sovp.execute(predstava);
            odgovor.setRezultat(sovp.vratiODO());
            odgovor.setPoruka(sovp.getPoruka());
            odgovor.setStatus(sovp.getStatus());
        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }

        return odgovor;
    }

    public Odgovor obrisiPredstavu(Predstava predstava) throws Exception {
        odgovor = new Odgovor();
        try {
            OpstaSO soog = new SOObrisiPredstavu();
            soog.execute(predstava);

            odgovor.setPoruka(soog.getPoruka());
            odgovor.setStatus(soog.getStatus());

        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }
        return odgovor;
    }

    public Odgovor sacuvajUlogu(Uloga uloga) {
        odgovor = new Odgovor();
        try {
            OpstaSO sozu = new SOZapamtiUlogu();
            sozu.execute(uloga);
            odgovor.setPoruka(sozu.getPoruka());
            odgovor.setStatus(sozu.getStatus());
            odgovor.setRezultat(sozu.vratiODO());

        } catch (Exception ex) {
            odgovor.setException(ex);
            //throw ex;
        }
        return odgovor;
    }

    public Odgovor vratiSveSale() throws Exception {
        odgovor = new Odgovor();
        try {
            OpstaSO sovsg = new SOVratiSveSale();
            sovsg.execute(new Sala());
            odgovor.setPoruka(sovsg.getPoruka());
            odgovor.setStatus(sovsg.getStatus());
            odgovor.setRezultat(sovsg.vratiListu());

        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }
        return odgovor;
    }

    public Odgovor sacuvajRepertoar(Repertoar repertoar) {
        odgovor = new Odgovor();
        try {
            OpstaSO sozr = new SOZapamtiRepertoar();
            sozr.execute(repertoar);
            odgovor.setPoruka(sozr.getPoruka());
            odgovor.setStatus(sozr.getStatus());
            odgovor.setRezultat(sozr.vratiODO());

        } catch (Exception ex) {
            odgovor.setException(ex);
            //throw ex;
        }
        return odgovor;
    }

    public Odgovor sacuvajStavkuRepertoara(StavkaRepertoara stavkaRepertoara) {
        odgovor = new Odgovor();
        try {
            OpstaSO sozsr = new SOZapamtiStavkuRepertoara();
            sozsr.execute(stavkaRepertoara);
            odgovor.setPoruka(sozsr.getPoruka());
            odgovor.setStatus(sozsr.getStatus());
            odgovor.setRezultat(sozsr.vratiODO());

        } catch (Exception ex) {
            odgovor.setException(ex);
            //throw ex;
        }
        return odgovor;
    }

    public Odgovor vratiSveKostime() throws Exception {
        odgovor = new Odgovor();
        try {
            OpstaSO sovsk = new SOVratiSveKostime();
            sovsk.execute(new Kostim());
            odgovor.setPoruka(sovsk.getPoruka());
            odgovor.setStatus(sovsk.getStatus());
            odgovor.setRezultat(sovsk.vratiListu());

        } catch (Exception ex) {
            odgovor.setException(ex);
            throw ex;
        }
        return odgovor;
    }

}
