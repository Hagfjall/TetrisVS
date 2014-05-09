package game.attacks;

import java.util.Random;

public class PowerupFactory {

	private Random rand;

	public PowerupFactory(long randomSeed) {
		rand = new Random(randomSeed);
	}

	/**
	 * 
	 * @return new randomized powerup
	 */
	public Attack getPowerup() {
		return getPowerup((byte) (rand.nextInt(Attack.INVISIBLE) + 1));
	}

	/**
	 * 
	 * @param type
	 *            which type of powerup to create
	 * @return the shape that matches type, otherwise NULL
	 */
	public Attack getPowerup(byte type) {
		switch (type) {
		case Attack.SINGLEBLOCK:
			return new SingleBlock();
		case Attack.INVISIBLE:
			return new InvisibleAttack();
		default:
			return new NullAttack();
		}
	}
}
