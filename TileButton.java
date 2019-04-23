import javafx.scene.control.Button;

/**
 * 
 * @author gershon This creates a button type for us that has the ability to
 *         store the location of the button which will make event handling
 *         easier
 */
public class TileButton extends Button {
	private int row;
	private int column;

	public TileButton(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	/**
	 * getter of number of column
	 * @return column the number set by user
	 */
	public int getColumn() {
		return column;
	}
}
