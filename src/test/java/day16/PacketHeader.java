package day16;

public class PacketHeader {

	private final String input;

	private final int version;

	private final int type;

	public PacketHeader(String input) {
		this.input = input;
		this.version = Integer.parseInt(input.substring(0, 3), 2);
		this.type = Integer.parseInt(input.substring(3), 2);
	}

	public String getInput() {
		return input;
	}

	public int getVersion() {
		return version;
	}

	public int getType() {
		return type;
	}
}
