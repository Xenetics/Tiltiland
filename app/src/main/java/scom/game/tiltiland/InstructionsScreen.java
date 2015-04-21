package scom.game.tiltiland;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class InstructionsScreen extends Screen 
{
	Graphics g = game.getGraphics();	    	
	public InstructionsScreen(Game game) 
	{
		super(game);

	}
	
	// Booleans for button presses
	boolean backPush = false;
	boolean forwardPush = false;
	boolean backwardPush = false;
	boolean oneOrTwo = false;
	
	public void update(float deltaTime)
	{
		Assets.playMusic(); // play music if its not playing 
		if(Assets.isOn())
		{
			Settings.Music = true;
		}
		else
		{
			Settings.Music = false;
		}
		
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size(); // length of touches array
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				//all if's for buttons
				if(inBounds(event, 256, 832, 256, 128)) // options
				{
					backPush = true;
					Assets.playSound(Assets.click);
				}
				
				if(inBounds(event, 608, 864, 128, 128)) // Forward/Backward
				{
					if(!oneOrTwo)
					{
						forwardPush = true;
					}
					else
					{
						backwardPush = true;
					}
				}
			}
		}
		
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				//all if's for buttons
				if(inBounds(event, 256, 832, 256, 128)) // options
				{
					backPush = false;
					game.setScreen(new MainMenuScreen(game));
				}
				
				if(inBounds(event, 608, 864, 128, 128)) // Forward/Backward
				{
					if(!oneOrTwo)
					{
						forwardPush = false;
						oneOrTwo = true;
					}
					else
					{
						backwardPush = false;
						oneOrTwo = false;
					}
				}
			}
		}
	}
	
    public void present(float deltaTime)
    {
    	if(!oneOrTwo)
    	{
    		g.drawPixmap(Assets.screens, 0, 0, 0, 0, 768, 1024);	    	
    	}
    	else
    	{
    		g.drawPixmap(Assets.screens, 0, 0, 768, 0, 768, 1024);
    	}
    	
    	// Buttons
    	if(backPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 832, 0, 512, 256, 128); // BackB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 832, 256, 512, 256, 128); // BackBD
    	}
    	
    	if(forwardPush == false && oneOrTwo == false)
    	{
    		g.drawPixmap(Assets.buttons, 608, 864, 0, 1664, 128, 128); // ForWardB
    	}
    	else if(forwardPush == true && oneOrTwo == false)
    	{
    		g.drawPixmap(Assets.buttons, 608, 864, 128, 1664, 128, 128); // ForWardBD
    	}
    	
    	if(backwardPush == false && oneOrTwo == true)
    	{
    		g.drawPixmap(Assets.buttons, 608, 864, 256, 1664, 128, 128); // BackWardB
    	}
    	else if (backwardPush == true && oneOrTwo == true)
    	{
    		g.drawPixmap(Assets.buttons, 608, 864, 384, 1664, 128, 128); // BackWardBD
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
