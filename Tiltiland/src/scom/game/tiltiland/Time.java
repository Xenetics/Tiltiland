package scom.game.tiltiland;

import java.util.Timer;
import java.util.TimerTask;

public class Time extends Object
{
	public int TheTime;
	AnimalHandler Zoo;
	public Time(AnimalHandler zoo)
	{
		Zoo = zoo;
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask()
		{
			@Override
			public void run() 
			{
				Zoo.Birthdays();
				TheTime += 1;
			}
		}, 1000, 1000);
	}
}
