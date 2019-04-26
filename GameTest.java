import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {

	/**
	 * testing the board initialization
	 */
	@Test
	public void testInitialize() {

		// Test 1:
		Game game = new Game(1, 1, 4);
		assertEquals(1, game.getRows());
		assertEquals(1, game.getColumns());
		assertFalse(game.getStar(0, 0));

		// Testing many in both directions
		Game game1 = new Game(4, 5, 4);
		assertEquals(4, game1.getRows());
		assertEquals(5, game1.getColumns());
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				assertFalse(game1.getStar(i, j));
			}
		}

		// testing many in one direction
		Game game2 = new Game(1, 5, 4);
		assertEquals(1, game2.getRows());
		assertEquals(5, game2.getColumns());

		for (int i = 0; i < 3; i++) {
			assertFalse(game2.getStar(0, i));
		}

		// testing in the other direction
		Game game3 = new Game(12, 1, 4);
		assertEquals(12, game3.getRows());
		assertEquals(1, game3.getColumns());

		for (int i = 0; i < 12; i++) {
			assertFalse(game3.getStar(i, 0));
		}
	}

	/**
	 * Testing that we can see if a made move is valid in the sense that its
	 * adjacent we have a separate function that tests that the move is valid in the
	 * sense the swap accomplishes something
	 */
	@Test
	public void testIsValidMove() {
		Game game = new Game(6, 6, 4);
		Move move = new Move(1, 1, 1, 1);

		// testing zero aka the same tile moving on itself
		assertFalse(game.isValidMove(move));
		// ---------------------------------------\\

		// First testing 1 away things
		// testing horizontal valid to the right
		move = new Move(1, 1, 1, 2);
		assertTrue(game.isValidMove(move));

		// testing horizontal valid to the left
		move = new Move(1, 1, 1, 0);
		assertTrue(game.isValidMove(move));

		// testing vertical valid up
		move = new Move(1, 1, 0, 1);
		assertTrue(game.isValidMove(move));

		// testing vertical valid down
		move = new Move(1, 1, 2, 1);
		assertTrue(game.isValidMove(move));

		// testing diagonality returning false
		move = new Move(1, 1, 1, 3);
		assertFalse(game.isValidMove(move));
		move = new Move(1, 1, 2, 3);
		assertFalse(game.isValidMove(move));
		move = new Move(1, 1, 0, 0);
		assertFalse(game.isValidMove(move));
		move = new Move(1, 1, 2, 0);
		assertFalse(game.isValidMove(move));
		// ------------------------------------------\\

		// Testing Many
		// same row but far away to the right
		move = new Move(1, 2, 1, 5);
		assertFalse(game.isValidMove(move));

		// same row but far away to the left
		move = new Move(1, 2, 1, 0);
		assertFalse(game.isValidMove(move));

		// same column but far away to the bottom
		move = new Move(1, 2, 5, 2);
		assertFalse(game.isValidMove(move));

		// same column but far away to the top
		move = new Move(3, 2, 0, 2);
		assertFalse(game.isValidMove(move));
	}

	/**
	 * Testing that with a swap that its valid given that it completes a row and not
	 * just a plain swap
	 */
	@Test
	public void testIsSwapValid() {
		Game game = new Game(4, 4, 0);
		game.setJewelType(0, 0, 4);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 4);

		game.setJewelType(1, 0, 4);
		game.setJewelType(1, 1, 3);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 1);

		game.setJewelType(2, 0, 4);
		game.setJewelType(2, 1, 3);
		game.setJewelType(2, 2, 2);
		game.setJewelType(2, 3, 1);

		game.setJewelType(3, 0, 1);
		game.setJewelType(3, 1, 4);
		game.setJewelType(3, 2, 4);
		game.setJewelType(3, 3, 4);
		// -------------------------------------------------\\
		// Test 0:
		// almost case, of three
		Move move = new Move(1, 1, 2, 1);
		assertFalse(game.isSwapValid(move));

		move = new Move(1, 3, 1, 2);
		assertFalse(game.isSwapValid(move));

		// Horizontal and Vertical tests 1 (end completions only)
		// testing that we can do it from the top end horizontally
		move = new Move(0, 1, 0, 2);
		assertTrue(game.isSwapValid(move));

		// vertical up and down completion test 1
		// end
		move = new Move(0, 0, 1, 0);
		assertTrue(game.isSwapValid(move));
		// middle
		move = new Move(2, 0, 1, 0);
		assertTrue(game.isSwapValid(move));
		// other end
		move = new Move(1, 0, 2, 0);
		assertTrue(game.isSwapValid(move));
		// other way around
		move = new Move(1, 0, 0, 0);
		assertTrue(game.isSwapValid(move));

		// long chain completion
		move = new Move(3, 0, 3, 1);
		assertTrue(game.isSwapValid(move));
		// checking reverse that it doesn't matter which one you choose first
		move = new Move(3, 1, 3, 0);
		assertTrue(game.isSwapValid(move));

		// -------------------------------------------------------------\\
		// completions of the middle
		game.setJewelType(0, 0, 4);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 4);

		game.setJewelType(1, 0, 2);
		game.setJewelType(1, 1, 3);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 1);

		game.setJewelType(2, 0, 2);
		game.setJewelType(2, 1, 2);
		game.setJewelType(2, 2, 1);
		game.setJewelType(2, 3, 4);

		game.setJewelType(3, 0, 3);
		game.setJewelType(3, 1, 4);
		game.setJewelType(3, 2, 3);
		game.setJewelType(3, 3, 1);

		// testing middle horizontal
		move = new Move(2, 1, 1, 1);
		assertTrue(game.isSwapValid(move));

		// testing middle vertical
		move = new Move(2, 2, 2, 3);
		assertTrue(game.isSwapValid(move));

	}

	/**
	 * Testing if reverse tile works nothing difficult
	 */
	public void testReverseTile() {
		Game game = new Game(1, 2, 0);
		game.setJewelType(0, 0, 3);
		game.setJewelType(0, 1, 2);
		game.reverseTile(0, 0, 0, 1);
		assertEquals(3, game.getJewelType(0, 1));
		assertEquals(2, game.getJewelType(0, 0));
	}

	/**
	 * Testing the move method, only that tiles are switched
	 */
	@Test
	public void testHandleMove() {
		Game game = new Game(4, 4, 0);
		game.setJewelType(0, 0, 1);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 1);

		game.setJewelType(1, 0, 2);
		game.setJewelType(1, 1, 1);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 4);

		game.setJewelType(2, 0, 3);
		game.setJewelType(2, 1, 2);
		game.setJewelType(2, 2, 1);
		game.setJewelType(2, 3, 4);

		game.setJewelType(3, 0, 2);
		game.setJewelType(3, 1, 3);
		game.setJewelType(3, 2, 4);
		game.setJewelType(3, 3, 2);

		// horizontal move side to side and it being marked
		Move move = new Move(3, 2, 3, 3);
		game.handleMove(move);
		assertEquals(1, game.getJewelType(3, 3));
		assertEquals(2, game.getJewelType(3, 2));
		assertEquals(1, game.getJewelType(2, 2));
		assertTrue(game.getStar(3, 3));
		assertTrue(game.getStar(2, 3));
		assertTrue(game.getStar(1, 3));
		assertFalse(game.getStar(3, 2));
		assertFalse(game.getStar(2, 2));

		// testing the vertical move up (doing up and down although a good test is
		// repetitive, because having tested the other involved classes does it
		// implicitly
		game.setJewelType(0, 0, 1);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 1);

		game.setJewelType(1, 0, 2);
		game.setJewelType(1, 1, 1);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 4);

		game.setJewelType(2, 0, 3);
		game.setJewelType(2, 1, 2);
		game.setJewelType(2, 2, 1);
		game.setJewelType(2, 3, 4);

		game.setJewelType(3, 0, 2);
		game.setJewelType(3, 1, 3);
		game.setJewelType(3, 2, 4);
		game.setJewelType(3, 3, 2);

		// here is the test, for somereason junit was going crazy if I didn't reset the
		// board
		Move move1 = new Move(2, 1, 1, 1);
		game.handleMove(move1);
		assertEquals(2, game.getJewelType(1, 1));
		assertEquals(1, game.getJewelType(2, 1));
		assertEquals(1, game.getJewelType(1, 0));
		assertEquals(3, game.getJewelType(1, 2));
		assertTrue(game.getStar(1, 1));
		assertTrue(game.getStar(1, 0));
		assertTrue(game.getStar(1, 2));
		assertFalse(game.getStar(2, 1));
		assertFalse(game.getStar(0, 0));

		// checking that the other ones are still marked
		assertTrue(game.getStar(3, 3));
		assertTrue(game.getStar(2, 3));
		assertTrue(game.getStar(1, 3));

		// ----------------------------------------------
		// testing the win
		Game game2 = new Game(3, 3, 0);
		game2.setJewelType(0, 0, 1);
		game2.setJewelType(0, 1, 1);
		game2.setJewelType(0, 2, 1);

		game2.setJewelType(1, 0, 1);
		game2.setJewelType(1, 1, 1);
		game2.setJewelType(1, 2, 1);

		game2.setJewelType(2, 0, 1);
		game2.setJewelType(2, 1, 1);
		game2.setJewelType(2, 2, 2);
		game2.setStar(0, 0, true);
		game2.setStar(0, 1, true);
		game2.setStar(0, 2, true);
		game2.setStar(1, 0, true);
		game2.setStar(1, 1, true);
		game2.setStar(1, 2, true);
		game2.setStar(2, 0, true);
		game2.setStar(2, 1, true);

		move = new Move(2, 2, 2, 1);
		game2.handleMove(move);
		// look at the console
		assertEquals(1, game2.getMoveCounter());

	}

	/**
	 * This checks if the board has lines of three of the same
	 */
	@Test
	public void testBoardCheck() {
		Game game = new Game(4, 4, 0);
		game.setJewelType(0, 0, 4);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 4);

		game.setJewelType(1, 0, 4);
		game.setJewelType(1, 1, 3);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 1);

		game.setJewelType(2, 0, 4);
		game.setJewelType(2, 1, 3);
		game.setJewelType(2, 2, 2);
		game.setJewelType(2, 3, 1);

		game.setJewelType(3, 0, 1);
		game.setJewelType(3, 1, 4);
		game.setJewelType(3, 2, 4);
		game.setJewelType(3, 3, 4);

		// checking the horizontal completion from the end
		game.boardCheck(3, 1);
		assertTrue(game.getStar(3, 1));
		assertTrue(game.getStar(3, 2));
		assertTrue(game.getStar(3, 3));
		// checking the one above horizontal is still false aka not marked
		assertFalse(game.getStar(2, 3));

		// checking vertical from end
		game.boardCheck(0, 0);
		assertTrue(game.getStar(0, 0));
		assertTrue(game.getStar(1, 0));
		assertTrue(game.getStar(2, 0));
		// making sure not everything changes
		assertFalse(game.getStar(3, 0));

		// -------------------------------------------\\
		game.setJewelType(0, 0, 4);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 4);

		game.setJewelType(1, 0, 4);
		game.setJewelType(1, 1, 3);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 1);

		game.setJewelType(2, 0, 4);
		game.setJewelType(2, 1, 3);
		game.setJewelType(2, 2, 2);
		game.setJewelType(2, 3, 1);

		game.setJewelType(3, 0, 1);
		game.setJewelType(3, 1, 4);
		game.setJewelType(3, 2, 4);
		game.setJewelType(3, 3, 4);

		// checking horizontal from middle
		game.boardCheck(3, 2);
		assertTrue(game.getStar(3, 1));
		assertTrue(game.getStar(3, 2));
		assertTrue(game.getStar(3, 3));
		// checking the one above horizontal is still false aka not marked
		assertFalse(game.getStar(2, 3));

		// checking vertical from middle
		game.boardCheck(1, 0);
		assertTrue(game.getStar(0, 0));
		assertTrue(game.getStar(1, 0));
		assertTrue(game.getStar(2, 0));
		// making sure not everything changes
		assertFalse(game.getStar(3, 0));

		// --------------------------------------------------\\
		game.setJewelType(0, 0, 4);
		game.setJewelType(0, 1, 2);
		game.setJewelType(0, 2, 3);
		game.setJewelType(0, 3, 4);

		game.setJewelType(1, 0, 4);
		game.setJewelType(1, 1, 3);
		game.setJewelType(1, 2, 2);
		game.setJewelType(1, 3, 1);

		game.setJewelType(2, 0, 4);
		game.setJewelType(2, 1, 3);
		game.setJewelType(2, 2, 2);
		game.setJewelType(2, 3, 1);

		game.setJewelType(3, 0, 1);
		game.setJewelType(3, 1, 4);
		game.setJewelType(3, 2, 4);
		game.setJewelType(3, 3, 4);

		// checking horizontal from other end
		game.boardCheck(3, 3);
		assertTrue(game.getStar(3, 1));
		assertTrue(game.getStar(3, 2));
		assertTrue(game.getStar(3, 3));
		// checking the one above horizontal is still false aka not marked
		assertFalse(game.getStar(2, 3));

		// checking vertical from other end
		game.boardCheck(2, 0);
		assertTrue(game.getStar(0, 0));
		assertTrue(game.getStar(1, 0));
		assertTrue(game.getStar(2, 0));
		// making sure not everything changes
		assertFalse(game.getStar(1, 1));

		// ----------------------------------------------\\
		// -----------------------------------------------\\

		// testing on bigger board with longer chains
		Game game1 = new Game(4, 6, 4);
		game1.setJewelType(0, 0, 4);
		game1.setJewelType(0, 1, 2);
		game1.setJewelType(0, 2, 3);
		game1.setJewelType(0, 3, 4);
		game1.setJewelType(0, 4, 4);
		game1.setJewelType(0, 5, 4);

		game1.setJewelType(1, 0, 4);
		game1.setJewelType(1, 1, 2);
		game1.setJewelType(1, 2, 3);
		game1.setJewelType(1, 3, 4);
		game1.setJewelType(1, 4, 4);
		game1.setJewelType(1, 5, 4);

		game1.setJewelType(2, 0, 4);
		game1.setJewelType(2, 1, 3);
		game1.setJewelType(2, 2, 1);
		game1.setJewelType(2, 3, 1);
		game1.setJewelType(2, 4, 1);
		game1.setJewelType(2, 5, 1);

		game1.setJewelType(3, 0, 4);
		game1.setJewelType(3, 1, 2);
		game1.setJewelType(3, 2, 3);
		game1.setJewelType(3, 3, 4);
		game1.setJewelType(3, 4, 4);
		game1.setJewelType(3, 5, 4);

		// checking vertical from end
		game1.boardCheck(0, 0);
		assertTrue(game1.getStar(0, 0));
		assertTrue(game1.getStar(1, 0));
		assertTrue(game1.getStar(2, 0));
		assertTrue(game1.getStar(3, 0));
		// checking that no match to the right
		assertFalse(game1.getStar(0, 1));
		assertFalse(game1.getStar(1, 1));

		// checking horizontal from end
		game1.boardCheck(2, 2);
		assertTrue(game1.getStar(2, 2));
		assertTrue(game1.getStar(2, 3));
		assertTrue(game1.getStar(2, 4));
		assertTrue(game1.getStar(2, 5));

		// checking that not all is
		assertFalse(game1.getStar(2, 1));

		// --------------------------------------------\\
		game1.setJewelType(0, 0, 4);
		game1.setJewelType(0, 1, 2);
		game1.setJewelType(0, 2, 3);
		game1.setJewelType(0, 3, 4);
		game1.setJewelType(0, 4, 4);
		game1.setJewelType(0, 5, 4);

		game1.setJewelType(1, 0, 4);
		game1.setJewelType(1, 1, 2);
		game1.setJewelType(1, 2, 3);
		game1.setJewelType(1, 3, 4);
		game1.setJewelType(1, 4, 4);
		game1.setJewelType(1, 5, 4);

		game1.setJewelType(2, 0, 4);
		game1.setJewelType(2, 1, 3);
		game1.setJewelType(2, 2, 1);
		game1.setJewelType(2, 3, 1);
		game1.setJewelType(2, 4, 1);
		game1.setJewelType(2, 5, 1);

		game1.setJewelType(3, 0, 4);
		game1.setJewelType(3, 1, 2);
		game1.setJewelType(3, 2, 3);
		game1.setJewelType(3, 3, 4);
		game1.setJewelType(3, 4, 4);
		game1.setJewelType(3, 5, 4);

		// checking horizontal from middle
		game1.boardCheck(2, 0);
		assertTrue(game1.getStar(0, 0));
		assertTrue(game1.getStar(1, 0));
		assertTrue(game1.getStar(2, 0));
		assertTrue(game1.getStar(3, 0));
		// checking that no match to the right
		assertFalse(game1.getStar(0, 1));
		assertFalse(game1.getStar(1, 1));

		// checking horizontal from middle
		game1.boardCheck(2, 3);
		assertTrue(game1.getStar(2, 2));
		assertTrue(game1.getStar(2, 3));
		assertTrue(game1.getStar(2, 4));
		assertTrue(game1.getStar(2, 5));

		// checking that not all is
		assertFalse(game1.getStar(2, 1));
//---------------------------------------------\\
		game1.setJewelType(0, 0, 4);
		game1.setJewelType(0, 1, 2);
		game1.setJewelType(0, 2, 3);
		game1.setJewelType(0, 3, 4);
		game1.setJewelType(0, 4, 4);
		game1.setJewelType(0, 5, 4);

		game1.setJewelType(1, 0, 4);
		game1.setJewelType(1, 1, 2);
		game1.setJewelType(1, 2, 3);
		game1.setJewelType(1, 3, 4);
		game1.setJewelType(1, 4, 4);
		game1.setJewelType(1, 5, 4);

		game1.setJewelType(2, 0, 4);
		game1.setJewelType(2, 1, 3);
		game1.setJewelType(2, 2, 1);
		game1.setJewelType(2, 3, 1);
		game1.setJewelType(2, 4, 1);
		game1.setJewelType(2, 5, 1);

		game1.setJewelType(3, 0, 4);
		game1.setJewelType(3, 1, 2);
		game1.setJewelType(3, 2, 3);
		game1.setJewelType(3, 3, 4);
		game1.setJewelType(3, 4, 4);
		game1.setJewelType(3, 5, 4);

		// checking horizontal from middle
		game1.boardCheck(3, 0);
		assertTrue(game1.getStar(0, 0));
		assertTrue(game1.getStar(1, 0));
		assertTrue(game1.getStar(2, 0));
		assertTrue(game1.getStar(3, 0));
		// checking that no match to the right
		assertFalse(game1.getStar(0, 1));
		assertFalse(game1.getStar(1, 1));

		// checking horizontal from middle
		game1.boardCheck(2, 5);
		assertTrue(game1.getStar(2, 2));
		assertTrue(game1.getStar(2, 3));
		assertTrue(game1.getStar(2, 4));
		assertTrue(game1.getStar(2, 5));

		// checking that not all is
		assertFalse(game1.getStar(2, 1));
	}

	/**
	 * Testing the drop function
	 */
	@Test
	public void testDrop() {
		Game game = new Game(4, 4, 0);
		game.setJewelType(0, 0, 4);
		game.setJewelType(1, 0, 2);
		game.setJewelType(2, 0, 3);
		game.setJewelType(3, 0, 4);

		// testing the vertical drop (dont test 0,0) bc its a rando number
		game.drop(3, 0);
		assertEquals(3, game.getJewelType(3, 0));
		assertEquals(2, game.getJewelType(2, 0));
		assertEquals(4, game.getJewelType(1, 0));

		// testing the vertical drop from the middle aka the one below is not changed
		game.setJewelType(0, 0, 4);
		game.setJewelType(1, 0, 2);
		game.setJewelType(2, 0, 3);
		game.setJewelType(3, 0, 4);
		game.drop(2, 0);
		assertEquals(4, game.getJewelType(3, 0));
		assertEquals(2, game.getJewelType(2, 0));
		assertEquals(4, game.getJewelType(1, 0));

		// testing from the top of the column part(not the rando though)
		game.setJewelType(0, 0, 4);
		game.setJewelType(1, 0, 2);
		game.setJewelType(2, 0, 3);
		game.setJewelType(3, 0, 4);
		game.drop(1, 0);
		assertEquals(4, game.getJewelType(3, 0));
		assertEquals(3, game.getJewelType(2, 0));
		assertEquals(4, game.getJewelType(1, 0));

		// testing the horizontal component drop is the same as the vertical so I will
		// not test
	}

}
