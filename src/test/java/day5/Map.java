package day5;

import java.util.Collection;

public class Map {

	private final int size;
	private final int[][] map;

	public Map(int size) {
		this.size = size;
		this.map = new int[size][size];
	}

	public void drawLine(Line line) {
		for (Point point : line.getPoints()) {
			map[point.getY()][point.getX()]++;
		}
	}

	public int countIntersects(int threshold) {
		int count = 0;
		for (int x = 0; x < this.size; x++) {
			for (int y = 0; y < this.size; y++) {
				if (map[x][y] >= threshold) {
					count++;
				}
			}
		}
		return count;
	}
}
