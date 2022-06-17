package FightingGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class HPconfig {
	private int hp1=500;
	private int hp2=500;
	public HPconfig() {
		// TODO Auto-generated constructor stub
	}
	public HPconfig(int x,int y) {
		this.hp1= x;
		this.hp2 = y;
	}
	void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(10, 20, 500, 20);
	}
	void draw1(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(1278-510+(500-hp2), 20, hp2, 20);
	}
	void whiteHpdraw1(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(1278-510, 20, 500-hp2, 20);
	}
	public int getHp1() {
		return hp1;
	}
	public void setHp1(int hp1) {
		this.hp1 = hp1;
	}
	public int getHp2() {
		return hp2;
	}
	public void setHp2(int hp2) {
		this.hp2 = hp2;
	}
}
