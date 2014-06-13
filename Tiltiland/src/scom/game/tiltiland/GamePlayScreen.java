package scom.game.tiltiland;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class GamePlayScreen extends Screen 
{
	public GamePlayScreen(Game game)
	{
		super(game);
	}
	
	// Booleans for button presses
	boolean pausePush = false;
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size(); // length of touches array
		
		for(int i = 0; i < len; i++) // touch down loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				//all if's for buttons
				if(inBounds(event, 256, 256, 256, 128)) // Pause
				{
					pausePush = true; // is button being pushed
					if(Settings.SFXEnabled)
					{
						Assets.push.play(1);
					}
				}
			}
		}
		
		for(int i = 0; i < len; i++) // touch up loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				//all if's for buttons
				if(inBounds(event, 256, 256, 256, 128)) // pause
				{
					pausePush = false;
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
    	
    	// Buttons
    	if(pausePush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 256, 0, 0, 256, 128); // pauseB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 256, 256, 0, 256, 128); // PauseBD
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