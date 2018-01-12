package chessclock;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChessClockTest {

	ChessClock chessClock;
	@Before
	public void setUp() throws Exception {
		chessClock = new ChessClock();
	}

	@Test
	public void convertInitialTimeToIntArrayTest() {
		String initialTime = "3.45";
		int[] expected = {3, 4, 5};
		int[] actual = chessClock.returnIntegerArray(initialTime);
		assertArrayEquals(expected, actual);
	}

}
