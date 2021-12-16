package day16;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HexString {

	private final Map<Character, String> hexMap = new HashMap<>();

	private final String input;

	public HexString(String input) {
		hexMap.put('0', "0000");
		hexMap.put('1', "0001");
		hexMap.put('2', "0010");
		hexMap.put('3', "0011");
		hexMap.put('4', "0100");
		hexMap.put('5', "0101");
		hexMap.put('6', "0110");
		hexMap.put('7', "0111");
		hexMap.put('8', "1000");
		hexMap.put('9', "1001");
		hexMap.put('A', "1010");
		hexMap.put('B', "1011");
		hexMap.put('C', "1100");
		hexMap.put('D', "1101");
		hexMap.put('E', "1110");
		hexMap.put('F', "1111");
		this.input = input;
	}

	public String binary() {
		return toCharStream(input).map(hexMap::get).collect(Collectors.joining());
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}

}
