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

	@Test
	public void ifGreaterThan8Return0Test() {
		int seconds = 9;
		int expected = 0;
		int actual = chessClock.ifGreaterThan8Return0(seconds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifGreaterThan8Return0TestReturn8WhenPassed8() {
		int seconds = 8;
		int expected = 8;
		int actual = chessClock.ifGreaterThan8Return0(seconds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifGreaterThanLimitReturn0Test() {
		int limit = 8;
		int seconds = 9;
		int expected = 0;
		int actual = chessClock.ifGreaterThanLimitReturn0(seconds, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifGreaterThanLimitReturn0TestReturn8WhenPassed8() {
		int limit = 8;
		int seconds = 8;
		int expected = 8;
		int actual = chessClock.ifGreaterThanLimitReturn0(seconds, limit);
		assertEquals(expected, actual);
	}
}
