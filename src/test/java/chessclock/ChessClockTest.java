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
	
	@Test
	public void ifMinutesAndTensAre0Return0Test() {
		int minutes = 0;
		int tens = 0;
		int seconds = 8;
		int expected = 0;
		int actual = chessClock.ifMinutesAndTensAre0Return0(minutes, tens, seconds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifMinutesAndTensAre0Return0TestWhenMinutesAre2() {
		int minutes = 2;
		int tens = 0;
		int secondsToSubtract = 8;
		int expected = 8;
		int actual = chessClock.ifMinutesAndTensAre0Return0(minutes, tens, secondsToSubtract);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifMinutesAndTensAre0Return0TestWhenTensAre2() {
		int minutes = 0;
		int tens = 2;
		int secondsToSubtract = 8;
		int expected = 8;
		int actual = chessClock.ifMinutesAndTensAre0Return0(minutes, tens, secondsToSubtract);
		assertEquals(expected, actual);
	}
	
	@Test
	public void secondsToSubtractFromPlayer1Test() {
		int secondsToSubtractFromPlayer1 = 7;
		int secondsToSubtractFromPlayer2 = 6;
		int limit = 8;
		int expected = 0;
		int actual = chessClock.secondsToSubtractFromClock(secondsToSubtractFromPlayer1, secondsToSubtractFromPlayer2, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void secondsToSubtractFromPlayer1TestWhenPlayer1SecondsIsLesser() {
		int secondsToSubtractFromPlayer1 = 5;
		int secondsToSubtractFromPlayer2 = 6;
		int limit = 8;
		int expected = 5;
		int actual = chessClock.secondsToSubtractFromClock(secondsToSubtractFromPlayer1, secondsToSubtractFromPlayer2, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void secondsToSubtractFromPlayer1TestWhenLimitIsEqualToCombinedNumbers() {
		int secondsToSubtractFromPlayer1 = 7;
		int secondsToSubtractFromPlayer2 = 6;
		int limit = 13;
		int expected = 7;
		int actual = chessClock.secondsToSubtractFromClock(secondsToSubtractFromPlayer1, secondsToSubtractFromPlayer2, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTest() {
		int secondsToSubtract = 7;
		int[] timeDigits = {0, 0, 0};
		int[] expected = {0, 0, 0};
		int[] actual = chessClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed345And7Subtracted() {
		int secondsToSubtract = 7;
		int[] timeDigits = {3, 4, 5};
		int[] expected = {3, 3, 8};
		int[] actual = chessClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed005And7Subtracted() {
		int secondsToSubtract = 7;
		int[] timeDigits = {0, 0, 5};
		int[] expected = {0, 0, 5};
		int[] actual = chessClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed500And7Subtracted() {
		int secondsToSubtract = 7;
		int[] timeDigits = {5, 0, 0};
		int[] expected = {4, 5, 3};
		int[] actual = chessClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed050And7Subtracted() {
		int secondsToSubtract = 7;
		int[] timeDigits = {0, 5, 0};
		int[] expected = {0, 4, 3};
		int[] actual = chessClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnSumOfTimeDigitsTest() {
		int[] timeDigits = {0, 0, 0};
		int expected = 0;
		int actual = chessClock.returnSumOfTimeDigits(timeDigits);
		assertEquals(expected, actual);
	}
	
	@Test
	public void returnSumOfTimeDigitsTestWhenPassed123() {
		int[] timeDigits = {1, 2, 3};
		int expected = 6;
		int actual = chessClock.returnSumOfTimeDigits(timeDigits);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTest() {
		int[] player1Clock = {1, 2, 3};
		int[] player2Clock = {1, 2, 3};
		int limit = 7;
		int expected = 17;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenLimitIs1000() {
		int[] player1Clock = {1, 2, 3};
		int[] player2Clock = {1, 2, 3};
		int limit = 1000;
		int expected = 22;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenTimesEndIn9() {
		int[] player1Clock = {6, 5, 9};
		int[] player2Clock = {7, 5, 9};
		int limit = 1;
		int expected = 41;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenTimesAreNegative() {
		int[] player1Clock = {-1, -5, -9};
		int[] player2Clock = {-1, -5, -9};
		int limit = 1;
		int expected = -30;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, limit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenTimesAreMixed() {
		int[] player1Clock = {-1, -5, 9};
		int[] player2Clock = {-1, -5, 9};
		int limit = 14;
		int expected = 6;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, limit);
		assertEquals(expected, actual);
	}
}
