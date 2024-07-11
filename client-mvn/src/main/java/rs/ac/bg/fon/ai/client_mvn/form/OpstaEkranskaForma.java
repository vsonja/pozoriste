package rs.ac.bg.fon.ai.client_mvn.form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sonja
 */
public class OpstaEkranskaForma extends JFrame {

    public void prikaziPoruku(String sadrzaj, String naslov) {
        JOptionPane.showMessageDialog(this, sadrzaj, naslov, JOptionPane.INFORMATION_MESSAGE);
    }

}
