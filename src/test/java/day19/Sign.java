package day19;

public enum Sign {

	POSITIVE(1), NEGATIVE(-1);

	public final int direction;

	Sign(int direction) {
		this.direction = direction;
	}
}
