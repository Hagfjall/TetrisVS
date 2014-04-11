package gui;

import game.ShapeBoard;
import game.blocks.Shape;
import game.blocks.Z_Left;

import javax.swing.JFrame;

public class GuiTest {

	public static void main(String[] args) {
		createAndShowGUI();
	}

	private static void createAndShowGUI() {
		JFrame f = new JFrame("Tetris VS");
		ShapeBoard b = new ShapeBoard(22, 10);
		Shape s = new Z_Left();
		b.setShape(s);
		b.printShape();
		InputListener input = new InputListener(b);
		GridPanel gridPanel = new GridPanel(b);
		f.addKeyListener(input);
		f.add(gridPanel);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gridPanel.repaint();
		}
	}
}
