package day4;

import static day4.Board.Builder.aBoard;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4SquidBingoTest {

	private static final String INPUT_FILE = "day4squidbingo.txt";

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			List<Integer> bingoNumbers = Stream.of(lines.get(0).split(","))
					.map(Integer::parseInt).collect(Collectors.toList());

			List<String> boardsInput = lines.subList(1, lines.size());
			List<Board> boards = buildBoard(boardsInput);

			Map.Entry<Integer, Board> winningEntry = getWinningBoard(bingoNumbers, boards);

			assertThat(winningEntry.getValue().sumUnmarked() * winningEntry.getKey()).isEqualTo(22680);

		}
	}

	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			List<Integer> bingoNumbers = Stream.of(lines.get(0).split(","))
					.map(Integer::parseInt).collect(Collectors.toList());

			List<String> boardsInput = lines.subList(1, lines.size());
			List<Board> boards = buildBoard(boardsInput);

			Map.Entry<Integer, Board> winningEntry = getLastWinningBoard(bingoNumbers, boards);

			assertThat(winningEntry.getValue().sumUnmarked() * winningEntry.getKey()).isEqualTo(1924);

		}
	}

	private Map.Entry<Integer, Board> getLastWinningBoard(List<Integer> bingoNumbers, List<Board> boards) {
		List<Board> wonBoards = new ArrayList<>();
		List<Map.Entry<Integer, Board>> won = new ArrayList<>();
		for (Integer bingoNumber : bingoNumbers) {
			for (Board board : boards) {
				if (!wonBoards.contains(board)) {
					boolean marked = board.markNumber(bingoNumber);
					if (board.checkWin().length > 0) {
						wonBoards.add(board);
						won.add(Map.entry(bingoNumber, board));
					}
				}
			}
		}
		Collections.reverse(won);
		return won.stream().findFirst().orElseThrow();
	}

	private Map.Entry<Integer, Board> getWinningBoard(List<Integer> bingoNumbers, List<Board> boards) {
		for (Integer bingoNumber : bingoNumbers) {
			for (Board board : boards) {
				boolean marked = board.markNumber(bingoNumber);
				if (board.checkWin().length > 0) {
					return Map.entry(bingoNumber, board);
				}
			}
		}
		throw new IllegalStateException("No winners");
	}

	private List<Board> buildBoard(List<String> boardsInput) {
		List<Board> boards = new ArrayList<>();
		Board.Builder boardBuilder = aBoard();
		for (String line : boardsInput) {
			if (line.isEmpty()) {
				if (boardBuilder.getCurrentIndex() == 5) {
					boards.add(boardBuilder.build());
				}
				boardBuilder = aBoard();
			} else {
				boardBuilder.addLine(line);
			}
		}
		boards.add(boardBuilder.build());
		return boards;
	}
}
