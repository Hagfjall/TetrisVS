package game.powerups;

public abstract class Powerup {

	public static final byte INCSPEED = 1;
	public static final byte MIRROR = INCSPEED + 1;

	protected byte type;

	public Powerup(byte type) {
		this.type = type;
	}

	public byte getType() {
		return type;
	}

}
