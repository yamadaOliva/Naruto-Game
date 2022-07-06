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
import Entity.Player;
import Entity.Skills;
import Entity.character;
import FightingGame.GamePanel;
import FightingGame.keyHandler;
import SkillSetup.Suriken;

public class Player1 extends Player {
	public keyHandler keyH1 = new keyHandler();
	// count frame
	public int frameCountStand;
	public int frameCountDef;
	public int frameCountWalk;
	public int frameCountPunch = 0;
	public int frameCountTele = 0;
	public int frameCountSuriken = 0;
	public int frameCountBeAttacked = 0;
	private int x, y;
	private String director;
	public GamePanel gp;
	private int choose = 1;
	// player's status
	private boolean onTop = false;
	private boolean upStatus = false;
	public boolean blockedLeft = false;
	public boolean blockedRight = false;
	public boolean blocked = false;
	private boolean surikenStatus = false;
	private int checkLeft = 0; // check xem nhan vat quay mat ve ben nao, 0 la trai 1 la phai
	private character char2;

	public void setEnemiesPos(int enemiesPos) {
		this.enemiesPos = enemiesPos;
	}

	character optionChoose[];
	private int isFalling = 0;// Tuc la dang roi,1 co, 0 la khong
	private int hoatAnhTiepDat = 0; // 1 la hien thi tiep hoat anh tiep dat, 0 la ket thuc
	private int countTiepDat = 0; // dem so lan tiep dat
	//
	int fixbug;
	// tuong tac
	// Skills
	public Skills skill;
	private int enemiesPos;

	public Player1(GamePanel gp, keyHandler keyH, int x, int y) { // thieu bien choose de chon nhan vat
		super(gp, keyH, x, y);
		this.gp = gp;
		this.keyH1 = keyH;
		this.y = y;
		this.x = x;
		setupCharacter(1);
		checkLeft = 1;
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
				break;
			}
			case 1: {
				char2 = new Sasuke();
				break;
			}
			case 2: {
				char2 = new Sakura();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choose);
		}
	}

	@Override
	public void update() {
		int speed = char2.getSpeed();
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
			char2.setCdFlash(char2.getCdFlashTime());
		}
		if (keyH1.skill1 && char2.getCdSkill1() <= 0) {
			surikenStatus = true;
			char2.setCdSkill1(char2.getCdSkill1Time());
			skill = new Suriken(x, y + 10);
			skill.coming = true;
			double randomDouble = Math.random();
			randomDouble = randomDouble * 4;
			int randomInt = (int) randomDouble;
			((Suriken) skill).setImgPos(randomInt);
		}
		if (surikenStatus)
			this.director = "skill1";
	}

	@Override
	public void draw(Graphics2D g2, Color e) {
		g2.setColor(e);
		g2.fillRect(x, y, GamePanel.titleSize, GamePanel.titleSize * 2);
	}

	public void draw1(Graphics2D g2) {
		BufferedImage img = null;
		// this.director = "stand";
		// System.out.println(this.director);
		int tmp;
		switch (this.director) {

			case "stand": {
				tmp = 0;
				if (this.checkLeft == 0) {
					tmp = 1;
				} else
					tmp = 0;
				// System.out.println(char2.getClass().getName());
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
					// naruto
					// System.out.println("Naruto");
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
					if (this.frameCountPunch >= 168) {
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
					this.x = enemiesPos + 50;
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
					img = char2.getIMGSuriken(0);
				if (frameCountSuriken >= 10 && frameCountSuriken < 20)
					img = char2.getIMGSuriken(1);
				if (frameCountSuriken >= 20 && frameCountSuriken < 30)
					img = char2.getIMGSuriken(2);
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
				}
				break;
			}
		}

		g2.drawImage(img, x, y, null);
	}

	public void blockedCase(int space) {
		if (GamePanel.mukouMigi)
			x -= space;
		else
			x += 6;
	}

	public character getChar2() {
		return char2;
	}

	public void setChar2(character char2) {
		this.char2 = char2;
	}

}
