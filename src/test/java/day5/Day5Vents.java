package day5;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day5Vents {

	private static final String INPUT_FILE = "day5vents.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<Line> lines = bufferedReader.lines()
					.map(String::trim)
					.map(l -> {
						String[] split = l.split("->");
						return new Line(new Point(split[0]), new Point(split[1]));
					})
					.filter(line -> line.getStart().getX() == line.getEnd().getX() || line.getStart().getY() == line.getEnd().getY())
					.collect(Collectors.toList());
			day5.Map map = new day5.Map(1000);
			for (Line line : lines) {
				map.drawLine(line);
			}
			System.out.println(map.countIntersects(2));
		}
	}

	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<Line> lines = bufferedReader.lines()
					.map(String::trim)
					.map(l -> {
						String[] split = l.split("->");
						return new Line(new Point(split[0]), new Point(split[1]));
					})
					.collect(Collectors.toList());

			day5.Map map = new day5.Map(1000);
			for (Line line : lines) {
				map.drawLine(line);
			}
			System.out.println(map.countIntersects(2));
		}
	}

}
