package FightingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DrawWindow {
	Image icon;
	public MapG mapG= new MapG();;
	BufferedImage[] iconSelector = new BufferedImage[5];
	BufferedImage[] iconFont = new BufferedImage[4];
	BufferedImage[] winnerFont = new BufferedImage[2];
	BufferedImage bgDraw  ;
	public static boolean right1 = false,left1 = false, right2 = false,left2 = false, choosingStatus1 = false, choosingStatus2 = false;
	public static int selectionStatus = 0;
	public DrawWindow() {
		ImageIcon u = new ImageIcon("C:\\Users\\Admin\\Desktop\\New folder\\ProjectOOP\\png\\png\\bgMenu.gif");
		icon = u.getImage();
		try {
			bgDraw = ImageIO.read(getClass().getResource("/png/bgDraw.png"));
			iconSelector[0] = ImageIO.read(getClass().getResource("/png/Naruto.png"));
			iconSelector[1] = ImageIO.read(getClass().getResource("/png/Sakura.png"));
			iconSelector[2] = ImageIO.read(getClass().getResource("/png/Sasuke.png"));
			iconSelector[3] = ImageIO.read(getClass().getResource("/png/RockLee.png"));
			iconSelector[4] = ImageIO.read(getClass().getResource("/skills/kunai/kunai_6.png"));
			iconFont[0] = ImageIO.read(getClass().getResource("/png/narutoFont.png"));
			iconFont[2] = ImageIO.read(getClass().getResource("/png/sakuraFont.png"));
			iconFont[1] = ImageIO.read(getClass().getResource("/png/sakukeFont.png"));
			iconFont[3] = ImageIO.read(getClass().getResource("/png/rockleeFont.png"));
			winnerFont[0] = ImageIO.read(getClass().getResource("/png/winner-1.png"));
			winnerFont[1] = ImageIO.read(getClass().getResource("/png/winner-2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void menuDraw(Graphics2D g2) {
		String title = "NARUTO FIGHT GAME";
		g2.setColor(new Color(74, 116, 53));
		g2.drawImage(icon, 0, 0,1278,720,null);
		//Shadow
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40));
		g2.setColor(Color.gray);
		g2.drawString(title, 273, 123);
		//title Game
		g2.setColor(Color.white);
		g2.drawString(title, 270, 120);
		//Detail
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,36));
		if(GamePanel.statusChoose == 0) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,44));
			g2.setColor(Color.gray);
			g2.drawString("START GAME ", 403, 253);
			g2.setColor(Color.black);
			g2.drawImage(iconSelector[4], 300, 185,100,100,null);
			g2.drawString("START GAME ", 400, 250);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,36));
		}else {
			g2.setColor(Color.red);
			g2.drawString("START GAME ", 400, 250);
		}
		if(GamePanel.statusChoose == 1) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,44));
			g2.setColor(Color.gray);
			g2.drawString("TUTORIAL ", 403, 353);
			g2.setColor(Color.black);
			g2.drawImage(iconSelector[4], 300, 285,100,100,null);
			g2.drawString("TUTORIAL ", 400, 350);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,36));
		}else {
			g2.setColor(Color.red);
			g2.drawString("TUTORIAL ", 400, 350);
		}
		if(GamePanel.statusChoose == 2) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,44));
			g2.setColor(Color.gray);
			g2.drawString("QUIT", 403, 453);
			g2.setColor(Color.black);
			g2.drawImage(iconSelector[4], 300, 385,100,100,null);
			g2.drawString("QUIT", 400, 450);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,36));
		}else {
			g2.setColor(Color.red);
			g2.drawString("QUIT ", 400, 450);
		}
	}
	public void slectChampDraw(Graphics2D g2) {
		g2.drawImage(bgDraw, 0, 0,1278,720,null);
		if(right1) {
			GamePanel.choosingOne ++;
			if(GamePanel.choosingOne==4) GamePanel.choosingOne = 0;
			right1 = false;
		}
		if(left1) {
			GamePanel.choosingOne--;	
			if(GamePanel.choosingOne==-1)GamePanel.choosingOne = 3;
			left1 = false;
		}
		if(right2) {
			if(selectionStatus==0) GamePanel.choosingTwo ++;
			GamePanel.choosingMap ++;
			if(GamePanel.choosingMap == 4) GamePanel.choosingMap = 0;
			if(GamePanel.choosingTwo==4) GamePanel.choosingTwo = 0;
			right2 = false;
		}
		if(left2) {
			if(selectionStatus==0) GamePanel.choosingTwo--;
			GamePanel.choosingMap --;
			if(GamePanel.choosingMap == -1) GamePanel.choosingMap = 3;
			if(GamePanel.choosingTwo==-1) GamePanel.choosingTwo = 3;
			left2 = false;
		}
		switch (selectionStatus) {
		
		case 0: {
			g2.setBackground(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,24));
			g2.setColor(Color.red);
			String tutorialText = "Player 1 press 0, Player 2 press 9 to select";
			g2.drawString(tutorialText, 400, 70);
			drawChamp(0, g2, 300, 150);
			drawChamp(2, g2, 500, 150);
			drawChamp(1, g2, 700, 150);
			drawChamp(3, g2, 900, 150);
			drawName("player1", g2, 330+200*GamePanel.choosingOne, 135, Color.green);
			drawName("player2", g2, 330+200*GamePanel.choosingTwo, 285, Color.yellow);
			g2.drawImage(iconFont[GamePanel.choosingOne],100,300,150,300,null);
			g2.setColor(Color.green);
			String  statPlayer = null ;
			switch(GamePanel.choosingOne) {
				case 0:{
					 statPlayer ="Punch Dame : 10";
					 g2.drawString(statPlayer, 300, 350);
					 statPlayer ="Kunai Dame : 20";
					 g2.drawString(statPlayer, 300, 400);
					 statPlayer ="Supper Power : 200";
					 g2.drawString(statPlayer, 300, 450);
					 statPlayer ="speed : 4";
					 g2.drawString(statPlayer, 300, 500);
					break;
				}
				case 1:{
					 statPlayer ="Punch Dame : 8";
					 g2.drawString(statPlayer, 300, 350);
					 statPlayer ="Kunai Dame : 30";
					 g2.drawString(statPlayer, 300, 400);
					 statPlayer ="Supper Power : 150";
					 g2.drawString(statPlayer, 300, 450);
					 statPlayer ="speed : 5";
					 g2.drawString(statPlayer, 300, 500);
					break;
				}
				case 2:{
					 statPlayer ="Punch Dame : 15";
					 g2.drawString(statPlayer, 300, 350);
					 statPlayer ="Kunai Dame : 10";
					 g2.drawString(statPlayer, 300, 400);
					 statPlayer ="Supper Power : 250";
					 g2.drawString(statPlayer, 300, 450);
					 statPlayer ="speed : 3";
					 g2.drawString(statPlayer, 300, 500);
					break;
				}
				case 3:{
					g2.setFont(g2.getFont().deriveFont(Font.BOLD,32));
					g2.setColor(Color.red);
					 statPlayer ="He is coming soon!!";
					 g2.drawString(statPlayer, 300, 450);
					break;
				}

			}
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,12));
			g2.setColor(Color.yellow);
			switch(GamePanel.choosingTwo) {
			case 0:{
				 statPlayer ="Punch Dame : 10";
				 g2.drawString(statPlayer, 1278-400, 350);
				 statPlayer ="Kunai Dame : 20";
				 g2.drawString(statPlayer, 1278-400, 400);
				 statPlayer ="Supper Power : 200";
				 g2.drawString(statPlayer, 1278-400, 450);
				 statPlayer ="speed : 4";
				 g2.drawString(statPlayer, 1278-400, 500);
				break;
			}
			case 1:{
				 statPlayer ="Punch Dame : 8";
				 g2.drawString(statPlayer, 1278-400, 350);
				 statPlayer ="Kunai Dame : 30";
				 g2.drawString(statPlayer, 1278-400, 400);
				 statPlayer ="Supper Power : 150";
				 g2.drawString(statPlayer, 1278-400, 450);
				 statPlayer ="speed : 5";
				 g2.drawString(statPlayer, 1278-400, 500);
				break;
			}
			case 2:{
				 statPlayer ="Punch Dame : 15";
				 g2.drawString(statPlayer, 1278-400, 350);
				 statPlayer ="Kunai Dame : 10";
				 g2.drawString(statPlayer, 1278-400, 400);
				 statPlayer ="Supper Power : 250";
				 g2.drawString(statPlayer, 1278-400, 450);
				 statPlayer ="speed : 3";
				 g2.drawString(statPlayer, 1278-400, 500);
				break;
			}
			case 3:{
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,32));
				g2.setColor(Color.red);
				 statPlayer ="He is coming soon!!";
				 g2.drawString(statPlayer, 1278-450, 450);
				break;
			}

		}
			g2.drawImage(iconFont[GamePanel.choosingTwo],1278-250,300,150,300,null);
			break;
		}
		case 1:{
			
			g2.drawImage(mapG.map[0],300,500,100,100,null);
			g2.drawImage(mapG.map[1],500,500,100,100,null);
			g2.drawImage(mapG.map[2],700,500,100,100,null);
			g2.drawImage(mapG.map[3],900,500,100,100,null);
			String tutorialText = "(press Enter to Select map and Start Game)";
			g2.setColor(Color.red);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,32));
			g2.drawString(tutorialText,325,675);
			g2.drawImage(mapG.map[GamePanel.choosingMap],300,50,700,400,null);
			drawName("<.|.>", g2, 340+GamePanel.choosingMap*200, 635, Color.red);
		}
		
		}
	}
	private void drawChamp(int pos,Graphics2D g2,int x,int y) {
		g2.drawImage(iconSelector[pos],x,y,100,100,null);
	}
	private void drawName(String name,Graphics2D g2, int x, int y, Color beautiful) {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,12));
		g2.setColor(beautiful);
		g2.drawString(name, x, y);
	}
	public void drawSetting(Graphics2D g2) {
		BufferedImage ptr = null;
		try {
			ptr = ImageIO.read(getClass().getResource("/png/tutorial.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(ptr,200,200,null);
		String tutorialText = "(press Enter to back)";
		g2.setColor(Color.red);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32));
		g2.drawString(tutorialText,325,675);
	}
	public void drawWinner(int check,Graphics2D g2 ) {
		if(check == 1) g2.drawImage(winnerFont[0],0,0,1278,720,null);
		else g2.drawImage(winnerFont[1],0,0,1278,720,null);
		String tutorialText = "(press Enter to play again)";
		g2.setColor(Color.red);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32));
		g2.drawString(tutorialText,325,675);
	}
}
