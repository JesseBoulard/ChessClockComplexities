package chessclock;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChessClockTest 
{

	ChessClock smallestClock;
	ChessClock largestClock;
	@Before
	public void setUp() throws Exception 
	{
		String[] initialTime = {"3.45", "3.45"};
		int timeLimit = 6;
		smallestClock = new ChessClock(initialTime, timeLimit, "smallestSum");
		largestClock = new ChessClock(initialTime, timeLimit, "largestSum");
	}

	@Test
	public void convertInitialTimeToIntArrayTest() 
	{
		String initialTime = "3.45";
		int[] expected = {3, 4, 5};
		int[] actual = largestClock.returnIntegerArray(initialTime);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void ifGreaterThan8Return0Test() 
	{
		int seconds = 9;
		int expected = 0;
		int actual = largestClock.ifGreaterThan8Return0(seconds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifGreaterThan8Return0TestReturn8WhenPassed8() 
	{
		int seconds = 8;
		int expected = 8;
		int actual = largestClock.ifGreaterThan8Return0(seconds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifGreaterThanLimitReturn0Test() 
	{
		int timeLimit = 8;
		int seconds = 9;
		int expected = 0;
		int actual = largestClock.ifGreaterThanLimitReturn0(seconds, timeLimit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifGreaterThanLimitReturn0TestReturn8WhenPassed8() 
	{
		int timeLimit = 8;
		int seconds = 8;
		int expected = 8;
		int actual = largestClock.ifGreaterThanLimitReturn0(seconds, timeLimit);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifMinutesAndTensAre0Return0Test() 
	{
		int minutes = 0;
		int tens = 0;
		int seconds = 8;
		int expected = 0;
		int actual = largestClock.ifMinutesAndTensAre0Return0(minutes, tens, seconds);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifMinutesAndTensAre0Return0TestWhenMinutesAre2() 
	{
		int minutes = 2;
		int tens = 0;
		int secondsToSubtract = 8;
		int expected = 8;
		int actual = largestClock.ifMinutesAndTensAre0Return0(minutes, tens, secondsToSubtract);
		assertEquals(expected, actual);
	}
	
	@Test
	public void ifMinutesAndTensAre0Return0TestWhenTensAre2() 
	{
		int minutes = 0;
		int tens = 2;
		int secondsToSubtract = 8;
		int expected = 8;
		int actual = largestClock.ifMinutesAndTensAre0Return0(minutes, tens, secondsToSubtract);
		assertEquals(expected, actual);
	}
	
	@Test
	public void secondsToBeSubtractedFromPlayer1Test() 
	{
		largestClock.initialTime[0] = "1.16";
		largestClock.initialTime[1] = "1.07";
		largestClock.secondsToBeSubtractedFromPlayer1 = 7;
		largestClock.secondsToBeSubtractedFromPlayer2 = 8;
		largestClock.timeLimit = 8;
		int expected = 0;
		int actual = largestClock.secondsToSubtractFromClock(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void secondsToBeSubtractedFromPlayer1TestWhenPlayer1SecondsIsLesser() 
	{
		largestClock.initialTime[0] = "1.25";
		largestClock.initialTime[1] = "1.26";
		largestClock.secondsToBeSubtractedFromPlayer1 = 6;
		largestClock.secondsToBeSubtractedFromPlayer2 = 7;
		largestClock.timeLimit = 8;
		int expected = 6;
		int actual = largestClock.secondsToSubtractFromClock(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void csecondsToBeSubtractedFromPlayer1TestWhenLimitIsEqualToCombinedNumbers() 
	{
		largestClock.initialTime[0] = "1.16";
		largestClock.initialTime[1] = "1.17";
		largestClock.secondsToBeSubtractedFromPlayer1 = 7;
		largestClock.secondsToBeSubtractedFromPlayer2 = 8;
		largestClock.timeLimit = 13;
		int expected = 7;
		int actual = largestClock.secondsToSubtractFromClock(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTest() 
	{
		int secondsToSubtract = 7;
		int[] timeDigits = {0, 0, 0};
		int[] expected = {0, 0, 0};
		int[] actual = largestClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed345And7Subtracted() 
	{
		int secondsToSubtract = 7;
		int[] timeDigits = {3, 4, 5};
		int[] expected = {3, 3, 8};
		int[] actual = largestClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed005And7Subtracted() 
	{
		int secondsToSubtract = 7;
		int[] timeDigits = {0, 0, 5};
		int[] expected = {0, 0, 5};
		int[] actual = largestClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed500And7Subtracted() 
	{
		int secondsToSubtract = 7;
		int[] timeDigits = {5, 0, 0};
		int[] expected = {4, 5, 3};
		int[] actual = largestClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnTimeDigitsAfterSubtractionTestWhenPassed050And7Subtracted() 
	{
		int secondsToSubtract = 7;
		int[] timeDigits = {0, 5, 0};
		int[] expected = {0, 4, 3};
		int[] actual = largestClock.returnTimeDigitsAfterSubtraction(secondsToSubtract, timeDigits);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void returnSumOfTimeDigitsTest() 
	{
		int[] timeDigits = {0, 0, 0};
		int expected = 0;
		int actual = largestClock.returnSumOfTimeDigits(timeDigits);
		assertEquals(expected, actual);
	}
	
	@Test
	public void returnSumOfTimeDigitsTestWhenPassed123() 
	{
		int[] timeDigits = {1, 2, 3};
		int expected = 6;
		int actual = largestClock.returnSumOfTimeDigits(timeDigits);
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTest() 
	{
		String[] initialTime = {"1.23", "1.23"};
		int timeLimit = 7;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int expected = 17;
		int actual = resultsArray[1];
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenLimitIs1000() 
	{
		String[] initialTime = {"1.23", "1.23"};
		int timeLimit = 1000;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int expected = 22;
		int actual = resultsArray[1];
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessClockLargestSumOfDigitsTestWhenTimesEndIn9() 
	{
		String[] initialTime = {"6.59", "7.59"};
		int timeLimit = 1;
		int expected = 41;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int actual = resultsArray[1];
	}
	
	@Test
	public void ifGreaterThanOrEqualToLimitReturnLimitTest() 
	{
		int timeLimit = 7;
		int seconds = 8;
		int expected = 7;
		int actual = smallestClock.ifGreaterThanOrEqualToLimitReturnLimit(seconds, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void ifGreaterThanOrEqualToLimitReturnLimitTestWhenPassed6Limit7() 
	{
		int timeLimit = 7;
		int seconds = 6;
		int expected = 6;
		int actual = smallestClock.ifGreaterThanOrEqualToLimitReturnLimit(seconds, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTest() 
	{
		smallestClock.initialTime[0] = "7.29";
		smallestClock.initialTime[1] = "0.01";
		int timeLimit = 1000;
		int expected = 1;
		int actual = smallestClock.chessClockSmallestSumOfDigits(smallestClock.initialTime, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs0() 
	{
		String[] initialTime = {"7.29", "0.01"};
		int timeLimit = 0;
		int expected = 19;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int actual = resultsArray[0];
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs8() 
	{
		String[] initialTime = {"7.27", "0.01"};
		int timeLimit = 8;
		int expected = 9;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int actual = resultsArray[0];
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs14AndTensAre0() 
	{
		smallestClock.initialTime[0] = "7.07";
		smallestClock.initialTime[1] = "7.01";
		int timeLimit = 11;
		int expected = 14;
		int actual = smallestClock.chessClockSmallestSumOfDigits(smallestClock.initialTime, timeLimit);
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs129AndTensAre0() 
	{
		String[] initialTime = {"7.07", "7.01"};
		int timeLimit = 129;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int expected = 12;
		int actual = resultsArray[0];
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSmallestSumOfDigitsTestWhenLimitIs129AndMinutesAre0() 
	{
		String[] initialTime = {"0.27", "0.21"};
		int timeLimit = 129;
		int[] resultsArray = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		int expected = 1;
		int actual = resultsArray[0];
		assertEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTest() 
	{
		String[] initialTime = {"9.59", "9.01"};
		int timeLimit = 129;
		int[] expected = {17, 45};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenLimitIs0() 
	{
		String[] initialTime = {"9.59", "9.01"};
		int timeLimit = 0;
		int[] expected = {33, 33};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenLimitIs1000() 
	{
		String[] initialTime = {"9.59", "9.01"};
		int timeLimit = 1000;
		int[] expected = {3, 45};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenTimeIsTiny() 
	{
		String[] initialTime = {"0.01", "0.01"};
		int timeLimit = 10;
		int[] expected = {1, 2};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenSecondsCombinedExceedLimit() 
	{
		String[] initialTime = {"2.05", "2.04"};
		int timeLimit = 7;
		int[] expected = {6, 22};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenSecondsAndTensAreZero() 
	{
		String[] initialTime = {"2.00", "2.00"};
		int timeLimit = 1000;
		int[] expected = {1, 30};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenSecondsAndMinutesAreZero() 
	{
		String[] initialTime = {"0.10", "0.10"};
		int timeLimit = 11;
		int[] expected = {1, 18};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenOneTimeIsTinyAndLimitIsSmall() 
	{
		String[] initialTime = {"1.53", "0.03"};
		int timeLimit = 4;
		int[] expected = {8, 17};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void chessClockSumOfDigitsTestWhenMinusOneCreatesTwoDifferentValuesAndLimitIs1() 
	{
		String[] initialTime = {"1.00", "1.10"};
		int timeLimit = 1;
		int[] expected = {3, 16};
		int[] actual = ChessClock.chessClockSumOfDigits(initialTime, timeLimit);
		assertArrayEquals(expected, actual);
	}
}
