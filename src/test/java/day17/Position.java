package day17;

public class Position {

	public final int x;
	public final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(String s) {
		String[] split = s.split(",");
		this.x = Integer.parseInt(split[0].trim());
		this.y = Integer.parseInt(split[1].trim());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean onTarget(Target target) {
		return target.contains(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Position)) return false;

		Position point = (Position) o;

		if (x != point.x) return false;
		return y == point.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
