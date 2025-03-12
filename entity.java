package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage up, down, left, right;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;
    
}
