package main;

import java.awt.Font;
import java.awt.Graphics2D;

public class UI{
	Main gp;
	public UI(Main gp){
		this.gp=gp;
	}
	public void draw(Graphics2D g2){
		g2.setFont(new Font("Calibri",Font.PLAIN,35));
	}
}