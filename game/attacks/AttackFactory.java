package game.attacks;

import java.util.Random;

public class AttackFactory {

	private Random rand;

	public AttackFactory(long randomSeed) {
		rand = new Random(randomSeed);
	}

	/**
	 * 
	 * @return new randomized attack
	 */
	public Attack getAttack() {
		return getAttack((byte) (rand.nextInt(Attack.INVISIBLE_GAME) + 1));
	}

	/**
	 * 
	 * @param type
	 *            which type of attack to create
	 * @return the attack that matches type, otherwise NullAttack
	 */
	public static Attack getAttack(byte type) {
		switch (type) {
		case Attack.SINGLE_SHAPE:
			return new SingleBlockAttack();
		case Attack.INVISIBLE_GAME:
			return new InvisibleAttack();
		default:
			return new NullAttack();
		}
	}
}
