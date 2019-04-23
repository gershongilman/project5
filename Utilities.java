
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class Utilities {

	// method that chooses color by randomly selected number
	public Color tileColor(int num) {
		switch (num) {

		case 1:
			return Color.BLUE.brighter();
		case 2:
			return Color.GREEN.brighter();
		case 3:
			return Color.ORANGE.brighter();
		case 4:
			return Color.WHITE.brighter();
		case 5:
			return Color.GRAY.brighter();
		case 6:
			return Color.RED.brighter();
		case 7:
			return Color.BROWN.brighter();
		case 8:
			return Color.YELLOW.brighter();
		case 9:
			return Color.SALMON.brighter();
		case 10:
			return Color.PURPLE.brighter();

		}
		return Color.BLACK;

	}

	/*
	 * sets the inset of the buttons
	 */
	public Insets inset() {
		return new Insets(3, 5, 5, 3);
	}
}
