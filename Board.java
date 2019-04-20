import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Gershon Gilman Class to create the game board
 */
public class Board extends Application {
	private int row = 8;
	private int column = 2;

	public Board() {
		this.row = getRow();
		this.column = getColumn();
	}

	public void start(Stage primaryStage) {
		GridPane gridpane = new GridPane();
		System.out.println(getRow());
		System.out.println(getColumn());
		for (int i = 0; i < getColumn(); i++) {
			for (int j = 0; j < getRow(); j++) {
				Button button = new Button(i + "," + j);
				gridpane.add(button, i, j);

				button.setOnAction(new EventHandler<ActionEvent>() {
					// right now it just turns the button blue. Maybe have a field that is an array
					// of color and have the button cycle through colors?
					public void handle(ActionEvent e) {
						System.out.println("You clicked button:");
					}
				});
			}
		}
//		Button button = new Button("Describe");
//		gridpane.add(button, 1, 2);
		// button.setOnAction(new ProcessClick());

//		button.setOnAction(new EventHandler<ActionEvent>() {
//
//			// right now it just turns the button blue. Maybe have a field that is an array
//			// of color and have the button cycle through colors?
//			public void handle(ActionEvent e) {
//				System.out.println("You clicked button 1");
//			}
//		});
		Scene scene = new Scene(gridpane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * getter for number of rows user wants
	 * 
	 * @return row the number of rows
	 */
	public int getRow() {
		return row;
	}

	/**
	 * setter for number of rows
	 * 
	 * @param row the number of rows
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * getter of number of columns
	 * 
	 * @return column the number set by user
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * setter for number of columns
	 * 
	 * @param column the number set by user
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Launch the GUI application
	 * 
	 * @param args the command line arguments, currently ignored
	 */
	public static void main(String[] args) {
		Application.launch(args);
		String inputRow = args[0];
		String inputColumn = args[1];
		Board board = new Board();
		board.setRow(Integer.parseInt(inputRow));
		board.setColumn(Integer.parseInt(inputColumn));

	}

}
