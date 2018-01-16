package chessclock;

public class ChessClock {

	int [] player1Clock;
	int [] player2Clock;
	String[] initialTime;
	int timeLimit;
	int secondsToBeSubtractedFromPlayer1;
	int secondsToBeSubtractedFromPlayer2;

	public ChessClock(String[] initialTime, int timeLimit, String largestSumOrSmallestSum)
	{
		this.player1Clock = returnIntegerArray(initialTime[0]);
		this.player2Clock = returnIntegerArray(initialTime[1]);
		this.initialTime = initialTime;
		this.timeLimit = timeLimit;	
		if (largestSumOrSmallestSum.equals("largestSum"))
		{
			this.secondsToBeSubtractedFromPlayer1 = player1Clock[player1Clock.length - 1] + 1;	
			this.secondsToBeSubtractedFromPlayer2 = player2Clock[player2Clock.length - 1] + 1;	
		}
		else if (largestSumOrSmallestSum.equals("smallestSum"))
		{
			this.secondsToBeSubtractedFromPlayer1 = player1Clock[player1Clock.length - 1];	
			this.secondsToBeSubtractedFromPlayer2 = player2Clock[player2Clock.length - 1];	
		}
	}

	public int[] chessClockSumOfDigits(String[] initialTime, int timeLimit) 
	{
		int[] sumOfDigitsArray = {chessClockSmallestSumOfDigits(initialTime, timeLimit), chessClockLargestSumOfDigits(initialTime, timeLimit)};
		return sumOfDigitsArray;
	}

	protected int chessClockLargestSumOfDigits(String[] initialTime, int timeLimit) 
	{
		ChessClock chessClock = new ChessClock(initialTime, timeLimit, "largestSum");
		secondsToBeSubtractedFromPlayer1 = ifGreaterThan8Return0(secondsToBeSubtractedFromPlayer1);
		secondsToBeSubtractedFromPlayer2 = ifGreaterThan8Return0(secondsToBeSubtractedFromPlayer2);
		secondsToBeSubtractedFromPlayer1 = ifGreaterThanLimitReturn0(secondsToBeSubtractedFromPlayer1, timeLimit);
		secondsToBeSubtractedFromPlayer2 = ifGreaterThanLimitReturn0(secondsToBeSubtractedFromPlayer2, timeLimit);
		secondsToBeSubtractedFromPlayer1 = ifMinutesAndTensAre0Return0(player1Clock[player1Clock.length - 3], player1Clock[player1Clock.length - 2], secondsToBeSubtractedFromPlayer1);
		secondsToBeSubtractedFromPlayer2 = ifMinutesAndTensAre0Return0(player2Clock[player2Clock.length - 3], player2Clock[player2Clock.length - 2], secondsToBeSubtractedFromPlayer2);
		secondsToBeSubtractedFromPlayer1 = secondsToSubtractFromClock(1);
		secondsToBeSubtractedFromPlayer2 = secondsToSubtractFromClock(2);

		player1Clock = returnTimeDigitsAfterSubtraction(secondsToBeSubtractedFromPlayer1, player1Clock);
		int player1ClockSumOfDigits = returnSumOfTimeDigits(player1Clock);

		player1Clock = returnTimeDigitsAfterSubtraction(secondsToBeSubtractedFromPlayer2, player2Clock);
		int player2ClockSumOfDigits = returnSumOfTimeDigits(player2Clock);

		return player1ClockSumOfDigits + player2ClockSumOfDigits;
	}

	protected int chessClockSmallestSumOfDigits(String[] initialTime, int timeLimit) 
	{
		ChessClock chessClock = new ChessClock(initialTime, timeLimit, "smallestSum");
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

	protected int secondsToSubtractFromClock(int whichPlayer)
	{
		int returnValue = 0;
		if (whichPlayer == 1)
		{
			if (whichPlayersClockToSubtractFrom(initialTime, timeLimit) == 2) 
			{
				secondsToBeSubtractedFromPlayer1 = 0;
				returnValue = secondsToBeSubtractedFromPlayer1;
			}
		}
		else if (whichPlayer == 2)
		{
			if (whichPlayersClockToSubtractFrom(initialTime, timeLimit) == 1) 
			{
				secondsToBeSubtractedFromPlayer2 = 0;
				returnValue = secondsToBeSubtractedFromPlayer2;
			}
		}
		return returnValue;
	}

	protected int whichPlayersClockToSubtractFrom(String[] initialTime, int timeLimit)
	{


		int secondsToBeSubtractedFromPlayer1 = player1Clock[player1Clock.length - 1] + 1;
		int secondsToBeSubtractedFromPlayer2 = player2Clock[player2Clock.length - 1] + 1;
		//3 means subtract from both clocks, 1 is player1 and 2 is player2
		int whichClock = 3;
		if (secondsToBeSubtractedFromPlayer1 + secondsToBeSubtractedFromPlayer2 > timeLimit)
		{
			if (secondsToBeSubtractedFromPlayer1 >= secondsToBeSubtractedFromPlayer2)
			{
				whichClock = 2;
			}
			if (secondsToBeSubtractedFromPlayer2 == 0)
			{
				whichClock = 1;
			}
			if (player1Clock[player1Clock.length - 2] == 0 && player1Clock[player1Clock.length - 3] > 0
					&& player1Clock[player2Clock.length - 2] > 0)
			{
				whichClock = 1;
			}
		}
		return whichClock;
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
		String[] initialTime = {"2.05", "2.04"};
		int timeLimit = 7;
		ChessClock clock = new ChessClock(initialTime, timeLimit, "smallestSum");
		int[] clockDigitsArray = clock.chessClockSumOfDigits(initialTime, timeLimit);
		System.out.println(clockDigitsArray[0]);
		System.out.println(clockDigitsArray[1]);
	}

}
