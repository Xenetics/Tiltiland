package scom.game.tiltiland;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

import android.content.Context;

public class LoadingScreen extends Screen 
{
	public LoadingScreen(Game game, Context ctx)
	{
		super(game);
	}
	
	public int splashCounter = 0;
	public int RocPosY = -128;
	public int JamPosX = -320;
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		
		// loading image assets
		if(Assets.splash == null)
		{
			Assets.splash = g.newPixmap("splashAnim.png", PixmapFormat.RGB565);
		}
		if(Assets.layers == null)
		{
			Assets.layers = g.newPixmap("layers.png", PixmapFormat.RGB565);
		}
		if(Assets.title == null)
		{
			Assets.title = g.newPixmap("title.png", PixmapFormat.ARGB4444);
		}
		if(Assets.stats == null)
		{
			Assets.stats = g.newPixmap("stats.png", PixmapFormat.ARGB4444);
		}
		if(Assets.buttons == null)
		{
			Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		}
		if(Assets.animals == null)
		{
			Assets.animals = g.newPixmap("animals.png", PixmapFormat.ARGB4444);
		}
		if(Assets.island == null)
		{
			Assets.island = g.newPixmap("island.png", PixmapFormat.ARGB4444);
		}
		if(Assets.screens == null)
		{
			Assets.screens = g.newPixmap("screens.png", PixmapFormat.ARGB4444);
		}
		
		// loading sound assets
		if(Assets.click == null)
		{
			Assets.click = game.getAudio().newSound("button.ogg");
		}
		if(Assets.music == null)
		{
			Assets.music = game.getAudio().newMusic("RollinE.ogg");
		}
		
		//load font
		if(Assets.font == null)
		{
			Assets.font = g.newPixmap("Font.png", PixmapFormat.ARGB4444);
		}
		if(Assets.fontBitmap == null)
		{
			Assets.fontBitmap = Assets.font.getBitmap();
		}
		
		Settings.load(game.getFileIO()); // loads saved settings file
		
		if(RocPosY < 384)
		{
			RocPosY += 10;
		}
		if(RocPosY > 384)
		{
			RocPosY = 384;
		}
		if(JamPosX < 64 && RocPosY >= 384)
		{
			JamPosX += 10;
		}
		if(JamPosX > 64)
		{
			JamPosX = 64;
		}
		if(!Settings.IntroPlayed)
		{
			g.drawPixmap(Assets.splash, 0, 0); // Background
			g.drawPixmap(Assets.splash, 384, RocPosY, 1088, 0, 256, 128); // Roc
			g.drawPixmap(Assets.splash, JamPosX, 384, 768, 0, 320, 128); // Jam
		}
		if(splashCounter < 150)
		{
			splashCounter++;
		}
		if(splashCounter == 150)
		{
			Settings.IntroPlayed = true;
			game.setScreen(new MainMenuScreen(game)); // creates and sets up main menu
		}
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
