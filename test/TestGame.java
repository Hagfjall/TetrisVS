package test;

import game.Game;

public class TestGame {
	private Game game;

	public static void main(String[] args) {
		new TestGame().run();
	}

	public void run() {
		game = new Game(0,0);
		game.test();
		
	}
	
	public void getBoard(){
		
	}

}