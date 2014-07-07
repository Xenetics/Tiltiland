package scom.game.tiltiland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class OptionsScreen extends Screen 
{
	Graphics g = game.getGraphics();
	public OptionsScreen(Game game) 
	{
		super(game);
		CloudCover();
	}
	// Booleans for button presses
	boolean backPush = false;
	boolean creditsPush = false;
	boolean creditsShow = false;
	boolean exitPush = false;
	
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
				if(!creditsShow)
				{
					if(inBounds(event, 128, 256, 512, 128)) // Music slider
					{
						Settings.MusicEnabled = !Settings.MusicEnabled;
						Settings.save(game.getFileIO());
						Assets.playMusic();
						Assets.playSound(Assets.click);
					}
					
					if(inBounds(event, 128, 448, 512, 128)) // SFX slider
					{
						Settings.SFXEnabled = !Settings.SFXEnabled;
						Settings.save(game.getFileIO());
						Assets.playSound(Assets.click);
					}
					
					if(inBounds(event, 256, 640, 256, 128)) // Credits button
					{
						creditsPush = true;
						Assets.playSound(Assets.click);
					}
				}
				
				if(inBounds(event, 64, 832, 256, 128)) // Back
				{
					backPush = true;
					Assets.playSound(Assets.click);
				}
				
				if(inBounds(event, 448, 832, 256, 128)) // Exit
				{
					exitPush = true;
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
				if(!creditsShow)
				{
					if(inBounds(event, 256, 640, 256, 128)) // Credits button
					{
						creditsPush = false;
						creditsShow = true;
					}
				}
				
				if(inBounds(event, 64, 832, 256, 128)) // Back
				{
					backPush = false;
					Settings.save(game.getFileIO());
					game.setScreen(new MainMenuScreen(game));
				}
				
				if(inBounds(event, 448, 832, 256, 128)) // Exit
				{
					exitPush = false;
					Settings.save(game.getFileIO());
					System.exit(0);
				}
			}
		}
	}
	
    public void present(float deltaTime)
    {
    	if(creditsShow == true)
    	{
    		g.drawPixmap(Assets.screens, 0, 0, 1536, 0, 768, 1024); // BackB
    	}
    	else
    	{
    		g.drawPixmap(Assets.layers, 0, 0, 0, 0, 768, 1024); // Background
        	ManageClouds();
	    	g.drawPixmap(Assets.island, 133, 384);
	    	g.drawPixmap(Assets.layers, 0, 0, 768, 0, 768, 1024); // ForeWater
	    	g.drawPixmap(Assets.title, 0, 0, 768, 0, 768, 320);
	    	g.drawPixmap(Assets.buttons, 128, 256, 0, 640, 512, 128); // top slider backing
	    	g.drawPixmap(Assets.buttons, 128, 448, 0, 640, 512, 128); // bottom slider backing
	    	
	    	// Sliders
	    	if(Settings.MusicEnabled) // Music Slider
	    	{
	    		g.drawPixmap(Assets.buttons, 320, 256, 0, 768, 320, 128); // on
	    	}
	    	else
	    	{
	    		g.drawPixmap(Assets.buttons, 128, 256, 0, 768, 320, 128); // off
	    	}
	    	
	    	if(Settings.SFXEnabled) // SFX Slider
	    	{
	    		g.drawPixmap(Assets.buttons, 320, 448, 0, 896, 320, 128); // on
	    	}
	    	else
	    	{
	    		g.drawPixmap(Assets.buttons, 128, 448, 0, 896, 320, 128); // off
	    	}
	    	
	    	// Button
	    	if(creditsPush == false)
	    	{
	    		g.drawPixmap(Assets.buttons, 256, 640, 0, 1536, 256, 128); // CreditsB
	    	}
	    	else
	    	{
	    		g.drawPixmap(Assets.buttons, 256, 640, 256, 1536, 256, 128); // CreditsBD
	    	}
    	}
    	
    	// Buttons
    	if(backPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 64, 832, 0, 512, 256, 128); // BackB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 64, 832, 256, 512, 256, 128); // BackBD
    	}
    	
    	if(exitPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 448, 832, 0, 1920, 256, 128); // ExitB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 448, 832, 256, 1920, 256, 128); // ExitBD
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
