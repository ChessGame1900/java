package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import main.Main;
import main.KeyHandler;

public class Player extends entity {
    Main gp;
    KeyHandler KeyH;
    public final int screenX;
    public final int screenY;
    public int hasKey;

    public Player(Main gp, KeyHandler KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle(12, 24, 48, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/player2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (KeyH.upPressed) {
            direction = "up";
        } else if (KeyH.downPressed) {
            direction = "down";
        } else if (KeyH.leftPressed) {
            direction = "left";
        } else if (KeyH.rightPressed) {
            direction = "right";
        }

        collisionOn = false;
        gp.checker.checkTile(this);
        int objIndex = gp.checker.checkObject(this, true);
        pickObject(objIndex);

        if (!collisionOn) { 
            switch (direction) {
                case "up":
                    worldY -= speed; 
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
    }

    public void pickObject(int i) { 
        if (i != 999 && gp.obj[i] != null) { 
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door_locked":
                    gp.playSoundEffect(2);
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
                case "Door":
                    gp.playSoundEffect(2);
                    try{
                        gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/objects/doorOpened.png"));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    gp.obj[i].collision = false;
                    break;
                
                	
                   
            }
            if (gp.obj[i].name != null && gp.obj[i].name.equals("Door") && !gp.obj[i].collision) {
                if (Math.abs(gp.obj[i].worldY - worldY) > gp.tileSize || Math.abs(gp.obj[i].worldX - worldX) > gp.tileSize) {
                    try {
                        gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gp.obj[i].collision = true;
        }
    }
    

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}