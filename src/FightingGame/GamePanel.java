package FightingGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entity.Player;
import Entity.*;
public class GamePanel extends JPanel implements Runnable {
	Image bg ;
	public static final int originalTitleSize =16;
	public static final int scale = 3;
	public static final double FPS = 60;
	//set window scale
	public static final int titleSize = originalTitleSize*scale;
	public final int maxScreenCol = 27;
	public final int maxScreenRow =16;
	final int screenWidth = 1278;// 960px
	final int screenHeight = 720;// 720px
	keyHandler keyH=new keyHandler();
	keyHandler1 keyH1=new keyHandler1();
	Player player1 = new Player(this,keyH,200,550);
	Player player2 = new Player(this,keyH1,screenWidth-200,550,1,500);
	Thread gameThread;
	Skills skill1 = new Skills();
	HPconfig hp = new HPconfig();
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
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
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime =  System.nanoTime();
		long currentTime;
		long timer = 0;
		while(gameThread!=null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/ drawInterval;
			lastTime = currentTime;
			if(delta>=1) {
			if(player1.skillStatus) {
				skill1.setX(player1.getX()+10);
				skill1.setY(player1.getY()-10);
				skill1.status = true;
			}
			player1.skillStatus = false;
			
			if(skill1.getX()==player2.getX()) {
				skill1.coming = true;
				player2.setHp(player2.getHp()-skill1.getDame());	
				hp.setHp2(player2.getHp());
				System.out.println(player2.getHp());
				skill1 = null;
				skill1 = new Skills();
				}
			if(skill1.status&&!skill1.coming) {
				skill1.update();
			}
			if(player2.getHp()==0) {
				JOptionPane.showMessageDialog(null, "Player1 Win");
				gameThread = null;
			}
			player1.update();
			player2.update1();
			repaint();
			
			delta--;
			}
			
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bg, 0, 0, null);
		Graphics2D g2 = (Graphics2D)g;
		if(skill1.status&&!skill1.coming) skill1.draw(g2,Color.black);
		if(skill1.coming) {
			skill1.draw(g2, Color.blue);
			skill1.coming = false;
		}
		hp.draw(g2);
		hp.draw1(g2);
		hp.whiteHpdraw1(g2);
		player1.draw1(g2);
		player2.draw(g2,Color.yellow);
		g2.dispose();
	}

}