package day17;

public class Target {

	private final Position min;

	private final Position max;

	public Target(Position min, Position max) {
		this.min = min;
		this.max = max;
	}

	public Position getMin() {
		return min;
	}

	public Position getMax() {
		return max;
	}

	public boolean contains(Position p) {
		return (p.x >= min.x && p.x <= max.x) && (p.y >= min.y && p.y <= max.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Target)) return false;

		Target target = (Target) o;

		if (!min.equals(target.min)) return false;
		return max.equals(target.max);
	}

	@Override
	public int hashCode() {
		int result = min.hashCode();
		result = 31 * result + max.hashCode();
		return result;
	}
}
