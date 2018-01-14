package chessclock;

public class ChessClock {

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
	
	protected int secondsToSubtractFromPlayer1(int secondsToSubtractFromPlayer1, int secondsToSubtractFromPlayer2, int limit)
	{
		if (secondsToSubtractFromPlayer1 + secondsToSubtractFromPlayer2 > limit && secondsToSubtractFromPlayer1 >= secondsToSubtractFromPlayer2) 
		{
			secondsToSubtractFromPlayer1 = 0;
		}
		return secondsToSubtractFromPlayer1;
	}
	
	protected int secondsToSubtractFromPlayer2(int secondsToSubtractFromPlayer1, int secondsToSubtractFromPlayer2, int limit)
	{
		if (secondsToSubtractFromPlayer1 + secondsToSubtractFromPlayer2 > limit && secondsToSubtractFromPlayer2 >= secondsToSubtractFromPlayer1) 
		{
			secondsToSubtractFromPlayer2 = 0;
		}
		return secondsToSubtractFromPlayer2;
	}
}
