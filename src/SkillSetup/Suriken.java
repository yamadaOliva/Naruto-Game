package SkillSetup;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Skills;
import FightingGame.GamePanel;

public class Suriken extends Skills{
	private BufferedImage[] SkillsImg = new BufferedImage[5];
	private int x,y;
	private int speed = 8;
	private int imgPos = 0;
	
	public boolean comingStatus = false;
	public Suriken(int x,int y) {
		getKunaiImg();
		this.setDame(10);
		this.x = x;
		this.y = y;
	}
	public Suriken() {
		coming = false;
	}
	//////////////
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
	public int getImgPos() {
		return imgPos;
	}
	public void setImgPos(int imgPos) {
		this.imgPos = imgPos;
	}
	//////////////
	public void update() {
		if(GamePanel.mukouMigi) {
			x+=speed;
		}else {
			x-=speed;
		}
	}
	private void getKunaiImg() {
		try {
			SkillsImg[0] = ImageIO.read(getClass().getResource("/skills/kunai/kunai_1.png"));
			SkillsImg[1] = ImageIO.read(getClass().getResource("/skills/kunai/kunai_2.png"));
			SkillsImg[2] = ImageIO.read(getClass().getResource("/skills/kunai/kunai_3.png"));
			SkillsImg[3] = ImageIO.read(getClass().getResource("/skills/kunai/kunai_4.png"));
			SkillsImg[4] = ImageIO.read(getClass().getResource("/skills/kunai/kunai_5.png"));


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSkillsImg(int i) {
		return SkillsImg[i];
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(SkillsImg[imgPos], x, y, GamePanel.titleSize, GamePanel.titleSize, null);
	}
	
}
