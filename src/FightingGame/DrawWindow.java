package FightingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class DrawWindow {
	Image icon;
	BufferedImage[] iconSelector = new BufferedImage[4]; 
	public DrawWindow() {
		ImageIcon u = new ImageIcon("C:\\Users\\Admin\\Desktop\\ProjectOOP\\png\\png\\bgMenu.gif");
		icon = u.getImage();
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
		
	}
}
