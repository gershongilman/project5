// this is the logic of the whole game, the jewel type is stored as a number and so is if the star as a bool
public class Game {
	int[][] jewelType;
	boolean[][] hasStar;
	int rows = 8;
	int columns = 10;
	int moveCounter = 0;

	// num of colors in reality
	int jewelNum = 4;

	public Game(int rows, int columns, int jewelNum) {
		this.rows = rows;
		this.columns = columns;
		this.jewelNum = jewelNum;
		jewelType = new int[rows][columns];
		hasStar = new boolean[rows][columns];
		initialize();
	}

	private int getNumber() {
		return (int) (Math.random() * jewelNum) + 1;
	}

	// initial board
	public void initialize() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				jewelType[i][j] = getNumber();
				hasStar[i][j] = false;
			}
		}
	}

	/**
	 * Checks if we made a valid move of a tile
	 * 
	 * @param move
	 * @return
	 */
	// take arguments move, goes into game class
	public boolean isValidMove(Move move) {
		int x = move.getFromRow();
		int y = move.getFromColumn();
		int x1 = move.getToRow();
		int y1 = move.getToColumn();
		if (!hasStar[x][y] && !hasStar[x1][y1]) {
			if (x == x1 && (y + 1 == y1 || y - 1 == y1)) {
				return true;
			}
			if (y == y1 && (x + 1 == x1 || x - 1 == x1)) {
				return true;
			}
		}
		System.out.println("Illegal Move");
		return false;
	}

	/**
	 * We handle the moves with checking the board and also making the moves
	 * 
	 * @param move the tile row and column of the piece being moved and moved to
	 *             where
	 */
	public void handleMove(Move move) {
		int temp = jewelType[move.getFromRow()][move.getFromColumn()];
		jewelType[move.getFromRow()][move.getFromColumn()] = jewelType[move.getToRow()][move.getToColumn()];
		jewelType[move.getToRow()][move.getToColumn()] = temp;
		boardCheck(move.getToRow(), move.getToColumn());
		boardCheck(move.getFromRow(), move.getFromColumn());
		moveCounter++;
		if (gameFinished()) {
			System.out.print("YOU WIN!!" + "it took" + getMoveCounter());
		}
	}

	// set of getters and setters
	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public int getMoveCounter() {
		return moveCounter;
	}

	public int getJewelType(int row, int column) {
		return jewelType[row][column];
	}

	public boolean getStar(int row, int column) {
		return hasStar[row][column];
	}

	public int getJewelNum() {
		return jewelNum;
	}

	public void setJewelNum(int num) {
		this.jewelNum = num;
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
		// first check that its not at the edge of the board
		// for loop forwards then with count for loop backwards

		// to the bottom check
		for (int j = column; j < getColumns() && type == jewelType[row][j]; j++) {
			bottom = j;
		}

		// to the top check
		for (int j = column; j >= 0 && type == jewelType[row][j]; j--) {
			top = j;
		}

		// to the right check
		for (int j = row; j < getRows() && type == jewelType[j][column]; j++) {
			right = j;
		}

		// to the left check
		for (int j = row; j >= 0 && type == jewelType[j][column]; j--) {
			left = j;
		}

		// setting the game right for the horizontal
		if (right - left > 1) {
			for (int j = left; j < right + 1; j++) {
				hasStar[j][column] = true;
			}
		}
		if (bottom - top > 1) {
			for (int j = top; j < bottom + 1; j++) {
				hasStar[row][j] = true;
			}
		}
	}

	/**
	 * checking if the game should be terminated
	 * 
	 * @return
	 */
	public boolean gameFinished() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (hasStar[i][j] == false) {
					return false;
				}
			}
		}
		return true;
	}
}
