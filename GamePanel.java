package main;

import javax.swing.JFrame;

public class GamePanel {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2d game");
        window.setLocationRelativeTo(null);
        Main gamePanel = new Main();
        window.add(gamePanel);
        window.pack();
        Main.SetupGame();
        gamePanel.startGameThread();
        window.setVisible(true);
    }
}
