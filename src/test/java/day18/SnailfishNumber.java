package day18;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SnailfishNumber implements Number {

	private static final BigInteger MULTIPLY_LEFT = new BigInteger("3");
	private static final BigInteger MULTIPLY_RIGHT = new BigInteger("2");
	private final UUID uuid = UUID.randomUUID();

	private SnailfishNumber parent;

	private int depth = 0;

	private Number a;

	private Number b;

	public SnailfishNumber(Number a, Number b, int depth) {
		this.parent = null;
		this.a = a;
		this.b = b;
		this.depth = depth;
	}

	@Override
	public Optional<BigInteger> value() {
		return Optional.empty();
	}

	@Override
	public UUID label() {
		return uuid;
	}

	@Override
	public void setParent(SnailfishNumber parent) {
		this.parent = parent;
	}

	@Override
	public List<Number> children() {
		return List.of(a, b);
	}

	@Override
	public Optional<SnailfishNumber> getParent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public BigInteger magnitude() {
		return MULTIPLY_LEFT.multiply(a.magnitude()).add(MULTIPLY_RIGHT.multiply(b.magnitude()));
	}

	@Override
	public int depth() {
		return depth;
	}

	@Override
	public void incrementDepth() {
		depth++;
	}

	@Override
	public void addValue(Number number) {
		// Do nothing
	}

	public Number getA() {
		return a;
	}

	public void setA(Number a) {
		this.a = a;
	}

	public Number getB() {
		return b;
	}

	public void setB(Number b) {
		this.b = b;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SnailfishNumber)) return false;

		SnailfishNumber that = (SnailfishNumber) o;

		return uuid.equals(that.uuid);
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public String toString() {
		return String.format("[%s,%s]", a.toString(), b.toString());
	}
}
