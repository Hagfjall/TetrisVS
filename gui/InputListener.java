package gui;

import game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {

	private Game game;
	private int input;

	public InputListener(Game game /*NETWORK */) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		input = e.getKeyCode();
		switch (input) {
		case KeyEvent.VK_UP:
			game.rotateClockwise();
			break;
		case KeyEvent.VK_DOWN:
			game.rotateCounterClockwise();
			break;
		case KeyEvent.VK_LEFT:
			game.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			game.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			game.moveBottom();
			break;
		case KeyEvent.VK_X:
			game.usePowerup();
			break;	
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
