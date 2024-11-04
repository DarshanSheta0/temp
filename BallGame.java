import java.awt.*;
import java.awt.event.*;

class Level extends Frame implements Runnable, KeyListener {
    public static int LevelNumber = 1;//current level
    int x = 100, y = 250;//ball no starting pt
    int xside = 0, yside = 0;//Direction of ball
    int darshan = 0;//track game states
    Button b;
    public static boolean levelcompleted = false;
    public static boolean start = false;

    Level() {
        super("BallGame");
        setSize(500, 500);
        setVisible(true);
        setLayout(null);
        setBackground(Color.BLACK);
        requestFocus();  //frame has focus
        setLocationRelativeTo(null);//center

        if (!start) {
            b = new Button("START");
            b.setBounds(240, 240, 50, 20);
            b.setBackground(Color.DARK_GRAY);
            b.setForeground(Color.CYAN);
            add(b);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    start = true;
                    requestFocus();  // Keep focus on the frame
                    if (LevelNumber == 1) {
                        System.out.println("Level-1 Start");
                        levelcompleted = false;
                        Level1 l1 = new Level1();
                    } else if (LevelNumber == 2) {
                        System.out.println("Level-2 Start");
                        levelcompleted = false;
                        Level2 l2 = new Level2();
                    } else if (LevelNumber == 3) {
                        System.out.println("Level-3 Start");
                        levelcompleted = false;
                        Level3 l3 = new Level3();
                    }
                }
            });
        }
        addKeyListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            yside = -1;
            xside = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            yside = 1;
            xside = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xside = -1;
            yside = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xside = 1;
            yside = 0;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void run() {
        while (!levelcompleted) {
            repaint();
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
    }

    public void paint(Graphics g) {}
}

class Level1 extends Level {
    Thread t1;

    Level1() {
        if (darshan != 1 && darshan != 2 && start && LevelNumber == 1) {
            t1 = new Thread(this);
            t1.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (start) {
            setBackground(Color.BLACK);

            if (darshan == 1) {
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 200, 250);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                System.out.println("GAME OVER");
                System.exit(0);
            }

            if (darshan == 2) {
                levelcompleted = true;
                g.setColor(Color.GREEN);
                g.drawString("Hurray! Level-1 Complete", 200, 250);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
                System.out.println("Hurray! Level-1 Complete");
                start = false;
                LevelNumber++;
                darshan = 0;
                Level L2 = new Level();
            }

            if (darshan != 1 && darshan != 2) {
                g.setColor(Color.YELLOW); 
                g.drawLine(250, 150, 250, 350);//vrtical
                g.drawLine(450, 200, 500, 200);//horizontal
                g.drawLine(450, 300, 500, 300);//horizontal

                x += 5 * xside;
                y += 5 * yside;

                g.setColor(Color.YELLOW);
                g.fillOval(x, y, 10, 10);

                if (((x >= 245 && x <= 255) && (y > 150 && y < 350)) || (x > 500 || x < 0 || y > 500 || y < 0)) {
                    darshan = 1;
                    repaint();
                }

                if (x > 490 && (y > 200 && y < 400)) {
                    darshan = 2;
                }
            }
        }
    }
}

class Level2 extends Level {
    Thread t2;

    Level2() {
        if (darshan != 1 && darshan != 2 && start && LevelNumber == 2) {
            t2 = new Thread(this);
            t2.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (start) {
            setBackground(Color.BLACK);

            if (darshan == 1) {
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 200, 250);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                System.out.println("GAME OVER");
                System.exit(0);
            }

            if (darshan == 2) {
                levelcompleted = true;
                g.setColor(Color.GREEN);
                g.drawString("Hurray! Level-2 Complete", 200, 250);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                System.out.println("Hurray! Level-2 Complete");
                start = false;
                LevelNumber++;
                darshan = 0;
                Level L3 = new Level();
            }

            g.setColor(Color.YELLOW); 
            g.drawLine(250, 10, 250, 250);
            g.drawLine(350, 200, 350, 500);
            g.drawLine(450, 200, 500, 200);
            g.drawLine(450, 300, 500, 300);

            x += 5 * xside;
            y += 5 * yside;

            g.setColor(Color.YELLOW); 
            g.fillOval(x, y, 10, 10);

            if (((x >= 245 && x <= 255) && (y > 10 && y < 250)) || ((x >= 345 && x <= 355) && (y > 200 && y < 500)) || (x > 500 || x < 0 || y > 500 || y < 0)) {
                darshan = 1;
                repaint();
            }

            if (x > 490 && (y > 200 && y < 400)) {
                darshan = 2;
            }
        }
    }
}

class Level3 extends Level {
    Thread t3;

    Level3() {
        if (darshan != 1 && darshan != 2 && start && LevelNumber == 3) {
            t3 = new Thread(this);
            t3.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (start) {
            setBackground(Color.BLACK);

            if (darshan == 1) {
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 200, 250);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                System.out.println("GAME OVER");
                System.exit(0);
            }

            if (darshan == 2) {
                levelcompleted = true;
                g.setColor(Color.GREEN);
                g.drawString("Hurray! Level-3 Complete", 200, 250);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                System.out.println("Hurray! Level-3 Complete");
                LevelNumber++;
                start = false;
                darshan = 0;
                System.exit(0);
            }

            g.setColor(Color.YELLOW); 
            g.drawLine(150, 100, 150, 400);
            g.drawLine(250, 20, 250, 200);
            g.drawLine(250, 300, 250, 480);
            g.drawLine(350, 100, 350, 400);
            g.drawLine(450, 230, 500, 230);
            g.drawLine(450, 270, 500, 270);

            x += 5 * xside;
            y += 5 * yside;

            g.setColor(Color.YELLOW);
            g.fillOval(x, y, 10, 10);

            if (((x >= 148 && x <= 158) && (y > 100 && y < 400)) || ((x >= 348 && x <= 358) && (y > 100 && y < 400)) || ((x >= 248 && x <= 258) && (y > 20 && y < 200)) || ((x >= 248 && x <= 258) && (y > 300 && y < 480)) || (x > 500 || x < 0 || y > 500 || y < 0)) {
                darshan = 1;
                repaint();
            }

            if (x > 490 && (y > 230 && y < 270)) {
                darshan = 2;
            }
        }
    }
}

public class BallGame {
    public static void main(String[] args) {
        Level L1 = new Level();
    }
}