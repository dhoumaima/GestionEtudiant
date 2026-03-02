package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class EcouteurLabel extends MouseAdapter {
    GestionProfiles gp ;

    public EcouteurLabel(GestionProfiles gestionProfiles) {
        this.gp=gestionProfiles;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel src=(JLabel) e.getSource();
        src.setForeground(Color.pink);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel src=(JLabel) e.getSource();
        src.setForeground(null);


    }
}
