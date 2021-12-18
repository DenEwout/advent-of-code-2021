package day17;

public class Velocity {

	public final int x;
	public final int y;

	public Velocity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Velocity)) return false;

		Velocity velocity = (Velocity) o;

		if (x != velocity.x) return false;
		return y == velocity.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
