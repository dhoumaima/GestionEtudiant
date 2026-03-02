package IHM;

import javax.swing.*;
import java.awt.*;

public class FrameFlow extends JInternalFrame {
    JButton B1,B2,B3,B4,B5;
    FrameFlow(){
        //Creation Frame
        this.setLayout(new FlowLayout());
        this.setSize(800,600);


        B1 = new JButton("B1");
        this.add(B1);
        B2 = new JButton("B2");
        this.add(B2);
        B3 = new JButton("B3");
        this.add(B3);
        B4 = new JButton("B4");
        this.add(B4);
        B5 = new JButton("B5");
        this.add(B5);


    }
}
