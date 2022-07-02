package Entity;

import java.awt.image.BufferedImage;

public class character {
	private int HP;
	private int defe;
	private int speed;
	private boolean blocked;
	private int punchdame;
	
	public BufferedImage[] stand = new BufferedImage[10];
	public BufferedImage[] defIMG = new BufferedImage[2];
	public BufferedImage[] moveRight = new BufferedImage[10];
	public BufferedImage[] moveLeft = new BufferedImage[10];
	public BufferedImage[] walkIMG = new BufferedImage[20];
	public BufferedImage[] jumpIMG = new BufferedImage[20];
	public BufferedImage[] comboIMGRight = new BufferedImage[20];
	public BufferedImage[] comboIMGLeft = new BufferedImage[20];
	public BufferedImage[] teleposIMG = new BufferedImage[10];
	public int getHP() {
		return HP;
	}

	

	public void setHP(int hP) {
		HP = hP;
	}

	public int getPunchdame() {
		return punchdame;
	}

	public void setPunchdame(int punchdame) {
		this.punchdame = punchdame;
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

	public BufferedImage getImgStand(int i) {
		return this.stand[i];
	}

	public BufferedImage getImgDef(int i) {
		return this.defIMG[i];
	}

	public BufferedImage getImgWalking(int i) {
		return this.walkIMG[i];
	}

	public BufferedImage getImgJump(int i) {
		return this.jumpIMG[i];
	}
	public BufferedImage getImgComboRight(int i) {
		return this.comboIMGRight[i];
	}
	public BufferedImage getImgComboLeft(int i) {
		return this.comboIMGLeft[i];
	}
	public BufferedImage getImgTelepos(int i) {
		return this.teleposIMG[i];
	}
	public void blockedCase(int dame) {
		
	}
	
}
