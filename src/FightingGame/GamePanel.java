package FightingGame;

import Entity.HPconfig;
import playerSetup.Player2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.math.*;
import Entity.*;

public class GamePanel extends JPanel implements Runnable {
	Image bg;
	public static final int originalTitleSize = 16;
	public static final int scale = 3;
	public static final double FPS = 60;
	private boolean moving = false;
	private boolean kickStatus = false;
	// set window scale
	public static final int titleSize = originalTitleSize * scale;
	public final int maxScreenCol = 27;
	public final int maxScreenRow = 16;
	final int screenWidth = 1278;// 960px
	final int screenHeight = 720;// 720px
	keyHandler keyH = new keyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, 'k');
	keyHandler keyH1 = new keyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT,
			KeyEvent.VK_L);
	Player player1 = new Player(this, keyH, 200, 550);
	Player2 player2 = new Player2(this, keyH1, screenWidth - 200, 550);
	Thread gameThread;
	HPconfig hp = new HPconfig();

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		ImageIcon j = new ImageIcon("C:\\Users\\Admin\\Desktop\\ProjectOOP\\png\\png\\bg_Game.png");
		bg = j.getImage();
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addKeyListener(keyH1);
		this.setFocusable(true);
	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		keyH.setPunchKickKey(KeyEvent.VK_J, KeyEvent.VK_K);
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		while (gameThread != null) {
			if (moving)
				player1.skillStatus = false;
			if (kickStatus)
				player1.kickStatus = false;
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				// skill
				// checkSkill(player1, player2);
				// kick
				checkKick();
				player1.update();
				player2.update();
				repaint();
				delta--;
				if (kickStatus)
					player1.kickCount++;
				player2.frameCountStand++;
				player2.frameCountWalk++;
			}

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bg, 0, 0, null);
		Graphics2D g2 = (Graphics2D) g;
		if (player1.skill1.status && !player1.skill1.coming)
			player1.skill1.draw(g2);
		if (player1.skill1.coming) {
			player1.skill1.draw(g2, Color.blue);
			player1.skill1.coming = false;
		}
		hp.draw(g2);
		hp.draw1(g2);
		player1.draw1(g2);
		player2.draw1(g2);
		g2.dispose();
	}

	// check skill
	private void checkSkill(Player player1, Player player2) {
		if (player1.skill1 == null)
			player1.skill1 = new Skills();
		if (player1.skillStatus) {
			player1.skill1.setX(player1.getX() + 10);
			player1.skill1.setY(player1.getY() - 10);
			player1.skill1.status = true;
			moving = true;
		}
		player1.skillStatus = false;
		if ((player1.skill1.getX() < player2.getX() + 10 && player1.skill1.getX() > player2.getX() - 4)
				&& (player1.skill1.getY() < player2.getY() && player1.skill1.getY() > player2.getY() - 70)) {
			player1.skill1.coming = true;
			player2.setHp(player2.getHp() - player1.skill1.getDame());
			hp.setHp2(player2.getHp());
			player2.setX(player2.getX() + 2);
			System.out.println(player2.getHp());
			player1.skill1 = null;
			player1.skill1 = new Skills();
			moving = false;
		}
		if (player1.skill1.getX() > 1200) {
			moving = false;
			player1.skill1 = null;
			player1.skill1 = new Skills();
		}
		if (player1.skill1.status && !player1.skill1.coming) {
			player1.skill1.update();
		}
	}

	// check kick
	private void checkKick() {
		if (player1.kickStatus) {
			kickStatus = true;
			player1.kick.setX(player1.getX() + 30);
			player1.kick.setY(player1.getY() + 50);
		}
		if (player1.kickCount > 30) {
			kickStatus = false;
			player1.kickCount = 0;
		}
		if (Math.abs(player1.getX() - player2.getX()) < 70 && player1.kickCount == 15) {
			player2.setHp(player2.getHp() - player1.kick.getDame());
			hp.setHp2(player2.getHp());
			System.out.println(player2.getHp());
		}

		if (player2.getHp() <= 0) {
			JOptionPane.showMessageDialog(null, "Player1 Win");
			gameThread = null;
		}
	}
}