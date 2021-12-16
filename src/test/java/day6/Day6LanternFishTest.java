package day6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6LanternFishTest {

	public static int DAYS = 80;
	public static int PART_TWO = 256;

	public static List<Integer> TEST = Arrays.asList(3, 4, 3, 1, 2);
	public static List<Integer> INITIAL = Arrays.asList(3, 1, 4, 2, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 2, 1, 1, 2, 1, 3, 4, 5, 1, 1, 4, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 3, 3, 1, 5, 5, 1, 1, 3, 1, 1, 2, 1, 1, 1, 3, 1, 4, 3, 2, 1, 4, 3, 3, 1, 1, 1, 1, 5, 1, 4, 1, 1, 1, 4, 1, 4, 4, 1, 5, 1, 1, 4, 5, 1, 1, 2, 1, 1, 1, 4, 1, 2, 1, 1, 1, 1, 1, 1, 5, 1, 3, 1, 1, 4, 4, 1, 1, 5, 1, 2, 1, 1, 1, 1, 5, 1, 3, 1, 1, 1, 2, 2, 1, 4, 1, 3, 1, 4, 1, 2, 1, 1, 1, 1, 1, 3, 2, 5, 4, 4, 1, 3, 2, 1, 4, 1, 3, 1, 1, 1, 2, 1, 1, 5, 1, 2, 1, 1, 1, 2, 1, 4, 3, 1, 1, 1, 4, 1, 1, 1, 1, 1, 2, 2, 1, 1, 5, 1, 1, 3, 1, 2, 5, 5, 1, 4, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 4, 4, 1, 1, 4, 1, 3, 4, 1, 5, 4, 2, 5, 1, 2, 1, 1, 1, 1, 1, 1, 4, 3, 2, 1, 1, 3, 2, 5, 2, 5, 5, 1, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 4, 1, 4, 2, 1, 3, 4, 1, 1, 1, 2, 3, 1, 1, 1, 4, 1, 2, 5, 1, 2, 1, 5, 1, 1, 2, 1, 2, 1, 1, 1, 1, 4, 3, 4, 1, 5, 5, 4, 1, 1, 5, 2, 1, 3);

	@Test
	void partOne() {
		long[] counts = new long[]{
				INITIAL.stream().filter(i -> i == 0).count(),
				INITIAL.stream().filter(i -> i == 1).count(),
				INITIAL.stream().filter(i -> i == 2).count(),
				INITIAL.stream().filter(i -> i == 3).count(),
				INITIAL.stream().filter(i -> i == 4).count(),
				INITIAL.stream().filter(i -> i == 5).count(),
				INITIAL.stream().filter(i -> i == 6).count(),
				INITIAL.stream().filter(i -> i == 7).count(),
				INITIAL.stream().filter(i -> i == 8).count()
		};
		System.out.println(simulatePerformant(counts, DAYS));
	}

	@Test
	void partTwo() {
		long[] counts = new long[]{
				INITIAL.stream().filter(i -> i == 0).count(),
				INITIAL.stream().filter(i -> i == 1).count(),
				INITIAL.stream().filter(i -> i == 2).count(),
				INITIAL.stream().filter(i -> i == 3).count(),
				INITIAL.stream().filter(i -> i == 4).count(),
				INITIAL.stream().filter(i -> i == 5).count(),
				INITIAL.stream().filter(i -> i == 6).count(),
				INITIAL.stream().filter(i -> i == 7).count(),
				INITIAL.stream().filter(i -> i == 8).count()
		};
		System.out.println(simulatePerformant(counts, PART_TWO));
	}

	public long simulatePerformant(long[] counts, int days) {
		if (days == 0) {
			long sum = 0;
			for (long count : counts) {
				sum = sum + count;
			}
			return sum;
		}
		long[] newCounts = new long[9];
		long zeroes = counts[0];
		for (int i = 1; i < counts.length; i++) {
			newCounts[i - 1] = counts[i];
		}
		newCounts[6] = newCounts[6] + zeroes;
		newCounts[8] = newCounts[8] + zeroes;
		return simulatePerformant(newCounts, days - 1);
	}

	public int simulateNaive(List<Integer> list, int days) {
		System.out.println(days);
		if (days == 0) {
			return list.size();
		}
		List<Integer> newList = new ArrayList<>();
		List<Integer> newFishes = new ArrayList<>();
		for (int fish : list) {
			int newTime = -1;
			if (fish == 0) {
				newTime = 6;
				newFishes.add(8);
			} else {
				newTime = fish - 1;
			}
			newList.add(newTime);
		}
		newList.addAll(newFishes);
		return simulateNaive(newList, days - 1);
	}
}
