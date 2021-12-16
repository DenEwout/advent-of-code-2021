package day1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day1SonarSweepTest {

	private static final String INPUT_FILE = "day1sonarsweep.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<String> collect = bufferedReader.lines().collect(Collectors.toList());
			int previousValue = -1;
			int counter = 0;
			for (String line : collect) {
				int currentValue = Integer.parseInt(line);
				if (previousValue > -1) {
					if (currentValue > previousValue) {
						counter++;
					}
				}
				previousValue = currentValue;
			}
			assertThat(counter).isEqualTo(1162);
		}
	}

	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<Integer> collect = bufferedReader.lines().map(Integer::parseInt).collect(Collectors.toList());

			int lastSum = -1;
			int counter = 0;
			for (int i = 0; i < collect.size(); i++) {
				if (i > 2) {
					int currentSum = collect.get(i) + collect.get(i - 1) + collect.get(i - 2);
					if (lastSum > -1) {
						if (currentSum > lastSum){
							counter++;
						}
					}
					lastSum = currentSum;
				}
			}
			assertThat(counter).isEqualTo(1190);
		}
	}
}
