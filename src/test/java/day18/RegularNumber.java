package day18;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RegularNumber implements Number {

	private final UUID uuid = UUID.randomUUID();

	private int depth;

	private SnailfishNumber parent;

	private BigInteger value;

	public RegularNumber(BigInteger value, int depth) {
		this.value = value;
		this.depth = depth;
	}


	@Override
	public void setParent(SnailfishNumber parent) {
		this.parent = parent;
	}

	@Override
	public List<Number> children() {
		return Collections.emptyList();
	}

	@Override
	public Optional<SnailfishNumber> getParent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public BigInteger magnitude() {
		return value;
	}

	@Override
	public int depth() {
		return depth;
	}

	@Override
	public UUID label() {
		return uuid;
	}

	@Override
	public void addValue(Number number) {
		if (number.value().isPresent()) {
			this.value = this.value.add(number.value().get());
		}
	}

	@Override
	public Optional<BigInteger> value() {
		return Optional.of(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RegularNumber)) return false;

		RegularNumber that = (RegularNumber) o;

		return uuid.equals(that.uuid);
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public void incrementDepth() {
		depth++;
	}


	@Override
	public String toString() {
		return value.toString();
	}
}
