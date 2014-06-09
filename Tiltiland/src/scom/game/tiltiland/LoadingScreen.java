package scom.game.tiltiland;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen 
{
	public LoadingScreen(Game game)
	{
		super(game);
	}
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.shroud = g.newPixmap("shroud.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.animals = g.newPixmap("animals.pnd", PixmapFormat.ARGB4444);
		Assets.island = g.newPixmap("island.png", PixmapFormat.ARGB4444);
		
		game.setScreen(new MainMenuScreen(game));
	}
	
    public void present(float deltaTime)
    {
    	
    }

    public void pause()
    {
    	
    }

    public void resume()
    {
    	
    }

    public void dispose()
    {
    	
    }
}
