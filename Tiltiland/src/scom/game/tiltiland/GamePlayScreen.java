package scom.game.tiltiland;

import java.util.List;

import scom.game.tiltiland.AnimalHandler.creatures;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GamePlayScreen extends Screen
{
	Graphics g = game.getGraphics();
    enum GameState //state machine
    {
        Ready, Running, Paused, GameOver
    }
    
    static GameState state;
    
	public GamePlayScreen(Game game)
	{
		super(game);
		canvas = new Canvas(g.getFrameBuffer());
		island = new Island(133, 384);
		zoo = island.zoo;
		zoo.SetScore(0);
		midPoint =  384;
		state = GameState.Ready;
		if(Settings.MusicEnabled || Settings.SFXEnabled)
		{
			mute = false;
		}
		else
		{
			mute = true;
		}
	}
	
	Ad ad; // Ad instance
	
	// Booleans for button presses
	boolean pausePush = false;
	boolean resumePush = false;
	boolean quitPush = false;
	boolean replayPush = false;
	boolean mutePush = false;
	boolean mute;
	
	// Objects game has
	Island island; 
	AnimalHandler zoo; // Keeps track of animals within the island
	Font points; // displays points o screen
	Time timer;
	Font Timer;
	Canvas canvas;
	
    // Fonts for Stat's draw
    Font StatHead;
    Font Elephants;
    Font Giraffe;
    Font Tiger;
    Font Zebra;
    Font Snake;
    Font Gorilla;
    Font Penguin;
    Font Bear;
    Font Sheep;
    Font Kangaroo;
	
	// Some variables
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
    float midPoint;
	
    public creatures[] drawOrder = {creatures.elephant, creatures.snake, creatures.tiger, creatures.zebra, creatures.giraffe, creatures.bear, creatures.gorilla, creatures.penguin, creatures.sheep, creatures.kangaroo };
    
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
		
        if(state == GameState.Ready)
        {
            UpdateReady(touchEvents);
        }
        if(state == GameState.Running)
        {
            UpdateRunning(touchEvents, deltaTime);
        }
        if(state == GameState.Paused)
        {
            UpdatePaused(touchEvents);
        }
        if(state == GameState.GameOver)
        {
            UpdateGameOver(touchEvents);  
        }
	}
	
	private void UpdateReady(List<TouchEvent> touchEvents)
	{
		if(touchEvents.size() >0)
		{
			state = GameState.Running;
			timer = new Time(zoo); // creates new timer object
		}
	}

	private double getWeightDistrubution()
	{
		double left = 0;
		double right = 0;
		
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).GetOnGround())
    		{
				int x = zoo.Pen.get(i).GetXPos();
	    		if(x > midPoint)
	    		{	
	    			//right
	    			right += zoo.Pen.get(i).GetWeight() * -(midPoint - x) * 0.000025 ;
	    		}
	    		else
	    		{
	    			//left
	    			left += zoo.Pen.get(i).GetWeight() * (midPoint - x) * 0.000025 ;
	    		}
    		}
    	}
		
		return right - left;
	}
	
	private void CheckDrown()
	{
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).GetOnGround())
    		{
    			if(zoo.Pen.get(i).GetXPos() < midPoint)
    			{
		    		int x = zoo.Pen.get(i).GetXPos();
		    		
		    		double globalY =  Math.abs((midPoint - x)) * Math.tan(Math.toRadians(island.rotation));
		    		
		    		if (globalY < -128)
		    		{
		    			zoo.Pen.get(i).SetOnGround(false);
		    			zoo.TakeCensus(zoo.Pen.get(i).GetType(), zoo.Pen.get(i).GetGender(), false);
		    			zoo.Pen.get(i).SetYPos((int) Math.round(globalY + 512 + 128));
		    		}
    			}
    			else
    			{
		    		int x = zoo.Pen.get(i).GetXPos();
		    		
		    		double globalY =  Math.abs((midPoint - x)) * -Math.tan(Math.toRadians(island.rotation));
		    		
		    		if (globalY < -128)
		    		{
		    			zoo.Pen.get(i).SetOnGround(false);
		    			zoo.TakeCensus(zoo.Pen.get(i).GetType(), zoo.Pen.get(i).GetGender(), false);
		    			zoo.Pen.get(i).SetYPos((int) Math.round(globalY + 512 + 128));
		    		}
    			}
    		}
    		else if (zoo.Pen.get(i).GetYPos() > g.getHeight())
    		{
    			zoo.Murder(i);
    		}
    	}
	}
	
	private void UpdateRunning(List<TouchEvent> touchEvents, float deltaTime)
	{
		// Accelerometer stuff
		float rotationAmount = 0;
		rotationAmount += game.getInput().getAccelX() * -Math.abs(0.5 * game.getInput().getAccelX()); // make variable for power
		rotationAmount += getWeightDistrubution();
		
		island.rotation += rotationAmount;
		/*
		if( island.rotation > 50 || island.rotation < -50 )
		{
			island.rotation = 50;
		}
		*/
		canvas.rotate(rotationAmount, canvas.getWidth()*0.5f, canvas.getHeight()*0.5f);
		
		CheckDrown();
		MoveAnimals(); // updates animal movement
		GameOverCheck();
		
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
					Assets.playSound(Assets.click);
				}
				
				if(inBounds(event, 608, 864, 128, 128)) // Mute
				{
					mutePush = !mutePush;
					Assets.playSound(Assets.click);
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
					state = GameState.Paused;
					pausePush = false;
				}
				
				if(inBounds(event, 608, 864, 128, 128)) /// Mute D
				{
					mutePush = !mutePush;
					Settings.MusicEnabled = !Settings.MusicEnabled;
					Assets.playMusic();
					Settings.SFXEnabled = !Settings.SFXEnabled;
					mute = !mute;
				}
			}
		}
	}
	
	private void UpdatePaused(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size(); // length of touches array
		for(int i = 0; i < len; i++) // touch down loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				//all if's for buttons
				if(inBounds(event, 256, 320, 256, 128)) // Resume
				{
					resumePush = true; // is button being pushed
					if(Settings.SFXEnabled)
					{
						Assets.playSound(Assets.click);
					}
				}
				
				if(inBounds(event, 256, 640, 256, 128)) // Quit
				{
					quitPush = true; // is button being pushed
					Assets.playSound(Assets.click);
				}
			}
		}
		
		for(int i = 0; i < len; i++) // touch up loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				//all if's for buttons
				if(inBounds(event, 256, 320, 256, 128)) // Resume
				{
					state = GameState.Running;
					resumePush = false;
				}
				
				if(inBounds(event, 256, 640, 256, 128)) // Quit
				{
					game.setScreen(new MainMenuScreen(game));
					quitPush = false;
				}
			}
		}
	}
	
	private void UpdateGameOver(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size(); // length of touches array
		for(int i = 0; i < len; i++) // touch down loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				//all if's for buttons
				if(inBounds(event, 256, 640, 256, 128)) // Quit
				{
					quitPush = true; // is button being pushed
					Assets.playSound(Assets.click);
				}
			}
		}
		
		for(int i = 0; i < len; i++) // touch up loop
		{
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP)
			{
				//all if's for buttons
				if(inBounds(event, 256, 640, 256, 128)) // Quit
				{
					quitPush = false;
					Settings.addScore(zoo.GetScore());
					Settings.save(game.getFileIO());
					game.setScreen(new MainMenuScreen(game));
				}
			}
		}
	}
    public void present(float deltaTime)
    {
    	DrawWorld(); // draws background and world
    	
    	DrawAnimals(); // draws all animals
    	
    	if(state == GameState.Ready)
    	{
    		DrawReadyUI();
    	}
    	if(state == GameState.Running)
    	{
    		DrawGameUI();
    	}
    	if(state == GameState.Paused)
    	{
    		DrawPausedUI();
    	}
    	if(state == GameState.GameOver)
    	{
    		DrawGameOverUI();
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
    
    private void DrawWorld()
    {
    	g.drawPixmap(Assets.layers, 0, 0, 0, 0, 768, 1024); // Background
    	//draw canvas in here 
    	canvas.drawBitmap(Assets.island.getBitmap() , island.XPos, island.YPos, null);
    	g.drawPixmap(Assets.layers, 0, 0, 768, 0, 768, 1024); // ForeWater
    }
    
    private void DrawReadyUI()
    {
    	g.drawPixmap(Assets.layers, 0, 0, 1536, 0, 768, 1024); // Shroud
    	g.drawPixmap(Assets.title, 0, 0, 0, 320, 768, 320);
    }
    
    private void DrawGameUI()
    {    	
    	points = new Font(game, zoo.GetScore(), 32, 32, 1);
    	Timer = new Font(game, timer.TheTime, 544, 32, 1);
    	
    	DrawStats();
    	
    	// Buttons
    	if(pausePush == false)
    	{
    		g.drawPixmap(Assets.buttons, 32, 864, 0, 1024, 128, 128); // PauseB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 32, 864, 128, 1024, 128, 128); // PauseBD
    	}
    	
    	if(!mute)
    	{
	    	if(!mutePush)
	    	{
	    		g.drawPixmap(Assets.buttons, 608, 864, 0, 1792, 128, 128); // MuteB
	    	}
	    	else
	    	{
	    		g.drawPixmap(Assets.buttons, 608, 864, 128, 1792, 128, 128); // MuteBD
	    	}
    	}
    	else
    	{
    		if(!mutePush)
	    	{
	    		g.drawPixmap(Assets.buttons, 608, 864, 256, 1792, 128, 128); // MuteB
	    	}
	    	else
	    	{
	    		g.drawPixmap(Assets.buttons, 608, 864, 384, 1792, 128, 128); // MuteBD
	    	}
    	}
    }
    
    private void DrawPausedUI()
    {
    	g.drawPixmap(Assets.layers, 0, 0, 1536, 0, 768, 1024); // Shroud
    	g.drawPixmap(Assets.title, 0, 0, 768, 320, 768, 320);
    	
    	// Buttons
    	if(resumePush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 320, 0, 1152, 256, 128); // ResumeB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 320, 256, 1152, 256, 128); // ResumeBD
    	}
    	
    	if(quitPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 640, 0, 1280, 256, 128); // QuitB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 640, 256, 1280, 256, 128); // QuitBD
    	}
    }
    
    private void DrawGameOverUI()
    {
    	g.drawPixmap(Assets.layers, 0, 0, 1536, 0, 768, 1024); // Shroud
    	g.drawPixmap(Assets.title, 0, 0, 1536, 320, 768, 540);
    	points = new Font(game, zoo.GetScore(), 128, 340, 1);
    	points = new Font(game, Settings.highscores[0], 128, 540, 1);
    	
    	// Buttons
    	if(quitPush == false)
    	{
    		g.drawPixmap(Assets.buttons, 256, 640, 0, 1280, 256, 128); // QuitB
    	}
    	else
    	{
    		g.drawPixmap(Assets.buttons, 256, 640, 256, 1280, 256, 128); // QuitBD
    	}
    }
    
    private void DrawAnimals() // draws each animals on the screen
    {
    	for(int t = 0; t < drawOrder.length; t++)
    	{
	    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
	    	{
	    		if(zoo.Pen.get(i).GetType() == drawOrder[t])
	    		{
	    			if(zoo.Pen.get(i).GetOnGround())
	    			{
		    			// This can be cleaned up
		    			int x = zoo.Pen.get(i).GetXPos();
		    			int y = zoo.Pen.get(i).GetYPos();
		    			int srcX = zoo.Pen.get(i).GetSpriteX();
		    			int srcY = 0;
		    			int srcWidth = zoo.Pen.get(i).GetWidth();
		    			int srcHeight = zoo.Pen.get(i).GetHeight();
		    			
		    	        srcRect.left = srcX;
		    	        srcRect.top = srcY;
		    	        srcRect.right = srcX + srcWidth - 1;
		    	        srcRect.bottom = srcY + srcHeight - 1;
		
		    	        dstRect.left = x;
		    	        dstRect.top = y;
		    	        dstRect.right = x + srcWidth - 1;
		    	        dstRect.bottom = y + srcHeight - 1;
		
		    	        canvas.drawBitmap(Assets.animals.getBitmap(), srcRect, dstRect, null);
		    		}
	    			else 
	    			{
	    				g.drawPixmap(Assets.animals, zoo.Pen.get(i).GetXPos(), zoo.Pen.get(i).GetYPos(), zoo.Pen.get(i).GetSpriteX(), 0, zoo.Pen.get(i).GetWidth(), zoo.Pen.get(i).GetHeight());
	    				
	    			}
	    		}
	    	}
    	}
    }

    private void DrawStats()
    {
    	g.drawPixmap(Assets.stats, 128, 640, 0, 0, 478, 163);
    	Elephants = new Font(game, zoo.Census[0][0] + " : " + zoo.Census[0][1], 290, 668, 0.31f);
    	Giraffe = new Font(game, zoo.Census[1][0] + " : " + zoo.Census[1][1], 290, 696, 0.31f);
    	Tiger = new Font(game, zoo.Census[2][0] + " : " + zoo.Census[2][1], 290, 724, 0.31f);
    	Zebra = new Font(game, zoo.Census[3][0] + " : " + zoo.Census[3][1], 290, 752, 0.31f);
    	Snake = new Font(game, zoo.Census[4][0] + " : " + zoo.Census[4][1], 290, 781, 0.31f);
    	Gorilla = new Font(game, zoo.Census[5][0] + " : " + zoo.Census[5][1], 573, 668, 0.31f);
    	Penguin = new Font(game, zoo.Census[6][0] + " : " + zoo.Census[6][1], 573, 696, 0.31f);
    	Bear = new Font(game, zoo.Census[7][0] + " : " + zoo.Census[7][1], 573, 724, 0.31f);
    	Sheep = new Font(game, zoo.Census[8][0] + " : " + zoo.Census[8][1], 573, 752, 0.31f);
    	Kangaroo = new Font(game, zoo.Census[9][0] + " : " + zoo.Census[9][1], 573, 781, 0.31f);
    }
    
    private void MoveAnimals()
    {
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		zoo.Pen.get(i).Move(zoo, island.rotation);
    	}
    }
    
    private void GameOverCheck()
    {
    	int count = 0;
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(!zoo.Pen.get(i).GetOnGround())
    		{
    			count++;
    		}
    	}
    	if(count >= zoo.Pen.size())
    	{
    		state = GameState.GameOver;
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