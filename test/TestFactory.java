package test;

import game.attacks.Attack;
import game.attacks.AttackFactory;
import game.shapes.Shape;
import game.shapes.ShapeFactory;

public class TestFactory {

	public static void main(String[] args) {
		int shapes[] = new int[7];
		int powers[] = new int[2];
		ShapeFactory s = new ShapeFactory(System.currentTimeMillis());
		AttackFactory p = new AttackFactory(System.currentTimeMillis());
		for (int i = 0; i < 10000; i++){
			Attack pwrUp = p.getAttack();
			powers[pwrUp.getType()-1]++;
		}
		for (int i = 0; i < 10000; i++){
			Shape sh = s.getShape();
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
