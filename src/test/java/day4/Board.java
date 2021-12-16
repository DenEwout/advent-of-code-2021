package day4;

import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

	private static final int SIZE = 5;

	private final UUID id = UUID.randomUUID();

	private final int[][] numbers;
	private final int[][] marks;

	public Board(int[][] numbers, int[][] marks) {
		this.numbers = numbers;
		this.marks = marks;
	}

	public boolean markNumber(int number) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (numbers[i][j] == number) {
					marks[i][j] = 1;
					return true;
				}
			}
		}
		return false;
	}

	public int[] checkWin() {
		for (int i = 0; i < SIZE; i++) {
			if (IntStream.of(marks[i]).sum() == SIZE) {
				return numbers[i];
			}
		}
		int[][] transpose = transpose(marks);
		for (int i = 0; i < SIZE; i++) {
			if (IntStream.of(transpose[i]).sum() == SIZE) {
				return transpose[i];
			}
		}
		return new int[]{};
	}

	public int sumUnmarked() {
		int sum = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (marks[i][j] != 1) {
					sum += numbers[i][j];
				}
			}
		}
		return sum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Board)) return false;

		Board board = (Board) o;

		return id.equals(board.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public static int[][] transpose(int[][] matrix) {
		int[][] transposed = new int[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				transposed[i][j] = matrix[j][i];
			}
		}

		return transposed;
	}

	public static final class Builder {
		private int currentIndex = 0;
		private int[][] numbers = new int[SIZE][SIZE];
		private int[][] marks = new int[SIZE][SIZE];

		private Builder() {
		}

		public static Builder aBoard() {
			return new Builder();
		}

		public Builder addLine(String input) {
			String[] split = Stream.of(input.split(" ")).filter(Predicate.not(String::isEmpty)).collect(Collectors.toList()).toArray(new String[]{});
			IntStream.range(0, split.length)
					.forEach(index -> this.numbers[this.currentIndex][index] = Integer.parseInt(split[index]));
			this.currentIndex++;
			return this;
		}

		public int getCurrentIndex() {
			return currentIndex;
		}

		public Board build() {
			return new Board(this.numbers, this.marks);
		}
	}
}
