package IHM;

import javax.swing.*;
import java.awt.*;

public class FrameBorder extends JInternalFrame {
    JButton B1,B2,B3,B4,B5,B6;
    FrameBorder(){
        //Creation Frame
        this.setLayout(new BorderLayout());
        this.setSize(800,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        B1 = new JButton("B1");
        this.add(B1,BorderLayout.NORTH);
        B2 = new JButton("B2");
        this.add(B2,BorderLayout.WEST);
        B3 = new JButton("B3");
        this.add(B3,BorderLayout.EAST);
        B4 = new JButton("B4");
        B6= new JButton("B6");
        JPanel pSouth = new JPanel();
        pSouth.setLayout(new FlowLayout());
        pSouth.add(B4);
        pSouth.add(B6);
        this.add(pSouth,BorderLayout.SOUTH);
        B5 = new JButton("B5");
        this.add(B5,BorderLayout.CENTER);


    }
}
