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
	private int col;
	private int row;
	private final int square = 25;
	private Graphics2D g2;

	public GridPanel(Board b) {

		col = b.getWidth() + 1;
		System.out.println(col);
		row = b.getHeight() + 1;
		System.out.println(row);
		width = col * (square + 2);
		height = row * (square + 1);
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
		g.dispose();
	}

	public void drawBricks() {
		drawBricks(g2);
	}

	public void drawBricks(Graphics2D g) {

		for (int x = 0; x < col- 1; x++) {
			for (int y = 0; y < row - 1; y++) {
				if (board.checkSlot(y, x)) {
					typeColor("I", g);
					g.fillRect(x * square + square, y * square + square,
							square, square);
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
		for (int i = 1; i <= col; i++) {
			g.drawLine(i * square, square, i * square, row * square);
		}

		// Horizontal
		for (int i = 1; i <= row; i++){
			g.drawLine(square, i * square, col * square, i * square);
		}
	}
}
