package day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OperatorPacket implements Packet {

	private static final int TYPE_ID_LENGTH_BITS = 15;
	private static final int TYPE_ID_AMOUNT_SUBPACKETS = 11;
	private final String input;

	private final PacketHeader header;

	private final String content;

	private final int lengthTypeId;

	private Integer subpacketsBitLength;

	private Integer amountOfSubpackets;

	private final List<Packet> subpackets = new ArrayList<>();

	public OperatorPacket(String input, PacketHeader header) {
		this.input = input;
		this.header = header;
		this.lengthTypeId = Integer.parseInt(input.substring(6, 7));
		switch (this.lengthTypeId) {
			case 0 -> {
				this.subpacketsBitLength = Integer.parseInt(input.substring(7, 7 + TYPE_ID_LENGTH_BITS), 2);
				this.content = input.substring(7 + TYPE_ID_LENGTH_BITS, 7 + TYPE_ID_LENGTH_BITS + this.subpacketsBitLength);
				Packet subPacket = new PacketFactory().packet(content);
				subpackets.add(subPacket);
				while (!subPacket.remainder().isEmpty()) {
					subPacket = new PacketFactory().packet(subPacket.remainder());
					subpackets.add(subPacket);
				}
			}
			case 1 -> {
				this.amountOfSubpackets = Integer.parseInt(input.substring(7, 7 + TYPE_ID_AMOUNT_SUBPACKETS), 2);
				String next = input.substring(7 + TYPE_ID_AMOUNT_SUBPACKETS);
				for (int i = 0; i < amountOfSubpackets; i++) {
					Packet subPacket = new PacketFactory().packet(next);
					subpackets.add(subPacket);
					next = subPacket.remainder();
				}
				this.content = this.input.substring(header.getInput().length() + 1 + TYPE_ID_AMOUNT_SUBPACKETS, this.input.length() - next.length());
			}
			default -> throw new IllegalArgumentException("type ID is " + this.lengthTypeId);
		}
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
		return switch (header.getType()) {
			case 0 -> subpackets.stream().map(Packet::value).reduce(BigInteger.ZERO, BigInteger::add);
			case 1 -> subpackets.stream().map(Packet::value).reduce(BigInteger.ONE, BigInteger::multiply);
			case 2 -> subpackets.stream().map(Packet::value).min(BigInteger::compareTo).orElseThrow();
			case 3 -> subpackets.stream().map(Packet::value).max(BigInteger::compareTo).orElseThrow();
			case 5 -> subpackets.get(0).value().compareTo(subpackets.get(1).value()) > 0 ? BigInteger.ONE : BigInteger.ZERO;
			case 6 -> subpackets.get(0).value().compareTo(subpackets.get(1).value()) < 0 ? BigInteger.ONE : BigInteger.ZERO;
			case 7 -> subpackets.get(0).value().equals(subpackets.get(1).value()) ? BigInteger.ONE : BigInteger.ZERO;
			default -> throw new IllegalArgumentException("Invalid type " + header.getType());
		};
	}

	@Override
	public List<Packet> getSubpackets() {
		return subpackets;
	}

	@Override
	public String remainder() {
		return switch (this.lengthTypeId) {
			case 0 -> input.substring(header.getInput().length() + 1 + TYPE_ID_LENGTH_BITS + content.length());
			case 1 -> input.substring(header.getInput().length() + 1 + TYPE_ID_AMOUNT_SUBPACKETS + content.length());
			default -> throw new IllegalArgumentException("type ID is " + this.lengthTypeId);
		};
	}

	@Override
	public int versionSum() {
		return header.getVersion() + subpackets.stream().map(Packet::versionSum).reduce(0, Integer::sum);
	}
}
