package FightingGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
	public boolean upStatus, downStatus, rightStatus, leftStatus;
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			upStatus = false;
		}
		if(code==KeyEvent.VK_S) {
			downStatus = false;
		}
		if(code==KeyEvent.VK_A) {
			leftStatus = false;
		}
		if(code==KeyEvent.VK_D) {
			rightStatus = false;
		}
	
	}

}
