package game.attacks;

public abstract class Attack {

	public static final byte NULL_ATTACK = 0;
	public static final byte SINGLE_SHAPE = 1;
	public static final byte INVISIBLE_GAME = SINGLE_SHAPE + 1;

	protected byte type;
	protected boolean active;

	protected Attack(byte type) {
		this.type = type;
		active = false;
	}

	public void activate() {
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}
	
	public byte getType() {
		return type;
	}

	public boolean isActive() {
		return active;
	}

}
