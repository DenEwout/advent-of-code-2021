package day2.part2;

import static day2.part1.Direction.DOWN;
import static day2.part1.Direction.FORWARD;
import static day2.part1.Direction.UP;

public class CourseInput {

	private final Direction direction;

	private final int power;

	public CourseInput(String input) {
		String[] split = input.split(" ");
		this.direction = Direction.valueOf(split[0].toUpperCase());
		this.power = Integer.parseInt(split[1]);
	}

	public CourseInput(Direction direction, int power) {
		this.direction = direction;
		this.power = power;
	}

	public Position toPosition() {
		return switch (getDirection()) {
			case UP -> new Position(0, 0, UP.getMultiplier() * getPower());
			case DOWN -> new Position(0, 0, DOWN.getMultiplier() * getPower());
			case FORWARD -> new Position(FORWARD.getMultiplier() * getPower(), 0, 0);
		};
	}

	public Direction getDirection() {
		return direction;
	}

	public int getPower() {
		return power;
	}
}
