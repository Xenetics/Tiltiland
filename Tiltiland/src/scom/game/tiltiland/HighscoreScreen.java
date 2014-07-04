package scom.game.tiltiland;

import java.util.List;

import android.content.Context;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class HighscoreScreen extends Screen {

	public HighscoreScreen(Game game) 
	{
		super(game);
	}
	
	Context context;
	
	// Fonts for HighScores
	Font score1;
	Font score2;
	Font score3;
	Font score4;
	Font score5;
	
	// Booleans for button presses
	boolean backPush = false;
	
	public void update(float deltaTime)
	{
		Assets.playMusic(); // play music if its not playing 
		if(Assets.isOn())
		{
			Settings.MusicEnabled = true;
		}
		else
		{
			Settings.MusicEnabled = false;
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
			}
		}
	}
	
    public void present(float deltaTime)
    {
    	Graphics g = game.getGraphics();	    	
    	
    	g.drawPixmap(Assets.background, 0, 0);
    	g.drawPixmap(Assets.island, 133, 384);
    	g.drawPixmap(Assets.foreWater, 0, 0);
    	g.drawPixmap(Assets.title, 0, 0, 1536, 0, 768, 320);
    	
    	score1 = new Font(game, "1. " + Settings.highscores[0], 128, 256, 1);
    	score2 = new Font(game, "2. " + Settings.highscores[1], 128, 352, 1);
    	score3 = new Font(game, "3. " + Settings.highscores[2], 128, 480, 1);
    	score4 = new Font(game, "4. " + Settings.highscores[3], 128, 608, 1);
    	score5 = new Font(game, "5. " + Settings.highscores[4], 128, 734, 1);
    	
    	// Buttons
    	if(backPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 832, 0, 512, 256, 128); // BackB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 832, 256, 512, 256, 128); // BackBD
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

