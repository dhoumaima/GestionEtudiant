package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CercleAnimation extends JInternalFrame {

    int x1 = 10, y1 = 200;
    int x1_init = 10, y1_init = 200;

    int x2 = 600, y2 = 200;
    int x2_init = 600, y2_init = 200;

    int x_fin, y_fin;

    boolean aller = false, retour = false;
    boolean paused = false;

    final Object lock = new Object();

    Animation a;
    PanelAnimation panel;
    JButton btnStop, btnContinue;

    public CercleAnimation() {
        setTitle("2 Cercles Animation");
        setSize(800, 600);
        setLayout(new BorderLayout());

        panel = new PanelAnimation();
        add(panel, BorderLayout.CENTER);

        JPanel ps = new JPanel();
        btnStop = new JButton("STOP");
        btnContinue = new JButton("CONTINUER");

        ps.add(btnStop);
        ps.add(btnContinue);
        add(ps, BorderLayout.SOUTH);


        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                x_fin = e.getX();
                y_fin = e.getY();

                aller = true;
                retour = false;

                if (a == null || !a.isAlive()) {
                    a = new Animation();
                    a.start();
                }
            }
        });


        btnStop.addActionListener(e -> {
            synchronized (lock) {
                paused = true;
            }
        });

        btnContinue.addActionListener(e -> {
            synchronized (lock) {
                paused = false;
                lock.notify();
            }
        });
    }

    class Animation extends Thread {
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (paused) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
                if (aller) {
                    if (Math.abs(x1 - x_fin) > 5)
                        x1 += (x_fin > x1) ? 5 : -5;

                    if (Math.abs(y1 - y_fin) > 5)
                        y1 += (y_fin > y1) ? 5 : -5;

                    if (Math.abs(x2 - x_fin) > 5)
                        x2 += (x_fin > x2) ? 5 : -5;

                    if (Math.abs(y2 - y_fin) > 5)
                        y2 += (y_fin > y2) ? 5 : -5;

                    if (Math.abs(x1 - x_fin) <= 5 && Math.abs(y1 - y_fin) <= 5 &&
                            Math.abs(x2 - x_fin) <= 5 && Math.abs(y2 - y_fin) <= 5) {

                        aller = false;
                        retour = true;
                    }
                }
                else if (retour) {
                    if (Math.abs(x1 - x1_init) > 5)
                        x1 += (x1_init > x1) ? 5 : -5;

                    if (Math.abs(y1 - y1_init) > 5)
                        y1 += (y1_init > y1) ? 5 : -5;

                    if (Math.abs(x2 - x2_init) > 5)
                        x2 += (x2_init > x2) ? 5 : -5;

                    if (Math.abs(y2 - y2_init) > 5)
                        y2 += (y2_init > y2) ? 5 : -5;

                    if (Math.abs(x1 - x1_init) <= 5 && Math.abs(y1 - y1_init) <= 5 &&
                            Math.abs(x2 - x2_init) <= 5 && Math.abs(y2 - y2_init) <= 5) {

                        retour = false;
                    }
                }
                panel.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    class PanelAnimation extends JPanel {
        public PanelAnimation() {
            setBackground(Color.PINK);
        }

        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.WHITE);
            g.fillOval(x1, y1, 100, 100);

            g.setColor(Color.BLUE);
            g.fillOval(x2, y2, 100, 100);

            g.setColor(Color.RED);
            g.drawRect(x_fin - 10, y_fin - 10, 100, 100);
        }
    }
}