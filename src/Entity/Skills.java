package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import FightingGame.GamePanel;

public abstract class Skills extends entity implements Action {
	private int x;
	private int y;
	private int dame;
	private int speed;
	public final long comingTime = 3000;
	public boolean status;
	public boolean coming=false;
	public boolean moving=false;
	private int MAX_RANGE = 1250;
	private int cd;
	public int getCd() {
		return cd;
	}
	
	public void setCd(int cd) {
		this.cd = cd;
	}
	private BufferedImage skillImage;
	private BufferedImage[] SkillsImg = new BufferedImage[5];
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Skills() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getDame() {
		return dame;
	}
	public void setDame(int dame) {
		this.dame = dame;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Skills(int x, int y) {
		this.x = x;
		this.y = y;
		status = true;
		
	}
	public Skills(String urlSkill,int x, int y,int dame,int max_range) {
		this.x = x;
		this.y = y;
		MAX_RANGE = max_range;
		this.dame= dame;
		try {
			skillImage = ImageIO.read(getClass().getResource(urlSkill));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if(x<MAX_RANGE) x+=speed;
		else status = false;
	}
	public void update1() {
		if(!GamePanel.mukouMigi) {
			x+=speed;
		}else {
			x-=speed;
		}
		System.out.println(1);
	}
	@Override
	public void coming() {
		// TODO Auto-generated method stub
		
	}
	public void remove() {
		
	}
	@Override
	public void draw(Graphics2D g2,Color cl) {
		// TODO Auto-generated method stub
		g2.setColor(cl);
		g2.fillRect(x, y, GamePanel.titleSize, GamePanel.titleSize);
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(skillImage, x, y, GamePanel.titleSize, GamePanel.titleSize, null);
	}
	public BufferedImage getSkillsImg(int i) {
		return SkillsImg[i];
	}
	public void skillCD() {
		this.cd--;
	}
}
