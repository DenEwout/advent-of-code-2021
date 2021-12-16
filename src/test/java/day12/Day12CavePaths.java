package day12;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day12CavePaths {

	private static final String INPUT_FILE = "day12passagepathing.txt";
	private static final String START = "start";
	private static final String END = "end";


	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());


		}

		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			Map<String, Set<String>> graph = new HashMap<>();
			lines.stream()
					.map(s -> s.split("-"))
					.forEach(split -> {
						String a = split[0].trim();
						String b = split[1].trim();
						graph.putIfAbsent(a, new HashSet<>());
						graph.putIfAbsent(b, new HashSet<>());
						graph.get(a).add(b);
						graph.get(b).add(a);
					});

			ArrayList<List<String>> acc = new ArrayList<>();
			ArrayList<String> currentPath = new ArrayList<>();
			currentPath.add(START);
			dfsPaths(graph, START, END, currentPath, acc);


			System.out.println(acc.size());

		}
	}

	private void dfsPaths(Map<String, Set<String>> graph,
	                      String start,
	                      String end,
	                      List<String> currentPath,
	                      List<List<String>> paths) {
		if (start.equals(end)) {
			paths.add(currentPath);
		}
		for (String node : graph.get(start)) {
			if ((StringUtils.isAllLowerCase(node) && !currentPath.contains(node)) || StringUtils.isAllUpperCase(node)) {
				currentPath.add(node);
				dfsPaths(graph, node, end, new ArrayList<>(currentPath), paths);
				currentPath.remove(currentPath.size() - 1);
			}
		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			Map<String, Set<String>> graph = new HashMap<>();
			lines.stream()
					.map(s -> s.split("-"))
					.forEach(split -> {
						String a = split[0].trim();
						String b = split[1].trim();
						graph.putIfAbsent(a, new HashSet<>());
						graph.putIfAbsent(b, new HashSet<>());
						graph.get(a).add(b);
						graph.get(b).add(a);
					});

			ArrayList<List<String>> acc = new ArrayList<>();
			ArrayList<String> currentPath = new ArrayList<>();
			currentPath.add(START);
			dfsPaths2(graph, START, END, currentPath, acc);


			System.out.println(acc.size());
		}
	}

	private void dfsPaths2(Map<String, Set<String>> graph,
	                       String start,
	                       String end,
	                       List<String> currentPath,
	                       List<List<String>> paths) {
		if (start.equals(end)) {
			paths.add(currentPath);
		}
		for (String node : graph.get(start)) {
			if (addToPath(currentPath, node)) {
				currentPath.add(node);
				dfsPaths2(graph, node, end, new ArrayList<>(currentPath), paths);
				currentPath.remove(currentPath.size() - 1);
			}
		}
	}

	private boolean addToPath(List<String> currentPath, String node) {
		if (node.equals(START) || node.equals(END)) {
			return !currentPath.contains(node);
		}
		if (StringUtils.isAllUpperCase(node)) {
			return true;
		}
		if (StringUtils.isAllLowerCase(node)) {
			Map<String, Long> counts = currentPath.stream()
					.filter(n -> !n.equals(START))
					.filter(n -> !n.equals(END))
					.filter(StringUtils::isAllLowerCase)
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			if (counts.get(node) == null) {
				return true;
			}
			Optional<Long> max = counts.values().stream().max(Long::compareTo);
			if (max.isPresent()) {
				return max.get() < 2;
			} else {
				return true;
			}
		}

		return false;
	}


}
