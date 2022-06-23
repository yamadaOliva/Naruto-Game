package playerSetup;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Entity.Player;
import Entity.character;
import FightingGame.GamePanel;
import FightingGame.keyHandler;

public class Player1 extends Player{
	public keyHandler keyH = new keyHandler();
	private int x,y;
	public GamePanel gp;
	private int choose;
	private BufferedImage[] stand, def, moveRight, moveLeft, skill1, skill2, ulti, kick, punch;
	character char1;
	public Player1(GamePanel gp, keyHandler keyH, int x, int y) {
		super(gp, keyH, x, y);
		setupCharacter(this.choose);
		// TODO Auto-generated constructor stub
	}
	private void setupCharacter(int choose) {
		
	}
}
