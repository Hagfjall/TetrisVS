package game.attacks;

public abstract class Attack {

	public static final byte NULLATTACK = 0;
	public static final byte SINGLEBLOCK = 1;
	public static final byte INVISIBLE = SINGLEBLOCK + 1;

	protected byte type;
	protected long startTime;

	public Attack(byte type) {
		this.type = type;
		//
	}

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
