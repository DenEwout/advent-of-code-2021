package day14;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day14ExtendedPolymerization {

	private static final String INPUT_FILE = "day14extendedpolymerization.txt";

	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			String template = lines.stream().filter(line -> !line.contains("->"))
					.filter(str -> !str.isBlank())
					.findFirst()
					.orElseThrow();

			Map<String, String> rules = lines.stream().filter(line -> line.contains(" -> "))
					.filter(str -> !str.isBlank())
					.map(str -> str.split(" -> "))
					.collect(Collectors.toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));


			Map<String, Long> pairHistogram = new HashMap<>();
			for (int i = 1; i < template.length(); i++) {
				String match = String.valueOf(template.charAt(i - 1)) + String.valueOf(template.charAt(i));
				pairHistogram.merge(match, 1L, Long::sum);
			}
			Map<String, Long> charHistogram = toCharStream(template)
					.collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

			for (int i = 0; i < 40; i++) {
				pairHistogram = iterateHistogram(pairHistogram, charHistogram, rules);
			}

			Map.Entry<String, Long> maxEntry = null;
			for (Map.Entry<String, Long> entry : charHistogram.entrySet()) {
				if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
					maxEntry = entry;
				}
			}
			Map.Entry<String, Long> minEntry = null;
			for (Map.Entry<String, Long> entry : charHistogram.entrySet()) {
				if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
					minEntry = entry;
				}
			}
			System.out.println(maxEntry.getValue() - (minEntry.getValue()));

		}
	}

	private Map<String, Long> iterateHistogram(Map<String, Long> template, Map<String, Long> charHistogram, Map<String, String> rules) {
		HashMap<String, Long> nextTemplate = new HashMap<>();

		for (Map.Entry<String, Long> entry : template.entrySet()) {
			String pair = entry.getKey();
			Long count = entry.getValue();
			String inBetween = rules.get(pair);

			if (inBetween != null) {
				String pairA = String.valueOf(pair.toCharArray()[0]) + inBetween;
				String pairB = inBetween + String.valueOf(pair.toCharArray()[1]);

				charHistogram.merge(inBetween, count, Long::sum);

				nextTemplate.merge(pairA, count, Long::sum);
				nextTemplate.merge(pairB, count, Long::sum);
			} else {
				nextTemplate.put(pair, count);
			}
		}
		return nextTemplate;
	}


	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			String template = lines.stream().filter(line -> !line.contains("->"))
					.filter(str -> !str.isBlank())
					.findFirst()
					.orElseThrow();

			Map<String, String> rules = lines.stream().filter(line -> line.contains(" -> "))
					.filter(str -> !str.isBlank())
					.map(str -> str.split(" -> "))
					.collect(Collectors.toMap(arr -> arr[0].trim(), arr -> arr[1].trim()));

			String nextTemplate = template;
			for (int i = 0; i < 10; i++) {
				nextTemplate = iterate(nextTemplate, rules);
				System.out.println(nextTemplate);
			}

			Map<Character, Long> collect = toCharStream(nextTemplate).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			Map.Entry<Character, Long> maxEntry = null;
			for (Map.Entry<Character, Long> entry : collect.entrySet()) {
				if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
					maxEntry = entry;
				}
			}
			Map.Entry<Character, Long> minEntry = null;
			for (Map.Entry<Character, Long> entry : collect.entrySet()) {
				if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
					minEntry = entry;
				}
			}
			System.out.println(maxEntry.getValue() - minEntry.getValue());


		}
	}

	private String iterate(String template, Map<String, String> rules) {
		StringBuilder nextTemplate = new StringBuilder();
		for (int i = 1; i < template.length(); i++) {
			String match = String.valueOf(template.charAt(i - 1)) + String.valueOf(template.charAt(i));
			String inBetween = rules.get(match);
			if (i <= 1) {
				nextTemplate.append(String.valueOf(template.charAt(i - 1)));
			}
			if (inBetween != null) {
				nextTemplate.append(inBetween);
			}
			nextTemplate.append(String.valueOf(template.charAt(i)));
		}
		return nextTemplate.toString();
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}


}
