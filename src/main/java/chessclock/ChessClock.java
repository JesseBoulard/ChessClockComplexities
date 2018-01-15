package chessclock;

public class ChessClock {

	public int[] chessClockSumOfDigits(String[] initialTime, int limit) 
	{
		int [] player1Clock = returnIntegerArray(initialTime[0]);
		int [] player2Clock = returnIntegerArray(initialTime[1]);
		int [] player1ClockCopy = returnIntegerArray(initialTime[0]);
		int [] player2ClockCopy = returnIntegerArray(initialTime[1]);
		int[] sumOfDigitsArray = {chessClockSmallestSumOfDigits(player1Clock, player2Clock, limit), chessClockLargestSumOfDigits(player1ClockCopy, player2ClockCopy, limit)};
		return sumOfDigitsArray;
	}

	protected int chessClockLargestSumOfDigits(int[] player1Clock, int[] player2Clock, int limit) 
	{
		int secondsToSubtractFromPlayer1 = player1Clock[player1Clock.length - 1] + 1;
		int secondsToSubtractFromPlayer2 = player2Clock[player2Clock.length - 1] + 1;

		secondsToSubtractFromPlayer1 = ifGreaterThan8Return0(secondsToSubtractFromPlayer1);
		secondsToSubtractFromPlayer1 = ifGreaterThanLimitReturn0(secondsToSubtractFromPlayer1, limit);
		secondsToSubtractFromPlayer1 = ifMinutesAndTensAre0Return0(player1Clock[player1Clock.length - 3], player1Clock[player1Clock.length - 2], secondsToSubtractFromPlayer1);
		secondsToSubtractFromPlayer1 = secondsToSubtractFromClock(secondsToSubtractFromPlayer1, secondsToSubtractFromPlayer2, limit);
		player1Clock = returnTimeDigitsAfterSubtraction(secondsToSubtractFromPlayer1, player1Clock);
		int player1ClockSumOfDigits = returnSumOfTimeDigits(player1Clock);

		secondsToSubtractFromPlayer2 = ifGreaterThan8Return0(secondsToSubtractFromPlayer2);
		secondsToSubtractFromPlayer2 = ifGreaterThanLimitReturn0(secondsToSubtractFromPlayer2, limit);
		secondsToSubtractFromPlayer2 = ifMinutesAndTensAre0Return0(player2Clock[player2Clock.length - 3], player2Clock[player2Clock.length - 2], secondsToSubtractFromPlayer2);
		secondsToSubtractFromPlayer2 = secondsToSubtractFromClock(secondsToSubtractFromPlayer2, secondsToSubtractFromPlayer1, limit);
		player1Clock = returnTimeDigitsAfterSubtraction(secondsToSubtractFromPlayer2, player2Clock);
		int player2ClockSumOfDigits = returnSumOfTimeDigits(player2Clock);

		return player1ClockSumOfDigits + player2ClockSumOfDigits;
	}

	protected int chessClockSmallestSumOfDigits(int[] player1Clock, int[] player2Clock, int limit) 
	{
		int secondsToSubtractFromPlayer1 = player1Clock[player1Clock.length - 1];
		int secondsToSubtractFromPlayer2 = player2Clock[player2Clock.length - 1];
		secondsToSubtractFromPlayer1 = ifGreaterThanOrEqualToLimitReturnLimit(secondsToSubtractFromPlayer1, limit);
		limit -= secondsToSubtractFromPlayer1;
		secondsToSubtractFromPlayer2 = ifGreaterThanOrEqualToLimitReturnLimit(secondsToSubtractFromPlayer2, limit);
		limit -= secondsToSubtractFromPlayer2;
		int tensToSubtractFromPlayer1 = player1Clock[player1Clock.length - 2];
		int tensToSubtractFromPlayer2 = player2Clock[player2Clock.length - 2];
		tensToSubtractFromPlayer1 = ifGreaterThanOrEqualToLimitReturnLimit(tensToSubtractFromPlayer1, limit / 10);
		limit -= (tensToSubtractFromPlayer1 * 10);
		tensToSubtractFromPlayer2 = ifGreaterThanOrEqualToLimitReturnLimit(tensToSubtractFromPlayer2, limit / 10);
		limit -= (tensToSubtractFromPlayer2 * 10);
		int minutesToSubtractFromPlayer1 = player1Clock[player1Clock.length - 3];
		int minutesToSubtractFromPlayer2 = player2Clock[player2Clock.length - 3];
		minutesToSubtractFromPlayer1 = ifGreaterThanOrEqualToLimitReturnLimit(minutesToSubtractFromPlayer1, limit / 60);
		limit -= (minutesToSubtractFromPlayer1 * 60);
		minutesToSubtractFromPlayer2 = ifGreaterThanOrEqualToLimitReturnLimit(minutesToSubtractFromPlayer2, limit / 60);

		player1Clock[player1Clock.length - 1] -= secondsToSubtractFromPlayer1;
		player1Clock[player1Clock.length - 2] -= tensToSubtractFromPlayer1;
		player1Clock[player1Clock.length - 3] -= minutesToSubtractFromPlayer1;

		player2Clock[player2Clock.length - 1] -= secondsToSubtractFromPlayer2;
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

	protected int ifGreaterThanLimitReturn0(int secondsToSubtract, int limit)
	{
		if (secondsToSubtract > limit) 
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

	protected int secondsToSubtractFromClock(int secondsToSubtractFromPlayer1, int secondsToSubtractFromPlayer2, int limit)
	{
		if (secondsToSubtractFromPlayer1 + secondsToSubtractFromPlayer2 > limit && secondsToSubtractFromPlayer1 >= secondsToSubtractFromPlayer2 && secondsToSubtractFromPlayer2 > 0) 
		{
			secondsToSubtractFromPlayer1 = 0;
		}
		return secondsToSubtractFromPlayer1;
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

	protected int ifGreaterThanOrEqualToLimitReturnLimit(int secondsToSubtract, int limit) 
	{
		if (secondsToSubtract > limit) 
		{
			secondsToSubtract = limit;
		}
		return secondsToSubtract;
		// in order for this method to be used correctly on both clocks, use "limit -= secondsToSubtract" after this method;
	}

	public static void main(String[] args) 
	{
		ChessClock clock = new ChessClock();
		String[] initialTime = {"9.59", "9.01"};
		int limit = 129;
		int[] clockDigitsArray = clock.chessClockSumOfDigits(initialTime, limit);
		System.out.println(clockDigitsArray[0]);
		System.out.println(clockDigitsArray[1]);
	}

}
