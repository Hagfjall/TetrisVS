package gui;

import game.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Board board;
	private int width;
	private int height;
	private int x;
	private int y;
	private final int bW = 25;
	private Graphics2D g2;

	public GridPanel(Board b) {

		x = b.getWidth();
		y = b.getHeight();
		width = x * (bW + 2);
		height = y * (bW + 1);
		board = b;
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		g2.setColor(Color.black);
		drawGrid(g2);
		drawBricks(g2);
		g2.setColor(Color.black);
		
		// more?

		g.dispose();
	}

	public void drawBricks(){
		drawBricks(g2);
	}
	
	public void drawBricks(Graphics2D g) {

		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.checkSlot(x, y)) {
					typeColor("I", g);
					g.fillRect(x * bW + bW, y * bW + bW, bW, bW);
				}
			}
		}
	}

	public void typeColor(String type, Graphics2D g) {

		switch (type.charAt(0)) {
		case 'I':
			g.setColor(Color.red);
			break;
		case 'J':
			g.setColor(Color.magenta);
			break;
		case 'L':
			g.setColor(Color.orange);
			break;
		case 'Z':
			g.setColor(Color.blue);
			break;
		case 'S':
			g.setColor(Color.green);
			break;
		case 'O':
			g.setColor(Color.yellow);
			break;
		case 'T':
			g.setColor(Color.black);
			break;
		}
	}

	public void drawGrid(Graphics2D g) {

		// Vertical
		for (int i = 1; i <= x; i++)
			g.drawLine(i * bW, bW, i * bW, y * bW);

		// Horizontal
		for (int i = 1; i <= y; i++)
			g.drawLine(bW, i * bW, x * bW, i * bW);
	}
}
