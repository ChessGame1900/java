package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Main;

public class tile_manager {
    Main gp;
    tile[] tile;
    int mapTileNum[][];

    public tile_manager(Main gp) {
        this.gp = gp;
        tile = new tile[15];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_tile.png"));
            tile[1] = new tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_tile2.png"));

            tile[2] = new tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2].collision = true;
            tile[3] = new tile(); // Create tile[3] object
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road.png"));
            tile[3].collision = true;
            tile[4] = new tile(); // Create tile[4] object
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[4].collision = true;
            tile[5] = new tile(); // Create tile[5] object
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
            tile[5].collision = true;

            tile[6] = new tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));
            tile[6].collision = true;
            tile[7] = new tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree2.png"));
            tile[7].collision = true;
            tile[8] = new tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree3.png"));
            tile[8].collision = true;
            tile[9] = new tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree4.png"));
            tile[9].collision = true;
            tile[10] = new tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree5.png")); 
            tile[10].collision = true;
            tile[11] = new tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree6.png"));
            tile[11].collision = true;
            tile[12] = new tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree7.png"));
            tile[12].collision = true;
            tile[13] = new tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree8.png"));
            tile[13].collision = true;
            tile[14] = new tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree9.png"));
            tile[14].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                col = 0;
                while (col < gp.maxWorldCol) { 
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) { 
                worldCol = 0;
                worldRow++;
            }
        }
    }
}