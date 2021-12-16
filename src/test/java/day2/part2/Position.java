package day2.part2;

public class Position {

	private final int horizontal;

	private final int depth;

	private final int aim;

	public Position() {
		this.horizontal = 0;
		this.depth = 0;
		this.aim = 0;
	}

	public Position(int horizontal, int depth, int aim) {
		this.horizontal = horizontal;
		this.depth = depth;
		this.aim = aim;
	}

	public Position add(Position other) {
		return new Position(horizontal + other.horizontal, other.horizontal > 0 ? depth + (other.horizontal * aim) : depth, aim + other.aim);
	}

	public int getHorizontal() {
		return horizontal;
	}

	public int getDepth() {
		return depth;
	}
}
