package day18;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class SnailfishNumberHelper {

	public BigInteger homeworkPartTwo(List<String> input) {
		SnailfishNumberFactory factory = new SnailfishNumberFactory();
		BigInteger max = BigInteger.ZERO;
		for (String a : input) {
			for (String b : input) {
				if (!a.equals(b)) {
					Number result = add(factory.number(a), factory.number(b));
					if (max.compareTo(result.magnitude()) <= 0) {
						max = result.magnitude();
					}
				}
			}
		}
		return max;
	}

	public Number homework(List<String> input) {
		SnailfishNumberFactory factory = new SnailfishNumberFactory();
		Number number = null;
		for (String line : input) {
			Number a = factory.number(line);
			if (number == null) {
				number = a;
			} else {
				number = add(number, a);
			}
		}
		return number;
	}

	public Number add(Number a, Number b) {
		dfsOrder(a, new ArrayList<>()).forEach(Number::incrementDepth);
		dfsOrder(b, new ArrayList<>()).forEach(Number::incrementDepth);
		SnailfishNumber added = new SnailfishNumber(a, b, 0);
		a.setParent(added);
		b.setParent(added);
		reduce(added);
		return added;
	}

	public List<Number> dfsOrder(Number graph, List<Number> discovered) {
		Deque<Number> stack = new ArrayDeque<>();
		stack.push(graph);
		while (!stack.isEmpty()) {
			Number node = stack.pop();
			if (!discovered.contains(node)) {
				discovered.add(node);
				node.children().forEach(child -> dfsOrder(child, discovered));
			}
		}
		return discovered;
	}

	public Number reduce(Number number) {
		while (true) {
			if (explode(number)) {
				continue;
			}
			if (split(number)) {
				continue;
			}
			break;
		}
		return number;
	}

	private boolean split(Number number) {
		List<Number> leafs = dfsOrder(number, new ArrayList<>()).stream()
				.filter(num -> num.value().isPresent())
				.collect(Collectors.toList());
		for (int i = 0; i < leafs.size(); i++) {
			Number leaf = leafs.get(i);
			BigInteger leafValue = leaf.value().orElseThrow();
			if (leafValue.compareTo(BigInteger.TEN) > -1) {
				splitLeaf(leaf);
				return true;
			}
		}
		return false;
	}

	private void splitLeaf(Number leaf) {
		SnailfishNumber parent = leaf.getParent().orElseThrow();
		BigInteger value = leaf.value().orElseThrow();
		BigInteger[] division = value.divideAndRemainder(new BigInteger("2"));
		RegularNumber a = new RegularNumber(division[0], leaf.depth() + 1);
		RegularNumber b = new RegularNumber(division[0].add(division[1]), leaf.depth() + 1);
		SnailfishNumber created = new SnailfishNumber(a, b, leaf.depth());
		a.setParent(created);
		b.setParent(created);
		created.setParent(parent);
		replace(leaf, created);
	}

	public boolean explode(Number number) {
		List<Number> numbers = dfsOrder(number, new ArrayList<>());
		for (int i = 0; i < numbers.size(); i++) {
			Number node = numbers.get(i);
			if (shouldExplode(node)) {
				RegularNumber inserted = new RegularNumber(BigInteger.ZERO, node.depth());
				inserted.setParent(node.getParent().orElseThrow());
				Number zero = replace(node, inserted);
				addLeftRight(number, node, zero);
				return true;
			}
		}
		return false;
	}

	private boolean shouldExplode(Number node) {
		return node.depth() == 4
				&& node.value().isEmpty()
				&& node.children().stream().allMatch(num -> num.value().isPresent());
	}

	private void addLeftRight(Number number, Number node, Number inserted) {
		List<Number> leafs = dfsOrder(number, new ArrayList<>()).stream()
				.filter(num -> num.value().isPresent())
				.collect(Collectors.toList());
		int index = leafs.indexOf(inserted);

		try {
			leafs.get(index - 1).addValue(node.children().get(0));
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			leafs.get(index + 1).addValue(node.children().get(1));
		} catch (IndexOutOfBoundsException e) {

		}
	}

	private Number replace(Number toReplace, Number inserted) {
		SnailfishNumber parent = toReplace.getParent().orElseThrow();
		if (parent.getA().label().equals(toReplace.label())) {
			parent.setA(inserted);
		}
		if (parent.getB().label().equals(toReplace.label())) {
			parent.setB(inserted);
		}
		inserted.setParent(parent);
		return inserted;
	}

}
