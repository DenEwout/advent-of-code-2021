package day5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Line {

	private final Axis axis;

	private final Point start;
	private final Point end;

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
		if (start.getX() == end.getX()) {
			this.axis = Axis.VERTICAL;
		} else if (start.getY() == end.getY()) {
			this.axis = Axis.HORIZONTAL;
		} else {
			this.axis = Axis.DIAGONAL;
		}

	}

	public List<Point> getPoints() {
		List<Point> points = new ArrayList<>();
		if (axis.equals(Axis.VERTICAL)) {
			int direction = (end.getY() - start.getY()) / Math.abs(end.getY() - start.getY());
			for (int y = 0; y <= Math.abs(end.getY() - start.getY()); y++) {
				points.add(new Point(start.getX(), start.getY() + (y * direction)));
			}
		} else if (axis.equals(Axis.HORIZONTAL)) {
			int direction = (end.getX() - start.getX()) / Math.abs(end.getX() - start.getX());
			for (int x = 0; x <= Math.abs(end.getX() - start.getX()); x++) {
				points.add(new Point(start.getX() + (x * direction), start.getY()));
			}
		} else if (axis.equals(Axis.DIAGONAL)) {
			int direction = (end.getX() - start.getX()) / Math.abs(end.getX() - start.getX());
			int rico = (end.getY() - start.getY()) / (end.getX() - start.getX());
			for (int x = 0; x <= Math.abs(end.getX() - start.getX()); x++) {
				points.add(new Point(start.getX() + (x * direction), start.getY() + (x * rico * direction)));
			}
		}
		return points;
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}
}
