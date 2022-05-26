package FightingGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler1 implements KeyListener {
	public boolean upStatus, downStatus, rightStatus, leftStatus;
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

}