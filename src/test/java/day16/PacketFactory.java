package day16;

public class PacketFactory {

	Packet packet(String input) {
		if (input.length() > 6) {
			PacketHeader packetHeader = new PacketHeader(input.substring(0, 6));
			return switch (packetHeader.getType()) {
				case 4 -> new LiteralPacket(input, packetHeader);
				default -> new OperatorPacket(input, packetHeader);
			};
		}
		throw new IllegalArgumentException("No packet header!");
	}

}
