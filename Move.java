/**
 * @author gershon 
 * Class that just moves the tiles that appear
 */
public class Move {
	
	//stores the row of the tile we choose first to move
	private int tileFromRow;
	
	//stores the column of the tile we choose first to move
	private int tileFromColumn;

	//stores the row of the tile we choose to swap with
	private int tileToRow;
	
	//stores the column of the tile we choose to swap with
	private int tileToColumn;

	/**
	 * Initializing the values
	 * @param tileFromRow the row in which the 1st tile is in
	 * @param tileFromColumn the column in which the 1st tile is in
	 * @param tileToRow the row in which the swap tile is in
	 * @param tileToColumn the column in which the swap tile is in
	 */
	public Move(int tileFromRow, int tileFromColumn, int tileToRow, int tileToColumn) {
		this.tileFromRow = tileFromRow;
		this.tileToRow = tileToRow;
		this.tileFromColumn = tileFromColumn;
		this.tileToColumn = tileToColumn;
	}

	/*
	 * Getter for the column from 
	 * @return the tilefrom column value
	 */
	public int getFromColumn() {
		return tileFromColumn;
	}

	/**
	 * getter for the row from
	 * @return the row from where we want to move
	 */
	public int getFromRow() {
		return tileFromRow;
	}

	/**
	 * getter for the column we want to swap with
	 * @return the column of the swap
	 */
	public int getToColumn() {
		return tileToColumn;
	}

	/**
	 * getter for the row to
	 * @return the row of the tile we want to swap with
	 */
	public int getToRow() {
		return tileToRow;
	}
}
