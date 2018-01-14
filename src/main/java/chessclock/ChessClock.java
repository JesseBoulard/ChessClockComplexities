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
}
