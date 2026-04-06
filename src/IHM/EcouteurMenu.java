package IHM;

import Threads.CercleAnimation;
import Threads.FrameAnimation;

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
        if (e.getSource() == d.Animation) {
            FrameAnimation animation = new FrameAnimation();
            animation.setVisible(true);
            animation.setSize(d.desktop.getSize());
            animation.setMaximizable(true);
            animation.setClosable(true);
            animation.setIconifiable(true);
            animation.setResizable(true);
            d.desktop.add(animation);
            animation.toFront();

        }

        if (e.getSource() == d.CercleAnimation) {
            CercleAnimation cercleAnimation = new CercleAnimation();
            cercleAnimation.setVisible(true);
            cercleAnimation.setSize(d.desktop.getSize());
            cercleAnimation.setMaximizable(true);
            cercleAnimation.setClosable(true);
            cercleAnimation.setIconifiable(true);
            cercleAnimation.setResizable(true);
            d.desktop.add(cercleAnimation);
            cercleAnimation.toFront();

        }
        if (e.getSource() == d.Client) {
            ClientInterface clientInterface = new ClientInterface();
            clientInterface.setVisible(true);
            clientInterface.setSize(d.desktop.getSize());
            clientInterface.setMaximizable(true);
            clientInterface.setClosable(true);
            clientInterface.setIconifiable(true);
            clientInterface.setResizable(true);
            d.desktop.add(clientInterface);
            clientInterface.toFront();

        }
    }
}
