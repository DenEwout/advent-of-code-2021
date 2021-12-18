package day18;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Number {

	void setParent(SnailfishNumber parent);

	List<Number> children();

	Optional<SnailfishNumber> getParent();

	BigInteger magnitude();

	int depth();

	void incrementDepth();

	Optional<BigInteger> value();

	UUID label();

	void addValue(Number number);

	String toString();
}
