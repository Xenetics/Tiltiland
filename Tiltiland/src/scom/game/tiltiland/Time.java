package scom.game.tiltiland;

import java.util.Timer;
import java.util.TimerTask;

import scom.game.tiltiland.GamePlayScreen.GameState;

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
				if(GamePlayScreen.state == GameState.Running)
				{
					Zoo.Birthdays();
					TheTime += 1;
				}
			}
		}, 1000, 1000);
	}
}
