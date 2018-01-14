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
	
	protected int ifGreaterThan8Return0(int seconds)
	{
		int secondsToSubtract = seconds;
		if (secondsToSubtract > 8) 
		{
			secondsToSubtract = 0;
		}
		return secondsToSubtract;
	}
	
	protected int ifGreaterThanLimitReturn0(int seconds, int limit)
	{
		if (seconds > limit) 
		{
			seconds = 0;
		}
		return seconds;
	}
	
	protected int ifMinutesAndTensAre0Return0(int minutes, int tens, int seconds)
	{
		return 0;
	}
}
