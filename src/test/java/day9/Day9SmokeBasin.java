package day9;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9SmokeBasin {

	private static final String INPUT_FILE = "day9smokebasin.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			SmokeMap smokeMap = new SmokeMap(lines.size(), lines.get(0).length());

			for (int y = 0; y < lines.size(); y++) {
				String line = lines.get(y);
				List<Integer> values = toCharStream(line).map(c -> Integer.parseInt(String.valueOf(c))).collect(Collectors.toList());
				for (int x = 0; x < values.size(); x++) {
					smokeMap.setPoint(x, y, values.get(x));
				}
			}

			List<Integer> lowPoints = smokeMap.getLowPointsScore();

			System.out.println(lowPoints.stream().reduce(0, Integer::sum));


		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			SmokeMap smokeMap = new SmokeMap(lines.size(), lines.get(0).length());

			for (int y = 0; y < lines.size(); y++) {
				String line = lines.get(y);
				List<Integer> values = toCharStream(line).map(c -> Integer.parseInt(String.valueOf(c))).collect(Collectors.toList());
				for (int x = 0; x < values.size(); x++) {
					smokeMap.setPoint(x, y, values.get(x));
				}
			}

			List<Set<Point>> basins = smokeMap.findBasins();
			basins.sort(Comparator.comparingInt(Set::size));
			Collections.reverse(basins);

			Integer reduce = basins.stream().limit(3).map(Set::size).reduce(1, (a, b) -> a * b);

			System.out.println(reduce);
		}
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}

}
