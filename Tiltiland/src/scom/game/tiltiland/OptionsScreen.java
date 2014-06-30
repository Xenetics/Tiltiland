package scom.game.tiltiland;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class OptionsScreen extends Screen 
{

	public OptionsScreen(Game game) 
	{
		super(game);
	}
	// Booleans for button presses
		boolean backPush = false;
		boolean creditsPush = false;
		boolean creditsShow = false;
		
		public void update(float deltaTime)
		{
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
						}
					}
					
					if(inBounds(event, 256, 832, 256, 128)) // Back
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
					if(inBounds(event, 256, 832, 256, 128)) // Back
					{
						backPush = false;
						Settings.save(game.getFileIO());
						game.setScreen(new MainMenuScreen(game));
					}
					
					if(inBounds(event, 256, 640, 256, 128)) // Credits button
					{
						creditsPush = false;
						creditsShow = true;
					}
				}
			}
		}
		
	    public void present(float deltaTime)
	    {
	    	Graphics g = game.getGraphics();	    	

	    	if(creditsShow == true)
	    	{
	    		g.drawPixmap(Assets.screens, 0, 0, 1536, 0, 768, 1024); // BackB
	    	}
	    	else
	    	{
	    		g.drawPixmap(Assets.background, 0, 0);
		    	g.drawPixmap(Assets.island, 133, 384);
		    	g.drawPixmap(Assets.foreWater, 0, 0);
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
