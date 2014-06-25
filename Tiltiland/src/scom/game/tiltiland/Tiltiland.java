package scom.game.tiltiland;

import com.badlogic.androidgames.framework.impl.AndroidGame;
import com.badlogic.androidgames.framework.Screen;


public class Tiltiland extends AndroidGame
{
	public Screen getStartScreen()
	{
		return new LoadingScreen(this);
	}

}
