package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurMenu implements ActionListener {
    Dashboard d;

    EcouteurMenu(Dashboard d) {
        this.d = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == d.Flow) {
            FrameFlow f = new FrameFlow();
            f.setVisible(true);
            d.desktop.add(f);
            f.toFront();
            f.setMaximizable(true);
            f.setResizable(true);
            f.setClosable(true);
            f.setIconifiable(true);
        }
        if (e.getSource() == d.Grid) {
            Grid g = new Grid();
            g.setVisible(true);
            d.desktop.add(g);
            g.toFront();
            g.setMaximizable(true);
            g.setResizable(true);
            g.setClosable(true);
            g.setIconifiable(true);

        }
        if (e.getSource() == d.Border) {
            FrameBorder b = new FrameBorder();
            b.setVisible(true);
            d.desktop.add(b);
            b.toFront();
            b.setMaximizable(true);
            b.setResizable(true);
            b.setClosable(true);
            b.setIconifiable(true);
            b.toFront();
        }
        if (e.getSource() == d.GestionProfils) {
            GestionProfiles gestion = new GestionProfiles();
            gestion.setVisible(true);

            gestion.setMaximizable(true);
            gestion.setResizable(true);
            gestion.setClosable(true);
            gestion.setIconifiable(true);
            gestion.toFront();
            d.desktop.add(gestion);
            gestion.toFront();
        }
        if (e.getSource() == d.CuruclumVitae) {
            CurriculumVitae cv = new CurriculumVitae();
            cv.setVisible(true);
            d.desktop.add(cv);
            cv.toFront();
            cv.setMaximizable(true);
            cv.setResizable(true);
            cv.setClosable(true);
            cv.setIconifiable(true);
            cv.toFront();
            //setactive
        }
        if (e.getSource() == d.GestionEtudiant) {
            GestionEtudiant gestion = new GestionEtudiant();
            gestion.setVisible(true);
            gestion.setMaximizable(true);
            gestion.setClosable(true);
            gestion.setIconifiable(true);
            gestion.toFront();
            gestion.setResizable(true);
            gestion.toFront();
            d.desktop.add(gestion);

        }
    }
}
