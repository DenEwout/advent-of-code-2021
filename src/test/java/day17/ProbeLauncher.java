package day17;

public class ProbeLauncher {

	public ProbeTrajectory launch(Position position, Velocity velocity, Target target) {
		ProbeTrajectory trajectory = new ProbeTrajectory();
		Probe probe = new Probe(velocity, position, target);
		trajectory.add(probe, target);
		while (probe.hasNext()) {
			probe = probe.next();
			trajectory.add(probe, target);
		}
		return trajectory;
	}

}
