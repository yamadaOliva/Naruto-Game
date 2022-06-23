package CharacterSetup;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;

import javax.imageio.ImageIO;

import Entity.character;

public class Naruto extends character {
	public BufferedImage[] stand = new BufferedImage[10];
	public BufferedImage[] defIMG = new BufferedImage[10];
	public BufferedImage[] moveRight = new BufferedImage[10];
	public BufferedImage[] moveLeft = new BufferedImage[10];
	public BufferedImage[] walkIMG = new BufferedImage[20];

	public Naruto() {
		this.setSpeed(3);
		getStandIMG();
		getDefIMG();
		getWalkIMG();
	}

	private void getStandIMG() {
		try {
			this.stand[0] = ImageIO.read(getClass().getResource("/character/Naruto/stand1.png"));
			this.stand[1] = ImageIO.read(getClass().getResource("/character/Naruto/stand1_2.png"));
			this.stand[2] = ImageIO.read(getClass().getResource("/character/Naruto/stand2.png"));
			this.stand[3] = ImageIO.read(getClass().getResource("/character/Naruto/stand2_2.png"));
			this.stand[4] = ImageIO.read(getClass().getResource("/character/Naruto/stand3.png"));
			this.stand[5] = ImageIO.read(getClass().getResource("/character/Naruto/stand3_2.png"));
			this.stand[6] = ImageIO.read(getClass().getResource("/character/Naruto/stand4.png"));
			this.stand[7] = ImageIO.read(getClass().getResource("/character/Naruto/stand4_2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDefIMG() {
		try {
			this.defIMG[0] = ImageIO.read(getClass().getResource("/character/Naruto/block1.png"));
			this.defIMG[1] = ImageIO.read(getClass().getResource("/character/Naruto/block1_2.png"));
			this.defIMG[2] = ImageIO.read(getClass().getResource("/character/Naruto/block2.png"));
			this.defIMG[3] = ImageIO.read(getClass().getResource("/character/Naruto/block2_2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getWalkIMG() {
		try {
			this.walkIMG[0] = ImageIO.read(getClass().getResource("/character/Naruto/walk1.png"));
			this.walkIMG[1] = ImageIO.read(getClass().getResource("/character/Naruto/walk1_2.png"));
			this.walkIMG[2] = ImageIO.read(getClass().getResource("/character/Naruto/walk2.png"));
			this.walkIMG[3] = ImageIO.read(getClass().getResource("/character/Naruto/walk2_2.png"));
			this.walkIMG[4] = ImageIO.read(getClass().getResource("/character/Naruto/walk3.png"));
			this.walkIMG[5] = ImageIO.read(getClass().getResource("/character/Naruto/walk3_2.png"));
			this.walkIMG[6] = ImageIO.read(getClass().getResource("/character/Naruto/walk4.png"));
			this.walkIMG[7] = ImageIO.read(getClass().getResource("/character/Naruto/walk4_2.png"));
			this.walkIMG[8] = ImageIO.read(getClass().getResource("/character/Naruto/walk5.png"));
			this.walkIMG[9] = ImageIO.read(getClass().getResource("/character/Naruto/walk5_2.png"));
			this.walkIMG[10] = ImageIO.read(getClass().getResource("/character/Naruto/walk6.png"));
			this.walkIMG[11] = ImageIO.read(getClass().getResource("/character/Naruto/walk6_2.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
