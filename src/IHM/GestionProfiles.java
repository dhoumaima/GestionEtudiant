package IHM;

import DataBase.ProfileImplementation;
import Model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class GestionProfiles  extends JInternalFrame {
    JLabel lb_Nom,lb_Prenom,lb_Pseudo,lb_Help;
    JTextField tf_Nom,tf_Prenom,tf_Pseudo;
    JButton btn_enregistrer;
    DefaultListModel model;
    JTabbedPane jtp;
    JList jl;
     public GestionProfiles(){
        this.setLayout(new BorderLayout());
        this.setTitle("Gestion des profiles");
        this.setSize(800,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lb_Nom = new JLabel("Nom: ");
        lb_Nom.setPreferredSize(new Dimension(50,60));
        tf_Nom=new JTextField();
        tf_Nom.setPreferredSize(new Dimension(120,40));


        lb_Prenom = new JLabel("Prénom: ");
        lb_Prenom.setPreferredSize(new Dimension(50,60));
        tf_Prenom=new JTextField();
        tf_Prenom.setPreferredSize(new Dimension(120,40));


        lb_Pseudo = new JLabel("Pseudo: ");
        lb_Pseudo.setPreferredSize(new Dimension(50,60));
        tf_Pseudo=new JTextField();
        tf_Pseudo.setPreferredSize(new Dimension(120,40));

        btn_enregistrer = new JButton("Enregistrer");
        btn_enregistrer.setPreferredSize(new Dimension(120,40));
        btn_enregistrer.addActionListener(e -> {
            String pseudo = tf_Pseudo.getText();
            String nom = tf_Nom.getText();
            String prenom = tf_Prenom.getText();
            if(!nom.isEmpty())
            {
                if(!prenom.isEmpty())
                {if(!pseudo.isEmpty()) {
                        boolean existe = false;
                        for(Profile p : DataProfil.data) {
                            if (p.getPseudo().equalsIgnoreCase(pseudo)) {
                                existe = true;
                                break;
                            }
                        }
                        if(!existe){
                            Profile profile = new Profile(nom,prenom,pseudo);
                            ProfileImplementation profileimp = new ProfileImplementation();
                            int a = profileimp.insertProfile(profile);
                            if(a>0){
                                System.out.println("OK");
                                DataProfil.AddProfile(profile);
                            }
                            model.addElement(pseudo);

                            tf_Pseudo.setText("");
                            tf_Prenom.setText("");
                            tf_Nom.setText("");
                        }
                        else{lb_Help.setText("Ce pseudo existe déja !");}
                    }
                }
                else lb_Help.setText("Tu dois donner un prenom");
            }
            else lb_Help.setText("Tu dois donner un nom");
        });

        JPanel pNorth = new JPanel();
        pNorth.setLayout(new FlowLayout());
        pNorth.add(lb_Nom);
        pNorth.add(tf_Nom);
        pNorth.add(lb_Prenom);
        pNorth.add(tf_Prenom);
        pNorth.add(lb_Pseudo);
        pNorth.add(tf_Pseudo);
        pNorth.add(btn_enregistrer);
        this.add(pNorth,BorderLayout.NORTH);

        //partie centre
        JSplitPane jsp = new JSplitPane();

        jl = new JList();
        model = new DefaultListModel();
        jl.setModel(model);

         ProfileImplementation profileImp = new ProfileImplementation();
         ResultSet rs = profileImp.selectProfile("SELECT * FROM profile");

         try {
             while (rs.next()) {
                 String nom = rs.getString("nom");
                 String prenom = rs.getString("prenom");
                 String pseudo = rs.getString("pseudo");

                 Profile p = new Profile(nom, prenom, pseudo);

                 model.addElement(pseudo);       // ajout dans la JList
                 DataProfil.AddProfile(p);       // ajout dans votre liste locale
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }

        jl.setPreferredSize(new Dimension(300,500));
        jsp.setLeftComponent(jl);
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getClickCount()==2){
                    String ps = jl.getSelectedValue().toString();
                    int index = jtp.indexOfTab(ps);
                    Profile profileSelectionne = null;
                    for(Profile p : DataProfil.data)
                    {
                        if(p.getPseudo().equalsIgnoreCase(ps)){
                            profileSelectionne=p;
                            break;
                        }
                    }
                    if(index ==-1)
                    {   FormPanel fp = new FormPanel(profileSelectionne);
                        jtp.addTab(ps,fp);
                        jtp.setSelectedComponent(fp);
                    }
                    else{
                        jtp.setSelectedIndex(index);
                    }

                }
                //Button3 = click droite
                if(e.getButton()==MouseEvent.BUTTON3)
                {
                    MyPopUp popUp = new MyPopUp(GestionProfiles.this);
                    popUp.show(jl,e.getX(),e.getY());
                }
            }
        });

        jtp = new JTabbedPane();

        jsp.setRightComponent(jtp);
        this.add(jsp,BorderLayout.CENTER);

        //help
        lb_Help= new JLabel("HELP!");
        lb_Help.setPreferredSize(new Dimension(200,60));
        this.add(lb_Help,BorderLayout.SOUTH);

        //ecouteurlabel
         lb_Nom.addMouseListener(new EcouteurLabel(this));
         lb_Pseudo.addMouseListener(new EcouteurLabel(this));
         lb_Prenom.addMouseListener(new EcouteurLabel(this));

         //ecouteur text
         tf_Nom.addMouseListener(new EcouteurTextField(this));
         tf_Pseudo.addMouseListener(new EcouteurTextField(this));
         tf_Prenom.addMouseListener(new EcouteurTextField(this));
    }

}
