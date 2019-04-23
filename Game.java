// this is the logic of the whole game, the jewel type is stored as a number and so is if the star as a bool
public class Game {
	int[][] jewelType;
	boolean[][] hasStar;
	int rows = 8;
	int columns = 10;

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
	// take arguements move, goes into game class
	public boolean isValidMove(Move move) {
		int x = move.getFromRow();
		int y = move.getFromColumn();
		int x1 = move.getToRow();
		int y1 = move.getToColumn();
		if (x == x1 && (y + 1 == y1 || y - 1 == y1)) {
			return true;
		}
		if (y == y1 && (x + 1 == x1 || x - 1 == x1)) {
			return true;
		}
		System.out.println("Illegal Move");
		return false;
	}

	// set of getters and setters
	public Move handleMove(Move move) {
		return move;
	}

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
	 * Checks for if there are tiles in lines of the same type
	 */
	public void boardCheck() {
		int count = 0; // counts how many tiles in a row
		int compareRow = 0;
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				compareRow = jewelType[i][j];
				if (j - 1 > getColumns()) {
					if (compareRow == jewelType[i][j - 1]) {
						count++;
						if (count > 2) {
							for (; count > 0; count--) {
								hasStar[i][j] = true;
								hasStar[i][j - 1] = true;
							}
						}
					}
				}
			}
		}
	}
}
