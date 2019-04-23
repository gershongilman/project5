import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

/**
 * @author Gershon Gilman This class will deal with event handling
 *
 */
public class Listener {

	// boolean
	private boolean firstClick = true;

	// original button clicked
	private TileButton tileOne = null;

	public Listener() {

	}

	public void listen(Board board) {
		Game game = board.getGame();
		Button[][] buttons = board.getButtons();
		DropShadow shadow = new DropShadow();

		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {

				buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {

					// right now it just turns the button blue. Maybe have a field that is an array
					// of color and have the button cycle through colors?
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