import javafx.scene.paint.Color;

public class Utilities {

	public Color tileColor() {
		switch (setNumber()) {

		case 1:
			return Color.BLUE;

		case 2:
			return Color.GREEN;

		case 3:
			return Color.ORANGE;

		case 4:
			return Color.RED;
		}
		return Color.BLACK;

	}

	public int setNumber() {
		return (int) (Math.random() * ((4 - 0) + 1)) + 1;

	}

	public static void main(String[] args) {
		Utilities test = new Utilities();
		test.setNumber();
		test.setNumber();
		test.setNumber();
		test.setNumber();

	}
}
