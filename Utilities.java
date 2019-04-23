
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
			return Color.WHITE;
		case 5:
			return Color.GRAY;
		case 6:
			return Color.RED;
		case 7:
			return Color.BROWN;
		case 8:
			return Color.YELLOW;
		case 9:
			return Color.SALMON;
		case 10:
			return Color.PURPLE;

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
