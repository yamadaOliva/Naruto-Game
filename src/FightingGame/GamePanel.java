package FightingGame;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import Entity.*;
public class GamePanel extends JPanel implements Runnable {
	public static final int originalTitleSize =16;
	public static final int scale = 3;
	public static final double FPS = 60;
	//set window scale
	public static final int titleSize = originalTitleSize*scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow =15;
	final int screenWidth = titleSize * maxScreenCol;// 960px
	final int screenHeight = titleSize * maxScreenRow;// 720px
	keyHandler keyH=new keyHandler();
	keyHandler1 keyH1=new keyHandler1();
	Player player1 = new Player(this,keyH,100,500);
	Player player2 = new Player(this,keyH1,700,500,1);
	Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
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
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime =  System.nanoTime();
		long currentTime;
		long timer = 0;
		while(gameThread!=null) {
			System.out.println("("+ player1.getY() + "," + player1.getX() + ")");
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/ drawInterval;
			lastTime = currentTime;
			if(delta>=1) {
			
			player1.update();
			player2.update1();
			repaint();
			
			delta--;
			}
			
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		player1.draw(g2,Color.white);
		player2.draw(g2,Color.blue);
		g2.dispose();
	}

}