
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class Utilities {
	// stores the number of colors the user wants
	private int numColors = 4;

	// method that chooses color by randomly selected number
	public Color tileColor(int num) {
		setNumColor(num);
		switch (getNumber()) {

		case 1:
			return Color.BLUE;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.ORANGE;
		case 4:
			return Color.BLACK;
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

	/**
	 * randomly generates number to choose for color
	 * @return int that will represent a color
	 */
	public int getNumber() {
		return (int) (Math.random() * ((getNumColor() - 1) + 1)) + 1;
	}

	/*
	 * sets the number of colors the user wants, minimum of 4
	 */
	public void setNumColor(int numColors) {
		if (numColors < 4 || numColors > 10) {
		}
		this.numColors = numColors;

	}

	/*
	 * returns the number of colors
	 */
	public int getNumColor() {
		return numColors;
	}

	/*
	 * sets the inset of the buttons
	 */
	public Insets inset() {
		return new Insets(3, 3, 3, 3);
	}
}
