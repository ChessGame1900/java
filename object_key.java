package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class object_key extends object{
	public  object_key(){
		name= "Key";
		try{
			image=ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
	
