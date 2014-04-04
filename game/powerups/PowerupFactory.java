package game.powerups;

import java.util.Random;

public abstract class PowerupFactory {

	private static Random rand = new Random();

	/**
	 * 
	 * @return new randomized powerup
	 */
	public static Powerup getPowerup() {
		return getPowerup((byte) (rand.nextInt(Powerup.MIRROR ) + 1));
	}

	/**
	 * 
	 * @param type
	 *            which type of powerup to create
	 * @return the shape that matches type, otherwise NULL
	 */
	public static Powerup getPowerup(byte type) {
		switch (type) {
		case Powerup.INCSPEED:
			return new IncreaseSpeed();
		case Powerup.MIRROR:
			return new Mirror();
		default:
			return null;
		}
	}
}