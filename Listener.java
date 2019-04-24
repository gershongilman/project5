import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

/**
 * @author Gershon Gilman 
 * This class will deal with event handling and calling the logic of the game
 */
public class Listener {

	// boolean that tells us if its the first click of the mouse on a button
	private boolean firstClick = true;

	// original button clicked
	private TileButton tileOne = null;

	public Listener() {
	}

	/**
	 * This is our event class that has the event handler and deals with actions and calling the game methods to connecct logic to clicks
	 * @param board the board specs on whic we are playing
	 */
	public void listen(Board board) {
		
		// ths game logic begins (same logic though as before, we want to keep the same board
		Game game = board.getGame();
		
		// we have an array of buttons that we want to make sure the handler can access
		Button[][] buttons = board.getButtons();
		
		//for the affect of clicking
		DropShadow shadow = new DropShadow();

		//we iterate through all the buttons making sure everything is alright and executing whats need when needed.
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {
				buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {

					/**
					 * Event handler that deals with the most important part of the graphics the button click
					 */
					public void handle(ActionEvent e) {
						TileButton b1 = (TileButton) e.getSource();

						if (firstClick) {
							// showing the selection
							b1.setEffect(shadow);
							pressedButton(b1);
							firstClick = false;
						}

						else {

							Move move = new Move(tileOne.getRow(), tileOne.getColumn(), b1.getRow(), b1.getColumn());

							if (game.isValidMove(move)) {
								game.handleMove(move);

							}
							firstClick = true;
							tileOne.setEffect(null);
							board.updateBoard();
						}
					}
				});
			}
		}

	}

	/**
	 * storing the pressed button
	 */
	public void pressedButton(TileButton pressed) {
		this.tileOne = pressed;
	}

	public TileButton getPressedButton(TileButton pressed) {
		return tileOne;
	}
}