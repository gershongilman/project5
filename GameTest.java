import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	 * adjacent
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
//		Game game = new Game(4, 4, 0);
//		for (int i = 0; i < 4; i++) {
//			game.jewelType[0][i] = i + 1;
//			System.out.print(game.jewelType[0][i]);
//		}
//		System.out.println();
//		for (int i = 3; i > -1; i--) {
//			game.jewelType[1][i] = i + 1;
//			System.out.print(game.jewelType[1][i]);
//		}
//		System.out.println();
//		for (int i = 3; i > -1; i--) {
//			game.jewelType[2][i] = i + 1;
//			System.out.print(game.jewelType[1][i]);
//		}
//		System.out.println();
//		game.jewelType[3][0] = 1;
//		game.jewelType[3][1] = 4;
//		game.jewelType[3][2] = 2;
//		game.jewelType[3][3] = 3;

		// vertical tests
		// testing that we can do it from the top end
//		 Move move = new Move(0,1,0,2);
//		 handleMove(move);
		// assertTrue(isSwapValid(,2));
	}

	/**
	 * Testing the move method, only that tiles are switched
	 */
	@Test
	public void testHandleMove() {
//		Game game = new Game(3, 3, 0);
//		game.setJewelType(0, 0, 4);
//		game.setJewelType(0, 1, 2);
//		game.setJewelType(0, 2, 3);
//		game.setJewelType(1, 0, 4);
//		game.setJewelType(1, 1, 3);
//		game.setJewelType(1, 2, 2);
//		game.setJewelType(2, 0, 4);
//		game.setJewelType(2, 1, 3);
//		game.setJewelType(2, 2, 2);
//
//		Move move = new Move(0, 1, 0, 2);
//		game.handleMove(move);
//		assertFalse(game.getJewelType(0, 2) != 2);
//		// assertEquals(1, game.getJewelType(1, 0));
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
//
//	@Test
//	void testIsGameFinished() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetColumns() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetRows() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetMoveCounter() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetJewelType() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetStar() {
//		fail("Not yet implemented");
//	}

}
