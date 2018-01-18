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
		int secondsToBeSubtractedFromPlayer1 = player1Clock[player1Clock.length - 1] + 1;
		int secondsToBeSubtractedFromPlayer2 = player2Clock[player2Clock.length - 1] + 1;
		secondsToBeSubtractedFromPlayer1 = ifGreaterThan8Return0(secondsToBeSubtractedFromPlayer1);
		secondsToBeSubtractedFromPlayer2 = ifGreaterThan8Return0(secondsToBeSubtractedFromPlayer2);
		secondsToBeSubtractedFromPlayer1 = ifGreaterThanLimitReturn0(secondsToBeSubtractedFromPlayer1, timeLimit);
		secondsToBeSubtractedFromPlayer2 = ifGreaterThanLimitReturn0(secondsToBeSubtractedFromPlayer2, timeLimit);
		secondsToBeSubtractedFromPlayer1 = ifMinutesAndTensAre0Return0(player1Clock[player1Clock.length - 3], player1Clock[player1Clock.length - 2], secondsToBeSubtractedFromPlayer1);
		secondsToBeSubtractedFromPlayer2 = ifMinutesAndTensAre0Return0(player2Clock[player2Clock.length - 3], player2Clock[player2Clock.length - 2], secondsToBeSubtractedFromPlayer2);
		secondsToBeSubtractedFromPlayer1 = secondsToBeSubtractedFromPlayer1(player1Clock, player2Clock, secondsToBeSubtractedFromPlayer1, secondsToBeSubtractedFromPlayer2, timeLimit);
		secondsToBeSubtractedFromPlayer2 = secondsToBeSubtractedFromPlayer2(player1Clock, player2Clock, secondsToBeSubtractedFromPlayer1, secondsToBeSubtractedFromPlayer2, timeLimit);

		player1Clock = returnTimeDigitsAfterSubtraction(secondsToBeSubtractedFromPlayer1, player1Clock);
		int player1ClockSumOfDigits = returnSumOfTimeDigits(player1Clock);

		player2Clock = returnTimeDigitsAfterSubtraction(secondsToBeSubtractedFromPlayer2, player2Clock);
		int player2ClockSumOfDigits = returnSumOfTimeDigits(player2Clock);

		return player1ClockSumOfDigits + player2ClockSumOfDigits;
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

	protected int ifGreaterThan8Return0(int secondsToSubtract)
	{
		if (secondsToSubtract > 8) 
		{
			secondsToSubtract = 0;
		}
		return secondsToSubtract;
	}

	protected int ifGreaterThanLimitReturn0(int secondsToSubtract, int timeLimit)
	{
		if (secondsToSubtract > timeLimit) 
		{
			secondsToSubtract = 0;
		}
		return secondsToSubtract;
	}

	protected int ifMinutesAndTensAre0Return0(int minutes, int tens, int secondsToSubtract)
	{
		if (minutes == 0 && tens == 0) 
		{
			secondsToSubtract = 0;
		}
		return secondsToSubtract;
	}

	protected int secondsToBeSubtractedFromPlayer1(int [] player1Clock, int [] player2Clock, int secondsToBeSubtractedFromPlayer1, int secondsToBeSubtractedFromPlayer2, int timeLimit)
	{
		if (secondsToBeSubtractedFromPlayer1 + secondsToBeSubtractedFromPlayer2 > timeLimit)
		{
			if (secondsToBeSubtractedFromPlayer1 >= secondsToBeSubtractedFromPlayer2)
			{
				secondsToBeSubtractedFromPlayer1 = 0;
			}
			if (player2Clock[player2Clock.length - 2] == 0 && player2Clock[player2Clock.length - 3] > 0
					&& player1Clock[player1Clock.length - 2] > 0)
			{
				secondsToBeSubtractedFromPlayer1 = 0;
			}
		}
		return secondsToBeSubtractedFromPlayer1;
	}
	
	protected int secondsToBeSubtractedFromPlayer2(int [] player1Clock, int [] player2Clock, int secondsToBeSubtractedFromPlayer1, int secondsToBeSubtractedFromPlayer2, int timeLimit)
	{
		if (secondsToBeSubtractedFromPlayer1 + secondsToBeSubtractedFromPlayer2 > timeLimit)
		{
			if (secondsToBeSubtractedFromPlayer2 >= secondsToBeSubtractedFromPlayer1)
			{
				secondsToBeSubtractedFromPlayer2 = 0;
			}
			if (player1Clock[player1Clock.length - 2] == 0 && player1Clock[player1Clock.length - 3] > 0
					&& player2Clock[player2Clock.length - 2] > 0)
			{
				secondsToBeSubtractedFromPlayer2 = 0;
			}
		}
		return secondsToBeSubtractedFromPlayer2;
	}

	protected int[] returnTimeDigitsAfterSubtraction(int secondsToSubtract, int[] timeDigits) 
	{
		if (secondsToSubtract > 0)
		{
			if (timeDigits[timeDigits.length - 2] == 0
					&& timeDigits[timeDigits.length - 3] > 0) 
			{
				timeDigits[timeDigits.length - 3] -= 1;
				timeDigits[timeDigits.length - 2] = 5;
				timeDigits[timeDigits.length - 1] = 10 + (timeDigits[timeDigits.length - 1] - secondsToSubtract);
			} 
			else if (timeDigits[timeDigits.length - 2] > 0) 
			{
				timeDigits[timeDigits.length - 1] = 10 + (timeDigits[timeDigits.length - 1] - secondsToSubtract);
				timeDigits[timeDigits.length - 2] -= 1;
			}
		}
		return timeDigits;
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
