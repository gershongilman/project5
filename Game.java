
/**
 * @author gershon this is the logic of the whole game, the jewel type is stored
 *         as a number and so is if the star as a bool
 */
public class Game {

	// store the jewel type, i.e. a numerical representation of the color of the
	// jewel
	int[][] jewelType;

	// stores the bool of the star being present in the certain cell or not
	boolean[][] hasStar;

	// the number of rows in the board
	int rows;

	// the columns of the board
	int columns;

	// counter of how many moves have occured
	int moveCounter;

	// num of colors in reality
	int jewelNum;

	/**
	 * The constructor where we initialize everything
	 * 
	 * @param rows     the number is passed in when we created it in board
	 * @param columns  the number of columns the board has
	 * @param jewelNum the number of colors the user wants in the game
	 */
	public Game(int rows, int columns, int jewelNum) {
		this.rows = rows;
		this.columns = columns;
		this.jewelNum = jewelNum;
		jewelType = new int[rows][columns];
		hasStar = new boolean[rows][columns];

		// the initial board needs to be initialized here to do everything after
		initialize();
	}

	/**
	 * The initial board of the game that we generate
	 */
	public void initialize() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				// getNumber is the random number generator
				jewelType[i][j] = getNumber();

				// setting so none of the square have the asterix yet
				setStar(i, j, false);
			}
		}
	}

	/**
	 * Checks if we made a valid move of a tile
	 * 
	 * @param move the move made up or down, left or right
	 * @return true if the move is valid in the sense that its an adjacent square,
	 *         not that the swap is valid
	 */
	// take arguments move, goes into game class
	public boolean isValidMove(Move move) {

		// these are pretty self explanatory variables. (x,y) = from (x1,y1) = with what
		// you want to swap
		int x = move.getFromRow();
		int y = move.getFromColumn();
		int x1 = move.getToRow();
		int y1 = move.getToColumn();

		// same row different column (moving left or right)
		if (x == x1 && (y + 1 == y1 || y - 1 == y1)) {
			return true;
		}

		// same column (moving up or down)
		if (y == y1 && (x + 1 == x1 || x - 1 == x1)) {
			return true;
		}
		System.out.println("Illegal Move");
		return false;
	}

	/**
	 * This is where we actually check if we have a valid swap aka we will get
	 * asterix if this is done correctly Keep in mind that the move has to happen
	 * virtually before we call this
	 * 
	 * @param move the one that will be made
	 * @return true if the swap creates a chain of atleast three
	 */
	public boolean isSwapValid(Move move) {
		// these are pretty self explanatory variables. (x,y) = from (x1,y1) = with what
		// you want to swap
		int x = move.getFromRow();
		int y = move.getFromColumn();
		int x1 = move.getToRow();
		int y1 = move.getToColumn();

		// function that imitates a swap
		reverseTile(x, x1, y, y1);

		int type = jewelType[x][y]; // stores the color of the square
		int right = 0; // will store how many of the same are to the right
		int left = 0; // will store how many of the same are to the left
		int top = 0; // will store how many of the same are to the top
		int bottom = 0; // will store how many of the same are to the bottom

		// this is for the first tile that is switched
		// to the bottom check
		for (int j = y; j < getColumns() && type == jewelType[x][j]; j++) {
			right = j;
		}

		// to the top check
		for (int j = y; j >= 0 && type == jewelType[x][j]; j--) {
			left = j;
		}

		// to the right check
		for (int j = x; j < getRows() && type == jewelType[j][y]; j++) {
			bottom = j;
		}

		// to the left check
		for (int j = x; j >= 0 && type == jewelType[j][y]; j--) {
			top = j;
		}

		// setting the game right for the horizontal did we have enough tiles or
		// not(3+)?
		if (right - left > 1) {
			reverseTile(x1, x, y1, y);
			return true;
		}

		// setting game up vertically did we have enough tiles or not(3+)?
		if (bottom - top > 1) {
			reverseTile(x1, x, y1, y);
			return true;
		}

		// for the second tile switch check
		type = jewelType[x1][y1];
		right = 0;
		left = 0;
		top = 0;
		bottom = 0;

		// to the bottom check
		for (int j = y1; j < getColumns() && type == jewelType[x1][j]; j++) {
			right = j;
		}

		// to the top check
		for (int j = y1; j >= 0 && type == jewelType[x1][j]; j--) {
			left = j;
		}

		// to the right check
		for (int j = x1; j < getRows() && type == jewelType[j][y1]; j++) {
			bottom = j;
		}

		// to the left check
		for (int j = x1; j >= 0 && type == jewelType[j][y1]; j--) {
			top = j;
		}

		// setting the game right for the horizontal did we have enough tiles or
		// not(3+)?
		if (right - left > 1) {
			reverseTile(x1, x, y1, y);
			return true;
		}

		// setting game up vertically did we have enough tiles or not(3+)?
		if (bottom - top > 1) {
			reverseTile(x1, x, y1, y);
			return true;
		}
		reverseTile(x1, x, y1, y);
		System.out.println("Illegal Move");
		return false;
	}

	/**
	 * Function that swaps tiles
	 * 
	 * @param row1    of the first tile
	 * @param row2    of the second tile you wanna switch with
	 * @param column1 of the tile you have first
	 * @param column2 of the switching with
	 */
	public void reverseTile(int row1, int row2, int column1, int column2) {
		int temp = jewelType[row1][column1]; // stores the jewel color(num) temporarily
		// making the swap
		jewelType[row1][column1] = jewelType[row2][column2];
		jewelType[row2][column2] = temp;

	}

	/**
	 * We handle the moves with checking the board and also making the moves
	 * 
	 * @param move the tile row and column of the piece being moved and moved to
	 *             where
	 */
	public void handleMove(Move move) {
		// making the move
		reverseTile(move.getFromRow(), move.getToRow(), move.getFromColumn(), move.getToColumn());

		// marking the board
		boardCheck(move.getToRow(), move.getToColumn());
		boardCheck(move.getFromRow(), move.getFromColumn());

		// count up with valid moves
		moveCounter++;

		// check if the game is won
		if (isGameFinished()) {
			System.out.print(
					"YOU WIN!!" + "it took" + " " + getMoveCounter() + " move" + ((getMoveCounter() > 1 ? "s" : "")));
		}

	}

	/**
	 * Checks for if there are tiles in lines of the same type after a certain move
	 * 
	 * @param row    the row of the moved tile
	 * @param column the column of the moved tile
	 */
	public void boardCheck(int row, int column) {
		int type = jewelType[row][column];
		int right = 0;
		int left = 0;
		int top = 0;
		int bottom = 0;

		// for loop forwards then with count for loop backwards (same concept for all
		// directions)
		// to the bottom check
		for (int j = column; j < getColumns() && type == jewelType[row][j]; j++) {
			right = j;
		}

		// to the top check
		for (int j = column; j >= 0 && type == jewelType[row][j]; j--) {
			left = j;
		}

		// to the right check
		for (int j = row; j < getRows() && type == jewelType[j][column]; j++) {
			bottom = j;
		}

		// to the left check
		for (int j = row; j >= 0 && type == jewelType[j][column]; j--) {
			top = j;
		}

		// setting the game right for the horizontal
		if (right - left > 1) {
			for (int j = left; j < right + 1; j++) {
				setStar(row, j, true);
				// calling to slide the tiles
				drop(row, j);
			}
		}

		// setting game up vertically
		if (bottom - top > 1) {
			for (int j = top; j < bottom + 1; j++) {
				setStar(j, column, true);
				// calling to slide the tiles
				drop(j, column);
			}
		}
	}

	/**
	 * A method that slides everything down
	 * 
	 * @param row    the row of the tile from which we slide
	 * @param column the column from which we slide the tiles
	 */
	public void drop(int row, int column) {

		// we get the previous color tile
		for (int j = row; j > 0; j--) {
			jewelType[j][column] = jewelType[j - 1][column];
		}

		// once we reach the top we need to get a new tile
		jewelType[0][column] = getNumber();
	}

	/**
	 * checking if the game should be terminated
	 * 
	 * @return true if the game is over aka everything has an asterix or "true"
	 */
	public boolean isGameFinished() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (hasStar[i][j] == false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * random num generator
	 * 
	 * @return the number that corresponds to color
	 */
	private int getNumber() {
		return (int) (Math.random() * jewelNum) + 1;
	}

	/**
	 * getter for the columns
	 * 
	 * @return the number of columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * getter for the rows
	 * 
	 * @return the number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * getter for the number of moves (could also have a setter but not needed)
	 * 
	 * @return the number of moves
	 */
	public int getMoveCounter() {
		return moveCounter;
	}

	/**
	 * gets the jewel color
	 * 
	 * @param row    of the tile
	 * @param column of the tile
	 * @return the color of the tile/ the number stored there
	 */
	public int getJewelType(int row, int column) {
		return jewelType[row][column];
	}

	/**
	 * This setter was created for testing purposes and allows us to test
	 * swapping,etc
	 * 
	 * @param row       given a specific row
	 * @param column    given a specific column
	 * @param jewelType and a specific jewel, we will set a square to it
	 */
	public void setJewelType(int row, int column, int jewelType) {
		this.jewelType[row][column] = jewelType;
	}

	/**
	 * the asterix value (yes or no) of a tile
	 * 
	 * @param row    of the tile
	 * @param column of the tile
	 * @return if the tile has an asterix or not
	 */
	public boolean getStar(int row, int column) {
		return hasStar[row][column];
	}

	/**
	 * setting the asterix value of a square
	 * 
	 * @param row    the tile is in
	 * @param column the column the tile is in
	 * @param bool   the yes or no of if the tile has been marked
	 */
	public void setStar(int row, int column, boolean bool) {
		this.hasStar[row][column] = bool;
	}
}
