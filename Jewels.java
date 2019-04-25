import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Gershon Gilman 
 * This class creates the game, initializes, and take the user input
 */
public class Jewels extends Application {
	TextField text = new TextField();

	// store the number of rows (default)
	private int rows = 8;

	// stores the number of columns (default)
	private int columns = 10;

	// storest the number of colors (default)
	private int colors = 4;

	// stores the borderpane
	BorderPane borderPane;

	/**
	 * Setting up the board
	 */
	public void start(Stage primaryStage) {

		// reading the input parameters
		Parameters params = getParameters();

		// stores the list from the parameter reading
		List<String> list = params.getRaw();

		// if the list is empty we bypass setting them and we just go straight to
		// initialization
		if (!list.isEmpty()) {

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
		}

		// initializing our board
		Board board = new Board(rows, columns, colors);
		borderPane = new BorderPane();
		Label label = new Label();
		label.setText("  Welcome to Bejeweled  ");
		label.setCenterShape(true);
		
		label.setMaxWidth(Double.POSITIVE_INFINITY);
	    label.setMaxHeight(Double.POSITIVE_INFINITY);
		label.setAlignment(Pos.TOP_CENTER);
		borderPane.setTop(label);
		borderPane.setCenter(board.show());

		// adding the board to the scene
		Scene scene = new Scene(borderPane);

		// showing the board and the game
		primaryStage.setScene(scene);

		// user sees
		primaryStage.show();

	}

	public void setText(int counter) {
		text.appendText(Integer.toString(counter));
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
