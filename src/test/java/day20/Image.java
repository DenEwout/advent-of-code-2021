package day20;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Image {

	private String[][] pixels;
	private final int width;
	private final int height;

	public Image(List<String> pixelLines) {
		this.width = pixelLines.get(0).length();
		this.height = pixelLines.size();
		this.pixels = new String[pixelLines.size()][pixelLines.get(0).length()];
		for (int i = 0; i < pixelLines.size(); i++) {
			char[] pixelLine = pixelLines.get(i).toCharArray();
			for (int j = 0; j < pixelLine.length; j++) {
				pixels[j][i] = String.valueOf(pixelLine[j]);
			}
		}
	}

	public void add(int x, int y, String value) {
		pixels[y][x] = value;
	}

	public String get(int x, int y) {
		try {
			return pixels[y][x];
		} catch (ArrayIndexOutOfBoundsException outofbounds) {
			return ".";
		}
	}

	public int neighboursIndex(int x, int y) {
		String collect = Stream.of(
				get(x - 1, y + 1),
				get(x, y + 1),
				get(x + 1, y + 1),
				get(x - 1, y),
				get(x, y),
				get(x + 1, y),
				get(x - 1, y - 1),
				get(x, y - 1),
				get(x + 1, y - 1)
		).collect(Collectors.joining());
		String binaryString = collect.replace(".", "0")
				.replace("#", "1");
		return Integer.parseInt(binaryString, 2);
	}

	public void enhance(String algorithm) {
		String[][] enhanced = new String[width][height];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				enhanced[y][x] = String.valueOf(algorithm.toCharArray()[neighboursIndex(x, y)]);
			}
		}
		this.pixels = enhanced;
	}

	public int countLit() {
		int count = 0;
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				if ("#".equals(pixels[y][x])) {
					count++;
				}

			}
		}
		return count;
	}
}
