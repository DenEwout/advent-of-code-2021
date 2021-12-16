package day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class SmokeMap {

	private final int[][] heightMap;
	private final int length;
	private final int width;

	public SmokeMap(int length, int width) {
		this.heightMap = new int[length][width];
		this.length = length;
		this.width = width;
	}

	public void setPoint(int x, int y, int value) {
		heightMap[y][x] = value;
	}

	public Optional<Integer> get(int x, int y) {
		try {
			return Optional.of(heightMap[y][x]);
		} catch (ArrayIndexOutOfBoundsException outofbounds) {
			return Optional.empty();
		}
	}

	public boolean isLowpoint(int x, int y) {
		return Stream.of(get(x + 1, y), get(x - 1, y), get(x, y + 1), get(x, y - 1))
				.flatMap(Optional::stream)
				.allMatch(value -> value > heightMap[y][x]);
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public List<Integer> getLowPointsScore() {
		List<Integer> lowPoints = new ArrayList<>();
		for (int y = 0; y < getLength(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (isLowpoint(x, y)) {
					lowPoints.add(get(x, y).get() + 1);
				}
			}
		}
		return lowPoints;
	}

	public List<Set<Point>> findBasins() {
		List<Set<Point>> basins = new ArrayList<>();
		List<Point> lowPoints = getLowPoints();
		for (Point point : lowPoints) {
			basins.add(getBasin(point));
		}
		return basins;
	}

	private Set<Point> getBasin(Point start) {
		Set<Point> done = new HashSet<>();
		Set<Point> basin = new HashSet<>();
		basin.add(start);
		boolean toContinue = true;
		while (toContinue) {
			Set<Point> addToBasin = new HashSet<>();
			for (Point point : basin) {
				if (!done.contains(point)) {
					int x = point.getX();
					int y = point.getY();
					Stream.of(new Point(x + 1, y), new Point(x - 1, y), new Point(x, y + 1), new Point(x, y - 1))
							.filter(p -> get(p.getX(), p.getY()).isPresent())
							.filter(p -> get(p.getX(), p.getY()).get() < 9)
							.forEach(addToBasin::add);
				}
				done.add(point);
			}
			basin.addAll(addToBasin);
			if (addToBasin.isEmpty()) {
				toContinue = false;
			}
		}
		return basin;
	}

	public List<Point> getLowPoints() {
		List<Point> lowPoints = new ArrayList<>();
		for (int y = 0; y < getLength(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (isLowpoint(x, y)) {
					lowPoints.add(new Point(x, y));
				}
			}
		}
		return lowPoints;
	}
}
