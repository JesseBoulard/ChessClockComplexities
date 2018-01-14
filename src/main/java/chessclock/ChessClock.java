package chessclock;

public class ChessClock {
	
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

	public int ifGreaterThanOrEqualToLimitReturnLimit(int secondsToSubtract, int limit) 
	{
		if (secondsToSubtract > limit) 
		{
			secondsToSubtract = limit;
		}
		return secondsToSubtract;
	}
	
	public static void main(String[] args) 
	{
		ChessClock clock = new ChessClock();
		int[] player1Clock = {1, 2, 3};
		int[] player2Clock = {1, 2, 3};
		clock.chessClockLargestSumOfDigits(player1Clock, player2Clock, 7); 
	}

}
