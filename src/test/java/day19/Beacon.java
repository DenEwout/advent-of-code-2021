package day19;

public class Beacon {

	public final int x;
	public final int y;
	public final int z;

	public Beacon(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Beacon(String s) {
		String[] split = s.split(",");
		this.x = Integer.parseInt(split[0].trim());
		this.y = Integer.parseInt(split[1].trim());
		this.z = Integer.parseInt(split[2].trim());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Beacon minus(Beacon other) {
		return new Beacon(x - other.x, y - other.y, z - other.z);
	}

	public Beacon add(Beacon other) {
		return new Beacon(x + other.x, y + other.y, z + other.z);
	}

	public int direction(Direction direction) {
		return direction.sign.direction * switch (direction.axis) {
			case X -> x;
			case Y -> y;
			case Z -> z;
		};
	}

	public Beacon transform(Orientation orientation) {
		return new Beacon(direction(orientation.x), direction(orientation.y), direction(orientation.z));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Beacon)) return false;

		Beacon beacon = (Beacon) o;

		if (x != beacon.x) return false;
		if (y != beacon.y) return false;
		return z == beacon.z;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		result = 31 * result + z;
		return result;
	}

	@Override
	public String toString() {
		return "Beacon{" + x + ", " + y + ", " + z + '}';
	}


}
