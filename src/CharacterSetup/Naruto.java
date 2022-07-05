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
	public BufferedImage[] jumpIMG = new BufferedImage[20];
	public BufferedImage[] comboIMGRight = new BufferedImage[20];
	public BufferedImage[] comboIMGLeft = new BufferedImage[20];
	public BufferedImage[] teleposIMG = new BufferedImage[10];
	public BufferedImage[] surikenIMG = new BufferedImage[5];
	public BufferedImage[] beAttackedIMG = new BufferedImage[15];
	private String characterName = "Naruto";
	private final int  cdSkill1Time= 600;
	private final int  cdFlashTime = 600;

	private int cdFlash = 0;

	public int getCdSkill1Time() {
		return cdSkill1Time;
	}
	
	public int getCdFlashTime() {
		return cdFlashTime;
	}
	
	public Naruto() {
		this.setSpeed(3);
		this.setPunchdame(5);
		getStandIMG();
		getDefIMG();
		getWalkIMG();
		getJumpIMG();
		getComboIMGRight();
		getComboIMGLeft();
		getTeleposIMG();
		getSurikenIMG();
		getBeAttackedIMG();
	}
	public void getSurikenIMG() {
		try {
			this.surikenIMG[0] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/suriken_1.png"));
			this.surikenIMG[1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/suriken_2.png"));
			this.surikenIMG[2] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/suriken_3.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getStandIMG() {
		try {
			this.stand[0] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand1.png"));
			this.stand[1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand1_2.png"));
			this.stand[2] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand2.png"));
			this.stand[3] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand2_2.png"));
			this.stand[4] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand3.png"));
			this.stand[5] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand3_2.png"));
			this.stand[6] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand4.png"));
			this.stand[7] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/stand4_2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getComboIMGRight() {
		try {
			this.comboIMGRight[0]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_1.png"));
			this.comboIMGRight[1]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_2.png"));
			this.comboIMGRight[2]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_3.png"));
			this.comboIMGRight[3]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_4.png"));
			this.comboIMGRight[4]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_5.png"));
			this.comboIMGRight[5]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_6.png"));
			this.comboIMGRight[6]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_7.png"));
			this.comboIMGRight[7]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_8.png"));
			this.comboIMGRight[8]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_9.png"));
			this.comboIMGRight[9]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_10.png"));
			this.comboIMGRight[10]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_11.png"));
			this.comboIMGRight[11]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_12.png"));
			this.comboIMGRight[12]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_13.png"));
			this.comboIMGRight[13]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combo_14.png"));

			}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getTeleposIMG() {
		try {
			this.teleposIMG[0] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_1.png"));
			this.teleposIMG[1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_2.png"));
			this.teleposIMG[2] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_3.png"));
			this.teleposIMG[3] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_4.png"));
			this.teleposIMG[4] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_5.png"));
			this.teleposIMG[5] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_6.png"));
			this.teleposIMG[6] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_7.png"));
			this.teleposIMG[7] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_8.png"));
			this.teleposIMG[8] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/telepos_9.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getComboIMGLeft() {
		try {
			this.comboIMGLeft[0]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_1 (1).png"));
			this.comboIMGLeft[1]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_2 (1).png"));
			this.comboIMGLeft[2]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_3 (1).png"));
			this.comboIMGLeft[3]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_4 (1).png"));
			this.comboIMGLeft[4]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_5 (1).png"));
			this.comboIMGLeft[5]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_6 (1).png"));
			this.comboIMGLeft[6]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_7 (1).png"));
			this.comboIMGLeft[7]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_8 (1).png"));
			this.comboIMGLeft[8]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_9 (1).png"));
			this.comboIMGLeft[9]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_10 (1).png"));
			this.comboIMGLeft[10]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_11 (1).png"));
			this.comboIMGLeft[11]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_12 (1).png"));
			this.comboIMGLeft[12]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_13 (1).png"));
			this.comboIMGLeft[13]= ImageIO.read(getClass().getResource("/character/" + characterName  +"/combof_14 (1).png"));
			
			}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getDefIMG() {
		try {
			this.defIMG[0] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/block1.png"));
			this.defIMG[1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/block1_2.png"));
			this.defIMG[2] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/block2.png"));
			this.defIMG[3] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/block2_2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getWalkIMG() {
		try {
			this.walkIMG[0] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk1.png"));
			this.walkIMG[1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk1_2.png"));
			this.walkIMG[2] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk2.png"));
			this.walkIMG[3] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk2_2.png"));
			this.walkIMG[4] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk3.png"));
			this.walkIMG[5] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk3_2.png"));
			this.walkIMG[6] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk4.png"));
			this.walkIMG[7] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk4_2.png"));
			this.walkIMG[8] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk5.png"));
			this.walkIMG[9] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk5_2.png"));
			this.walkIMG[10] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk6.png"));
			this.walkIMG[11] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/walk6_2.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void getJumpIMG() {
		try {
			this.jumpIMG[0] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump1.png"));
			this.jumpIMG[1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump1_2.png"));
			this.jumpIMG[2] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump2.png"));
			this.jumpIMG[3] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump2_2.png"));
			this.jumpIMG[4] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump3.png"));
			this.jumpIMG[5] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump3_2.png"));
			this.jumpIMG[6] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump4.png"));
			this.jumpIMG[7] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump4_2.png"));
			this.jumpIMG[8] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump5.png"));
			this.jumpIMG[9] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump5_2.png"));
			this.jumpIMG[10] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump6.png"));
			this.jumpIMG[11] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump6_2.png"));
			this.jumpIMG[12] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump7.png"));
			this.jumpIMG[13] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump7_2.png"));
			this.jumpIMG[14] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump8.png"));
			this.jumpIMG[15] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/jump8_2.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void getBeAttackedIMG() {
		try {
			for(int i =1;i<=14;++i) {
				this.beAttackedIMG[i-1] = ImageIO.read(getClass().getResource("/character/" + characterName  +"/beattacked_"+Integer.toString(i)+".png")); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getImgStand(int i) {
		return this.stand[i];
	}

	public BufferedImage getImgDef(int i) {
		return this.defIMG[i];
	}

	public BufferedImage getImgWalking(int i) {
		return this.walkIMG[i];
	}

	public BufferedImage getImgJump(int i) {
		return this.jumpIMG[i];
	}
	public BufferedImage getImgComboRight(int i) {
		return this.comboIMGRight[i];
	}
	public BufferedImage getImgComboLeft(int i) {
		return this.comboIMGLeft[i];
	}
	public BufferedImage getImgTelepos(int i) {
		return this.teleposIMG[i];
	}
	public BufferedImage getIMGSuriken(int i) {
		return this.surikenIMG[i];
	}
	public BufferedImage getIMGBeAttaced(int i) {
		return this.beAttackedIMG[i];
	}
}
