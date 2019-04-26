import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * 
 * @author gershon testing the board class really nothing to test that isn't
 *         graphic
 */
public class BoardTest {

	/**
	 * testing the getters and setters
	 */
	@Test
	public void testBoard() {
		Board board = new Board(3, 2, 2);
		assertEquals(3, board.getRow());
		assertEquals(2, board.getColumn());
		assertEquals(2, board.numColors());

		// example of being able to access the game
		assertFalse(board.getGame().isGameFinished());
		assertFalse(board.getGame().getStar(1, 1));

		// changing, setters.
		board.setColumn(4);
		board.setRow(2);
		assertEquals(2, board.getRow());
		assertEquals(4, board.getColumn());
	}
}
