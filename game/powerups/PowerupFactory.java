package game.powerups;

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
	public Powerup getPowerup() {
		return getPowerup((byte) (rand.nextInt(Powerup.INVISIBLE) + 1));
	}

	/**
	 * 
	 * @param type
	 *            which type of powerup to create
	 * @return the shape that matches type, otherwise NULL
	 */
	public Powerup getPowerup(byte type) {
		switch (type) {
		case Powerup.SINGLEBLOCK:
			return new SingleBlock();
		case Powerup.INVISIBLE:
			return new Invisible();
		default:
			return new NullPowerup();
		}
	}
}
