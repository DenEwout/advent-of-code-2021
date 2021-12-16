package day8;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8SevenSegment {

	private static final String INPUT_FILE = "day8sevensegment.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			long count = bufferedReader.lines()
					.map(line -> line.split("\\|")[1])
					.flatMap(output -> Arrays.stream(output.split(" ")))
					.map(String::trim)
					.filter(Predicate.not(String::isEmpty))
					.map(String::length)
					.filter(length -> Set.of(2, 3, 4, 7).contains(length))
					.count();

			System.out.println(count);
		}
	}

	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			Map<String, Set<Integer>> segmentOptions = Map.of(
					"a", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
					"b", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
					"c", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
					"d", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
					"e", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
					"f", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
					"g", Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
			);
			Map<Integer, Integer> lengthDict = Map.of(
					2, 1,
					3, 7,
					4, 4,
					7, 8
			);

			long sum = bufferedReader.lines()
					.map(line -> line.split("\\|"))
					.map(arr -> this.decodeSignal(this.deductCode(arr[0]), arr[1]))
					.reduce(0, Integer::sum);
			System.out.println(sum);

		}
	}

	private List<String> cleanUp(String input) {
		return Arrays.stream(input.split(" "))
				.map(String::trim)
				.filter(Predicate.not(String::isEmpty))
				.collect(Collectors.toList());
	}

	private int decodeSignal(Map<String, Set<Character>> deductCode, String input) {
		List<String> strings = cleanUp(input);
		String collect = strings.stream()
				.map(str -> deductCode.entrySet().stream()
						.filter(entry -> entry.getValue().size() == str.length())
						.filter(entry -> entry.getValue().stream().allMatch(c -> str.contains(String.valueOf(c))))
						.map(Entry::getKey)
						.findFirst().orElseThrow()
				).collect(Collectors.joining());
		return Integer.parseInt(collect);
	}

	private Map<String, Set<Character>> deductCode(String input) {
		Map<String, Set<Character>> segmentOptions = new TreeMap<>(Map.of(
				"0", new HashSet<>(),
				"1", new HashSet<>(),
				"2", new HashSet<>(),
				"3", new HashSet<>(),
				"4", new HashSet<>(),
				"5", new HashSet<>(),
				"6", new HashSet<>(),
				"7", new HashSet<>(),
				"8", new HashSet<>(),
				"9", new HashSet<>()
		));

		List<String> cleanInput = cleanUp(input);
		cleanInput.sort(Comparator.comparingInt(String::length));


		for (String str : cleanInput) {
			switch (str.length()) {
				case 2 -> toCharStream(str)
						.forEach(c -> Stream.of("0", "1", "3", "4", "7", "8", "9")
								.forEach(number -> segmentOptions.get(number).add(c)));
				case 3 -> toCharStream(str)
						.filter(c -> !segmentOptions.get("7").contains(c))
						.forEach(c -> Stream.of("0", "2", "3", "5", "6", "7", "8", "9")
								.forEach(number -> segmentOptions.get(number).add(c)));
				case 4 -> toCharStream(str)
						.filter(c -> !segmentOptions.get("4").contains(c))
						.forEach(c -> Stream.of("4", "5", "6", "8", "9")
								.forEach(number -> segmentOptions.get(number).add(c)));
				case 7 -> toCharStream(str)
						.filter(c -> !segmentOptions.get("8").contains(c))
						.forEach(c -> Stream.of("0", "8", "2", "6")
								.forEach(number -> segmentOptions.get(number).add(c)));
			}
		}

		Character middle = getMiddle(segmentOptions, cleanInput);
		segmentOptions.get("8").stream()
				.filter(c -> !c.equals(middle))
				.forEach(c -> segmentOptions.get("0").add(c));
		segmentOptions.get("2").add(middle);
		segmentOptions.get("3").add(middle);

		HashSet<Character> topRightCandidates = new HashSet<>(segmentOptions.get("1"));
		topRightCandidates.removeAll(segmentOptions.get("2"));

		Character bottom = getBottom(segmentOptions, cleanInput, middle);
		segmentOptions.get("3").add(bottom);
		segmentOptions.get("5").add(bottom);
		segmentOptions.get("9").add(bottom);

		Optional<String> six = cleanInput.stream().filter(str -> str.length() == 6)
				.filter(str -> !segmentOptions.get("0").stream().allMatch(c -> str.contains(String.valueOf(c))))
				.filter(str -> !segmentOptions.get("9").stream().allMatch(c -> str.contains(String.valueOf(c))))
				.findFirst();
		Character missing = 'z';
		if (six.isPresent()) {
			for (Character c : six.get().toCharArray()) {
				if (segmentOptions.get("6").add(c)) {
					missing = c;
				}
			}
		}
		segmentOptions.get("5").add(missing);

		cleanInput.stream().filter(str -> str.length() == 5)
				.filter(str -> !segmentOptions.get("3").stream().allMatch(c -> str.contains(String.valueOf(c))))
				.filter(str -> !segmentOptions.get("5").stream().allMatch(c -> str.contains(String.valueOf(c))))
				.findFirst().ifPresent(str -> toCharStream(str).forEach(c -> segmentOptions.get("2").add(c)));

		return segmentOptions;
	}

	private Character getBottom(Map<String, Set<Character>> segmentOptions, List<String> cleanInput, Character middle) {
		Set<Character> bottomCandidatesFromLength5 = getMax(cleanInput.stream().filter(str -> str.length() == 5).flatMap(this::toCharStream)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		bottomCandidatesFromLength5.remove(middle);
		bottomCandidatesFromLength5.removeAll(segmentOptions.get("7"));
		return bottomCandidatesFromLength5.stream().findFirst().orElseThrow();
	}

	private Character getMiddle(Map<String, Set<Character>> segmentOptions, List<String> cleanInput) {
		Map<Character, Long> histogram = getHistogram(segmentOptions, Set.of("4", "5", "6", "8", "9"));
		Set<Character> middleCandidatesFrom4 = getMax(histogram);
		Stream.of("1", "2").forEach(num -> middleCandidatesFrom4.removeAll(segmentOptions.get(num)));

		Set<Character> middleCandidatesFromLength5 = getMax(cleanInput.stream().filter(str -> str.length() == 5).flatMap(this::toCharStream)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		Stream.of("1", "2", "3").forEach(num -> middleCandidatesFromLength5.removeAll(segmentOptions.get("1")));

		List<Character> middleCandidates = new ArrayList<>();
		middleCandidates.addAll(middleCandidatesFrom4);
		middleCandidates.addAll(middleCandidatesFromLength5);
		Set<Character> middleMax = getMax(middleCandidates.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		return middleMax.stream().findFirst().orElseThrow();
	}

	private Set<Character> getMax(Map<Character, Long> histogram) {
		TreeMap<Long, Set<Character>> inverted = new TreeMap<>();
		histogram.values().forEach(count -> inverted.put(count, new HashSet<>()));
		histogram.entrySet().stream().forEach(entry -> inverted.get(entry.getValue()).add(entry.getKey()));
		return inverted.get(inverted.lastKey());
	}

	private Map<Character, Long> getHistogram(Map<String, Set<Character>> segmentOptions, Set<String> commonMiddle) {
		return segmentOptions.entrySet().stream()
				.filter(entry -> commonMiddle.contains(entry.getKey()))
				.flatMap(entry -> entry.getValue().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}

}
