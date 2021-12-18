package day18;

import java.math.BigInteger;

public class SnailfishNumberFactory {

	public Number number(String input) {
		return number(input,0);
	}

	public Number number(String input, int depth) {
		if (input.matches("[0-9]")) {
			return new RegularNumber(new BigInteger(input), depth);
		} else {
			return snailfishnumber(input, depth);
		}
	}

	public SnailfishNumber snailfishnumber(String input, int depth) {
		String[] pair = snailfishpair(input);
		Number a = number(pair[0], depth + 1);
		Number b = number(pair[1], depth + 1);
		SnailfishNumber snailfishNumber = new SnailfishNumber(a, b, depth);
		a.setParent(snailfishNumber);
		b.setParent(snailfishNumber);
		return snailfishNumber;
	}

	public String[] snailfishpair(String input) {
		String[] pair = new String[2];
		int indexOfComma = -1;
		int opens = 0;
		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
				case '[' -> opens++;
				case ']' -> opens--;
				case ',' -> {
					if (opens == 1) {
						indexOfComma = i;
					}
				}
				default -> {
				}
			}
			if (indexOfComma > 0) {
				break;
			}
		}
		pair[0] = input.substring(1, indexOfComma);
		pair[1] = input.substring(indexOfComma + 1, input.length() - 1);
		return pair;
	}

}
