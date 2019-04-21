import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

/**
 * @author Gershon Gilman Class to create the game board
 */
public class Board {
	// store the number of rows
	private int row;

	// store the number of columns
	private int column;

	// stores the number of colors
	private int colors;

	// the array of buttons
	//private Button[][] buttons = new Button[getColumn()][getRow()];

	public Board(int row, int column, int colors) {
		this.row = row;
		this.column = column;
		this.colors = colors;
	}

	/**
	 * Setting up the board
	 */
	public GridPane show() {
		// creates the utility that we will use
		Utilities utility = new Utilities();

		// creating the actual grid
		GridPane gridpane = new GridPane();

		// filling up the board with buttons
		for (int i = 0; i < getColumn(); i++) {
			for (int j = 0; j < getRow(); j++) {

				// creating the button
				Button button = new Button(" ");

				// adding the button in
				gridpane.add(button, i, j);

				// setting the button specs
				button.setBackground(new Background(
						new BackgroundFill(utility.tileColor(numColors()), new CornerRadii(7.0), utility.inset())));

			//	buttons[i][j] = button;
			}
		}
//		Listener listen = new Listener(buttons);
//		listen.listen();
		return gridpane;
	}

	/**
	 * getter for number of rows user wants
	 * 
	 * @return row the number of rows
	 */
	public int getRow() {
		return row;
	}

	/**
	 * setter for number of rows
	 * 
	 * @param row the number of rows
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * getter of number of columns
	 * 
	 * @return column the number set by user
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * setter for number of columns
	 * 
	 * @param column the number set by user
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * getting the number of colors
	 */
	public int numColors() {
		return colors;
	}

	/**
	 * retrieving the array of buttons
	 */
//	public Button[][] getButtons() {
//		return buttons;
//	}
}
