package day2.part1;

public class Position {

	private final int horizontal;

	private final int depth;

	public Position() {
		this.horizontal = 0;
		this.depth = 0;
	}

	public Position(int horizontal, int depth) {
		this.horizontal = horizontal;
		this.depth = depth;
	}

	public Position add(Position other) {
		return new Position(horizontal + other.horizontal, depth + other.depth);
	}

	public int getHorizontal() {
		return horizontal;
	}

	public int getDepth() {
		return depth;
	}
}
