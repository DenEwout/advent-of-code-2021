package day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProbeTrajectory implements Comparable<ProbeTrajectory> {

	public final List<Probe> steps = new ArrayList<>();
	public boolean hit = false;

	public ProbeTrajectory() {
	}

	public void add(Probe probe, Target target) {
		steps.add(probe);
		if (!hit) {
			if (probe.position().onTarget(target)) {
				hit = true;
			}
		}
	}

	public List<Probe> getSteps() {
		return Collections.unmodifiableList(steps);
	}

	public int maxHeight() {
		return this.steps.stream().map(Probe::position).map(p -> p.y).max(Integer::compareTo).orElseThrow();
	}

	@Override
	public int compareTo(ProbeTrajectory o) {
		return Integer.compare(
				this.maxHeight(),
				o.maxHeight()
		);
	}

	@Override
	public String toString() {
		return "ProbeTrajectory{" +
				"steps=" + steps +
				", hit=" + hit +
				'}';
	}
}
