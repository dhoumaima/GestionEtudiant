package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



public class CurriculumVitae extends JInternalFrame {
    //declaration des composantes
    JButton btn_valider,btn_quitter,btn_photo,btn_color;
    JLabel lb_titre,lb_nom,lb_prenom,lb_age,lb_diplome,lb_sexe,lb_langues, lb_skills,lb_image;
    JList<String> listSkills;
    JScrollPane scrollSkills;
    JTextField tf_nom,tf_prenom;
    JSpinner sp_age;//input date or numbers
    JComboBox<String> cb_diplome;
    JRadioButton rb_homme,rb_femme;
    ButtonGroup bg_sexe;
    JCheckBox cb_fr,cb_en,cb_ar;
    JFileChooser fc_photo;
    File selectedPhoto;
    JLabel[] labels;

    CurriculumVitae()
    {
        this.setTitle("Curriculum Vitae");
        this.setSize(800,600);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        //creation d'interface
        //title
        lb_titre=new JLabel("Curriculum Vitea");
        lb_titre.setForeground(Color.pink);
        lb_titre.setBackground(Color.gray);
        lb_titre.setOpaque(true);
        lb_titre.setPreferredSize(new Dimension(800,40));
        lb_titre.setHorizontalAlignment(JLabel.CENTER);
        lb_titre.setFont(new Font(Font.SERIF,Font.BOLD|Font.ITALIC,32));

        JPanel pTitle = new JPanel();
        pTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        pTitle.add(lb_titre);
        this.add(pTitle);

        //photo
        lb_image = new JLabel();
        lb_image.setPreferredSize(new Dimension(100,100));
        lb_image.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        btn_photo = new JButton("Chosir une photo");
        btn_photo.addActionListener(e->{
            fc_photo = new JFileChooser();
            fc_photo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "jpg","png"
            ));
            int option = fc_photo.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                selectedPhoto=fc_photo.getSelectedFile();
                ImageIcon icon = new ImageIcon(selectedPhoto.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                lb_image.setIcon(new ImageIcon(img));
            }
        });
        JPanel pPhotoInfo = new JPanel();
        JPanel pPhoto = new JPanel();
        pPhoto.setLayout(new BorderLayout(20,10));//20 hor et 10 vert
        pPhoto.add(lb_image,BorderLayout.WEST);
        pPhoto.add(btn_photo,BorderLayout.SOUTH);
        pPhotoInfo.add(pPhoto, BorderLayout.WEST);


        //generale information
        JPanel pInfo = new JPanel();
        pInfo.setLayout(new GridLayout(4,2,10,10));
        lb_nom=new JLabel("Nom");
        lb_nom.setPreferredSize(new Dimension(400,30));
        pInfo.add(lb_nom);

        tf_nom=new JTextField(20);
        tf_nom.setPreferredSize(new Dimension(400,30));
        pInfo.add(tf_nom);

        lb_prenom=new JLabel("Prenom");
        lb_prenom.setPreferredSize(new Dimension(400,30));
        pInfo.add(lb_prenom);

        tf_prenom=new JTextField(20);
        tf_prenom.setPreferredSize(new Dimension(400,30));
        pInfo.add(tf_prenom);

        //age
        lb_age = new JLabel("Age");
        lb_age.setPreferredSize(new Dimension(400,30));

       SpinnerNumberModel modelAge = new SpinnerNumberModel(18,16,120,1);
       sp_age = new JSpinner(modelAge);
       JPanel pAge=new JPanel(new FlowLayout());
       pAge.add(lb_age);
       pAge.add(sp_age);
       pInfo.add(pAge);


        //gender
        lb_sexe=new JLabel("Sexe:");
        lb_sexe.setPreferredSize(new Dimension(150,30));

        rb_femme=new JRadioButton("Femme");
        rb_homme=new JRadioButton("Homme");

        //only one
        bg_sexe=new ButtonGroup();
        bg_sexe.add(rb_femme);
        bg_sexe.add(rb_homme);

        //one line
        JPanel pSexe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pSexe.add(lb_sexe);
        pSexe.add(rb_femme);
        pSexe.add(rb_homme);
        pInfo.add(pSexe);
        pPhotoInfo.add(pInfo,BorderLayout.CENTER);
        this.add(pPhotoInfo);

        //education
        JPanel pDilpome = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        lb_diplome = new JLabel("Diplôme");
        lb_diplome.setPreferredSize(new Dimension(150,30));
        String[] diplomeOptions = {"Bac","Licence","Master","Ingenieurie","Doctorat"};
        cb_diplome=new JComboBox<>(diplomeOptions);
        cb_diplome.setPreferredSize(new Dimension(250,30));

        pDilpome.add(lb_diplome);
        pDilpome.add(cb_diplome);
        pDilpome.setBorder(BorderFactory.createTitledBorder("Formation"));
        this.add(pDilpome);


        //languages
        JPanel pLangues = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        lb_langues = new JLabel("Langues:");
        cb_ar = new JCheckBox("Arabe");
        cb_fr = new JCheckBox("Français");
        cb_en = new JCheckBox("Anglais");

        pLangues.add(lb_langues);
        pLangues.add(cb_fr);
        pLangues.add(cb_ar);
        pLangues.add(cb_en);
        pLangues.setBorder(BorderFactory.createTitledBorder("Langues"));
        this.add(pLangues);


        //skills
        JPanel pSkills = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        lb_skills = new JLabel("Compétences:");
        lb_skills.setPreferredSize(new Dimension(150,30));
        String[] skills = {"java","Python","C++","HTML","CSS","JS","SQL","c#","Git"};
        listSkills = new JList<>(skills);
        listSkills.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollSkills = new JScrollPane(listSkills);
        scrollSkills.setPreferredSize(new Dimension(300,100));

        pSkills.add(lb_skills);
        pSkills.add(scrollSkills);
        pSkills.setBorder(BorderFactory.createTitledBorder("Compétences"));
        this.add(pSkills);


        //choisir couleur
        labels= new JLabel[]{
                lb_nom,
                lb_prenom,
                lb_skills,
                lb_sexe,
                lb_age,
                lb_langues,
                lb_diplome,
        };
        btn_color=new JButton("Choisir une couleur");
        btn_color.addActionListener(e->{
            Color selectedColor = JColorChooser.showDialog(
                    null,
                    "Choisir couleur",
                    labels[0].getForeground()//initiale
            );
            if(selectedColor != null)
            {
                for(JLabel l : labels){
                    l.setForeground(selectedColor);
                }
            }
        });

        JPanel pColor=new JPanel(new FlowLayout());
        pColor.add(btn_color);
        this.add(pColor);

        btn_valider=new JButton("Valider");
        this.setVisible(true);
        this.add(btn_valider);

        btn_quitter=new JButton("Quitter");
        this.setVisible(true);
        this.add(btn_quitter);

        //events: ecouteur
        btn_quitter.addActionListener(new EcouteurButton());
        btn_valider.addActionListener(new EcouteurButton());


    }
    class EcouteurButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btn_quitter){
                System.exit(0);
            }
            if(e.getSource() == btn_valider) {
                // Récupération des informations
                String nom = tf_nom.getText();
                String prenom = tf_prenom.getText();
                int age = (Integer) sp_age.getValue();
                String sexe = rb_homme.isSelected() ? "Homme" : (rb_femme.isSelected() ? "Femme" : "");

                StringBuilder langues = new StringBuilder();
                if(cb_ar.isSelected()) langues.append("Arabe, ");
                if(cb_fr.isSelected()) langues.append("Français, ");
                if(cb_en.isSelected()) langues.append("Anglais, ");
                if(langues.length() > 0) langues.setLength(langues.length() - 2); // supprime la dernière virgule

                java.util.List<String> selectedSkills = listSkills.getSelectedValuesList();
                String skillsText = String.join(", ", selectedSkills);
                String diplome = (String) cb_diplome.getSelectedItem();

                // Choisir le fichier PDF
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("CV_" + nom + "_" + prenom + ".pdf"));
                int option = fileChooser.showSaveDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    File pdfFile = fileChooser.getSelectedFile();
                    try {
                        // Création du document PDF
                        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                        com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(pdfFile));
                        document.open();

                        // Ajout du contenu
                        com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, com.itextpdf.text.Font.BOLD);
                        com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12);

                        document.add(new com.itextpdf.text.Paragraph("Curriculum Vitae", titleFont));
                        document.add(new com.itextpdf.text.Paragraph(" "));
                        document.add(new com.itextpdf.text.Paragraph("Nom: " + nom, normalFont));
                        document.add(new com.itextpdf.text.Paragraph("Prénom: " + prenom, normalFont));
                        document.add(new com.itextpdf.text.Paragraph("Age: " + age, normalFont));
                        document.add(new com.itextpdf.text.Paragraph("Sexe: " + sexe, normalFont));
                        document.add(new com.itextpdf.text.Paragraph("Diplôme: " + diplome, normalFont));
                        document.add(new com.itextpdf.text.Paragraph("Langues: " + langues, normalFont));
                        document.add(new com.itextpdf.text.Paragraph("Compétences: " + skillsText, normalFont));

                        document.close();

                        JOptionPane.showMessageDialog(null, "PDF généré avec succès !\n" + pdfFile.getAbsolutePath());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de la génération du PDF !");
                    }
                }
            }

        }
    }
}
