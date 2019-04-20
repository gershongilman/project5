import java.awt.Color;
import java.util.Random;

public class Utilities {
	private int number;

	public Color tileColor() {

		switch (number) {

		case 1:
			return Color.BLUE;

		case 2:
			return Color.GREEN;

		case 3:
			return Color.ORANGE;

		case 4:
			return Color.RED;

		default:
			return Color.BLACK;
		}

	}

	public void setNumber() {
		Random num = new Random();
		number = num.nextInt(5);
	}

}
