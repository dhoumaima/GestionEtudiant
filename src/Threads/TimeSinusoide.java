package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimeSinusoide extends JInternalFrame{
    int x=0;
    int y=200;

    AnimationTime t=null;
    PanelTime panel;
    JButton btnStart,btnStop;

    public TimeSinusoide(){
        setTitle("Temps Sinusoide");
        setSize(800,600);
        setLayout(new BorderLayout());

        btnStop =new JButton("STOP");
        btnStart =new JButton("START");
        JPanel ps = new JPanel();
        ps.add(btnStart);
        ps.add(btnStop);
        this.add(ps, BorderLayout.SOUTH);

        panel= new PanelTime();
        this.add(panel);

        t = new AnimationTime();
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t==null || !t.isAlive()) {
                    t = new TimeSinusoide.AnimationTime();
                    t.start();
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.interrupt();
            }
        });
    }
    class AnimationTime extends Thread{
        @Override
        public void run() {
            while (!isInterrupted()){
                if (x > panel.getWidth()) {
                    x = 0;
                }
                x+=5;
                y=(int) (200+100*Math.sin(x*0.05));

                panel.repaint();
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    break;
                }
            }
        }
    }

    class PanelTime extends JPanel{
        public PanelTime(){
            this.setBackground(Color.black);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.pink);
            g.setFont(new Font(Font.SERIF,Font.BOLD,14));

            g.drawString(new Date().toLocaleString(),x,y);
        }
    }
}
