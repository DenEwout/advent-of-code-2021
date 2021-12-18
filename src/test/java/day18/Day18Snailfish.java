package day18;

import static org.assertj.core.api.Assertions.assertThat;

import day16.PacketFactory;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day18Snailfish {

	private static final String INPUT_FILE = "day18snailfish.txt";
	private SnailfishNumberFactory factory = new SnailfishNumberFactory();
	private SnailfishNumberHelper helper = new SnailfishNumberHelper();
	;


	@Test
	void example() {
		Number number = factory.number("[[9,1],[1,9]]");
		List<Number> numbers = helper.dfsOrder(number, new ArrayList<>()).stream()
				.filter(num -> num.value().isPresent())
				.collect(Collectors.toList());


		Number explode = factory.number("[[6,[5,[4,[3,2]]]],1]");
		boolean exploded = helper.explode(explode);
	}

	@Test
	void test1() {
		Number a = factory.number("[[[[4,3],4],4],[7,[[8,4],9]]]");
		Number b = factory.number("[1,1]");
		Number result = helper.add(a, b);
	}

	@Test
	void test2() {
		List<String> input = List.of(
				"[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
				"[[[5,[2,8]],4],[5,[[9,9],0]]]",
				"[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
				"[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
				"[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
				"[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
				"[[[[5,4],[7,7]],8],[[8,3],8]]",
				"[[9,3],[[9,9],[6,[4,9]]]]",
				"[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
				"[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
		);
		BigInteger magnitude = helper.homework(input).magnitude();
		assertThat(magnitude).isEqualTo(new BigInteger("4140"));
	}

	@Test
	void partOne() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			PacketFactory packetFactory = new PacketFactory();
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());
			BigInteger magnitude = helper.homework(lines).magnitude();
			System.out.println(magnitude);
		}
	}


	@Test
	void partTwo() throws Exception {
		try (
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(INPUT_FILE);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			PacketFactory packetFactory = new PacketFactory();
			List<String> lines = bufferedReader.lines().collect(Collectors.toList());
			System.out.println(helper.homeworkPartTwo(lines));
		}
	}


}
