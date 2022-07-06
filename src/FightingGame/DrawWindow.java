package FightingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DrawWindow {
	Image icon;
	BufferedImage[] iconSelector = new BufferedImage[4]; 
	public static boolean right1 = false,left1 = false, right2 = false,left2 = false, choosingStatus1 = false, choosingStatus2 = false;
	public static int selectionStatus = 0;
	public DrawWindow() {
		ImageIcon u = new ImageIcon("C:\\Users\\Admin\\Desktop\\ProjectOOP\\png\\png\\bgMenu.gif");
		icon = u.getImage();
		try {
			iconSelector[0] = ImageIO.read(getClass().getResource("/png/Naruto.png"));
			iconSelector[1] = ImageIO.read(getClass().getResource("/png/Sakura.png"));
			iconSelector[2] = ImageIO.read(getClass().getResource("/png/Sasuke.png"));
			iconSelector[3] = ImageIO.read(getClass().getResource("/png/RockLee.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void menuDraw(Graphics2D g2) {
		String title = "GAME PRO VIP DANH NHAU DUNG DUNG";
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
			g2.setColor(Color.black);
			g2.drawString("=>", 300, 250);
			g2.drawString("START GAME ", 400, 250);
		}else {
			g2.setColor(Color.white);
			g2.drawString("START GAME ", 400, 250);
		}
		if(GamePanel.statusChoose == 1) {
			g2.setColor(Color.black);
			g2.drawString("=>", 300, 350);
			g2.drawString("SETTINGS ", 400, 350);
		}else {
			g2.setColor(Color.white);
			g2.drawString("SETTINGS ", 400, 350);
		}
		if(GamePanel.statusChoose == 2) {
			g2.setColor(Color.black);
			g2.drawString("=>", 300, 450);
			g2.drawString("QUIT ", 400, 450);
		}else {
			g2.setColor(Color.white);
			g2.drawString("QUIT ", 400, 450);
		}
	}
	public void slectChampDraw(Graphics2D g2) {
		g2.setBackground(Color.white);
		drawChamp(0, g2, 300, 150);
		drawChamp(1, g2, 500, 150);
		drawChamp(2, g2, 700, 150);
		drawChamp(3, g2, 900, 150);
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
			GamePanel.choosingTwo ++;
			if(GamePanel.choosingTwo==4) GamePanel.choosingTwo = 0;
			right2 = false;
		}
		if(left2) {
			GamePanel.choosingTwo--;
			if(GamePanel.choosingTwo==-1) GamePanel.choosingTwo = 3;
			left2 = false;
		}
		drawName("player1", g2, 330+200*GamePanel.choosingOne, 135, Color.red);
		drawName("player2", g2, 330+200*GamePanel.choosingTwo, 285, Color.blue);
	}
	private void drawChamp(int pos,Graphics2D g2,int x,int y) {
		g2.drawImage(iconSelector[pos],x,y,100,100,null);
	}
	private void drawName(String name,Graphics2D g2, int x, int y, Color beautiful) {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,12));
		g2.setColor(beautiful);
		g2.drawString(name, x, y);
	}
}
