package test;

import game.blocks.ShapeFactory;
import game.powerups.PowerupFactory;

public class TestFactory {

	public static void main(String[] args) {
		ShapeFactory s = new ShapeFactory(System.currentTimeMillis());
		for (int i = 0; i < 100; i++)
			System.out.println(PowerupFactory.getPowerup());
		for (int i = 0; i < 100; i++)
			System.out.println(s.getShape());
	}

}
