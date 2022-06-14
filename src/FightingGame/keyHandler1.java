package FightingGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import FightingGame.keyHandler.thread;

public class keyHandler1 implements KeyListener {
	public boolean upStatus, downStatus, rightStatus, leftStatus;
	private final long JumpingTime = 5000;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_UP) {
			upStatus = true;
			new Thread(new thread()).start();
		}
		if(code==KeyEvent.VK_DOWN) {
			downStatus = true;
		}
		if(code==KeyEvent.VK_LEFT) {
			leftStatus = true;
		}
		if(code==KeyEvent.VK_RIGHT) {
			rightStatus = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_UP) {
			upStatus = false;
		}
		if(code==KeyEvent.VK_DOWN) {
			downStatus = false;
		}
		if(code==KeyEvent.VK_LEFT) {
			leftStatus = false;
		}
		if(code==KeyEvent.VK_RIGHT) {
			rightStatus = false;
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