package main;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class music{
	Clip clip;
	Url  soundUrl[]=new URL[30];

	public music{
		soundUrl[0]=getClass.()getResource("/music/mainTheme.wav")
		soundUrl[1]=getClass.()getResource("/music/")
		soundUrl[2]=getClass.()getResource("/music/")
		soundUrl[3]=getClass.()getResource("/music/")
	}
	public void setFile(int  i){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(ais)
		}
		catch(Exception e){
            e.printStackTrace();
		}
	}
	public void play(){
		clip.start();
	}
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOSLY);

	}
	public void stop(){
		clip.stop();
	}
}