package Entity;

import java.awt.image.BufferedImage;

public class character {
	private int HP;
	private int defe;
	private int speed;
	public BufferedImage[] stand = new BufferedImage[10];
	public BufferedImage[] defIMG = new BufferedImage[2];
	public BufferedImage[] moveRight = new BufferedImage[10];
	public BufferedImage[] moveLeft = new BufferedImage[10];
	public BufferedImage[] walkIMG = new BufferedImage[20];
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getDefe() {
		return defe;
	}
	public void setDefe(int defe) {
		this.defe = defe;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public  BufferedImage getImgStand(int i){
		return this.stand[i];
	}
	
	public  BufferedImage getImgDef(int i) {
		return this.defIMG[i];
	}
	public BufferedImage getImgWalking(int i) {
		return this.walkIMG[i];
	}
	
}
