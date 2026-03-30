package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class FrameAnimation extends JInternalFrame {
    int x = 10;
    int x_init = 10;
    int x_fin = x_init;
    final Object lock = new Object();
    boolean aller = false, retour = false, paused = false;

    int y = 200;
    int y_init = 200;
    int y_fin = y_init;

    Animation a = null;

    JButton btnContinue, btnStop;
    PanelAnimation pa;

    public FrameAnimation() {
        setTitle("Animation");
        this.setSize(800, 600);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        btnStop = new JButton("STOP");
        btnContinue = new JButton("Continue");
        JPanel ps = new JPanel();
        ps.add(btnContinue);
        ps.add(btnStop);
        this.add(ps, BorderLayout.SOUTH);

        pa = new PanelAnimation();
        pa.addMouseListener(new MouseAdapter() {
            @Override
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
        this.add(pa);


        a = new Animation();
        btnContinue.addActionListener(e -> {
            synchronized (lock) {
                paused = false;
                lock.notify();
            }
        });
        btnStop.addActionListener(e -> {
            synchronized (lock) {
                paused = true;
            }
        });
    }

    class Animation extends Thread {
        @Override
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
                    if (aller) {
                        // aller vers (x_fin, y_fin)
                        if (Math.abs(x - x_fin) > 5) {
                            x += (x_fin > x) ? 5 : -5;
                        }

                        if (Math.abs(y - y_fin) > 5) {
                            y += (y_fin > y) ? 5 : -5;
                        }

                        // arrivé
                        if (Math.abs(x - x_fin) <= 5 && Math.abs(y - y_fin) <= 5) {
                            aller = false;
                            retour = true;
                        }
                    } else if (retour) {
                        // retour vers (x_init, y_init)
                        if (Math.abs(x - x_init) > 5) {
                            x += (x_init > x) ? 5 : -5;
                        }

                        if (Math.abs(y - y_init) > 5) {
                            y += (y_init > y) ? 5 : -5;
                        }

                        // arrivé origine
                        if (Math.abs(x - x_init) <= 5 && Math.abs(y - y_init) <= 5) {
                            retour = false;
                        }
                    }
                    pa.repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }



    }
    class PanelAnimation extends JPanel {
        public PanelAnimation() {
            this.setBackground(Color.PINK);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);//face le contnu ancien
            g.setColor(Color.white);
            g.fillOval(x, y, 100, 100);//cercle balnche
            g.setFont(new Font(Font.SERIF, Font.BOLD, 14));
            g.setColor(Color.black);
            g.setColor(Color.RED);
            g.drawRect(x_fin - 10, y_fin - 10, 100, 100);
            g.drawString(new Date().toLocaleString(), x, y);
        }
    }
}

