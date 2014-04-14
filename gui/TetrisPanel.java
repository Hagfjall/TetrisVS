package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Board;
import game.Game;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {
	private static final long serialVersionUID = -4465366882639674458L;
	private Game game;
	private int width;
	private int height;
	private int col;
	private int row;
	private final int square = 25;
	private Graphics2D g2;

	public TetrisPanel(Game game) {
		this.game = game;
		col = game.getWidth() + 1;
		row = game.getHeight() + 1;
		width = col * (square + 2);
		height = row * (square + 1);
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		g2.setColor(Color.black);
		drawGrid();
		drawBricks();
		g2.setColor(Color.black);
		g2.dispose();
	}

	public void drawBricks() {

		byte[][] board = game.getBoard();
		for (int x = 0; x < col- 1; x++) {
			for (int y = 0; y < row - 1; y++) {
				if (board.checkSlot(y, x)) {
					byte type = board.getType(y, x);
					typeColor(type, g);
					g.fillRect(x * square + square, y * square + square,
							square, square);
				}
			}
		}
	}

	public void typeColor(Byte type) {

		switch (type) {
		case '1':
			g2.setColor(Color.red);
			break;
		case '2':
			g2.setColor(Color.magenta);
			break;
		case '3':
			g2.setColor(Color.orange);
			break;
		case '4':
			g2.setColor(Color.blue);
			break;
		case '5':
			g2.setColor(Color.green);
			break;
		case '6':
			g2.setColor(Color.yellow);
			break;
		case '7':
			g2.setColor(Color.black);
			break;
		}
	}

	public void drawGrid() {

		// Vertical
		for (int i = 1; i <= col; i++) {
			g2.drawLine(i * square, square, i * square, row * square);
		}

		// Horizontal
		for (int i = 1; i <= row; i++){
			g2.drawLine(square, i * square, col * square, i * square);
		}
	}

}
