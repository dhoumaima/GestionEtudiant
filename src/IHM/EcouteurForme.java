package IHM;

import DataBase.ProfileImplementation;
import Model.Langue;
import Model.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurForme implements ActionListener {
    FormPanel fr;
    public EcouteurForme(FormPanel formPanel) {
        this.fr=formPanel;
    }
//,"2eme Cycle"
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fr.cb_cycle){
            if(fr.cb_cycle.getSelectedItem().equals("1er Cycle")){
                fr.panelAnnee.removeAll();
                fr.groupeAnnee.clearSelection();
                fr.groupeAnnee.add(fr.Annee_1);
                fr.groupeAnnee.add(fr.Annee_2);
                fr.panelAnnee.add(fr.Annee_1);
                fr.panelAnnee.add(fr.Annee_2);

            }
            else {
                fr.groupeAnnee.add(fr.Annee_1);
                fr.groupeAnnee.add(fr.Annee_2);
                fr.groupeAnnee.add(fr.Annee_3);
                fr.panelAnnee.add(fr.Annee_1);
                fr.panelAnnee.add(fr.Annee_2);
                fr.panelAnnee.add(fr.Annee_3);
            }
            fr.panelAnnee.revalidate();
            fr.panelAnnee.repaint();
        }

        if(e.getSource()==fr.btn_save){
            Profile p = fr.profile;
            p.setCycle((String) fr.cb_cycle.getSelectedItem());
            if(fr.Annee_2.isSelected()){
                p.setAnnee(2);
            }
            if(fr.Annee_3.isSelected()){
                p.setAnnee(3);
            }

            p.getLangues().clear();
            for(java.awt.Component c : fr.panelLanguesListe.getComponents()){
                JPanel bloc = (JPanel) c;
                JTextField tf = (JTextField) bloc.getComponent(1);
                JComboBox cb = (JComboBox) bloc.getComponent(3);
                String nomLangue = tf.getText();
                int niveau = (int) cb.getSelectedItem();
                if(!nomLangue.isEmpty()){
                    p.getLangues().add(new Langue(nomLangue,niveau));
                }
            }

            ProfileImplementation pi = new ProfileImplementation();
            pi.updateProfileComplet(p);
            JOptionPane.showMessageDialog(null,"Enregistré avec succès !");
        }
        if(e.getSource()==fr.btn_fermer) {
            JTabbedPane tabPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, fr);
            if (tabPane != null) {
                tabPane.remove(fr);
            }
        }
    }
}
