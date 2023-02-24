package Main;

import Entity.Player;
import tile.tileManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePan extends JPanel implements Runnable{

    final int ogPixSize = 32;
    final int scale = 2;

    public final int pixSize = ogPixSize * scale;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 24;
    public final int screenWidth = pixSize * maxScreenCol;
    public final int screenHeight = pixSize * maxScreenRow;

    public final int maxWorldCol = 100;
    public final int maxWorldRow = 20;
    public final int worldWidth = pixSize * maxWorldCol;
    public final int worldHeight = pixSize * maxScreenRow;

    int FPS = 60;
    tileManager tileM = new tileManager(this);
    Keys keyH = new Keys();
    Thread gameThread;
    Player player = new Player(this,keyH);

    public GamePan() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInter = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInter;

        while(gameThread != null) {

            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInter;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update () {

        player.update();

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
