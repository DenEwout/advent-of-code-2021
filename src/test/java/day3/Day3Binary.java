package day3;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Day3Binary {

	private static final String INPUT_FILE = "day3binary.txt";
	private static final String INPUT_FILE_TEST = "day3binarytest.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<String> lines = bufferedReader.lines().collect(Collectors.toList());
			Map<Integer, int[]> map = new HashMap<>();
			for (String line : lines) {
				char[] chars = line.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					map.computeIfAbsent(i, k -> new int[2]);
					if (chars[i] == '1') {
						int[] ints = map.get(i);
						map.put(i, new int[]{ints[0] + 1, ints[1]});
					} else if (chars[i] == '0') {
						int[] ints = map.get(i);
						map.put(i, new int[]{ints[0], ints[1] + 1});
					}
				}
			}

			String maxString = map.entrySet().stream()
					.map(entry -> entry.getValue()[0] >= entry.getValue()[1] ? "1" : "0")
					.collect(Collectors.joining());
			Integer max = Integer.valueOf(maxString, 2);

			String minString = map.entrySet().stream()
					.map(entry -> entry.getValue()[0] <= entry.getValue()[1] ? "1" : "0")
					.collect(Collectors.joining());
			System.out.println(minString);
			Integer min = Integer.valueOf(minString, 2);

			System.out.println(max * min);
		}
	}

	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<String> lines = bufferedReader.lines().collect(Collectors.toList());
			String first = lines.stream().findFirst().orElseThrow();
			String oxygen = findOxygen(lines, first);
			String co2 = findCo2(lines, first);

			System.out.println(Integer.valueOf(oxygen.toString(), 2) * Integer.valueOf(co2.toString(), 2));
		}
	}

	private String findOxygen(List<String> lines, String first) {
		List<String> oxygenCopy = new ArrayList<>(lines);
		for (int i = 0; i < first.length(); i++) {
			if (oxygenCopy.size() == 1) {
				return oxygenCopy.stream().findFirst().orElseThrow();
			}
			Entry<String, List<String>> stringListEntry = parseIndexMax(i, oxygenCopy);
			oxygenCopy = stringListEntry.getValue();
		}
		if (oxygenCopy.size() == 1) {
			return oxygenCopy.stream().findFirst().orElseThrow();
		}
		throw new IllegalStateException();
	}

	private String findCo2(List<String> lines, String first) {
		List<String> findCo2 = new ArrayList<>(lines);
		for (int i = 0; i < first.length(); i++) {
			if (findCo2.size() == 1) {
				return findCo2.stream().findFirst().orElseThrow();
			}
			Entry<String, List<String>> stringListEntry = parseIndexMin(i, findCo2);
			findCo2 = stringListEntry.getValue();
		}
		if (findCo2.size() == 1) {
			return findCo2.stream().findFirst().orElseThrow();
		}
		throw new IllegalStateException();
	}

	private Map.Entry<String, List<String>> parseIndexMax(int i, List<String> lines) {
		int count1 = 0;
		int count0 = 0;
		for (String line : lines) {
			char c = line.toCharArray()[i];
			if (c == '1') {
				count1++;
			} else if (c == '0') {
				count0++;
			}
		}
		char key = count1 >= count0 ? '1' : '0';
		List<String> newLines = lines.stream()
				.filter(line -> line.toCharArray()[i] == key)
				.toList();
		return Map.entry(String.valueOf(key), newLines);
	}

	private Map.Entry<String, List<String>> parseIndexMin(int i, List<String> lines) {
		int count1 = 0;
		int count0 = 0;
		for (String line : lines) {
			char c = line.toCharArray()[i];
			if (c == '1') {
				count1++;
			} else if (c == '0') {
				count0++;
			}
		}
		char key = count1 < count0 ? '1' : '0';
		List<String> newLines = lines.stream()
				.filter(line -> line.toCharArray()[i] == key)
				.toList();
		return Map.entry(String.valueOf(key), newLines);
	}
}
