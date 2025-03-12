package object;

import main.Main;

public class AssetSetter {
    Main gp;

    public AssetSetter(Main gp) {
        this.gp = gp;
    }

    public void SetObject() {
        gp.obj[0] = new object_key();
        gp.obj[1] = new object_door();
        gp.obj[2] = new object_chest();

        
        gp.obj[0].worldX = 0 * gp.tileSize;
        gp.obj[0].worldY = 0 * gp.tileSize; 

        gp.obj[1].worldX = 1 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize;

        gp.obj[2].worldX = 2 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;
    }
}
