import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Gershon Gilman Class to create the game board
 */
public class Jewels extends Application {
	// store the number of rows
	private int rows = 8;

	private int columns = 10;

	private int colors = 4;

	/**
	 * Setting up the board
	 */
	public void start(Stage primaryStage) {

		// reading the input parameters
		Parameters params = getParameters();
		List<String> list = params.getRaw();

		// assigning the number of rows
		int tempRow = (Integer.valueOf(list.get(0)) != null) ? Integer.valueOf(list.get(0)) : 0;
		if (tempRow > 10) {
			rows = tempRow;
		}

		// assinging the number of columns
		int tempColumn = (Integer.valueOf(list.get(1)) != null) ? Integer.valueOf(list.get(1)) : 0;
		if (tempColumn > 10) {
			columns = tempColumn;
		}

		// assinging the number of columns
		int tempColors = (Integer.valueOf(list.get(2)) != null) ? Integer.valueOf(list.get(2)) : 0;
		if (tempColors > 4) {
			colors = tempColors;
		}

		// initializing our board
		Board board = new Board(rows, columns, colors);

		// showing the board
		Scene scene = new Scene(board.show(), 500, 500);
		primaryStage.setScene(scene);

		// user sees
		primaryStage.show();
	}

	/**
	 * Launch the GUI application
	 * 
	 * @param args the command line arguments, currently ignored
	 */
	public static void main(String[] args) {
		try {
			Application.launch(args);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

}
