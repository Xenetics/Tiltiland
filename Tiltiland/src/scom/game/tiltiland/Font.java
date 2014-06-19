package scom.game.tiltiland;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;

public class Font 
{
	Game game;
	Graphics g = game.getGraphics();
	
	public Font(String input)
	{
		Input = input;
	}
	
	String Input;
	public char Collection[];
	
	public void Collect()
	{
		for (int i = 0 ; i <= Input.length() ; ++i)
		{
			Collection[i] = Input.charAt(i);
		}
	}
	
	public void Draw()
	{
		
	}
}
