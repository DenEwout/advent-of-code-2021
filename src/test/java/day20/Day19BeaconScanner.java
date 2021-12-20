package day20;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day19BeaconScanner {

	private static final String INPUT_FILE = "day20trenchmap.txt";


	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			String algorithm = lines.stream().findFirst().orElseThrow();
			List<String> pixels = lines.subList(2, lines.size());
			int width = pixels.get(0).length();
			int height = pixels.size();
			List<String> breder = pixels.stream().map(line -> ".".repeat(1000) + line + ".".repeat(1000)).collect(Collectors.toList());
			List<String> newPixels = new ArrayList<>();
			String black = ".".repeat((1000 * 2) + width);
			IntStream.range(0, 1000).forEach(i -> newPixels.add(black));
			newPixels.addAll(breder);
			IntStream.range(0, 1000).forEach(i -> newPixels.add(black));


			Image image = new Image(newPixels);

			image.enhance(algorithm);
			image.enhance(algorithm);

			System.out.println(image.countLit());
		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

		}
	}


}
