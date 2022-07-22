package FightingGame;

import Entity.HPconfig;
import SkillSetup.Suriken;
import playerSetup.Player1;
import playerSetup.Player2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.math.*;
import java.net.URL;

import Entity.*;

public class GamePanel extends JPanel implements Runnable {
	BufferedImage bg;
	public static final int originalTitleSize = 16;
	public static final int scale = 3;
	public static final double FPS = 60;
	int countTest = 0;
	private final int minuteGame = 1;
	private int minute;
	private int second;
	private int totalFame = minuteGame*60*60;
	private boolean moving = false;
	private boolean kickStatus = false;
	private DrawWindow dwd = new DrawWindow();
	//UI
	
	//SOund
	static Sound sound = new Sound();
	//setup status of game
	public static int statusGame=0;
	public static boolean mukouMigi;
	public static int choosingOne = 0;
	public static int choosingTwo = 3;
	public static int choosingMap = 0;
	private boolean clock = true;
	// set window scale
	public static final int titleSize = originalTitleSize * scale;
	public final int maxScreenCol = 27;
	public final int maxScreenRow = 16;
	final int screenWidth = 1278;// 960px
	final int screenHeight = 720;// 720px
	//how to choose option
	public static int statusChoose =0;
	keyHandler keyH = new keyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, 'k');
	keyHandler keyH1 = new keyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT,
			KeyEvent.VK_L);
	Player1 player1 = new Player1(this, keyH, 200, 550,choosingOne);
	Player2 player2 = new Player2(this, keyH1, screenWidth - 200, 550,choosingTwo);
	Thread gameThread;
	HPconfig hp = new HPconfig();

	private int distance2player;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
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
		
		playMusic(1);
		keyH.setPunchKickKey(KeyEvent.VK_J, KeyEvent.VK_K);
		keyH.setKillKey(KeyEvent.VK_L, KeyEvent.VK_I, KeyEvent.VK_O);
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(statusGame==2&&clock) {
				player1 = new Player1(this, keyH, 200, 550,choosingOne);
				player2 = new Player2(this, keyH1, screenWidth - 200, 550,choosingTwo);
				clock = false;
			}
			if (delta >= 1) {
				if(statusGame==2) {
 
				player1.update();
				player2.update();
				if(player1.getDirector().equals("punch")) checkPunch(player1, player2);
				if(player1.getDirector().equals("ultimate")) checkUltimate();
			
				setupStatus();
				TestSkill();
				if(totalFame>0) totalFame --;
				}
				 
				repaint();
				if(player2.getHp()<=0||player1.getHp()<=0) {
					if(player2.getHp()==0) JOptionPane.showMessageDialog(null, "player1 win"); 
					else JOptionPane.showMessageDialog(null, "player2 Win");
					gameThread = null;
					System.exit(0);
				}
				delta--;
			}
			
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(statusGame==0) {
			dwd.menuDraw(g2);
		}
		if(statusGame==1) {
			dwd.slectChampDraw(g2);
		}
		if(statusGame==2) {
		if(choosingMap==4) g2.drawImage(dwd.mapG.mapGif, 0, 0, null);
		else g2.drawImage(dwd.mapG.map[choosingMap], 0, 0, null);
		if (player1.skill1.status && !player1.skill1.coming)
			player1.skill1.draw(g2);
		if (player1.skill1.coming) {
			player1.skill1.draw(g2, Color.blue);
			player1.skill1.coming = false;
		}
		
		minute = totalFame/3600;
		second = (totalFame/60)%60;
		hp.draw(g2,second,minute);
		hp.draw1(g2);
		if(player1.skill == null) player1.skill = new Suriken();
		if(player1.skill.coming) player1.skill.draw(g2);
		player1.draw1(g2);
		player2.draw1(g2);
		
		//win
		g2.dispose();
		}
	}
	//draw menu
	
	
	//test skill
	private void TestSkill() {
		
		if (player1.skill == null) {
			 player1.skill = new Suriken();
		}
		if (player1.skill.coming) player1.skill.update();
		if(player1.skill.getX() >= player2.getX()&&player1.skill.getX() <= player2.getX()+20&&checkYcollision(player1.skill.getY(),player2.getY())) {
			player2.setBeAttackedStatus(player1.skill.getDame());
			setconfig();
			player1.skill =new Suriken();
		}
		if(player1.skill.getX()>=1200||player1.skill.getX()<=0) {
			player1.skill = new Suriken();
		}
	}
	private void checkUltimate() {
		hp.setPower1(0);
		int frameDamedMin = 0;
		int frameDameMax = 0;
		int frameGetDame = 0;
		if(player1.getChoose()==0) {
			frameDamedMin = 320;
			frameDameMax = 420;
			frameGetDame = 2;
		} else if(player1.getChoose()==1) {
			frameDamedMin = 240;
			frameDameMax = 405;
			frameGetDame = 2;
		} else {
			frameDamedMin = 20;
			frameDameMax = 300;
			frameGetDame = 1;
		}
		if(player2.getX()-player1.getX()<=100&&(player1.frameCountUitlmate<=frameDameMax&&player1.frameCountUitlmate>=frameDamedMin)) {
			player2.setSuperBeAttackedStatus(frameGetDame);
			setconfig();
		}
		if(player1.frameCountUitlmate==frameDameMax) {
			
		}
	}
	// check skill
	

	// check kick
	
	// check punch
	private void checkPunch(Player attack,Player attacked) {
		if(mukouMigi) {
		if(((attacked.getX()-attack.getX())<(titleSize))&&checkY()) {
			if(attack.getFrameCountPunch()==6) {
				attacked.setHp(attacked.getHp()-10);
				setconfig();
			}
			if(attack.getFrameCountPunch()==30) {
				attacked.setHp(attacked.getHp()-10);
				setconfig();
			}
			if(attack.getFrameCountPunch()==56) {
				attacked.setHp(attacked.getHp()-10);
				setconfig();
			}
			if(attack.getFrameCountPunch()==80) {
				attacked.setHp(attacked.getHp()-10);
				setconfig();
			}
			if(attack.getFrameCountPunch()==122) {
				attacked.setHp(attacked.getHp()-10);
				setconfig();
			}
			if(attack.getFrameCountPunch()==160) {
				attacked.setHp(attacked.getHp()-10);
				setconfig();
			}
			
		}}else {
			if(((attack.getX()-attacked.getX())<(titleSize))&&checkY()) {
				if(attack.getFrameCountPunch()==6) {
					attacked.setHp(attacked.getHp()-10);
					
					setconfig();
				}
				if(attack.getFrameCountPunch()==30) {
					attacked.setHp(attacked.getHp()-10);
					
					setconfig();
				}
				if(attack.getFrameCountPunch()==56) {
					attacked.setHp(attacked.getHp()-10);
					
					setconfig();
				}
				if(attack.getFrameCountPunch()==80) {
					attacked.setHp(attacked.getHp()-10);
					
					attacked.blockedCase(10);
					setconfig();
				}
				if(attack.getFrameCountPunch()==122) {
					attacked.setHp(attacked.getHp()-10);
					
					setconfig();
				}
				if(attack.getFrameCountPunch()==160) {
					attacked.setHp(attacked.getHp()-10);
					
					setconfig();
				}
				
			}
		}
	}
	// check win if timeup
	
	// thiet lap cac trang thai
	private void setupStatus() {
		if (kickStatus) player1.kickCount++;
		//player2
		player2.frameCountStand++;
		player2.frameCountWalk++;
		if(player2.getDirector().equals("beAttacked")) player2.frameCountBeAttacked++;
		if(player2.getDirector().equals("superBeAttacked")) player2.frameCountBeAttacked++;
		//player1
		if(player1.getChar2().getCdFlash()>0) player1.getChar2().setcdFlash();
		if(player1.getChar2().getCdSkill1()>0) player1.getChar2().setCDTime1();
		if(player1.getDirector().equals("punch"))player1.frameCountPunch++;
		if(player1.getDirector().equals("stand"))player1.frameCountStand++;
		player1.frameCountWalk++;
		if(player1.getDirector().equals("tele"))player1.frameCountTele++;
		if(player1.getDirector().equals("skill1")) player1.frameCountSuriken++;
		if(player1.getDirector().equals("ultimate")) player1.frameCountUitlmate++;
		if(player1.getDirector().equals("beAttacked")) player2.frameCountBeAttacked++;
		if(player1.getDirector().equals("superBeAttacked")) player2.frameCountBeAttacked++;
		if(statusGame==1) {
			if(totalFame>0) {
				totalFame--;
			}
			}
		player1.setEnemiesPos(player2.getX());
		if(player1.getX()<player2.getX()) mukouMigi = true;
		else mukouMigi = false;
		if(mukouMigi) {
			if(((player2.getX()-player1.getX())<(titleSize))&&checkY()) {
				player1.blockedRight = true;
				player1.blockedLeft = false;
				player2.blockedLeft = true;
				player2.blockedRight = false;
			}else {
				player1.blockedRight = false;
				player2.blockedLeft = false;
			}
		}else {
			if(((player1.getX()-player2.getX())<(titleSize))&&checkY()) {
				player2.blockedRight = true;
				player2.blockedLeft = false;
				player1.blockedLeft = true;
				player1.blockedRight = false;
			}else {
				player2.blockedRight = false;
				player1.blockedLeft = false;
			}
		}
		
		if(player2.getX() <= player1.getX()+24&&player2.getX()>=player1.getX()&& checkY()) {
			player2.blockedCase(6);
			player1.blockedCase(6);
		}
		if(player1.getX() <= player2.getX()+24&&player1.getX()>=player2.getX()&& checkY()) player1.blockedCase(6);
		
	}
	private boolean checkY() {
		if((player1.getY()<=player2.getY()+24)&&(player1.getY()>=player2.getY())) {
			return true;
		}
		if((player2.getY()<=player1.getY()+24)&&(player2.getY()>=player1.getY())) {
			return true;
		}
		return false;
	}
	private boolean checkYcollision(int y1,int y2){
		if(y1<=y2+48&&y1>y2) return true;
		return false;
	}
	//set config
	private void setconfig() {
		hp.setPower1(hp.getPower1()+50);
		hp.setHp2(player2.getHp());
		hp.setPower2(hp.getPower2()+5);
	}

	// get sound
	public static void playMusic(int i ) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public static void playMusicNoLoop(int i ) {
		sound.setFile(i);
		sound.play();
	}
}