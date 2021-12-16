package day16;

import java.math.BigInteger;
import java.util.List;

public interface Packet {

	int HEADER_LENGTH = 6;

	String getInput();

	PacketHeader getHeader();

	String getContent();

	BigInteger value();

	List<Packet> getSubpackets();

	String remainder();

	int versionSum();

}
