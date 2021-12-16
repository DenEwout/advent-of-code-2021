package day13;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13TransparantOrigami {

	private static final String INPUT_FILE = "day13transparantorigami.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			Set<Point> dots = lines.stream().filter(line -> !line.startsWith("fold"))
					.filter(str -> !str.isBlank())
					.map(Point::new)
					.collect(Collectors.toSet());

			List<Fold> folds = lines.stream().filter(line -> line.startsWith("fold along"))
					.filter(str -> !str.isBlank())
					.map(Fold::new)
					.limit(1)
					.collect(Collectors.toList());

			for (Fold fold : folds) {
				Set<Point> toRemove = new HashSet<>();
				Set<Point> toAdd = new HashSet<>();
				if (fold.getAxis().equals(Axis.VERTICAL)) {
					for (Point dot : dots) {
						if (dot.getX() > fold.getPoint().getX()) {
							toRemove.add(dot);
							toAdd.add(new Point(dot.getX() - ((dot.getX() - fold.getPoint().getX()) * 2), dot.getY()));
						}
						if (dot.getX() == fold.getPoint().getX()) {
							toRemove.add(dot);
						}
					}
				}
				if (fold.getAxis().equals(Axis.HORIZONTAL)) {
					for (Point dot : dots) {
						if (dot.getY() > fold.getPoint().getY()) {
							toRemove.add(dot);
							toAdd.add(new Point(dot.getX(), dot.getY() - ((dot.getY() - fold.getPoint().getY()) * 2)));
						}
						if (dot.getY() == fold.getPoint().getY()) {
							toRemove.add(dot);
						}
					}
				}
				dots.removeAll(toRemove);
				dots.addAll(toAdd);
				// print(dots);
			}
			System.out.println(dots.size());

		}
	}

	private void print(Set<Point> dots) {
		String[][] origami = new String[1000][1000];
		for (int y = 0; y < origami.length; y++) {
			for (int x = 0; x < origami.length; x++) {
				origami[y][x] = ".";
			}
		}
		for (Point dot : dots) {
			origami[dot.getY()][dot.getX()] = "#";
		}
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < origami.length; y++) {
			for (int x = 0; x < origami.length; x++) {
				sb.append(origami[y][x]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			Set<Point> dots = lines.stream().filter(line -> !line.startsWith("fold"))
					.filter(str -> !str.isBlank())
					.map(Point::new)
					.collect(Collectors.toSet());

			List<Fold> folds = lines.stream().filter(line -> line.startsWith("fold along"))
					.filter(str -> !str.isBlank())
					.map(Fold::new)
					.collect(Collectors.toList());

			for (Fold fold : folds) {
				Set<Point> toRemove = new HashSet<>();
				Set<Point> toAdd = new HashSet<>();
				if (fold.getAxis().equals(Axis.VERTICAL)) {
					for (Point dot : dots) {
						if (dot.getX() > fold.getPoint().getX()) {
							toRemove.add(dot);
							toAdd.add(new Point(dot.getX() - ((dot.getX() - fold.getPoint().getX()) * 2), dot.getY()));
						}
						if (dot.getX() == fold.getPoint().getX()) {
							toRemove.add(dot);
						}
					}
				}
				if (fold.getAxis().equals(Axis.HORIZONTAL)) {
					for (Point dot : dots) {
						if (dot.getY() > fold.getPoint().getY()) {
							toRemove.add(dot);
							toAdd.add(new Point(dot.getX(), dot.getY() - ((dot.getY() - fold.getPoint().getY()) * 2)));
						}
						if (dot.getY() == fold.getPoint().getY()) {
							toRemove.add(dot);
						}
					}
				}
				dots.removeAll(toRemove);
				dots.addAll(toAdd);
			}
			print(dots);
			System.out.println(dots.size());

		}
	}


}
