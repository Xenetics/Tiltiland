package scom.game.tiltiland;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class GamePlayScreen extends Screen 
{
    enum GameState //state machine
    {
        Ready,
        Running,
        Paused,
        GameOver
    }
    
    GameState state = GameState.Ready;
    
	public GamePlayScreen(Game game)
	{
		super(game);
		zoo = new AnimalHandler(); // creates animal handler
	}
	
	// Booleans for button presses
	boolean pausePush = false;
	
	// Objects game has
	AnimalHandler zoo;
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		List < TouchEvent > touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		zoo.Birth("giraffe", 133, 384);
		
		int len = touchEvents.size(); // length of touches array
		for(int i = 0; i < len; i++) // touch down loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				//all if's for buttons
				if(inBounds(event, 32, 864, 128, 128)) // Pause
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
				if(inBounds(event, 32, 864, 128, 128)) // pause
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
    	
    	DrawAnimals(); // draws all animals
    	
    	// Buttons
    	if(pausePush == false)
    	{
    		g.drawPixmap(Assets.buttons, 32, 864, 0, 1024, 128, 128); // pauseB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 32, 864, 128, 1024, 128, 128); // PauseBD
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
    
    public void DrawAnimals() // draws each animal on the screen
    {
    	Graphics g = game.getGraphics();
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    	}
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