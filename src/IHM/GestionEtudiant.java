package IHM;

import Adapter.EtudiantTableModel;
import DataBase.EtudiantImplementation;
import DataBase.ProfileImplementation;
import Model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class GestionEtudiant extends JInternalFrame {
    JLabel lb_Nom,lb_Prenom,lb_moyenne,lb_cin;

    JTextField tf_Nom,tf_Prenom,tf_moyenne,tf_cin;

    JButton btn_ajouter;

    JTable jtab;
    EtudiantTableModel model;
    public GestionEtudiant(){
        this.setLayout(new BorderLayout());
        this.setTitle("Gestion des étudiants");
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lb_cin = new JLabel("Cin: ");
        lb_cin.setPreferredSize(new Dimension(50,60));
        tf_cin=new JTextField();
        tf_cin.setPreferredSize(new Dimension(120,40));

        lb_Nom = new JLabel("Nom: ");
        lb_Nom.setPreferredSize(new Dimension(50,60));
        tf_Nom=new JTextField();
        tf_Nom.setPreferredSize(new Dimension(120,40));


        lb_Prenom = new JLabel("Prénom: ");
        lb_Prenom.setPreferredSize(new Dimension(70,60));
        tf_Prenom=new JTextField();
        tf_Prenom.setPreferredSize(new Dimension(120,40));


        lb_moyenne = new JLabel("moyenne: ");
        lb_moyenne.setPreferredSize(new Dimension(50,60));
        tf_moyenne=new JTextField();
        tf_moyenne.setPreferredSize(new Dimension(120,40));

        btn_ajouter = new JButton("Ajouter");
        btn_ajouter.setPreferredSize(new Dimension(120,40));
        btn_ajouter.addActionListener(e -> {
                    int cin = Integer.parseInt(tf_cin.getText());
                    String nom = tf_Nom.getText();
                    String prenom = tf_Prenom.getText();
                    double moyenne = Double.parseDouble(tf_moyenne.getText());

                    if (tf_cin != null) {
                        model.ajouterEtudiant(cin, nom, prenom, moyenne);

                        tf_cin.setText("");
                        tf_Prenom.setText("");
                        tf_Nom.setText("");
                        tf_moyenne.setText("");
                    }
                }
        );


                    String requete_selection = "select * from etudiant";
                    EtudiantImplementation implementation = new EtudiantImplementation();
                    ResultSet rs = implementation.selectEtudiant(requete_selection);

                    model = new EtudiantTableModel(rs, implementation);
                    jtab = new JTable();
                    jtab.setModel(model);//passe la gestion de jtable  a model
                    this.add(new JScrollPane(jtab));

                    jtab.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            //Button3 = click droite
                            if(e.getButton()==MouseEvent.BUTTON3)
                            {
                                EtudiantPopUp popUp = new EtudiantPopUp(GestionEtudiant.this);
                                popUp.show(jtab,e.getX(),e.getY());
                            }
                        }
                    });
                    JPanel pNorth = new JPanel();
                    pNorth.setLayout(new FlowLayout());
                    pNorth.add(lb_cin);
                    pNorth.add(tf_cin);
                    pNorth.add(lb_Nom);
                    pNorth.add(tf_Nom);
                    pNorth.add(lb_Prenom);
                    pNorth.add(tf_Prenom);
                    pNorth.add(lb_moyenne);
                    pNorth.add(tf_moyenne);
                    pNorth.add(btn_ajouter);
                    this.add(pNorth, BorderLayout.NORTH);
                }


            }
