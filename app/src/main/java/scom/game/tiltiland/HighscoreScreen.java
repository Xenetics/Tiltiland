package scom.game.tiltiland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class HighscoreScreen extends Screen 
{
	Graphics g = game.getGraphics();
	public HighscoreScreen(Game game) 
	{
		super(game);
		CloudCover();
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
	
	// List for Clouds
    private List<Cloud> Clouds = new ArrayList<Cloud>();
	
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
    	g.drawPixmap(Assets.layers, 0, 0, 0, 0, 768, 1024); // Background
    	ManageClouds();
    	g.drawPixmap(Assets.island, 133, 384);
    	g.drawPixmap(Assets.layers, 0, 0, 768, 0, 768, 1024); // ForeWater;
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
    
    private void CloudCover()
    {
    	for(int i = 0 ; i < 7 ; ++i)
    	{
    		Random generator = new Random();
    		int y = generator.nextInt(192 - (-64)) + (-64);
    		Random generator2 = new Random();
    		int x = generator2.nextInt(768 - (-128)) + (-128);
    		Random generator3 = new Random();
    		int s = generator3.nextInt(3 - 1) + 1;
    		Clouds.add(new Cloud(x, y, s));
    	}
    }
    
    private void ManageClouds()
    {
    	if(Clouds.size() < 10) // Makes new Clouds when there is not enough
    	{
    		Random generator = new Random();
    		int y = generator.nextInt(192 - (-64)) + (-64);
    		Random generator2 = new Random();
    		int x = generator2.nextInt(896 - 768) + 768;
    		Random generator3 = new Random();
    		int s = generator3.nextInt(3 - 1) + 1;
    		Clouds.add(new Cloud(x, y, s));
    	}
    	
    	for(int i = 0 ; i < Clouds.size() ; ++i) // Removes Clouds when off screen
    	{
    		if(Clouds.get(i).GetXPos() <= -256)
    		{
    			Clouds.remove(i);
    		}
    	}
    	
    	for(int i = 0 ; i < Clouds.size() ; ++i) // Moves In X
    	{
    		Clouds.get(i).SetXPos(Clouds.get(i).GetXPos() - Clouds.get(i).GetSpeed());
    	}
    	
    	for(int i = 0 ; i < Clouds.size() ; ++i) // Draws Clouds
    	{
    		g.drawPixmap(Assets.cloud, Clouds.get(i).GetXPos(), Clouds.get(i).GetYPos(), 0, 0, 256, 128);
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

