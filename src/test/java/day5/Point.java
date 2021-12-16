package day5;

public class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(String s) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Point)) return false;

		Point point = (Point) o;

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
