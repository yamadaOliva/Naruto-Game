package FightingGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;
import javax.xml.stream.events.StartDocument;

public class keyHandler implements KeyListener {
	public boolean upStatus = false, downStatus, rightStatus, leftStatus,standStatus, falling, skill, kick,punch,skill1,skill2,until;
	public int upKey, downKey, rightkey, leftKey;
	private int skill1Key,skill2Key,untilKey;
	private int punchKey, kickKey;
	private final long JumpingTime = 10000;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setKillKey(int skill1Key,int skill2Key,int ultilKey) {
		this.skill1Key = skill1Key;
		this.skill2Key = skill2Key;
		this.untilKey = ultilKey;
	}
	public void setPunchKickKey(int punchKey, int kickKey) {
		this.punchKey  = punchKey;
		this.kickKey = kickKey;
	}
	public keyHandler() {
		
	}
	public keyHandler(int up, int down, int right, int left, int kick) {
		this.upKey = up;
		this.downKey = down;
		this.rightkey = right;
		this.leftKey = left;
		this.kickKey = kick;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(GamePanel.statusGame==0) {
			if(code == KeyEvent.VK_DOWN) {
				GamePanel.statusChoose --;
				if(GamePanel.statusChoose==-1) GamePanel.statusChoose = 2;
			}
			if(code == KeyEvent.VK_UP) {
				GamePanel.statusChoose ++;
				if(GamePanel.statusChoose==3) GamePanel.statusChoose = 0;
			}
			if(code == KeyEvent.VK_ENTER) {
				if(GamePanel.statusChoose==0) {
					GamePanel.sound.stop();
					GamePanel.statusGame = 1;
					//GamePanel.playMusic(0);
				}
				if(GamePanel.statusChoose==2) {
					System.exit(0);
				}
			}
		}
		//
		if(GamePanel.statusGame == 1) {
			if(code == KeyEvent.VK_D ) {
				DrawWindow.right1 = true;
			}
			if(code == KeyEvent.VK_A ) {
				DrawWindow.left1 = true;
			}
			if(code == KeyEvent.VK_RIGHT ) {
				DrawWindow.right2 = true;
			}
			if(code == KeyEvent.VK_LEFT ) {
				DrawWindow.left2 = true;
			}
			if(code == KeyEvent.VK_9) {
				DrawWindow.choosingStatus1 = true;
			}
			if(code == KeyEvent.VK_0) {
				DrawWindow.choosingStatus2 = true;
			}
			if(DrawWindow.choosingStatus2&&DrawWindow.choosingStatus1) {
				DrawWindow.selectionStatus = 1;
			}
			if(DrawWindow.selectionStatus == 1&&code == KeyEvent.VK_ENTER) {
				GamePanel.statusGame = 2;
				//GamePanel.playMusic(0);
			}
		}
		//
		
		if(GamePanel.statusGame==2) {
		
		standStatus = false;
		if(code==upKey) {
			upStatus = true;
			new Thread(new thread()).start();
		}
		if(code==downKey) {
			downStatus = true;
		}
		if(code==leftKey) {
			leftStatus = true;
			rightStatus = false;
		}
		if(code==rightkey) {
			leftStatus = false;
			rightStatus = true;
		}
		if(code == skill1Key) {
			skill1 = true;
		}
		if(code == skill2Key) {
			skill2 = true;
		}
		if(code == untilKey) {
			until = true;
		}
		if(code == kickKey) {
			kick = true;
		}
		if(code == punchKey)
		{
			punch = true;
			downStatus = false;
			upStatus = false;
			rightStatus = false;
			leftStatus = false;
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code==upKey) {
			upStatus = false;
		}
		if(code==downKey) {
			downStatus = false;
			standStatus = true;
		}
		if(code==leftKey) {
			leftStatus = false;
			standStatus = true;
		}
		if(code==rightkey) {
			rightStatus =false;
			standStatus = true;
		}
		if(code == skill1Key) {
			skill1 = false;
		}
		if(code == skill2Key) {
			skill2 = false;
		}
		if(code == untilKey) {
			until = false;
		}
		if(code == kickKey) {
			kick = false;
		}
		if(code == punchKey)
		{
			punch = false;
		}
	
	}
	public class thread implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(JumpingTime);
				upStatus = false;
			} catch (Exception e) {
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
			}
			
		}
		
	}

}
