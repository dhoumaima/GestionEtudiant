package IHM;

import DataBase.ProfileImplementation;
import Model.Profile;

import javax.swing.*;

public class MyPopUp extends JPopupMenu {
    GestionProfiles gp;

    public MyPopUp(GestionProfiles gp){
        this.gp =gp;


        JMenuItem itemMod= new JMenuItem("Modifier");
        JMenuItem itemSupp= new JMenuItem("Suprimer");
        JMenuItem itemSuppTt= new JMenuItem("Suprimer Tout ");

        itemSuppTt.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Voulez-vous supprimer tous les profils ?",
                    "Supprimer Tout",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION){

                ProfileImplementation pi = new ProfileImplementation();

                for(Profile p : DataProfil.data){
                    pi.deleteProfile(p.getPseudo());
                }

                DataProfil.data.clear();

                gp.model.clear();
                gp.jtp.removeAll();

                JOptionPane.showMessageDialog(null,"Tous les profils ont été supprimés !");
            }
        });
        itemSupp.addActionListener(e -> {

            int location = gp.jl.getSelectedIndex();

            String pseudo = gp.jl.getModel().getElementAt(location).toString();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Voulez-vous supprimer ce profile " + pseudo + " ?",
                    "Supprimer",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION){
                ProfileImplementation pi = new ProfileImplementation();
                pi.deleteProfile(pseudo);

                DataProfil.data.removeIf(p -> p.getPseudo().equalsIgnoreCase(pseudo));

                gp.model.removeElementAt(location);


                int indexTab = gp.jtp.indexOfTab(pseudo);
                if(indexTab != -1){
                    gp.jtp.removeTabAt(indexTab);
                }

                JOptionPane.showMessageDialog(null,"Profil supprimé avec succès !");
            }
        });


        itemMod.addActionListener(e -> {

            int index = gp.jl.getSelectedIndex();
            if(index == -1) return;

            String pseudo = gp.jl.getSelectedValue().toString();

            Profile profile = null;

            for(Profile p : DataProfil.data){
                if(p.getPseudo().equalsIgnoreCase(pseudo)){
                    profile = p;
                    break;
                }
            }
            if(profile == null) return;

            String newNom = JOptionPane.showInputDialog(
                    null,
                    "Modifier le nom :",
                    profile.getNom()
            );
            if(newNom == null) return; // bouton cancel

            String newPrenom = JOptionPane.showInputDialog(
                    null,
                    "Modifier le prénom :",
                    profile.getPrenom()
            );

            if(newPrenom == null) return;

            if(newNom.isEmpty()) newNom = profile.getNom();
            if(newPrenom.isEmpty()) newPrenom = profile.getPrenom();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Confirmer modification ?\n\nAncien Nom : " + profile.getNom() +
                            "\nAncien Prénom : " + profile.getPrenom() +
                            "\n\nNouveau Nom : " + newNom +
                            "\nNouveau Prénom : " + newPrenom,
                    "Confirmer",
                    JOptionPane.YES_NO_OPTION
            );
            if(confirm == JOptionPane.YES_OPTION){
                profile.setNom(newNom);
                profile.setPrenom(newPrenom);

                ProfileImplementation pi = new ProfileImplementation();
                pi.updateProfile(profile);

                JOptionPane.showMessageDialog(null,"Profil modifié avec succès !");
            }
        });
        this.add(itemMod);
        this.add(itemSuppTt);
        this.add(itemSupp);
    }
}
