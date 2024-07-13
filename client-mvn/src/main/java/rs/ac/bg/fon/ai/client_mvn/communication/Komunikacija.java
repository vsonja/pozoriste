package rs.ac.bg.fon.ai.client_mvn.communication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import rs.ac.bg.fon.ai.common_mvn.communication.Odgovor;
import rs.ac.bg.fon.ai.common_mvn.communication.Zahtev;

/**
 *
 * @author Sonja
 */
public class Komunikacija {

    private static Komunikacija instanca;
    private Socket socket;

    private Komunikacija() {
	    try {
	        Properties props = new Properties();
	        props.load(new FileInputStream("src/main/java/rs/ac/bg/fon/ai/client_mvn/config/server.properties"));
	        String serverAddress = props.getProperty("server.address");
	        int port = Integer.parseInt(props.getProperty("server.port"));
	        socket = new Socket(serverAddress, port);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

    public static Komunikacija getInstance() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public void posaljiZahtev(Zahtev zahtev) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(zahtev);
        out.flush();
    }

    public Odgovor primiOdgovor() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Odgovor odgovor = (Odgovor) in.readObject();
        return odgovor;
    }

}
