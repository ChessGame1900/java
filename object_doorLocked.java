package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class object_doorLocked extends object {

    public object_doorLocked() { 
        name = "doorLocked";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
