package chessclock;

public class ChessClock {

	public int[] chessClockSumOfDigits(String[] initialTime, int timeLimit) 
	{
		int [] player1Clock = returnIntegerArray(initialTime[0]);
		int [] player2Clock = returnIntegerArray(initialTime[1]);
		int [] player1ClockDuplicate = returnIntegerArray(initialTime[0]);
		int [] player2ClockDuplicate = returnIntegerArray(initialTime[1]);
		int[] sumOfDigitsArray = {chessClockSmallestSumOfDigits(player1Clock, player2Clock, timeLimit), chessClockLargestSumOfDigits(player1ClockDuplicate, player2ClockDuplicate, timeLimit)};
		return sumOfDigitsArray;
	}

	protected int chessClockLargestSumOfDigits(int [] player1Clock, int [] player2Clock, int timeLimit) 
	{
		int secondsToBeSubtractedFromPlayer1 = secondsToSubtractFromPlayerToGetLargestSum(player1Clock, timeLimit);
		int secondsToBeSubtractedFromPlayer2 = secondsToSubtractFromPlayerToGetLargestSum(player2Clock, timeLimit);
		int player1SumBeforeSubtraction = player1Clock[0] + player1Clock[1] + player1Clock[2];
		int player2SumBeforeSubtraction = player2Clock[0] + player2Clock[1] + player2Clock[2];
		int player1SumAfterSubtraction = largestSumOfClockDigitsAfterSubtraction(secondsToBeSubtractedFromPlayer1, player1Clock, timeLimit);
		int player2SumAfterSubtraction = largestSumOfClockDigitsAfterSubtraction(secondsToBeSubtractedFromPlayer2, player2Clock, timeLimit);
		if (secondsToBeSubtractedFromPlayer1 + secondsToBeSubtractedFromPlayer2 > timeLimit
				&& (player1SumAfterSubtraction - player1SumBeforeSubtraction) >= (player2SumAfterSubtraction - player2SumBeforeSubtraction))
		{
			player2SumAfterSubtraction = player2SumBeforeSubtraction;
		}
		else if (secondsToBeSubtractedFromPlayer1 + secondsToBeSubtractedFromPlayer2 > timeLimit
				&& (player1SumAfterSubtraction - player1SumBeforeSubtraction) <= (player2SumAfterSubtraction - player2SumBeforeSubtraction))
		{
			player1SumAfterSubtraction = player1SumBeforeSubtraction;
		}

		return player1SumAfterSubtraction + player2SumAfterSubtraction;
	}

	protected int chessClockSmallestSumOfDigits(int [] player1Clock, int [] player2Clock, int timeLimit) 
	{
		int secondsToBeSubtractedFromPlayer1 = player1Clock[player1Clock.length - 1];
		int secondsToBeSubtractedFromPlayer2 = player2Clock[player2Clock.length - 1];
		secondsToBeSubtractedFromPlayer1 = ifGreaterThanOrEqualToLimitReturnLimit(secondsToBeSubtractedFromPlayer1, timeLimit);
		timeLimit -= secondsToBeSubtractedFromPlayer1;
		secondsToBeSubtractedFromPlayer2 = ifGreaterThanOrEqualToLimitReturnLimit(secondsToBeSubtractedFromPlayer2, timeLimit);
		timeLimit -= secondsToBeSubtractedFromPlayer2;
		int tensToSubtractFromPlayer1 = player1Clock[player1Clock.length - 2];
		int tensToSubtractFromPlayer2 = player2Clock[player2Clock.length - 2];
		tensToSubtractFromPlayer1 = ifGreaterThanOrEqualToLimitReturnLimit(tensToSubtractFromPlayer1, timeLimit / 10);
		timeLimit -= (tensToSubtractFromPlayer1 * 10);
		tensToSubtractFromPlayer2 = ifGreaterThanOrEqualToLimitReturnLimit(tensToSubtractFromPlayer2, timeLimit / 10);
		timeLimit -= (tensToSubtractFromPlayer2 * 10);
		int minutesToSubtractFromPlayer1 = player1Clock[player1Clock.length - 3];
		int minutesToSubtractFromPlayer2 = player2Clock[player2Clock.length - 3];
		minutesToSubtractFromPlayer1 = ifGreaterThanOrEqualToLimitReturnLimit(minutesToSubtractFromPlayer1, timeLimit / 60);
		timeLimit -= (minutesToSubtractFromPlayer1 * 60);
		minutesToSubtractFromPlayer2 = ifGreaterThanOrEqualToLimitReturnLimit(minutesToSubtractFromPlayer2, timeLimit / 60);

		player1Clock[player1Clock.length - 1] -= secondsToBeSubtractedFromPlayer1;
		player1Clock[player1Clock.length - 2] -= tensToSubtractFromPlayer1;
		player1Clock[player1Clock.length - 3] -= minutesToSubtractFromPlayer1;

		player2Clock[player2Clock.length - 1] -= secondsToBeSubtractedFromPlayer2;
		player2Clock[player2Clock.length - 2] -= tensToSubtractFromPlayer2;
		player2Clock[player2Clock.length - 3] -= minutesToSubtractFromPlayer2;

		int player1ClockSumOfDigits = returnSumOfTimeDigits(player1Clock);
		int player2ClockSumOfDigits = returnSumOfTimeDigits(player2Clock);
		int smallestSumOfDigits = player1ClockSumOfDigits + player2ClockSumOfDigits;
		if (smallestSumOfDigits == 0)
		{
			smallestSumOfDigits = 1;
		}

		return smallestSumOfDigits;
	}

	protected int[] returnIntegerArray(String initialTime)
	{
		String[] timeChars = initialTime.replace(".", "").split("");
		int[] timeDigits = new int[timeChars.length];
		for (int i = 0; i < timeChars.length; i++) 
		{
			timeDigits[i] = Integer.parseInt(timeChars[i]);
		}
		return timeDigits;
	}

	protected int largestSumOfClockDigitsAfterSubtraction(int secondsToSubtractFromPlayer, int[] playerClock, int timeLimit)
	{
		int minutes = playerClock[0];
		int tens = playerClock[1];
		int seconds = playerClock[2];
		int minutesAfterSubtraction = minutes;
		int tensAfterSubtraction = tens;
		int secondsAfterSubtraction = seconds;
		int secondsToSubtract = playerClock[2] + 1;
		int tensToSubtract = 1;
		int minutesToSubtract = 0;
		if (minutes + tens > 0)
		{
			if (secondsToSubtractFromPlayer > seconds)
			{
				tensToSubtract += (secondsToSubtractFromPlayer - secondsToSubtract) / 10;

				tensAfterSubtraction = tens - tensToSubtract;
				if (tensToSubtract >= tens && minutes > 0)
				{
					minutesToSubtract = 1;
					tensAfterSubtraction = 5;
				}
				secondsAfterSubtraction = 9;
				minutesAfterSubtraction = minutes - minutesToSubtract;
			}
		}

		int returnValue = minutesAfterSubtraction + tensAfterSubtraction + secondsAfterSubtraction;
		if ((minutes + tens + seconds) > (returnValue))
		{
			returnValue = minutes + tens + seconds;
		}
		return returnValue;
	}

	protected int secondsToSubtractFromPlayerToGetLargestSum(int[] playerClock, int timeLimit)
	{
		int secondsToSubtractFromPlayer = 0;
		int minutes = playerClock[0];
		int tens = playerClock[1];
		int seconds = playerClock[2];
		if (minutes > 0 && (minutes - 1 + 5 + 9) > (minutes + tens - 1 + 9)
				&& ((tens * 10) + seconds + 1) <= timeLimit)
		{
			secondsToSubtractFromPlayer = (tens * 10) + seconds + 1;
		}
		else
		{
			secondsToSubtractFromPlayer = seconds + 1;
		}
		if (secondsToSubtractFromPlayer > timeLimit)
		{
			secondsToSubtractFromPlayer = 0;
		}
		return secondsToSubtractFromPlayer;	
	}

	protected int returnSumOfTimeDigits(int[] timeDigits) 
	{
		int sum = 0;
		for(int i = 0; i < timeDigits.length; i++)
		{
			sum += timeDigits[i];
		}
		return sum;
	}

	protected int ifGreaterThanOrEqualToLimitReturnLimit(int secondsToSubtract, int timeLimit) 
	{
		if (secondsToSubtract > timeLimit) 
		{
			secondsToSubtract = timeLimit;
		}
		return secondsToSubtract;
		// in order for this method to be used correctly on both clocks, use "timeLimit -= secondsToSubtract" after this method;
	}

	public static void main(String[] args) 
	{
		ChessClock clock = new ChessClock();
		String[] initialTime = {"7.07", "7.01"};
		int timeLimit = 129;
		int[] resultsArray = clock.chessClockSumOfDigits(initialTime, timeLimit);
		System.out.println(resultsArray[0]);
		System.out.println(resultsArray[1]);
	}

}
