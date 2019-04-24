
/**
 * @author gershon
 *this is the logic of the whole game, the jewel type is stored as a number and so is if the star as a bool
 */
public class Game {
	
	//store the jewel type, i.e. a numerical representation of the color of the jewel
	int[][] jewelType;
	
	//stores the bool of the star being present in the certain cell or not
	boolean[][] hasStar;
	
	//the number of rows in the board
	int rows;
	
	//the columns of the board 
	int columns ;
	
	//counter of how many moves have occured
	int moveCounter;
	
	// num of colors in reality
	int jewelNum;

	/**
	 * The constructor where we initialize everything 
	 * @param rows the number is passed in when we created it in board
	 * @param columns the number of columns the board has
	 * @param jewelNum the number of colors the user wants in the game
	 */
	public Game(int rows, int columns, int jewelNum) {
		this.rows = rows;
		this.columns = columns;
		this.jewelNum = jewelNum;
		jewelType = new int[rows][columns];
		hasStar = new boolean[rows][columns];
		
		//the initial board needs to be initialized here to do everything after
		initialize();
	}


	/**
	 * The initial board of the game that we generate
	 */
	public void initialize() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				
				//getNumber is the random number generator
				jewelType[i][j] = getNumber();
				
				//setting so none of the square have the asterix yet
				hasStar[i][j] = false;
			}
		}
	}

	/**
	 * Checks if we made a valid move of a tile
	 * @param move the  move made up or down, left or right
	 * @return true if the move is valid in the sense that its an adjacent square, not that the swap is valid
	 */
	// take arguments move, goes into game class
	public boolean isValidMove(Move move) {
		
		//these are pretty self explanatory variables. (x,y) = from (x1,y1) = with what you want to swap
		int x = move.getFromRow();
		int y = move.getFromColumn();
		int x1 = move.getToRow();
		int y1 = move.getToColumn();

		//same row different column (moving left or right)
		if (x == x1 && (y + 1 == y1 || y - 1 == y1)) {
			return true;
		}
		
		//same column (moving up or down)
		if (y == y1 && (x + 1 == x1 || x - 1 == x1)) {
			return true;
		}
		System.out.println("Illegal Move");
		return false;
	}

	/**
	 * This is where we actually check if we have a valid swap aka we will get asterix if this is done correctly
	 * Keep in mind that the move has to happen virtually before we call this 
	 * @param row the input row 
	 * @param column the input column
	 * @return boolean of if the move will result in rows or columns of three
	 */
	public boolean isSwapValid(int row, int column) {
		int type = jewelType[row][column];																					//stores the color of the square
		int right = 0;																										//will store how many of the same are to the right
		int left = 0;																										//will store how many of the same are to the left
		int top = 0;																										//will store how many of the same are to the top
		int bottom = 0;																										//will store how many of the same are to the bottom
		
		
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

		// setting the game right for the horizontal did we have enough tiles or not(3+)?
		if (right - left > 1) {
			return true;
		}
		
		// setting game up vertically did we have enough tiles or not(3+)?
		if (bottom - top > 1) {
			return true;
		}
		return false;
	}

	/**
	 * We handle the moves with checking the board and also making the moves
	 * @param move the tile row and column of the piece being moved and moved to where
	 */
	public void handleMove(Move move) {
		//first we make a move, but keep in mind we still need to check if the actual swap is valid
		int temp = jewelType[move.getFromRow()][move.getFromColumn()];																//stores the jewel color(num) temporarily
	
		//making the swap
		jewelType[move.getFromRow()][move.getFromColumn()] = jewelType[move.getToRow()][move.getToColumn()];
		jewelType[move.getToRow()][move.getToColumn()] = temp;
		
		//now we check if the swap was valid 
		if (isSwapValid(move.getToRow(), move.getToColumn()) || isSwapValid(move.getFromRow(), move.getFromColumn())) {
			//if yes then we addin the asterix sorry but itll be the same as swap in a way
			boardCheck(move.getToRow(), move.getToColumn());
			boardCheck(move.getFromRow(), move.getFromColumn());
			//count up with valid moves
			moveCounter++;
		} else {
			//if it failed we need to revert back to the original board
			jewelType[move.getToRow()][move.getToColumn()] = jewelType[move.getFromRow()][move.getFromColumn()];
			jewelType[move.getFromRow()][move.getFromColumn()] = temp;
			System.out.println("Illegal Move");
		}
		//check if the game is won
		if (isGameFinished()) {
			System.out.print("YOU WIN!!" + "it took" + " " + getMoveCounter());
		}
	}



	/**
	 * Checks for if there are tiles in lines of the same type after a certain move 
	 * @param row    the row of the moved tile
	 * @param column the column of the moved tile
	 */
	public void boardCheck(int row, int column) {
		int type = jewelType[row][column];
		int right = 0;
		int left = 0;
		int top = 0;
		int bottom = 0;
		
		// for loop forwards then with count for loop backwards (same concept for all directions)
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
				hasStar[row][j] = true;
				//calling to slide the tiles
				drop(row, j);

			}

		}
		
		// setting game up vertically
		if (bottom - top > 1) {
			for (int j = top; j < bottom + 1; j++) {
				hasStar[j][column] = true;
				//calling to slide the tiles
				drop(j, column);
			}

		}
	}

	/**
	 * A method that slides everything down
	 * @param row the row of the tile from which we slide
	 * @param column the column from which we slide the tiles
	 */
	public void drop(int row, int column) {
		
		//we get the previous color tile
		for (int j = row; j > 0; j--) {
			jewelType[j][column] = jewelType[j - 1][column];
		}
		
		//once we reach the top we need to get a new tile
		jewelType[0][column] = getNumber();
	}

	/**
	 * checking if the game should be terminated
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
	 * @return the number that corresponds to color
	 */
	private int getNumber() {
		return (int) (Math.random() * jewelNum) + 1;
	}
	
	/**
	 * getter for the columns
	 * @return the number of columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * getter for the rows
	 * @return the number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * getter for the number of moves (could also have a setter but not needed)
	 * @return the number of moves
	 */
	public int getMoveCounter() {
		return moveCounter;
	}

	/**
	 * gets the jewel color 
	 * @param row of the tile
	 * @param column of the tile
	 * @return the color of the tile/ the number stored there
	 */
	public int getJewelType(int row, int column) {
		return jewelType[row][column];
	}

	/**
	 * the asterix value (yes or no) of a tile
	 * @param row of the tile
	 * @param column of the tile
	 * @return if the tile has an asterix or not
	 */
	public boolean getStar(int row, int column) {
		return hasStar[row][column];
	}
}
