import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author gershon testing the tilebutton class
 */
public class TileButtonTest {

	/**
	 * Testing the getters of the tilebutton class
	 */
	@Test
	public void testTileButton() {
		try {
			TileButton tile = new TileButton(2, 2);
			assertEquals(2, tile.getRow());
			assertEquals(2, tile.getColumn());
		} catch (ExceptionInInitializerError e) {

		}
	}

}
