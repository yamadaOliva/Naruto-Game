package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import FightingGame.*;

public class Player extends entity {
	private int x,y;
	private String director;
	private BufferedImage up1, down1, jump1, def1, skill, kick1;
	private static final int speed = 4;
	private int hp = 500;
	public boolean skillStatus = false;
	public boolean kickStatus = false;
	public Skills skill1 = new Skills();
	public Kick kick = new Kick();
	public int kickCount = 0;
	keyHandler keyH;
	keyHandler1 keyH1;
	GamePanel gp;
	public Player(GamePanel gp,keyHandler keyH,int x,int y) {
		this.x = x;
		this.y =y;
		this.gp =gp;
		this.keyH = keyH;
		getPlayerImage();
		this.director = "up";
	}
	public Player(GamePanel gp,keyHandler1 keyH1,int x,int y,int i,int hp) {
		this.x = x;
		this.y =y;
		this.gp =gp;
		this.keyH1 = keyH1;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResource("/character/char.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		getKickImage();
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public BufferedImage getUp1() {
		return up1;
	}
	public void setUp1(BufferedImage up1) {
		this.up1 = up1;
	}
	public BufferedImage getDown1() {
		return down1;
	}
	public void setDown1(BufferedImage down1) {
		this.down1 = down1;
	}
	public BufferedImage getJump1() {
		return jump1;
	}
	public void setJump1(BufferedImage jump1) {
		this.jump1 = jump1;
	}
	public BufferedImage getDef1() {
		return def1;
	}
	public void setDef1(BufferedImage def1) {
		this.def1 = def1;
	}
	public void update() {
		if(keyH.upStatus) {
			if(y<300) keyH.upStatus = false;
			else  y-=4;
			this.director = "up";
		}
		if(keyH.downStatus) {
			y+=speed;
			this.director = "down";
			if(y>550) y = 550;
		}
		if(keyH.rightStatus) {
			x+=speed;
			this.director = "right";
			if(x>1278-GamePanel.titleSize) x = 1278-GamePanel.titleSize;
		}
		if(keyH.leftStatus) {
			x-=speed;
			this.director = "left";
			if(x<0) x = 0;
		}
		if(!keyH.upStatus) {
			y+=5;
			if(y>550) y = 550;
		}
		if(keyH.skill) {
			skillStatus = true;
		}
		if(keyH.kick) {
			kickStatus = true;
			this.director = "kick1";
		}
	}
	
	public void update1() {
		if(keyH1.upStatus) {
			y-=4;
			this.director = "up";
		}
		if(keyH1.downStatus) {
			y+=speed;
			this.director = "down";
			if(y>550) y = 550;
		}
		if(keyH1.rightStatus) {
			x+=speed;
			this.director = "right";
			if(x>960-GamePanel.titleSize) x = 1278-GamePanel.titleSize;
		}
		if(keyH1.leftStatus) {
			x-=speed;
			this.director = "left";
			if(x<0) x = 0;
		}
		if(!keyH1.upStatus) {
			y+=1;
			if(y>550) y = 550;
		}
	}
	
	public void draw1(Graphics2D g2) {
		BufferedImage img = up1;
		switch (director) {
		case "up": {
			img = up1;
			break;
		}
		case "kick1":{
			if(kickCount<=10) img = kick1;
			else img = up1;
			break;
		}
		default:
			
		}
		
		g2.drawImage(img,x,y,GamePanel.titleSize, GamePanel.titleSize*2,null);
	}
	
	public void draw(Graphics2D g2,Color e) {
		g2.setColor(e);
		g2.fillRect(x, y, GamePanel.titleSize, GamePanel.titleSize*2);
	}
	private void getKickImage() {
		try {
			kick1 = ImageIO.read(getClass().getResource("/kick/kick1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
