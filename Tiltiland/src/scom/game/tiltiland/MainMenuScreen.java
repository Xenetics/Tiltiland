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
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++)
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				/*all if for buttons
				if(inBounds(event, ))
				{
					
				}
				
				if(inBounds(event, ))
				{
					
				}
				
				if(inBounds(event, ))
				{
					
				}
				
				if(inBounds(event, ))
				{
					
				}
				*/
			}
		}
	}
	
    public void present(float deltaTime)
    {
    	Graphics g = game.getGraphics();
    	
    	g.drawPixmap(Assets.background, 0, 0);
    	g.drawPixmap(Assets.island, 133, 384);
    	g.drawPixmap(Assets.foreWater, 0, 0);
    	g.drawPixmap(Assets.shroud, 0, 0);
    	g.drawPixmap(Assets.buttons, 256, 256, 0, 0, 256, 128);
    	g.drawPixmap(Assets.buttons, 256, 448, 0, 128, 256, 128);
    	g.drawPixmap(Assets.buttons, 256, 640, 0, 256, 256, 128);
    	g.drawPixmap(Assets.buttons, 256, 832, 0, 384, 256, 128);
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
