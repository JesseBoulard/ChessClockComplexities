package chessclock;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChessClockTest 
{

	ChessClock chessClock;

	@Before
	public void setUp() throws Exception 
	{
		chessClock = new ChessClock();
	}

	@Test
	public void convertInitialTimeToIntArrayTest() 
	{
		String initialTime = "3.45";
		int[] expected = {3, 4, 5};
		int[] actual = chessClock.returnIntegerArray(initialTime);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnSumOfTimeDigitsTest() 
	{
		int[] timeDigits = {0, 0, 0};
		int expected = 0;
		int actual = chessClock.returnSumOfTimeDigits(timeDigits);
		assertEquals(expected, actual);
	}
	
	@Test
	public void returnSumOfTimeDigitsTestWhenPassed123() 
	{
		int[] timeDigits = {1, 2, 3};
		int expected = 6;
		int actual = chessClock.returnSumOfTimeDigits(timeDigits);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTest() 
	{
		int[] player1Clock = {1, 2, 3};
		int[] player2Clock = {1, 2, 3};
		int timeLimit = 7;
		int expected = 17;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenLimitIs1000() 
	{
		int[] player1Clock = {1, 2, 3};
		int[] player2Clock = {1, 2, 3};
		int timeLimit = 1000;
		int expected = 28;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenTimesEndIn9() 
	{
		int[] player1Clock = {6, 5, 9};
		int[] player2Clock = {7, 5, 9};
		int timeLimit = 1;
		int expected = 41;
		int actual = chessClock.chessClockLargestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTest() 
	{
		int[] player1Clock = {7, 2, 9};
		int[] player2Clock = {0, 0, 1};
		int timeLimit = 1000;
		int expected = 1;
		int actual = chessClock.chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs0() 
	{
		int[] player1Clock = {7, 2, 9};
		int[] player2Clock = {0, 0, 1};
		int timeLimit = 0;
		int expected = 19;
		int actual = chessClock.chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs8() 
	{
		int[] player1Clock = {7, 2, 7};
		int[] player2Clock = {0, 0, 1};
		int timeLimit = 8;
		int expected = 9;
		int actual = chessClock.chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs14AndTensAre0() 
	{
		int[] player1Clock = {7, 0, 7};
		int[] player2Clock = {7, 0, 1};
		int timeLimit = 11;
		int expected = 14;
		int actual = chessClock.chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs129AndTensAre0() 
	{
		int[] player1Clock = {7, 0, 7};
		int[] player2Clock = {7, 0, 1};
		int timeLimit = 129;
		int expected = 12;
		int actual = chessClock.chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs129AndMinutesAre0() 
	{
		int[] player1Clock = {0, 2, 7};
		int[] player2Clock = {0, 2, 1};
		int timeLimit = 129;
		int expected = 1;
		int actual = chessClock.chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTest() 
	{
		String[] initialTime = {"9.59", "9.01"};
		int timeLimit = 129;
		int[] expected = {17, 45};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenLimitIs0() 
	{
		String[] initialTime = {"9.59", "9.01"};
		int timeLimit = 0;
		int[] expected = {33, 33};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenLimitIs1000() 
	{
		String[] initialTime = {"9.59", "9.01"};
		int timeLimit = 1000;
		int[] expected = {3, 45};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenTimeIsTiny() 
	{
		String[] initialTime = {"0.01", "0.01"};
		int timeLimit = 10;
		int[] expected = {1, 2};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenSecondsCombinedExceedLimit() 
	{
		String[] initialTime = {"2.05", "2.04"};
		int timeLimit = 7;
		int[] expected = {6, 22};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenSecondsAndTensAreZero() 
	{
		String[] initialTime = {"2.00", "2.00"};
		int timeLimit = 1000;
		int[] expected = {1, 30};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenSecondsAndMinutesAreZero() 
	{
		String[] initialTime = {"0.10", "0.10"};
		int timeLimit = 11;
		int[] expected = {1, 18};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenOneTimeIsTinyAndLimitIsSmall() 
	{
		String[] initialTime = {"1.53", "0.03"};
		int timeLimit = 4;
		int[] expected = {8, 17};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenMinusOneCreatesTwoDifferentValuesAndLimitIs1() 
	{
		String[] initialTime = {"1.10", "1.00"};
		int timeLimit = 1;
		int[] expected = {3, 16};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenMinusOneCreatesTwoDifferentValuesAndLimitIs1Reversed() 
	{
		String[] initialTime = {"1.00", "1.10"};
		int timeLimit = 1;
		int[] expected = {3, 16};
		int[] actual = chessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}
}
