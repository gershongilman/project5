import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

/**
 * @author Gershon Gilman 
 * This is the GUI class that connects the logic to the colors etc.
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

	// tells us if its the first time the game is called
	private boolean firstCall = true;

	// this stores our logic instance(very useful)
	private Game game;

	// this is our colors and insets
	private Utilities utility;

	/**
	 * This is our constructor that initializes everything.
	 * 
	 * @param row    the number of rows the user inputs
	 * @param column the number of columns of the playing grid
	 * @param colors the number of colors the user wants
	 */
	public Board(int row, int column, int colors) {
		this.row = row;
		this.column = column;
		this.colors = colors;

		// this is our current pane
		current = new GridPane();
		buttons = new Button[row][column];
		game = new Game(row, column, colors);
		utility = new Utilities();

		// we initialize the logic of the game which creates the first board here
		game.initialize();
	}

	/**
	 * Setting up the board. this is the general tying in of all the graphics of the
	 * game
	 */
	public GridPane show() {

		// checking that this is the first time the game is being created, aka just
		// started
		if (firstCall) {

			// this is the only time we call the initial board of graphics and after that
			// its the logic controlling it
			initialBoard();

			// we update the board automatically, but this is nessecary, because we really
			// want colors to appear.
			updateBoard();
			firstCall = false;
		}

		// this connects us to the event handler class that we will deal with more often
		Listener listen = new Listener();

		// actually calling the class and its event handling
		listen.listen(this);
		return getCurrentPane();
	}

	/**
	 * This Method just creates the original board without colors, just so that it
	 * appears. We have this as extra because of tilebutton which allows us to
	 * retrieve exact location of a button in a pane.
	 */
	public void initialBoard() {
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getColumn(); j++) {

				// creating the button
				TileButton button = new TileButton(i, j);

				// adding to the pane
				current.add(button, j, i);
				buttons[i][j] = button;
			}

		}
	}

	/**
	 * We update the board with color and we use this so that we can add in the
	 * stars into the board and have this seperate from logic and GUI based.
	 */
	public void updateBoard() {
		for (int i = 0; i < getRow(); i++) {
			for (int j = 0; j < getColumn(); j++) {

				// stores the individual button that we want to format
				Button button = buttons[i][j];

				// creating the button with necessary text
				button.setText(((game.getStar(i, j)) ? "*" : "  "));

				// setting the button specs (we use utilities to set color and etc.
				button.setBackground(new Background(new BackgroundFill(utility.tileColor(game.getJewelType(i, j)),
						new CornerRadii(7.0), utility.inset())));
			}

		}
	}

	/**
	 * getter for number of rows user wants
	 * @return row the number of rows
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * setter for number of rows
	 * @param row the number of rows
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * getter of number of columns
	 * @return column the number set by user
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * setter for number of columns
	 * @param column the number set by user
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * getting the number of colors
	 * @return colors the number of them
	 */
	public int numColors() {
		return colors;
	}

	/**
	 * retrieving the array of buttons
	 * @return buttons the array we have
	 */
	public Button[][] getButtons() {
		return buttons;
	}

	/**
	 * getting the current pane
	 * @return the pane we have at the moment
	 */
	public GridPane getCurrentPane() {
		return current;
	}

	/**
	 * setting the current pane
	 */
	public void setCurrentPane(GridPane current) {
		this.current = current;
	}

	/**
	 * The game we have, the logic of it 
	 * @return the instance of our logic of the game
	 */
	public Game getGame() {
		return game;
	}

}
