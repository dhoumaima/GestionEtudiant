package IHM;

import Model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.TextEvent;

public class FormPanel extends JPanel {
    JLabel lb_text,lb_langue,lb_cycle;
    JButton btn_langue, btn_save, btn_fermer;
    JPanel panelLangues ,contenu, panelCycle,panelButton, panelAnnee,panelLanguesListe;
    JComboBox<String> cb_cycle;
    JRadioButton Annee_1,Annee_2,Annee_3;
    ButtonGroup groupeAnnee;
    JScrollPane sc_Langues;

    Profile profile;

    public FormPanel(Profile profile)
    {
        this.profile =profile;

        this.setLayout(new BorderLayout(10,10));

        lb_text = new JLabel("Bonjour :"+profile.getPrenom()+" "+profile.getNom());
        lb_text.setFont(new Font("Arial",Font.BOLD,16));
        this.add(lb_text,BorderLayout.NORTH);

        contenu= new JPanel();
        contenu.setLayout(new GridLayout(4,1,10,10));

        panelLangues= new JPanel(new FlowLayout());
        panelCycle = new JPanel(new FlowLayout());
        panelButton = new JPanel(new FlowLayout());
        panelAnnee = new JPanel();
        panelAnnee.setLayout(new FlowLayout());


        lb_langue=new JLabel("Langues:");
        lb_langue.setPreferredSize(new Dimension(70,30));
        panelLangues.add(lb_langue);


        btn_langue= new JButton("+");
        btn_langue.setPreferredSize(new Dimension(60,60));
        btn_langue.addActionListener(new EcouteurForme(this));
        panelLangues.add(btn_langue);
        btn_langue.addActionListener(e -> {
            JPanel bloc = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JTextField tf = new JTextField();
            tf.setPreferredSize(new Dimension(120,25));

            Integer[] niveaux = {1,2,3,4,5};
            JComboBox<Integer> cb_niveau = new JComboBox<>(niveaux);
            cb_niveau.setPreferredSize(new Dimension(60,25));
            cb_niveau.setSelectedIndex(0);

            bloc.add(new JLabel("Langue:"));
            bloc.add(tf);
            bloc.add(new JLabel("Niveau:"));
            bloc.add(cb_niveau);

            panelLanguesListe.add(bloc);

            panelLanguesListe.revalidate();
            panelLanguesListe.repaint();
        });


        panelLanguesListe = new JPanel();
        panelLanguesListe.setLayout(new BoxLayout(panelLanguesListe,BoxLayout.Y_AXIS));

        sc_Langues=new JScrollPane(panelLanguesListe);
        sc_Langues.setPreferredSize(new Dimension(60,20));


        lb_cycle=new JLabel("Cycle Ingénierie:");
        lb_cycle.setPreferredSize(new Dimension(120,30));
        panelCycle.add(lb_cycle);

        String[] cycles={"1er Cycle","2eme Cycle"};
        cb_cycle = new JComboBox<>(cycles);
        panelCycle.add(cb_cycle);



        Annee_1= new JRadioButton("1er année");
        Annee_2= new JRadioButton("2éme année");
        Annee_3= new JRadioButton("3émé année");

        groupeAnnee = new ButtonGroup();
        groupeAnnee.add(Annee_1);
        groupeAnnee.add(Annee_2);

        panelAnnee.add(Annee_1);
        panelAnnee.add(Annee_2);


        btn_save= new JButton("Enregistrer");
        btn_save.setPreferredSize(new Dimension(100,50));
        panelButton.add(btn_save);

        btn_fermer= new JButton("Fermer");
        btn_fermer.setPreferredSize(new Dimension(100,50));
        btn_fermer.addActionListener(new EcouteurForme(this));
        panelButton.add(btn_fermer);


        contenu.add(panelLangues);
        contenu.add(sc_Langues);
        contenu.add(panelCycle);
        contenu.add(panelAnnee);


        this.add(contenu,BorderLayout.CENTER);
        this.add(panelButton,BorderLayout.SOUTH);
        //les ecouteur
        cb_cycle.addActionListener(new EcouteurForme(this));
        btn_save.addActionListener(new EcouteurForme(this));
    }
}
