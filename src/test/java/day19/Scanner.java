package day19;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Scanner {

	private final Set<Beacon> beacons = new HashSet<>();

	public Scanner() {
	}

	public boolean add(Beacon beacon) {
		return beacons.add(beacon);
	}


	public Map.Entry<Beacon, Set<Beacon>> commonBeacons(Scanner otherScanner) {
		Beacon bestDifference = null;
		Set<Beacon> bestOverlaps = new HashSet<>();
		for (Orientation orientation : new Orientations().orientations) {
			Set<Beacon> transformed = otherScanner.beacons.stream()
					.map(b -> b.transform(orientation))
					.collect(Collectors.toSet());
			for (Beacon transformedBeacon : transformed) {
				for (Beacon beacon : beacons) {
					Beacon difference = transformedBeacon.minus(beacon);
					Set<Beacon> overlaps = beacons.stream().map(b -> b.add(difference))
							.filter(transformed::contains)
							.collect(Collectors.toSet());
					if (overlaps.size() > bestOverlaps.size()) {
						bestOverlaps = overlaps;
						bestDifference = difference;
					}
				}
			}
		}
		return Map.entry(bestDifference, bestOverlaps);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Scanner)) return false;

		Scanner scanner = (Scanner) o;

		return beacons.equals(scanner.beacons);
	}

	@Override
	public int hashCode() {
		return beacons.hashCode();
	}
}
