package test;

import game.GameBoard;
import game.ShapeBoard;

import java.util.Observable;
import java.util.Observer;

public class TestObservable implements Observer {

	ShapeBoard sb;
	GameBoard gb;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestObservable().test();

	}

	public void test() {
		int c = 10, r = 6;
		sb = new ShapeBoard(r, c);
		gb = new GameBoard(r, c);
		sb.addObserver(this);
		gb.addObserver(this);

		sb.update(20);
		gb.update(1);

	}

	@Override
	public void update(Observable o, Object arg) {

		System.out.println("uppdatering! " + o);
		int i = (int) arg;
		System.out.println(i);

	}

}
