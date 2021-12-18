package day17;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day17TrickShot {


	private static final int ITERATIONS = 1000;

	@Test
	void partOne() {
		ProbeLauncher probeLauncher = new ProbeLauncher();
		List<ProbeTrajectory> trajectories = new ArrayList<>();
		for (int x = 0; x < ITERATIONS; x++) {
			for (int y = 0; y < ITERATIONS; y++) {
				ProbeTrajectory trajectory = probeLauncher.launch(new Position(0, 0), new Velocity(x, y), new Target(
						new Position(175, -134),
						new Position(227, -79)
				));
				if (trajectory.hit) {
					trajectories.add(trajectory);
				}
			}
		}
		System.out.println(trajectories.stream().max(ProbeTrajectory::compareTo).orElseThrow());
	}

	@Test
	void partTwo() {
		ProbeLauncher probeLauncher = new ProbeLauncher();
		List<ProbeTrajectory> trajectories = new ArrayList<>();
		for (int x = -500; x < ITERATIONS; x++) {
			for (int y = -500; y < ITERATIONS; y++) {
				ProbeTrajectory trajectory = probeLauncher.launch(new Position(0, 0), new Velocity(x, y), new Target(
						new Position(175, -134),
						new Position(227, -79)
				));
				if (trajectory.hit) {
					trajectories.add(trajectory);
				}
			}
		}
		System.out.println(trajectories.size());
	}
}
