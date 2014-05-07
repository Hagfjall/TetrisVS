package game.powerups;

public abstract class Powerup {

	public static final byte NULLPOWERUP = 0;
	public static final byte INCSPEED = 1;
	public static final byte MIRROR = INCSPEED + 1;
	public static final byte SINGLEBLOCK = MIRROR + 1;

	protected byte type;
	protected long startTime;

	public Powerup(byte type) {
		this.type = type;
		startTime = System.currentTimeMillis() + 30000;
	}

	public Powerup() {
	}

	public byte getType() {
		return type;
	}

	public boolean isActive() {
		return System.currentTimeMillis() < startTime;
	}

}
