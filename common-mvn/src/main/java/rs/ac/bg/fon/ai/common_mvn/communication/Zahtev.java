package rs.ac.bg.fon.ai.common_mvn.communication;

import rs.ac.bg.fon.ai.common_mvn.constants.Operacija;
import java.io.Serializable;

/**
 *
 * @author Sonja
 */
public class Zahtev implements Serializable {

    private Operacija operacija;
    private Object argument;

    public Zahtev() {
    }

    public Zahtev(Operacija operacija, Object argument) {
        this.operacija = operacija;
        this.argument = argument;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

}
