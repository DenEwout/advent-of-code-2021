package day19;

public class Direction {

	public final Axis axis;
	public final Sign sign;

	public Direction(Sign sign, Axis axis) {
		this.axis = axis;
		this.sign = sign;
	}

	public static Direction from(String input) {
		return switch (input) {
			case "x" -> new Direction(Sign.POSITIVE, Axis.X);
			case "-x" -> new Direction(Sign.NEGATIVE, Axis.X);
			case "y" -> new Direction(Sign.POSITIVE, Axis.Y);
			case "-y" -> new Direction(Sign.NEGATIVE, Axis.Y);
			case "z" -> new Direction(Sign.POSITIVE, Axis.Z);
			case "-z" -> new Direction(Sign.NEGATIVE, Axis.Z);
			default -> throw new IllegalArgumentException("Invalid direction");
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Direction)) return false;

		Direction direction = (Direction) o;

		if (axis != direction.axis) return false;
		return sign == direction.sign;
	}

	@Override
	public int hashCode() {
		int result = axis.hashCode();
		result = 31 * result + sign.hashCode();
		return result;
	}
}
