package FightingGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;
import javax.xml.stream.events.StartDocument;

public class keyHandler implements KeyListener {
	public boolean upStatus = false, downStatus, rightStatus, leftStatus, falling, skill, kick;
	private int upKey, downKey, rightkey, leftKey;
	private final long JumpingTime = 100000;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public keyHandler() {
		
	}
	public keyHandler(int up, int down, int right, int left) {
		this.upKey = up;
		this.downKey = down;
		this.rightkey = right;
		this.leftKey = left;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			upStatus = true;
			new Thread(new thread()).start();
		}
		if(code==KeyEvent.VK_S) {
			downStatus = true;
		}
		if(code==KeyEvent.VK_A) {
			leftStatus = true;
		}
		if(code==KeyEvent.VK_D) {
			rightStatus = true;
		}
		if(code == KeyEvent.VK_J) {
			skill = true;
		}
		if(code == KeyEvent.VK_K) {
			kick = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_S) {
			downStatus = false;
		}
		if(code==KeyEvent.VK_W) {
			upStatus = false;
		}
		if(code==KeyEvent.VK_A) {
			leftStatus = false;
		}
		if(code==KeyEvent.VK_D) {
			rightStatus = false;
		}
		if(code == KeyEvent.VK_J) {
			skill = false;
		}
		if(code == KeyEvent.VK_K) {
			kick = false;
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
