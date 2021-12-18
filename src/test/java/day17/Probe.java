package day17;

import java.util.Iterator;

public class Probe implements Iterator<Probe> {

	private final Velocity velocity;

	private final Position position;

	private final Target target;

	public Probe(Velocity velocity, Position position, Target target) {
		this.velocity = velocity;
		this.position = position;
		this.target = target;
	}

	@Override
	public boolean hasNext() {
		if (position.onTarget(target)) {
			return false;
		} else {
			return !this.overshot(target);
		}
	}

	private boolean overshot(Target target) {
		if (velocity.x == 0) {
			if (velocity.y <= 0 && target.getMin().y > position.y) {
				return true;
			}
		}
		return false;
	}

	public Position position() {
		return position;
	}

	@Override
	public Probe next() {
		int nextVelocityX;
		if (velocity.x > 0) {
			nextVelocityX = velocity.x - 1;
		} else if (velocity.x < 0) {
			nextVelocityX = velocity.x + 1;
		} else {
			nextVelocityX = 0;
		}
		return new Probe(
				new Velocity(nextVelocityX, this.velocity.y - 1),
				new Position(this.position.x + this.velocity.x, this.position.y + this.velocity.y),
				target);
	}
}
