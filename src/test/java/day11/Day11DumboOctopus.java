package day11;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11DumboOctopus {

	private static final String INPUT_FILE = "day11dumbooctopus.txt";
	private static final int STEPS = Integer.MAX_VALUE;

	private static final String STEP_1 =
			"6594254334\n" +
					"3856965822\n" +
					"6375667284\n" +
					"7252447257\n" +
					"7468496589\n" +
					"5278635756\n" +
					"3287952832\n" +
					"7993992245\n" +
					"5957959665\n" +
					"6394862637\n";
	private static final String STEP_2 =
			"8807476555\n" +
					"5089087054\n" +
					"8597889608\n" +
					"8485769600\n" +
					"8700908800\n" +
					"6600088989\n" +
					"6800005943\n" +
					"0000007456\n" +
					"9000000876\n" +
					"8700006848\n";
	private static final String STEP_3 =
			"0050900866\n" +
					"8500800575\n" +
					"9900000039\n" +
					"9700000041\n" +
					"9935080063\n" +
					"7712300000\n" +
					"7911250009\n" +
					"2211130000\n" +
					"0421125000\n" +
					"0021119000\n";
	private static final String STEP_4 =
			"2263031977\n" +
					"0923031697\n" +
					"0032221150\n" +
					"0041111163\n" +
					"0076191174\n" +
					"0053411122\n" +
					"0042361120\n" +
					"5532241122\n" +
					"1532247211\n" +
					"1132230211\n";
	private static final String STEP_5 =
			"4484144000\n" +
					"2044144000\n" +
					"2253333493\n" +
					"1152333274\n" +
					"1187303285\n" +
					"1164633233\n" +
					"1153472231\n" +
					"6643352233\n" +
					"2643358322\n" +
					"2243341322\n";
	private static final String STEP_6 =
			"5595255111\n" +
					"3155255222\n" +
					"3364444605\n" +
					"2263444496\n" +
					"2298414396\n" +
					"2275744344\n" +
					"2264583342\n" +
					"7754463344\n" +
					"3754469433\n" +
					"3354452433\n";
	private static final String STEP_7 =
			"6707366222\n" +
					"4377366333\n" +
					"4475555827\n" +
					"3496655709\n" +
					"3500625609\n" +
					"3509955566\n" +
					"3486694453\n" +
					"8865585555\n" +
					"4865580644\n" +
					"4465574644\n";
	private static final String STEP_8 =
			"7818477333\n" +
					"5488477444\n" +
					"5697666949\n" +
					"4608766830\n" +
					"4734946730\n" +
					"4740097688\n" +
					"6900007564\n" +
					"0000009666\n" +
					"8000004755\n" +
					"6800007755\n";
	private static final String STEP_9 =
			"9060000644\n" +
					"7800000976\n" +
					"6900000080\n" +
					"5840000082\n" +
					"5858000093\n" +
					"6962400000\n" +
					"8021250009\n" +
					"2221130009\n" +
					"9111128097\n" +
					"7911119976\n";
	private static final String STEP_10 = "0481112976\n" +
			"0031112009\n" +
			"0041112504\n" +
			"0081111406\n" +
			"0099111306\n" +
			"0093511233\n" +
			"0442361130\n" +
			"5532252350\n" +
			"0532250600\n" +
			"0032240000\n";


	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			OctopusMap octopi = new OctopusMap(lines.size(), lines.get(0).length());

			for (int y = 0; y < lines.size(); y++) {
				String line = lines.get(y);
				List<Integer> values = toCharStream(line).map(c -> Integer.parseInt(String.valueOf(c))).collect(Collectors.toList());
				for (int x = 0; x < values.size(); x++) {
					octopi.add(x, y, values.get(x));
				}
			}

			for (int i = 0; i < STEPS; i++) {
				octopi.incrementAll();
				octopi.flashStep();
				octopi.reset();
				int step = i + 1;
				System.out.println("Step: " + step);
			}

			System.out.println(octopi.getFlashes());

		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

			OctopusMap octopi = new OctopusMap(lines.size(), lines.get(0).length());

			for (int y = 0; y < lines.size(); y++) {
				String line = lines.get(y);
				List<Integer> values = toCharStream(line).map(c -> Integer.parseInt(String.valueOf(c))).collect(Collectors.toList());
				for (int x = 0; x < values.size(); x++) {
					octopi.add(x, y, values.get(x));
				}
			}

			int previous = -1;
			for (int i = 0; i < STEPS; i++) {
				octopi.incrementAll();
				octopi.flashStep();
				octopi.reset();

				int step = i + 1;
				System.out.println("Step: " + step + ", Flashes: " + (octopi.getFlashes() - previous));
				if (octopi.getFlashes() - previous == octopi.getLength() * octopi.getWidth()) {
					break;
				}
				previous = octopi.getFlashes();
			}
		}
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}

}
