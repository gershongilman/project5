import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * @author Gershon Gilman This class will deal with even handling
 *
 */
public class Listener {
	private Button[][] buttons;

	public Listener(Button[][] buttons) {
		this.buttons = buttons;
	}

	public void listen() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				Button listen = buttons[i][j];

				listen.setOnAction(new EventHandler<ActionEvent>() {

					// right now it just turns the button blue. Maybe have a field that is an array
					// of color and have the button cycle through colors?
					public void handle(ActionEvent e) {
						Button b = (Button) e.getSource();
						System.out.println("You clicked button:" + b);
					}
				});
			}
		}

	}
}