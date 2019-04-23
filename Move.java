/**
 * 
 * @author gershon Class that just moves the tiles that appear
 */
public class Move {
	private int tileFromRow;
	private int tileFromColumn;
	private int tileToRow;
	private int tileToColumn;

	// fix constructor
	public Move(int tileFromRow, int tileFromColumn, int tileToRow, int tileToColumn) {
		this.tileFromRow = tileFromRow;
		this.tileToRow = tileToRow;
		this.tileFromColumn = tileFromColumn;
		this.tileToColumn = tileToColumn;
	}

	/**
	 * Moving the tiles no matter what
	 */
	public void move() {
		int tempRow = getFromRow();
		int tempColumn = getFromColumn();
		setRowTo(getFromRow());
		setColumnTo(getFromColumn());
		setRowFrom(tempRow);
		setColumnFrom(tempColumn);
	}

	public void setColumnFrom(int columnFrom) {
		this.tileFromColumn = columnFrom;
	}

	public void setRowFrom(int rowFrom) {
		this.tileFromRow = rowFrom;
	}

	public void setColumnTo(int columnTo) {
		this.tileToColumn = columnTo;
	}

	public void setRowTo(int rowTo) {
		this.tileToRow = rowTo;

	}

	public int getFromColumn() {
		return tileFromColumn;
	}

	public int getFromRow() {
		return tileFromRow;
	}

	public int getToColumn() {
		return tileToColumn;
	}

	public int getToRow() {
		return tileToRow;
	}
}
