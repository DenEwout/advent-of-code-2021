package day19;

import static day19.Direction.from;

import java.util.List;

public class Orientations {

	public final List<Orientation> orientations = List.of(
			//Facing x
			new Orientation(from("x"), from("y"), from("z")),
			new Orientation(from("x"), from("z"), from("-y")),
			new Orientation(from("x"), from("-z"), from("y")),
			new Orientation(from("x"), from("-y"), from("-z")),

			//Facing -x
			new Orientation(from("-x"), from("y"), from("z")),
			new Orientation(from("-x"), from("z"), from("-y")),
			new Orientation(from("-x"), from("-z"), from("y")),
			new Orientation(from("-x"), from("-y"), from("-z")),

			//Facing y
			new Orientation(from("y"), from("x"), from("z")),
			new Orientation(from("y"), from("z"), from("-x")),
			new Orientation(from("y"), from("-z"), from("x")),
			new Orientation(from("y"), from("-x"), from("-z")),
			//Facing -y
			new Orientation(from("-y"), from("x"), from("z")),
			new Orientation(from("-y"), from("z"), from("-x")),
			new Orientation(from("-y"), from("-z"), from("x")),
			new Orientation(from("-y"), from("-x"), from("-z")),

			//Facing z
			new Orientation(from("z"), from("y"), from("x")),
			new Orientation(from("z"), from("x"), from("-y")),
			new Orientation(from("z"), from("-x"), from("y")),
			new Orientation(from("z"), from("-y"), from("-x")),

			//Facing -z
			new Orientation(from("-z"), from("y"), from("x")),
			new Orientation(from("-z"), from("x"), from("-y")),
			new Orientation(from("-z"), from("-x"), from("y")),
			new Orientation(from("-z"), from("-y"), from("-x"))
	);


}
