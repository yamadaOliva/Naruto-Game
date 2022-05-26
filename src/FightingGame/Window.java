package FightingGame;

import javax.swing.JFrame;


public class Window {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Game 2D");
		
		GamePanel  gamePN = new GamePanel();
		window.add(gamePN);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePN.startGame();
		gamePN.run();
		
	}

}
