package gui;

import game.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel implements Observer {
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
		game.addObserver(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		g2.setColor(Color.GRAY);
		drawGrid();
		drawBricks();
		g2.setColor(Color.black);
		g2.dispose();
	}

	public void drawBricks() {

		byte[][] board = game.getBoard();
		for (int r = 0; r < row - 1; r++) {
			for (int c = 0; c < col - 1; c++) {
				if (board[r][c] != 0) {
					byte type = board[r][c];
					typeColor(type);
					g2.fillRect(c * square + square + 1, r * square + square
							+ 1, square - 1, square - 1);
				}else{
					g2.setColor(Color.WHITE);
					g2.fillRect(c * square + square + 1, r * square + square
							+ 1, square - 1, square - 1);
				}
			}
		}
	}

	public void typeColor(Byte type) {

		switch (type) {
//		case 0:
//			g2.setColor(Color.GRAY);
//			break;
		case 1:
			g2.setColor(Color.RED);
			break;
		case 2:
			g2.setColor(Color.magenta);
			break;
		case 3:
			g2.setColor(Color.orange);
			break;
		case 4:
			g2.setColor(Color.blue);
			break;
		case 5:
			g2.setColor(Color.green);
			break;
		case 6:
			g2.setColor(Color.yellow);
			break;
		case 7:
			g2.setColor(Color.black);
			break;
		}
	}

	public void drawGrid() {
		// Vertical
		for (int i = 1; i <= col; i++)
			g2.drawLine(i * square, square, i * square, row * square);
		// Horizontal
		for (int i = 1; i <= row; i++)
			g2.drawLine(square, i * square, col * square, i * square);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
