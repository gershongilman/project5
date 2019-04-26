import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoveTest {

	/**
	 * Testing all the possible getters setters
	 */
	@Test
	public void testMove() {
		Move move = new Move(1, 2, 3, 4);
		assertEquals(1, move.getFromRow());
		assertEquals(2, move.getFromColumn());
		assertEquals(3, move.getToRow());
		assertEquals(4, move.getToColumn());
	}
}
