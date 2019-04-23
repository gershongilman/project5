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
	private Button[][] buttons;

	// the current board
	private GridPane current;

	private boolean firstCall = true;

	private Game game;

	private Utilities utility = new Utilities();

	public Board(int row, int column, int colors) {
		this.row = row;
		this.column = column;
		this.colors = colors;
		current = new GridPane();
		buttons = new Button[row][column];
		game = new Game(row, column, colors);
		game.initialize();
	}

	/**
	 * Setting up the board
	 */
	public GridPane show() {
		if (firstCall) {
			initialBoard();
			updateBoard();
			firstCall = false;
		}
		Listener listen = new Listener();
		listen.listen(this);
		return getCurrentPane();
	}

	public void initialBoard() {
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getColumn(); j++) {

				// creating the button
				TileButton button = new TileButton(i, j);
				// button.setText(((game.getStar(i, j)) ? "*" : " "));
				// adding the button in
				current.add(button, i, j);

				// setting the button specs
				// button.setBackground(new Background(new
				// BackgroundFill(utility.tileColor(game.getJewelType(i, j)),
				// new CornerRadii(7.0), utility.inset())));

				buttons[i][j] = button;
			}

		}
	}

	public void updateBoard() {
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getColumn(); j++) {
				Button button = buttons[i][j];
				// creating the button
				button.setText(((game.getStar(i, j)) ? "*" : "  "));

				// setting the button specs
				button.setBackground(new Background(new BackgroundFill(utility.tileColor(game.getJewelType(i, j)),
						new CornerRadii(7.0), utility.inset())));

			}

		}
	}

	public void play() {
		getCurrentPane();
		System.out.println("k");
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
	public Button[][] getButtons() {
		return buttons;
	}

	/**
	 * getting the current pane
	 */
	public GridPane getCurrentPane() {
		return current;
	}

	/**
	 * setting the current pane
	 */

	public void setCurrentPane(GridPane current) {
		play();
		this.current = current;
	}

	public Game getGame() {
		return game;
	}

}
