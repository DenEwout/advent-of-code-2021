package day10;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day10Syntax {

	public Map<Character, Character> OPEN_CLOSE = Map.of(
			'(', ')',
			'[', ']',
			'{', '}',
			'<', '>');

	public Map<Character, Character> CLOSE_OPEN = Map.of(
			')', '(',
			']', '[',
			'}', '{',
			'>', '<');

	public Map<Character, Integer> CLOSE_SCORE = Map.of(
			')', 3,
			']', 57,
			'}', 1197,
			'>', 25137);

	public Map<Character, Integer> COMPLETE_SCORE = Map.of(
			'(', 1,
			'[', 2,
			'{', 3,
			'<', 4);

	private static final String INPUT_FILE = "day10syntax.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			Integer score = lines.stream()
					.map(this::validationError)
					.flatMap(Optional::stream)
					.map(CLOSE_SCORE::get)
					.reduce(0, Integer::sum);

			System.out.println(score);
		}
	}


	public Optional<Character> validationError(String input) {
		Stack<Character> opens = new Stack<>();
		for (char character : input.toCharArray()) {
			if (OPEN_CLOSE.containsKey(character)) {
				opens.add(character);
			} else {
				if (opens.peek().equals(CLOSE_OPEN.get(character))) {
					opens.pop();
				} else {
					return Optional.of(character);
				}
			}
		}
		return Optional.empty();
	}

	private Optional<BigInteger> complete(String input) {
		Stack<Character> opens = new Stack<>();
		for (char character : input.toCharArray()) {
			if (OPEN_CLOSE.containsKey(character)) {
				opens.add(character);
			} else {
				if (opens.peek().equals(CLOSE_OPEN.get(character))) {
					opens.pop();
				}
			}
		}
		if (opens.empty()) {
			return Optional.empty();
		}
		BigInteger acc = BigInteger.ZERO;
		while (!opens.empty()) {
			acc = acc.multiply(BigInteger.valueOf(5)).add(BigInteger.valueOf(COMPLETE_SCORE.get(opens.pop())));
		}
		return Optional.of(acc);
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			List<BigInteger> autocompletescores = lines.stream()
					.filter(line -> validationError(line).isEmpty())
					.map(this::complete)
					.flatMap(Optional::stream)
					.sorted()
					.collect(Collectors.toList());

			System.out.println(autocompletescores.get((autocompletescores.size() / 2)));

		}
	}

}
