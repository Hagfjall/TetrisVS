package test;

import game.blocks.Shape;
import game.blocks.ShapeFactory;
import game.powerups.Powerup;
import game.powerups.PowerupFactory;

public class TestFactory {

	public static void main(String[] args) {
		int shapes[] = new int[7];
		int powers[] = new int[2];
		ShapeFactory s = new ShapeFactory(System.currentTimeMillis());
		for (int i = 0; i < 1000; i++){
			Powerup p = PowerupFactory.getPowerup();
			System.out.println(p);
			powers[p.getType()-1]++;
		}
		for (int i = 0; i < 1000; i++){
			Shape sh = s.getShape();
			System.out.println(s);
			shapes[sh.getType()-1]++;
		}
		System.out.print("powers: ");
		for(int i = 0; i < 2; i++){
			System.out.print(powers[i] + " ");
		}
		System.out.print("\nshapes: ");
		for(int i = 0; i < 7; i++){
			System.out.print(shapes[i] + " ");
		}
	}

}
