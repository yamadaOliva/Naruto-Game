package FightingGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.xml.stream.events.StartDocument;

public class keyHandler implements KeyListener {
	public boolean upStatus = false, downStatus, rightStatus, leftStatus, falling, skill;
	private final long JumpingTime = 100000;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
