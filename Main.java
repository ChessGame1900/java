package main;

import java.awt.*;
import javax.swing.JPanel;
import entity.Player;
import object.AssetSetter;
import object.object; 
import tile.Collision;
import tile.tile_manager;

public class Main extends JPanel implements Runnable {
    final int originalTileSize = 24;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 25;
    public final int worldWidth = tileSize * maxWorldCol; 
    public final int worldHeight = tileSize * maxWorldRow; 

    public tile_manager tileM = new tile_manager(this);
    KeyHandler keyH = new KeyHandler();
    public Collision checker = new Collision(this);
    music music = new music();
    music soundEffect = new music();
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    public Player player = new Player(this, keyH);
    public object[] obj = new object[10]; 

    public Main() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.SetObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta = delta + (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("Fps:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}