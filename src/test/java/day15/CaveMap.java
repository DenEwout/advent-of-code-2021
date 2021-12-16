package day15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaveMap {

	private final int[][] cave;
	private final int length;
	private final int width;

	private int flashes;

	public CaveMap(int length, int width) {
		this.cave = new int[length][width];
		this.length = length;
		this.width = width;
	}

	public void initialize(List<String> lines) {
		for (int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			List<Integer> values = toCharStream(line).map(c -> Integer.parseInt(String.valueOf(c))).collect(Collectors.toList());
			for (int x = 0; x < values.size(); x++) {
				this.add(x, y, values.get(x));
			}
		}
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}


	public void add(int x, int y, int value) {
		cave[y][x] = value;
	}

	public Optional<Integer> get(int x, int y) {
		try {
			return Optional.of(cave[y][x]);
		} catch (ArrayIndexOutOfBoundsException outofbounds) {
			return Optional.empty();
		}
	}

	public Set<Point> all() {
		Set<Point> all = new HashSet<>();
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				all.add(new Point(x, y));
			}
		}
		return all;
	}

	public List<Point> dijkstra() {
		Set<Point> visited = all();
		int[][] dist = new int[length][width];
		Point[][] prev = new Point[length][width];
		visited.forEach(p -> {
			dist[p.y][p.x] = Integer.MAX_VALUE;
			prev[p.y][p.x] = null;
		});
		dist[0][0] = 0;

		while (!visited.isEmpty()) {
			Point min = visited.stream().min(Comparator.comparingInt(p -> dist[p.y][p.x])).orElseThrow();
			visited.remove(min);

			for (Point neighbour : neighbours(min.x, min.y)) {
				if (visited.contains(neighbour)) {
					int alt = dist[min.y][min.x] + cave[neighbour.y][neighbour.x];
					if (alt < dist[neighbour.y][neighbour.x]) {
						dist[neighbour.y][neighbour.x] = alt;
						prev[neighbour.y][neighbour.x] = min;
					}
				}
			}
		}

		List<Point> sequence = new ArrayList<>();
		Point target = new Point(width - 1, length - 1);
		if (prev[target.y][target.x] != null || target.equals(new Point(0, 0))) {
			while (target != null) {
				sequence.add(0, target);
				target = prev[target.y][target.x];
			}
		}
		return sequence;
	}

	public int risk(List<Point> points) {
		return points.stream()
				.skip(1)
				.map(p -> cave[p.y][p.x])
				.reduce(0, Integer::sum);
	}

	public Set<Point> neighbours(int x, int y) {
		return Stream.of(
						new Point(x + 1, y),
						new Point(x - 1, y),
						new Point(x, y + 1),
						new Point(x, y - 1)
				).filter(p -> get(p.x, p.y).isPresent())
				.collect(Collectors.toSet());
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
				stringBuilder.append(cave[y][x]);
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public int shortestPathLength() {
		return 0;
	}
}
