package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class HPconfig {
	private int hp1 = 500;
	private int hp2 = 500;
	private int power1 = 0;
	private int power2 = 0;
	public boolean attacked = false;

	public HPconfig() {
		// TODO Auto-generated constructor stub
	}

	public HPconfig(int x, int y) {
		this.hp1 = x;
		this.hp2 = y;
	}

	public void drawPower1(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(power1 + 10, 50, 200 - power1, 15);
		drawPowerGray1(g2);
	}

	public void drawPowerGray1(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillRect(10, 50, power1, 15);
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(10, 20, hp1, 20);
		whiteHpdraw(g2);
		drawPower1(g2);
	}

	public void whiteHpdraw(Graphics2D g2) {
		g2.setColor(Color.white);
		if (hp1 < 0)
			hp1 = 0;
		g2.fillRect(hp1, 20, 500 - hp1, 20);
	}

	public void draw1(Graphics2D g2) {
		g2.setColor(Color.red);
		if (hp2 < 0)
			hp2 = 0;
		g2.fillRect(1278 - 510 + (500 - hp2), 20, hp2, 20);
		whiteHpdraw1(g2);
		drawPower2(g2);
	}

	public void drawPower2(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(1278 - 210, 50, 200 - power1, 15);
		if (power2 >= 200)
			power2 = 200;
		drawPowerGray2(g2);
	}

	public void drawPowerGray2(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillRect(1278 - 210 + (200 - power2), 50, power2, 15);
	}

	public void whiteHpdraw1(Graphics2D g2) {
		g2.setColor(Color.white);
		if (hp2 < 0)
			hp2 = 0;
		g2.fillRect(1278 - 510, 20, 500 - hp2, 20);
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

	public int getPower1() {
		return power1;
	}

	public void setPower1(int power1) {
		this.power1 = power1;
	}

	public int getPower2() {
		return power2;
	}

	public void setPower2(int power2) {
		this.power2 = power2;
	}
}
