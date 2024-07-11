package rs.ac.bg.fon.ai.common_mvn.communication;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import java.io.Serializable;

/**
 *
 * @author Sonja
 */
public class Odgovor implements Serializable {

    private Object rezultat;
    private Exception exception;
    private Status status;
    private String poruka;

    public Odgovor() {
    }

    public Odgovor(Object rezultat, Exception exception, Status status, String poruka) {
        this.rezultat = rezultat;
        this.exception = exception;
        this.status = status;
        this.poruka = poruka;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
