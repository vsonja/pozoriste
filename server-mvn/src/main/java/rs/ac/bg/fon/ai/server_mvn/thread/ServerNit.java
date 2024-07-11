package rs.ac.bg.fon.ai.server_mvn.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class ServerNit extends Thread {

    private ServerSocket serverSocket;
    private List<KlijentNit> klijenti;

    public ServerNit() {
        klijenti = new ArrayList<>();
    }

    public List<KlijentNit> getKlijenti() {
        return klijenti;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while (!interrupted()) {
                System.out.println("Ceka se klijent...");
                Socket klijentSocket = serverSocket.accept();
                System.out.println("Klijent je povezan!");
                KlijentNit klijentNit = new KlijentNit(klijentSocket, this);
                klijentNit.start();
                klijenti.add(klijentNit);
            }
        } catch (IOException ex) {

        }

        prekidKlijentskihNiti();
    }

    public void zaustavi() {
        interrupt();

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            for (KlijentNit klijentNit : klijenti) {
                if (klijentNit != null && klijentNit.isAlive()) {
                    klijentNit.getKlijentSocket().close();
                    klijentNit.interrupt();
                    klijenti.remove(klijentNit);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void izbaciKlijenta(KlijentNit kn) {
        try {
            for (int i = 0; i < klijenti.size(); i++) {
                if (klijenti.get(i).equals(kn)) {
                    klijenti.get(i).getKlijentSocket().close();
                    klijenti.remove(klijenti.get(i));
                    System.out.println("Klijent je odjavljen.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prekidKlijentskihNiti() {
        for (KlijentNit k : klijenti) {
            try {
                k.getKlijentSocket().close();
                klijenti.remove(k);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
