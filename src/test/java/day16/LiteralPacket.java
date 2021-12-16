package day16;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LiteralPacket implements Packet {

	private final String input;

	private final PacketHeader header;

	private final String content;

	public LiteralPacket(String input, PacketHeader header) {
		this.input = input;
		this.header = header;
		StringBuilder stringBuilder = new StringBuilder();
		String temp = input.substring(6);
		int lastIndex = -1;
		for (int i = 0; i < temp.length(); i = i + 5) {
			String group = temp.substring(i, i + 5);
			stringBuilder.append(group);
			if (group.startsWith("0")) {
				lastIndex = i + 5;
				break;
			}
		}
		if (temp.substring(lastIndex).length() > 0 && toCharStream(temp.substring(lastIndex)).allMatch(c -> c == '0')) {
			int zeroFill = ((header.getInput().length() + lastIndex) % 4) == 0 ? 0 : 4 - ((header.getInput().length() + lastIndex) % 4);
			stringBuilder.append(temp, lastIndex, lastIndex + zeroFill);
		}
		this.content = stringBuilder.toString();
	}

	private Stream<Character> toCharStream(String str) {
		return str.chars().mapToObj(c -> (char) c);
	}

	@Override
	public String getInput() {
		return input;
	}

	@Override
	public PacketHeader getHeader() {
		return header;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public BigInteger value() {
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < content.length(); i = i + 5) {
			int endIndex = i + 5;
			if (endIndex > content.length()) {
				break;
			}
			String group = content.substring(i, endIndex);
			binary.append(group.substring(1));
		}
		return new BigInteger(binary.toString(), 2);
	}

	@Override
	public List<Packet> getSubpackets() {
		return Collections.emptyList();
	}

	@Override
	public String remainder() {
		return input.substring(header.getInput().length() + content.length());
	}

	@Override
	public int versionSum() {
		return header.getVersion();
	}

}
