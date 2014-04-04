package game.powerups;

import java.util.Random;

public class PowerupFactory {

	private static Random rand = new Random();

	private PowerupFactory() {
	}

	public static Powerup getPowerup(){
		return getPowerup((byte)rand.nextInt(Powerup.MIRROR+1));
	}

	/**
	 * 
	 * @param type
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
