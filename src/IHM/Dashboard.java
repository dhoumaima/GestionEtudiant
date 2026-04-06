package IHM;

import Threads.FrameAnimation;
import Threads.TimeSinusoide;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JMenu Tp1,Tp2 ,Tp3,TP4,TP5;
    JLabel Title;
    JMenuItem Grid,Flow,Border,GestionProfils,CuruclumVitae,GestionEtudiant,Animation,CercleAnimation,Client;
    JDesktopPane desktop;

    Dashboard(){
        //title
        this.setTitle("Tp JAVA");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Flow=new JMenuItem("Flow");
        Flow.addActionListener(new EcouteurMenu(this));

        Border=new JMenuItem("Border");
        Border.addActionListener(new EcouteurMenu(this));

        Grid=new JMenuItem("Grid");
        Grid.addActionListener(new EcouteurMenu(this));
        //creation
        Tp1=new JMenu("TP1");
        Tp1.add(Flow);
        Tp1.add(Border);
        Tp1.add(Grid);


        CuruclumVitae=new JMenuItem("Curuclum Vitae");
        CuruclumVitae.addActionListener(new EcouteurMenu(this));

        GestionProfils=new JMenuItem("Gestion des Profiles");
        GestionProfils.addActionListener(new EcouteurMenu(this));

        Tp2=new JMenu("TP2");
        Tp2.add(CuruclumVitae);
        Tp2.add(GestionProfils);

        GestionEtudiant = new JMenuItem("Gestion Etudiants");
        GestionEtudiant.addActionListener(new EcouteurMenu(this));

        Tp3=new JMenu("TP3");
        Tp3.add(GestionEtudiant);

        Animation =new JMenuItem("Animation");
        Animation.addActionListener(new EcouteurMenu(this));


        CercleAnimation =new JMenuItem("Cercle Animation");
        CercleAnimation.addActionListener(new EcouteurMenu(this));

        TP4 = new JMenu("TP4");
        TP4.add(Animation);
        TP4.add(CercleAnimation);

        Client =new JMenuItem("Client");
        Client.addActionListener(new EcouteurMenu(this));

        TP5 = new JMenu("TP5");
        TP5.add(Client);

        //creation
        menuBar =new JMenuBar();
        menuBar.add(Tp1);
        menuBar.add(Tp2);
        menuBar.add(Tp3);
        menuBar.add(TP4);
        menuBar.add(TP5);
        this.setJMenuBar(menuBar);


        //desktop
        desktop = new JDesktopPane();
        this.add(desktop);

        TimeSinusoide ts = new TimeSinusoide();
        desktop.add(ts);
        ts.setVisible(true);
        try {
            ts.setMaximum(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
