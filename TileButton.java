import javafx.scene.control.Button;

/**
 * 
 * @author gershon 
 * This creates a button type for us that has the ability to
 * store the location of the button which will make event handling easier
 */
public class TileButton extends Button {
	//stores the row of the button
	private int row;
	
	//stores the column of the button
	private int column;

	/**
	 * Initializing everything
	 * @param row the row of tile
	 * @param column the column of the tile
	 */
	public TileButton(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Getter for the row
	 * @return the row of the button is in
	 */
	public int getRow() {
		return row;
	}

	/**
	 * getter of number of column
	 * @return column the number  of the button
	 */
	public int getColumn() {
		return column;
	}
}
