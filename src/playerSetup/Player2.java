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

import CharacterSetup.Kakashi;
import CharacterSetup.Naruto;
import Entity.Player;
import Entity.character;
import FightingGame.GamePanel;
import FightingGame.keyHandler;

public class Player2 extends Player {
	public keyHandler keyH1 = new keyHandler();
	public int frameCountStand;
	public int frameCountDef;
	public int frameCountWalk;
	private int x, y;
	private String director;
	public GamePanel gp;
	private int choose;
	private boolean onTop = false;
	private boolean upStatus = false;
	private int checkLeft = 0; // check xem nhan vat quay mat ve ben nao, 0 la trai 1 la phai
	character char2;
	character optionChoose[];
	private int isFalling = 0;// Tuc la dang roi,1 co, 0 la khong
	private int hoatAnhTiepDat = 0; // 1 la hien thi tiep hoat anh tiep dat, 0 la ket thuc
	private int countTiepDat = 0; // dem so lan tiep dat

	public Player2(GamePanel gp, keyHandler keyH, int x, int y) {
		super(gp, keyH, x, y);
		this.gp = gp;
		this.keyH1 = keyH;
		this.y = y;
		this.x = x;
		Naruto naruto = new Naruto();
		setupCharacter(0);
		this.director = "stand";
		this.frameCountStand = 0;
		this.frameCountDef = 0;
	}

	private void setupCharacter(int choose) {
		switch (choose) {
			case 0: {
				char2 = new Naruto();
				break;
			}
			case 1: {
				char2 = new Kakashi();
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
			}
		}

		if (keyH1.downStatus) {
			this.director = "def";
		}
		if (keyH1.rightStatus) {
			x += speed;
			this.director = "right";
			if (x > 1278 - GamePanel.titleSize)
				x = 1278 - GamePanel.titleSize;
		}
		if (keyH1.leftStatus) {
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

	}

	@Override
	public void draw(Graphics2D g2, Color e) {
		g2.setColor(e);
		g2.fillRect(x, y, GamePanel.titleSize, GamePanel.titleSize * 2);
	}

	public void draw1(Graphics2D g2) {
		BufferedImage img = null;
		// this.director = "stand";
		System.out.println(this.director);
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

				break;

			case "left":
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

		}

		g2.drawImage(img, x, y, GamePanel.titleSize, GamePanel.titleSize * 2, null);
	}

}
