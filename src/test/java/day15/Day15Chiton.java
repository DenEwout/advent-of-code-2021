package day15;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day15Chiton {

	private static final String INPUT_FILE = "day15chiton.txt";
	private static final int STEPS = Integer.MAX_VALUE;


	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			CaveMap caveMap = new CaveMap(lines.size(), lines.get(0).length());
			caveMap.initialize(lines);

			System.out.println(caveMap.risk(caveMap.dijkstra()));
		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			CaveMap original = new CaveMap(lines.size(), lines.get(0).length());
			original.initialize(lines);


			CaveMap big = new CaveMap(original.getLength() * 5, original.getWidth() * 5);
			for (int y = 0; y < original.getLength(); y++) {
				for (int x = 0; x < original.getWidth(); x++) {
					int originalValue = original.get(x, y).orElseThrow();
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							int afterAdd = originalValue + (i + j);
							big.add(x + (j * original.getWidth()),
									y + (i * original.getLength()),
									afterAdd > 9 ? afterAdd - 9 : afterAdd
							);
						}
					}
				}
			}


			System.out.println(big.risk(big.dijkstra()));
		}
	}


}
