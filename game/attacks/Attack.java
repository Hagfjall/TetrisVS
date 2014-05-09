package game.attacks;

public abstract class Attack {

	public static final byte NULL_ATTACK = 0;
	public static final byte SINGLE_SHAPE = 1;
	public static final byte INVISIBLE_GAME = SINGLE_SHAPE + 1;

	protected byte type;
	protected long startTime;

	protected Attack(byte type) {
		this.type = type;
	}

	/**
	 * Makes it active for 30 seconds from the time this runs
	 */
	public void activate() {
		startTime = System.currentTimeMillis() + 30000;
	}

	public byte getType() {
		return type;
	}

	public boolean isActive() {
		return System.currentTimeMillis() < startTime;
	}

}
