package playerSetup;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window.Type;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingConstants;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import CharacterSetup.Naruto;
import CharacterSetup.Sakura;
import CharacterSetup.Sasuke;
import Entity.HPconfig;
import Entity.Player;
import Entity.Skills;
import Entity.character;
import FightingGame.GamePanel;
import FightingGame.keyHandler;
import SkillSetup.Suriken;

public class Player2 extends Player {
	public keyHandler keyH1 = new keyHandler();
	// count frame
		public int frameCountStand;
		public int frameCountDef;
		public int frameCountWalk;
		public int frameCountPunch = 0;
		public int frameCountTele = 0;
		public int frameCountSuriken = 0;
		public int frameCountBeAttacked = 0;
		public int frameCountUitlmate = 0;
		
		private int x, y;
		private String director;
		public GamePanel gp;
		private int choose=1;
		// player's status
		private boolean onTop = false;
		private boolean upStatus = false;
		public boolean blockedLeft = false;
		public boolean blockedRight = false;
		public boolean blocked = false;
		private boolean surikenStatus = false;
		private boolean beAttacked = false;
		private boolean ultimateStatus = false;
		private int statusMukou;
		private boolean specialCase = false;
		public boolean superBeAttacked = false;
		private int kunaiDame;
		
		public int getKunaiDame() {
			return kunaiDame;
		}

		private int punchDame;
		public int getPunchDame() {
			return punchDame;
		}

	//
	private int checkLeft = 0; // check xem nhan vat quay mat ve ben nao, 0 la trai 1 la phai
	character char2;
	character optionChoose[];
	public Skills skill;
	private int enemiesPos;
	private int isFalling = 0;// Tuc la dang roi,1 co, 0 la khong

	public Player2(GamePanel gp, keyHandler keyH, int x, int y, int choose) { // thieu bien choose de chon nhan vat
		super(gp, keyH, x, y);
		this.gp = gp;
		this.keyH1 = keyH;
		this.y = y;
		this.x = x;
		this.choose = choose;
		setupCharacter(choose);
		this.director = "stand";
		this.frameCountStand = 0;
		this.frameCountDef = 0;
		this.frameCountPunch = 0;
	}

	// setter & getter
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getFrameCountStand() {
		return frameCountStand;
	}

	public void setFrameCountStand(int frameCountStand) {
		this.frameCountStand = frameCountStand;
	}

	public int getFrameCountDef() {
		return frameCountDef;
	}

	public void setFrameCountDef(int frameCountDef) {
		this.frameCountDef = frameCountDef;
	}

	public int getFrameCountWalk() {
		return frameCountWalk;
	}

	public void setFrameCountWalk(int frameCountWalk) {
		this.frameCountWalk = frameCountWalk;
	}

	public int getFrameCountPunch() {
		return frameCountPunch;
	}

	public void setFrameCountPunch(int frameCountPunch) {
		this.frameCountPunch = frameCountPunch;
	}

	//
	private void setupCharacter(int choose) {
		switch (choose) {
			case 0: {
				char2 = new Naruto();
				kunaiDame = 20;
				punchDame = 10;
				break;
			}
			case 1: {
				char2 = new Sasuke();
				kunaiDame = 30;
				punchDame = 8;
				break;
			}
			case 2: {
				char2 = new Sakura();
				kunaiDame = 15;
				punchDame = 10;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choose);
		}
		
	}

	@Override
	public void update() {
		int speed = char2.getSpeed();
		statusMukou =  GamePanel.mukouMigi?0:1;
		if (beAttacked||superBeAttacked) {
			this.director = beAttacked?"beAttacked":"superBeAttacked";
		}else if(ultimateStatus) {
				this.director = "ultimate";
			}else {
				if (onTop) {
					keyH1.upStatus = false;
					this.isFalling = 1;
				}
	
				if (keyH1.upStatus) {
					upStatus = true;
					if (y <= 300) {
						keyH1.upStatus = false;
						onTop = true;
					} else {
						y -= 3;
					}
					// this.director = "up";
				}
				if (upStatus) {
					y -= 3;
					this.director = "up";
					keyH1.upStatus = true;
					if (y <= 300) {
						upStatus = false;
						onTop = true;
						keyH1.kick = false;
					}
				}
				if (keyH1.downStatus && !upStatus && isFalling == 0) {
					this.director = "def";
				}
				if (keyH1.rightStatus) {
					if (!blockedRight)
						x += speed;
					this.director = "right";
					if (x > 1278 - GamePanel.titleSize)
						x = 1278 - GamePanel.titleSize;
				}
				if (keyH1.leftStatus) {
					if (!blockedLeft)
						x -= speed;
					this.director = "left";
					if (x < 0)
						x = 0;
			}
	
			if (!keyH1.upStatus) {
				y += 3;
				if (y >= 550) {
					y = 550;
					onTop = false;
				}
			}
			if (keyH1.standStatus) {
				this.director = "stand";
			}
			if (keyH1.punch) {
				this.director = "punch";
			}
			if (keyH1.kick && char2.getCdFlash() <= 0) {
				this.director = "tele";
				GamePanel.playMusicNoLoop(2);
				char2.setCdFlash(char2.getCdFlashTime());
			}
			if (keyH1.skill1 && char2.getCdSkill1() <= 0) {
				GamePanel.skill2Coming = true;
				surikenStatus = true;
				char2.setCdSkill1(char2.getCdSkill1Time());
				skill = new Suriken(x, y + 10);
				skill.coming = true;
				double randomDouble = Math.random();
				randomDouble = randomDouble * 4;
				int randomInt = (int) randomDouble;
				((Suriken) skill).setImgPos(randomInt);
			}
			if (surikenStatus)this.director = "skill1";
			if (keyH1.until&&HPconfig.getPower2()>=200) {
				ultimateStatus = true;
				if(choose==0) GamePanel.playMusicNoLoop(4);
				if(choose==1) GamePanel.playMusicNoLoop(6);
				if(choose == 2) {
					specialCase = true;
					GamePanel.playMusicNoLoop(7);
				}
			}
			
		}
	}

	public int getChoose() {
		return choose;
	}

	@Override
	public void draw(Graphics2D g2, Color e) {
		g2.setColor(e);
		g2.fillRect(x, y, GamePanel.titleSize, GamePanel.titleSize * 2);
	}

	public void draw1(Graphics2D g2) {
		BufferedImage img1 = null;	
		BufferedImage img = null;	
		int tmp;
		switch (this.director) {

			case "stand": {
				tmp = 0;
				if (this.checkLeft == 0) {
					tmp = 1;
				} else
					tmp = 0;
				if (this.frameCountStand >= 0 && this.frameCountStand < 15)
					img = char2.getImgStand(0 * 2 + tmp);
				if (this.frameCountStand >= 15 && this.frameCountStand < 30)
					img = char2.getImgStand(1 * 2 + tmp);
				if (this.frameCountStand >= 30 && this.frameCountStand < 45)
					img = char2.getImgStand(2 * 2 + tmp);
				if (this.frameCountStand >= 45 && this.frameCountStand < 60)
					img = char2.getImgStand(3 * 2 + tmp);
				if (this.frameCountStand >= 60) {
					this.frameCountStand = 0;
				}
				break;
			}
			case "def": {
				tmp = 0;
				if (this.checkLeft == 0) {
					tmp = 1;
				} else
					tmp = 0;
				img = char2.getImgDef(0 * 2 + tmp);
				break;
			}

			case "right":
				checkLeft = 0;
				if (this.checkLeft == 0) {
					tmp = 0;
				} else
					tmp = 1;

				if (this.upStatus || this.isFalling == 1) {

					if (this.isFalling == 0) {
						if (this.y <= 550 && this.y > 425) {
							img = char2.getImgJump(0 * 2 + tmp);
						} else if (this.y <= 425 && this.y > 300) {
							img = char2.getImgJump(1 * 2 + tmp);
						}
					} else if (this.isFalling == 1) {
						if (this.y <= 550 && this.y > 425) {
							img = char2.getImgJump(3 * 2 + tmp);
						} else if (this.y <= 425 && this.y >= 300) {
							img = char2.getImgJump(2 * 2 + tmp);
						}
					}
					if (this.y == 550) {
						this.isFalling = 0;
						this.director = "stand";
					}

				} else {
					if (this.frameCountWalk >= 0 && this.frameCountWalk < 10)
						img = char2.getImgWalking(0 * 2 + tmp);
					else if (this.frameCountWalk >= 10 && this.frameCountWalk < 20)
						img = char2.getImgWalking(1 * 2 + tmp);
					else if (this.frameCountWalk >= 20 && this.frameCountWalk < 30)
						img = char2.getImgWalking(2 * 2 + tmp);
					else if (this.frameCountWalk >= 30 && this.frameCountWalk < 40)
						img = char2.getImgWalking(3 * 2 + tmp);
					else if (this.frameCountWalk >= 40 && this.frameCountWalk < 50)
						img = char2.getImgWalking(4 * 2 + tmp);
					else if (this.frameCountWalk >= 50 && this.frameCountWalk < 60)
						img = char2.getImgWalking(5 * 2 + tmp);
					else if (this.frameCountWalk >= 60)
						this.frameCountWalk = 0;
				}
				checkLeft = 1;
				break;

			case "left":
				checkLeft = 0;
				if (this.checkLeft == 0) {
					tmp = 1;
				} else
					tmp = 0;

				if (this.upStatus || this.isFalling == 1) {

					if (this.isFalling == 0) {
						if (this.y <= 550 && this.y > 425) {
							img = char2.getImgJump(0 * 2 + tmp);
						} else if (this.y <= 425 && this.y > 300) {
							img = char2.getImgJump(1 * 2 + tmp);
						}
					} else if (this.isFalling == 1) {
						if (this.y <= 550 && this.y > 425) {
							img = char2.getImgJump(3 * 2 + tmp);
						} else if (this.y <= 425 && this.y >= 300) {
							img = char2.getImgJump(2 * 2 + tmp);
						}
					}
					if (this.y == 550) {
						this.isFalling = 0;
						this.director = "stand";
					}

				} else {
					if (this.frameCountWalk >= 0 && this.frameCountWalk < 10)
						img = char2.getImgWalking(0 * 2 + tmp);
					else if (this.frameCountWalk >= 10 && this.frameCountWalk < 20)
						img = char2.getImgWalking(1 * 2 + tmp);
					else if (this.frameCountWalk >= 20 && this.frameCountWalk < 30)
						img = char2.getImgWalking(2 * 2 + tmp);
					else if (this.frameCountWalk >= 30 && this.frameCountWalk < 40)
						img = char2.getImgWalking(3 * 2 + tmp);
					else if (this.frameCountWalk >= 40 && this.frameCountWalk < 50)
						img = char2.getImgWalking(4 * 2 + tmp);
					else if (this.frameCountWalk >= 50 && this.frameCountWalk < 60)
						img = char2.getImgWalking(5 * 2 + tmp);
					else if (this.frameCountWalk >= 60)
						this.frameCountWalk = 0;
				}

				break;

			case "up":
				tmp = 0;
				if (this.checkLeft == 0) {
					tmp = 1;
				} else
					tmp = 0;
				if (this.isFalling == 0) {
					if (this.y <= 550 && this.y > 425) {
						img = char2.getImgJump(0 * 2 + tmp);
					} else if (this.y <= 425 && this.y > 300) {
						img = char2.getImgJump(1 * 2 + tmp);
					}
				} else if (this.isFalling == 1) {
					if (this.y <= 550 && this.y > 425) {
						img = char2.getImgJump(3 * 2 + tmp);
					} else if (this.y <= 425 && this.y >= 300) {
						img = char2.getImgJump(2 * 2 + tmp);
					}
				}
				if (this.y == 550) {
					this.isFalling = 0;
					this.director = "stand";
				}
				break;
			case "punch": {
				if (choose == 0) {
					
					if (this.frameCountPunch >= 0 && this.frameCountPunch < 12)
						img = char2.getIMGCombo(0, this.checkLeft);
					if (this.frameCountPunch >= 12 && this.frameCountPunch < 24)
						img = char2.getIMGCombo(1, this.checkLeft);

					if (this.frameCountPunch >= 24 && this.frameCountPunch < 36)
						img = char2.getIMGCombo(2, this.checkLeft);

					if (this.frameCountPunch >= 36 && this.frameCountPunch < 48)
						img = char2.getIMGCombo(3, this.checkLeft);

					if (this.frameCountPunch >= 48 && this.frameCountPunch < 60)
						img = char2.getIMGCombo(4, this.checkLeft);

					if (this.frameCountPunch >= 60 && this.frameCountPunch < 72)
						img = char2.getIMGCombo(5, this.checkLeft);

					if (this.frameCountPunch >= 72 && this.frameCountPunch < 84) {
						img = char2.getIMGCombo(6, this.checkLeft);
						if (checkLeft == 1) {
							this.x += 6;
						} else {
							this.x -= 6;
						}
					}
					if (this.frameCountPunch >= 84 && this.frameCountPunch < 96)
						img = char2.getIMGCombo(7, this.checkLeft);
					if (this.frameCountPunch >= 96 && this.frameCountPunch < 108)
						img = char2.getIMGCombo(8, this.checkLeft);
					if (this.frameCountPunch >= 108 && this.frameCountPunch < 120)
						img = char2.getIMGCombo(9, this.checkLeft);
					if (this.frameCountPunch >= 120 && this.frameCountPunch < 132)
						img = char2.getIMGCombo(10, this.checkLeft);
					if (this.frameCountPunch >= 132 && this.frameCountPunch < 144)
						img = char2.getIMGCombo(11, this.checkLeft);
					if (this.frameCountPunch >= 144 && this.frameCountPunch < 156)
						img = char2.getIMGCombo(12, this.checkLeft);
					if (this.frameCountPunch >= 156 && this.frameCountPunch < 168)
						img = char2.getIMGCombo(13, this.checkLeft);

					if (this.frameCountPunch >= 168) {
						this.frameCountPunch = 0;
						this.director = "stand";
					}
				} else if (choose == 1) {
					// sasuke
					if (this.frameCountPunch >= 0 && this.frameCountPunch < 12)
						img = char2.getIMGCombo(0, this.checkLeft);
					if (this.frameCountPunch >= 12 && this.frameCountPunch < 24)
						img = char2.getIMGCombo(1, this.checkLeft);
					if (this.frameCountPunch >= 24 && this.frameCountPunch < 36)
						img = char2.getIMGCombo(2, this.checkLeft);
					if (this.frameCountPunch >= 36 && this.frameCountPunch < 48)
						img = char2.getIMGCombo(3, this.checkLeft);
					if (this.frameCountPunch >= 48 && this.frameCountPunch < 60)
						img = char2.getIMGCombo(4, this.checkLeft);
					if (this.frameCountPunch >= 60 && this.frameCountPunch < 72)
						img = char2.getIMGCombo(5, this.checkLeft);
					if (this.frameCountPunch >= 72 && this.frameCountPunch < 84) {
						img = char2.getIMGCombo(6, this.checkLeft);
						if (checkLeft == 1) {
							this.x += 6;
						} else {
							this.x -= 6;
						}
					}
					if (this.frameCountPunch >= 84 && this.frameCountPunch < 96)
						img = char2.getIMGCombo(7, this.checkLeft);
					if (this.frameCountPunch >= 96 && this.frameCountPunch < 108)
						img = char2.getIMGCombo(8, this.checkLeft);
					if (this.frameCountPunch >= 108 && this.frameCountPunch < 120)
						img = char2.getIMGCombo(9, this.checkLeft);
					if (this.frameCountPunch >= 120 && this.frameCountPunch < 132)
						img = char2.getIMGCombo(10, this.checkLeft);
					if (this.frameCountPunch >= 132 && this.frameCountPunch < 144)
						img = char2.getIMGCombo(11, this.checkLeft);
					if (this.frameCountPunch >= 144 && this.frameCountPunch < 156)
						img = char2.getIMGCombo(12, this.checkLeft);
					if (this.frameCountPunch >= 156 && this.frameCountPunch < 168)
						img = char2.getIMGCombo(13, this.checkLeft);
					if (this.frameCountPunch >= 168 && this.frameCountPunch < 180)
						img = char2.getIMGCombo(14, this.checkLeft);
					if (this.frameCountPunch >= 180 && this.frameCountPunch < 192) {
						img = char2.getIMGCombo(15, this.checkLeft);
						if (checkLeft == 1) {
							this.x += 6;
						} else {
							this.x -= 6;
						}
					}

					if (this.frameCountPunch >= 192 && this.frameCountPunch < 204)
						img = char2.getIMGCombo(16, this.checkLeft);
					if (this.frameCountPunch >= 204 && this.frameCountPunch < 216)
						img = char2.getIMGCombo(17, this.checkLeft);
					if (this.frameCountPunch >= 216 && this.frameCountPunch < 228)
						img = char2.getIMGCombo(18, this.checkLeft);
					if (this.frameCountPunch >= 228 && this.frameCountPunch < 240)
						img = char2.getIMGCombo(19, this.checkLeft);
					if (this.frameCountPunch >= 240 && this.frameCountPunch < 252)
						img = char2.getIMGCombo(20, this.checkLeft);
					if (this.frameCountPunch >= 252 && this.frameCountPunch < 264)
						img = char2.getIMGCombo(21, this.checkLeft);
					if (this.frameCountPunch >= 264 && this.frameCountPunch < 276)
						img = char2.getIMGCombo(22, this.checkLeft);
					if (this.frameCountPunch >= 276 && this.frameCountPunch < 288)
						img = char2.getIMGCombo(23, this.checkLeft);

					if (this.frameCountPunch >= 288) {
						this.frameCountPunch = 0;
						this.director = "stand";
					}

				} else if (choose == 2) {
					if (this.frameCountPunch >= 0 && this.frameCountPunch < 12)
						img = char2.getIMGCombo(0, this.checkLeft);
					if (this.frameCountPunch >= 12 && this.frameCountPunch < 24)
						img = char2.getIMGCombo(1, this.checkLeft);
					if (this.frameCountPunch >= 24 && this.frameCountPunch < 36)
						img = char2.getIMGCombo(2, this.checkLeft);
					if (this.frameCountPunch >= 36 && this.frameCountPunch < 48)
						img = char2.getIMGCombo(3, this.checkLeft);
					if (this.frameCountPunch >= 48 && this.frameCountPunch < 60)
						img = char2.getIMGCombo(4, this.checkLeft);
					if (this.frameCountPunch >= 60 && this.frameCountPunch < 72)
						img = char2.getIMGCombo(5, this.checkLeft);
					if (this.frameCountPunch >= 72 && this.frameCountPunch < 84) {
						img = char2.getIMGCombo(6, this.checkLeft);
						if (checkLeft == 1) {
							this.x += 6;
						} else {
							this.x -= 6;
						}
					}
					if (this.frameCountPunch >= 84 && this.frameCountPunch < 96)
						img = char2.getIMGCombo(7, this.checkLeft);
					if (this.frameCountPunch >= 96 && this.frameCountPunch < 108)
						img = char2.getIMGCombo(8, this.checkLeft);
					if (this.frameCountPunch >= 108 && this.frameCountPunch < 120)
						img = char2.getIMGCombo(9, this.checkLeft);
					if (this.frameCountPunch >= 120 && this.frameCountPunch < 132)
						img = char2.getIMGCombo(10, this.checkLeft);
					if (this.frameCountPunch >= 132 && this.frameCountPunch < 144)
						img = char2.getIMGCombo(11, this.checkLeft);
					if (this.frameCountPunch >= 144) {
						this.frameCountPunch = 0;
						this.director = "stand";
					}

				} else if (choose == 3) {

				}
				break;
			}
			case "tele": {
				if (this.frameCountTele >= 0 && frameCountTele < 10)
					img = char2.getImgTelepos(0);
				if (this.frameCountTele >= 10 && frameCountTele < 20)
					img = char2.getImgTelepos(1);
				if (this.frameCountTele >= 20 && frameCountTele < 30)
					img = char2.getImgTelepos(2);
				if (this.frameCountTele >= 30 && frameCountTele < 35)
					img = char2.getImgTelepos(3);
				if (this.frameCountTele >= 35 && frameCountTele < 38) {
					if(checkLeft<enemiesPos) this.x = enemiesPos + 50;
					else this.x = enemiesPos -50;
					img = char2.getImgTelepos(4);
				}
				if (this.frameCountTele >= 38 && frameCountTele < 41)
					img = char2.getImgTelepos(3);
				if (this.frameCountTele >= 41 && frameCountTele < 51)
					img = char2.getImgTelepos(4);
				if (this.frameCountTele >= 51 && frameCountTele < 61)
					img = char2.getImgTelepos(5);
				if (this.frameCountTele >= 61 && frameCountTele < 71)
					img = char2.getImgTelepos(6);
				if (this.frameCountTele >= 71 && frameCountTele < 81)
					img = char2.getImgTelepos(7);
				if (this.frameCountTele >= 81) {
					frameCountTele = 0;
					this.director = "stand";
				}
				break;
			}
			case "skill1": {
				if (frameCountSuriken >= 0 && frameCountSuriken < 10)
					img = char2.getIMGSuriken(0+statusMukou*3);
				if (frameCountSuriken >= 10 && frameCountSuriken < 20)
					img = char2.getIMGSuriken(1+statusMukou*3);
				if (frameCountSuriken >= 20 && frameCountSuriken < 30)
					img = char2.getIMGSuriken(2+statusMukou*3);
				if (frameCountSuriken >= 30) {
					frameCountSuriken = 0;
					surikenStatus = false;
					this.director = "stand";

				}
				break;
			}
			case "beAttacked": {
				if (frameCountBeAttacked >= 0 && frameCountBeAttacked < 10)
					img = char2.getIMGBeAttaced(0);
				if (frameCountBeAttacked >= 10 && frameCountBeAttacked < 20)
					img = char2.getIMGBeAttaced(1);
				if (frameCountBeAttacked >= 20 && frameCountBeAttacked < 30)
					img = char2.getIMGBeAttaced(2);
				if (frameCountBeAttacked >= 30) {
					frameCountBeAttacked = 0;
					this.director = "stand";
					beAttacked = false;
				}
				break;
			}
			case "ultimate":{
				if(choose==0) {
					if(frameCountUitlmate >= 0&&frameCountUitlmate<10) img = char2.getIMGUltimate(0);
					if(frameCountUitlmate >= 15&&frameCountUitlmate<30) img = char2.getIMGUltimate(1);
					if(frameCountUitlmate >= 30&&frameCountUitlmate<45) img = char2.getIMGUltimate(2);
					if(frameCountUitlmate >= 45&&frameCountUitlmate<60) img = char2.getIMGUltimate(3);
					if(frameCountUitlmate >= 60&&frameCountUitlmate<75) img = char2.getIMGUltimate(4);
					if(frameCountUitlmate >= 75&&frameCountUitlmate<90) {
						img = char2.getIMGUltimate(5);
						this.x = enemiesPos-40;
					}
					if(frameCountUitlmate >= 90&&frameCountUitlmate<105) img = char2.getIMGUltimate(6);
					if(frameCountUitlmate >= 105&&frameCountUitlmate<120) img = char2.getIMGUltimate(7);
					if(frameCountUitlmate >= 120&&frameCountUitlmate<135) img = char2.getIMGUltimate(8);
					if(frameCountUitlmate >= 135&&frameCountUitlmate<150) img = char2.getIMGUltimate(9);
					if(frameCountUitlmate >= 150&&frameCountUitlmate<165) img = char2.getIMGUltimate(10);
					if(frameCountUitlmate >= 165&&frameCountUitlmate<180) img = char2.getIMGUltimate(11);
					if(frameCountUitlmate >= 180&&frameCountUitlmate<195) img = char2.getIMGUltimate(12);
					if(frameCountUitlmate >= 195&&frameCountUitlmate<210) img = char2.getIMGUltimate(13);
					if(frameCountUitlmate >= 210&&frameCountUitlmate<225) img = char2.getIMGUltimate(14);
					if(frameCountUitlmate >= 225&&frameCountUitlmate<240) img = char2.getIMGUltimate(15);
					if(frameCountUitlmate >= 240&&frameCountUitlmate<255) img = char2.getIMGUltimate(16);
					if(frameCountUitlmate >= 255&&frameCountUitlmate<270) img = char2.getIMGUltimate(17);
					if(frameCountUitlmate >= 270&&frameCountUitlmate<295) img = char2.getIMGUltimate(18);
					if(frameCountUitlmate >= 295&&frameCountUitlmate<305) img = char2.getIMGUltimate(19);
					if(frameCountUitlmate >= 305&&frameCountUitlmate<320) img = char2.getIMGUltimate(20);
					if(frameCountUitlmate >= 320&&frameCountUitlmate<335) img = char2.getIMGUltimate(21);
					if(frameCountUitlmate >= 335&&frameCountUitlmate<350) img = char2.getIMGUltimate(22);
					if(frameCountUitlmate >= 350&&frameCountUitlmate<375) img = char2.getIMGUltimate(23);
					if(frameCountUitlmate >= 375&&frameCountUitlmate<390) img = char2.getIMGUltimate(24);
					if(frameCountUitlmate >= 390&&frameCountUitlmate<405) img = char2.getIMGUltimate(25);
					if(frameCountUitlmate >= 405&&frameCountUitlmate<420) img = char2.getIMGUltimate(26);
					if(frameCountUitlmate>=320&&frameCountUitlmate<420) {
						img1 = char2.getIMGUltimate(27);
						g2.drawImage(img1,x-100,y-50,null);
					}
					if(frameCountUitlmate>=420) {
						frameCountUitlmate = 0;
						this.director = "stand";
						this.ultimateStatus = false;
					}
				}else if(choose == 1) {
					if(frameCountUitlmate >= 0&&frameCountUitlmate<15) img = char2.getIMGUltimate(0);
					if(frameCountUitlmate >= 15&&frameCountUitlmate<30) img = char2.getIMGUltimate(1);
					if(frameCountUitlmate >= 30&&frameCountUitlmate<45) img = char2.getIMGUltimate(2);
					if(frameCountUitlmate >= 45&&frameCountUitlmate<60) img = char2.getIMGUltimate(3);
					if(frameCountUitlmate >= 60&&frameCountUitlmate<75) img = char2.getIMGUltimate(4);
					if(frameCountUitlmate >= 75&&frameCountUitlmate<90) img = char2.getIMGUltimate(5);
					if(frameCountUitlmate >= 90&&frameCountUitlmate<105) img = char2.getIMGUltimate(6);
					if(frameCountUitlmate >= 105&&frameCountUitlmate<120) img = char2.getIMGUltimate(7);
					if(frameCountUitlmate >= 120&&frameCountUitlmate<135) img = char2.getIMGUltimate(8);
					if(frameCountUitlmate >= 135&&frameCountUitlmate<150) img = char2.getIMGUltimate(9);
					if(frameCountUitlmate >= 150&&frameCountUitlmate<165) img = char2.getIMGUltimate(10);
					if(frameCountUitlmate >= 165&&frameCountUitlmate<180) img = char2.getIMGUltimate(11);
					if(frameCountUitlmate >= 180&&frameCountUitlmate<195) img = char2.getIMGUltimate(12);
					if(frameCountUitlmate >= 195&&frameCountUitlmate<210) img = char2.getIMGUltimate(13);
					if(frameCountUitlmate >= 210&&frameCountUitlmate<225) img = char2.getIMGUltimate(14);
					if(frameCountUitlmate >= 225&&frameCountUitlmate<240) {
						img = char2.getIMGUltimate(15);
						this.x = enemiesPos-200;
					}
					if(frameCountUitlmate>=240&&frameCountUitlmate<405) this.x+=3;
					if(frameCountUitlmate >= 240&&frameCountUitlmate<255) img = char2.getIMGUltimate(16);
					if(frameCountUitlmate >= 255&&frameCountUitlmate<270) img = char2.getIMGUltimate(17);
					if(frameCountUitlmate >= 270&&frameCountUitlmate<295) img = char2.getIMGUltimate(18);
					if(frameCountUitlmate >= 295&&frameCountUitlmate<305) img = char2.getIMGUltimate(19);
					if(frameCountUitlmate >= 305&&frameCountUitlmate<320) img = char2.getIMGUltimate(20);
					if(frameCountUitlmate >= 320&&frameCountUitlmate<335) img = char2.getIMGUltimate(21);
					if(frameCountUitlmate >= 335&&frameCountUitlmate<350) img = char2.getIMGUltimate(22);
					if(frameCountUitlmate >= 350&&frameCountUitlmate<375) img = char2.getIMGUltimate(23);
					if(frameCountUitlmate >= 375&&frameCountUitlmate<390) img = char2.getIMGUltimate(24);
					if(frameCountUitlmate >= 390&&frameCountUitlmate<405) img = char2.getIMGUltimate(25);
					if(frameCountUitlmate>=405) {
						frameCountUitlmate = 0;
						this.director = "stand";
						this.ultimateStatus = false;
					}
				} else {
					if(frameCountUitlmate >= 0&&frameCountUitlmate<20) {
						img = char2.getIMGUltimate(0);
						this.x = enemiesPos - 20;
					}
					if(frameCountUitlmate >= 20&&frameCountUitlmate<40) img = char2.getIMGUltimate(1);
					if(frameCountUitlmate >= 40&&frameCountUitlmate<60) img = char2.getIMGUltimate(2);
					if(frameCountUitlmate >= 60&&frameCountUitlmate<80) img = char2.getIMGUltimate(3);
					if(frameCountUitlmate >= 80&&frameCountUitlmate<100) img = char2.getIMGUltimate(4);
					if(frameCountUitlmate >= 100&&frameCountUitlmate<120) img = char2.getIMGUltimate(5);
					if(frameCountUitlmate >= 120&&frameCountUitlmate<140) img = char2.getIMGUltimate(6);
					if(frameCountUitlmate >= 140&&frameCountUitlmate<160) img = char2.getIMGUltimate(7);
					if(frameCountUitlmate >= 160&&frameCountUitlmate<180) img = char2.getIMGUltimate(8);
					if(frameCountUitlmate >= 180&&frameCountUitlmate<200) img = char2.getIMGUltimate(9);
					if(frameCountUitlmate >= 200&&frameCountUitlmate<220) img = char2.getIMGUltimate(10);
					if(frameCountUitlmate >= 220&&frameCountUitlmate<240) img = char2.getIMGUltimate(11);
					if(frameCountUitlmate >= 240&&frameCountUitlmate<260) img = char2.getIMGUltimate(12);
					if(frameCountUitlmate >= 260&&frameCountUitlmate<280) img = char2.getIMGUltimate(13);
					if(frameCountUitlmate >= 280&&frameCountUitlmate<300) img = char2.getIMGUltimate(14);
					if(frameCountUitlmate >= 300&&frameCountUitlmate<320) img = char2.getIMGUltimate(15);
					if(frameCountUitlmate>=320) {
						frameCountUitlmate = 0;
						this.director = "stand";
						this.ultimateStatus = false;
						specialCase = false;
						
					}
					
				}
				break;
			}
			case "superBeAttacked":{
				if (frameCountBeAttacked >= 0 && frameCountBeAttacked < 20) img = char2.getIMGBeAttaced(0);
				if (frameCountBeAttacked >= 20 && frameCountBeAttacked < 40) img = char2.getIMGBeAttaced(1);
				if (frameCountBeAttacked >= 40 && frameCountBeAttacked < 60) img = char2.getIMGBeAttaced(2);
				if (frameCountBeAttacked >= 60 && frameCountBeAttacked < 80) img = char2.getIMGBeAttaced(3);
				if (frameCountBeAttacked >= 80 && frameCountBeAttacked < 100) img = char2.getIMGBeAttaced(4);
				if (frameCountBeAttacked >= 100 && frameCountBeAttacked < 120) img = char2.getIMGBeAttaced(5);
				if (frameCountBeAttacked >= 120 && frameCountBeAttacked < 140) img = char2.getIMGBeAttaced(6);
				if (frameCountBeAttacked >= 140 && frameCountBeAttacked < 160) img = char2.getIMGBeAttaced(7);
				if (frameCountBeAttacked >= 160 && frameCountBeAttacked < 180) img = char2.getIMGBeAttaced(8);
				if (frameCountBeAttacked >= 180 && frameCountBeAttacked < 200) img = char2.getIMGBeAttaced(9);
				if (frameCountBeAttacked >= 200) {
					frameCountBeAttacked = 0;
					this.superBeAttacked = false;
					this.director = "stand";
				}
				break;
			}
		}
			
		if(!specialCase) g2.drawImage(img, x, y, null);
		else g2.drawImage(img, x, y-23, null);
	}

	public void blockedCase(int space) {
		if (GamePanel.mukouMigi)
			x += space;
		else
			x -= 6;
	}
	public void setBeAttackedStatus(int dame) {
		this.beAttacked = true;
		this.setHp(this.getHp() - dame);
		this.setPower(this.getPower()+10);
		
	}
	public void setSuperBeAttackedStatus(int dame) {
		this.superBeAttacked = true;
		this.setHp(this.getHp() - dame);
	}
	public character getChar2() {
		return char2;
	}

	public void setChar2(character char2) {
		this.char2 = char2;
	}
	public void setEnemiesPos(int enemiesPos) {
		this.enemiesPos = enemiesPos;
	}
}
