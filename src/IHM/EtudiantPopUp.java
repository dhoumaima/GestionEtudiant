package IHM;

import Adapter.EtudiantTableModel;
import DataBase.EtudiantImplementation;
import DataBase.ProfileImplementation;

import javax.swing.*;

public class EtudiantPopUp extends JPopupMenu {
    GestionEtudiant ge;
    public EtudiantPopUp(GestionEtudiant gestionEtudiant) {
        this.ge=gestionEtudiant;

        JMenuItem itemSupp= new JMenuItem("Suprimer");

        itemSupp.addActionListener(e -> {

            int row =ge.jtab.getSelectedRow();
            int cin = (int) ge.model.getValueAt(row,0);
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Voulez-vous supprimer ce profile  ?",
                    "Supprimer",
                    JOptionPane.YES_NO_OPTION
            );
            if(confirm == JOptionPane.YES_OPTION){
                ge.model.supprimerEtudiant(cin,row);

                JOptionPane.showMessageDialog(null,"Etudiant supprimé avec succès !");
            }
        });

        this.add(itemSupp);


    }


}
