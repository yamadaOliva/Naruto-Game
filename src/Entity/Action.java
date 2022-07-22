package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Action {
	 public void update ();
	 public void coming();
	 public	void draw(Graphics2D g,Color cl);
	 public void draw(Graphics2D g);
}
