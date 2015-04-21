package scom.game.tiltiland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen 
{
	Graphics g = game.getGraphics();
	public MainMenuScreen(Game game)
	{
		super(game);
		CloudCover();
	}
	
	// Booleans for button presses
	boolean playPush = false;
	boolean scorePush = false;
	boolean instructPush = false;
	boolean optionPush = false;
	
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
    	g.drawPixmap(Assets.layers, 0, 0, 0, 0, 768, 1024); // Background
    	ManageClouds();
    	g.drawPixmap(Assets.island, 133, 384);
    	g.drawPixmap(Assets.layers, 0, 0, 768, 0, 768, 1024); // ForeWater
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
