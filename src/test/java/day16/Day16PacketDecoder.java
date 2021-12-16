package day16;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day16PacketDecoder {

	private static final String INPUT_FILE = "day16packetdecoder.txt";
	private static final int STEPS = Integer.MAX_VALUE;


	@Test
	void example() {
		Packet d2FE28 = new PacketFactory().packet(new HexString("D2FE28").binary());
		System.out.println(d2FE28.value());
		System.out.println(d2FE28.remainder());

		Packet d2FE28Extra = new PacketFactory().packet(new HexString("D2FE2800").binary());
		System.out.println(d2FE28Extra.remainder());

		Packet packet38006F45291200 = new PacketFactory().packet(new HexString("38006F45291200").binary());
		System.out.println(packet38006F45291200.getContent());

		Packet packetEE00D40C823060 = new PacketFactory().packet(new HexString("EE00D40C823060").binary());
		System.out.println(packetEE00D40C823060.getContent());
	}

	@Test
	void versionSum() {
		System.out.println(new PacketFactory().packet(new HexString("8A004A801A8002F478").binary()).versionSum());
		System.out.println(new PacketFactory().packet(new HexString("620080001611562C8802118E34").binary()).versionSum());
		System.out.println(new PacketFactory().packet(new HexString("C0015000016115A2E0802F182340").binary()).versionSum());
		System.out.println(new PacketFactory().packet(new HexString("A0016C880162017C3686B18A3D4780").binary()).versionSum());
	}

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			PacketFactory packetFactory = new PacketFactory();
			bufferedReader.lines()
					.map(HexString::new)
					.map(HexString::binary)
					.map(packetFactory::packet)
					.map(Packet::versionSum)
					.forEach(System.out::println);
		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			PacketFactory packetFactory = new PacketFactory();
			bufferedReader.lines()
					.map(HexString::new)
					.map(HexString::binary)
					.map(packetFactory::packet)
					.map(Packet::value)
					.forEach(System.out::println);
		}
	}


}
