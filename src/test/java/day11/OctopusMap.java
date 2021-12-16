package day11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OctopusMap {

	private final int[][] octopi;
	private final int length;
	private final int width;

	private int flashes;

	public OctopusMap(int length, int width) {
		this.octopi = new int[length][width];
		this.length = length;
		this.width = width;
	}

	public void add(int x, int y, int value) {
		octopi[y][x] = value;
	}

	public Optional<Integer> get(int x, int y) {
		try {
			return Optional.of(octopi[y][x]);
		} catch (ArrayIndexOutOfBoundsException outofbounds) {
			return Optional.empty();
		}
	}

	public Set<Point> neighbours(int x, int y) {
		return Stream.of(
						new Point(x + 1, y),
						new Point(x + 1, y + 1),
						new Point(x + 1, y - 1),
						new Point(x - 1, y),
						new Point(x - 1, y + 1),
						new Point(x - 1, y - 1),
						new Point(x, y + 1),
						new Point(x, y - 1)
				).filter(p -> get(p.x, p.y).isPresent())
				.collect(Collectors.toSet());
	}

	public void incrementAll() {
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				increment(x, y);
			}
		}
	}

	public void increment(int x, int y) {
		octopi[y][x]++;
		if (octopi[y][x] == 10) {
			flashes++;
		}
	}

	public void flashStep() {
		Set<Point> sources = getSourceFlashes();
		for (Point source : sources) {
			boolean toContinue = true;
			List<Point> toIncrement = new ArrayList<>(neighbours(source.x, source.y));
			while (toContinue) {
				List<Point> newSourceFlashes = new ArrayList<>();
				for (Point point : toIncrement) {
					increment(point.x, point.y);
					if (octopi[point.y][point.x] == 10) {
						newSourceFlashes.add(point);
					}
				}

				toIncrement = newSourceFlashes.stream()
						.flatMap(p -> neighbours(p.x, p.y).stream())
						.toList();
				toContinue = !toIncrement.isEmpty();
			}
		}
	}

	private Set<Point> getSourceFlashes() {
		Set<Point> sources = new HashSet<>();
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				if (octopi[y][x] == 10) {
					sources.add(new Point(x, y));
				}
			}
		}
		return sources;
	}

	public void reset() {
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				if (octopi[y][x] > 9) {
					octopi[y][x] = 0;
				}
			}
		}
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getFlashes() {
		return flashes;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				stringBuilder.append(octopi[y][x]);
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
