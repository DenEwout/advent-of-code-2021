package day13;

public class Fold {

	private final Point point;

	private final Axis axis;


	public Fold(String input) {
		String[] split = input.replace("fold along ", "").trim().split("=");
		axis = split[0].equals("x") ? Axis.VERTICAL:  Axis.HORIZONTAL;
		if (axis.equals(Axis.HORIZONTAL)) {
			point = new Point(0, Integer.parseInt(split[1].trim()));
		} else {
			point = new Point(Integer.parseInt(split[1].trim()), 0);
		}
	}

	public Fold(Point point, Axis axis) {
		this.point = point;
		this.axis = axis;
	}

	public Point getPoint() {
		return point;
	}

	public Axis getAxis() {
		return axis;
	}

}
