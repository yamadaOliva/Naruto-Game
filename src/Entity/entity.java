package Entity;

import java.awt.image.BufferedImage;

public abstract class entity {
	private int x,y;
	private String director;
	private BufferedImage up1, down1, jump1, def1;
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
		
	}
}
