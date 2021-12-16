package day2.part2;

public enum Direction {

	UP(-1),
	DOWN(1),
	FORWARD(1);

	private final int multiplier;

	Direction(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getMultiplier() {
		return multiplier;
	}

}
