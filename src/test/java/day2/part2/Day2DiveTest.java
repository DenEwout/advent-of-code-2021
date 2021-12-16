package day2.part2;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day2DiveTest {

	private static final String INPUT_FILE = "day2dive.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			Position result = bufferedReader.lines()
					.map(CourseInput::new)
					.map(CourseInput::toPosition)
					.reduce(new Position(), Position::add);

			System.out.println(result.getHorizontal() * result.getDepth());
		}
	}

}
