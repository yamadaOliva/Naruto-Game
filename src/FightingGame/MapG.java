package FightingGame;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MapG {
	public BufferedImage[] map = new BufferedImage[6];
	public Image mapGif;
	public MapG() {
		getImgMap();
	}
	private void getImgMap() {
		ImageIcon u = new ImageIcon("C:\\Users\\Admin\\Desktop\\ProjectOOP\\png\\png\\map2.gif");
		mapGif = u.getImage();
		System.out.println(mapGif);
		try {
			map[0] = ImageIO.read(getClass().getResource("/png/bg_Game.png"));
			map[1] = ImageIO.read(getClass().getResource("/png/map1.jpg"));
			map[2] = ImageIO.read(getClass().getResource("/png/map3.jpg"));
			map[3] = ImageIO.read(getClass().getResource("/png/map4.jpg"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
 }
