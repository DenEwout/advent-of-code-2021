package day19;

import day16.PacketFactory;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Day19BeaconScanner {

	private static final String INPUT_FILE = "day19beaconscannertest.txt";


	/**
	 * xyz
	 * xzy
	 * yxz
	 * zxy
	 * yzx
	 * zyx
	 * <p>
	 * 1, 1, 1
	 * -1, 1, 1
	 * 1,-1, 1
	 * -1,-1, 1
	 *
	 * @throws Exception
	 */
	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			PacketFactory packetFactory = new PacketFactory();
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());
			List<Scanner> scanners = getScanners(lines);

			Scanner reference = scanners.stream().findFirst().orElseThrow();
			Entry<Beacon, Set<Beacon>> beaconSetEntry = reference.commonBeacons(scanners.get(1));

			System.out.println(scanners.size());
		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			PacketFactory packetFactory = new PacketFactory();
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());

		}
	}

	private List<Scanner> getScanners(List<String> lines) {
		List<Scanner> scanners = new ArrayList<>();

		Scanner scanner = new Scanner();
		String scannerName = null;
		for (String line : lines) {
			if (!line.isBlank()) {
				if (line.contains("--- scanner")) {
					if (scannerName == null) {
						scannerName = line;
					} else {
						scanners.add(scanner);
						scanner = new Scanner();
						scannerName = line;
					}
				} else {
					scanner.add(new Beacon(line));
				}
			}
		}
		return scanners;
	}


}
