package Entity;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class character {
	private int HP;
	private int defe;
	private int speed;
	private boolean blocked;
	private int punchdame;
	private int dameUltimate;
	private final int cdSkill1Time = 600;
	private final int cdFlashTime = 600;
	public int getCdSkill1Time() {
		return cdSkill1Time;
	}

	public int getCdFlashTime() {
		return cdFlashTime;
	}

	public BufferedImage[] stand = new BufferedImage[10];
	public BufferedImage[] defIMG = new BufferedImage[2];
	public BufferedImage[] moveRight = new BufferedImage[10];
	public BufferedImage[] moveLeft = new BufferedImage[10];
	public BufferedImage[] walkIMG = new BufferedImage[20];
	public BufferedImage[] jumpIMG = new BufferedImage[20];
	public BufferedImage[] comboIMGRight = new BufferedImage[30];
	public BufferedImage[] comboIMGLeft = new BufferedImage[30];
	public BufferedImage[] teleposIMG = new BufferedImage[10];
	public BufferedImage[] surikenIMG = new BufferedImage[5];
	public BufferedImage[] beAttackedIMG = new BufferedImage[15];
	public BufferedImage[] ultimateSkill = new BufferedImage[30];
	private int cdSkill1 = 0;

	public int getCdSkill1() {
		return cdSkill1;
	}

	private int cdFlash = 0;

	public int getCdFlash() {
		return cdFlash;
	}

	public void setCdFlash(int cdFlash) {
		this.cdFlash = cdFlash;
	}

	public void setCdSkill1(int cdSkill1) {
		this.cdSkill1 = cdSkill1;
	}

	public void setcdFlash() {
		this.cdFlash--;
	}

	public void setCDTime1() {
		this.cdSkill1--; // dem thoi gian su dung cua skill1
	}

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

	public BufferedImage getIMGSuriken(int i) {
		return this.surikenIMG[i];
	}

	public BufferedImage getIMGBeAttaced(int i) {
		return this.beAttackedIMG[i];
	}
	public BufferedImage getIMGUltimate(int i) {
		return this.ultimateSkill[i];
	}
	public BufferedImage getIMGCombo(int i, int checkLeft) {
		if (checkLeft == 0) {
			return this.comboIMGLeft[i];
		} else {
			return this.comboIMGRight[i];
		}
	}
}
