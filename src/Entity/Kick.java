package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import FightingGame.GamePanel;

public class Kick implements Action{

	private int dame=10;
	private int x;
	private int y;
	public int getDame() {
		return dame;
	}

	public void setDame(int dame) {
		this.dame = dame;
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coming() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, Color cl) {
		// TODO Auto-generated method stub
		g.fillRect(x, y, GamePanel.titleSize*2, GamePanel.titleSize/4);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
