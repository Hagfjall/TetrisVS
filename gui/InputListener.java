package gui;

import game.ShapeBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {

	private ShapeBoard b;
	private int input;

	public InputListener(ShapeBoard b) {
		this.b = b;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		input = e.getKeyCode();
		System.out.println("something pressed");
		switch (input) {
		case KeyEvent.VK_UP:
			b.rotateClockwise();
			break;
		case KeyEvent.VK_DOWN:
			b.moveDown();
			System.out.println("down");
			break;
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveLeft();
			break;
		case KeyEvent.VK_SPACE:
			b.moveBottom();
			break;
		case KeyEvent.VK_X:
			b.fireAttack();
			break;	
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
