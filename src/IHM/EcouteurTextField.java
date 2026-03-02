package IHM;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurTextField extends MouseAdapter {
    GestionProfiles gp ;
    public EcouteurTextField(GestionProfiles gp) {
        this.gp=gp;
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    if(e.getSource()==gp.tf_Nom)
    {
        gp.lb_Help.setText("saisir votre nom");
    }
        if(e.getSource()==gp.tf_Prenom)
        {
            gp.lb_Help.setText("saisir votre prenom");
        }
        if(e.getSource()==gp.tf_Pseudo)
        {
            gp.lb_Help.setText("pseudo unique");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==gp.tf_Nom)
        {
            gp.lb_Help.setText("Help!");
        }
        if(e.getSource()==gp.tf_Prenom)
        {
            gp.lb_Help.setText("Help!");
        }
        if(e.getSource()==gp.tf_Pseudo)
        {
            gp.lb_Help.setText("Help!");
        }

    }


}
