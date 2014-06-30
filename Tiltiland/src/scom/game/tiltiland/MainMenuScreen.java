package scom.game.tiltiland;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen 
{
	public MainMenuScreen(Game game)
	{
		super(game);
	}
	
	// Booleans for button presses
	boolean playPush = false;
	boolean scorePush = false;
	boolean instructPush = false;
	boolean optionPush = false;
	
	public void update(float deltaTime)
	{
		Assets.playMusic();
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size(); // length of touches array
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				//all if's for buttons
				if(inBounds(event, 256, 256, 256, 128)) // play
				{
					playPush = true; // is button being pushed
					Assets.playSound(Assets.click);
				}
				
				if(inBounds(event, 256, 448, 256, 128)) // high score
				{
					scorePush = true;
					Assets.playSound(Assets.click);
				}
				
				if(inBounds(event, 256, 640, 256, 128)) // instructions
				{
					instructPush = true;
					Assets.playSound(Assets.click);
				}
				
				if(inBounds(event, 256, 832, 256, 128)) // options
				{
					optionPush = true;
					Assets.playSound(Assets.click);
				}
			}
		}
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				//all if's for buttons
				if(inBounds(event, 256, 256, 256, 128)) // play
				{
					playPush = false;
					game.setScreen(new GamePlayScreen(game));
				}
				
				if(inBounds(event, 256, 448, 256, 128)) // high score
				{
					scorePush = false;
					game.setScreen(new HighscoreScreen(game));
				}
				
				if(inBounds(event, 256, 640, 256, 128)) // instructions
				{
					instructPush = false;
					game.setScreen(new InstructionsScreen(game));
				}
				
				if(inBounds(event, 256, 832, 256, 128)) // options
				{
					optionPush = false;
					game.setScreen(new OptionsScreen(game));
				}
			}
		}
	}
	
    public void present(float deltaTime)
    {
    	Graphics g = game.getGraphics();
    	
    	g.drawPixmap(Assets.background, 0, 0);
    	g.drawPixmap(Assets.island, 133, 384);
    	g.drawPixmap(Assets.foreWater, 0, 0);
    	g.drawPixmap(Assets.title, 0, 0, 0, 0, 768, 320);
    	
    	
    	// Buttons
    	if(playPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 256, 0, 0, 256, 128); // playB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 256, 256, 0, 256, 128); // playBD
    	}
    	
    	if(scorePush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 448, 0, 128, 256, 128); // HighscoreB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 448, 256, 128, 256, 128); // HighscoreBD
    	}
    	
    	if(instructPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 640, 0, 256, 256, 128); // InstructionsB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 640, 256, 256, 256, 128); // InstructionsBD
    	}
    	
    	
    	if(optionPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 832, 0, 384, 256, 128); // OptionsB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 832, 256, 384, 256, 128); // OptionsBD
    	}
    }

	public void pause()
    {
    	Settings.save(game.getFileIO());
    }

    public void resume()
    {
    	
    }

    public void dispose()
    {
    	
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) 
    {
        if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
