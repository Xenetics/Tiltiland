package scom.game.tiltiland;

import java.util.List;

import scom.game.tiltiland.AnimalHandler.creatures;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class GamePlayScreen extends Screen 
{
	Graphics g = game.getGraphics();
    enum GameState //state machine
    {
        Ready, Running, Paused, GameOver
    }
    
    GameState state = GameState.Ready;
    
	public GamePlayScreen(Game game)
	{
		super(game);
		island = new Island(133, 384);
		zoo = island.zoo;
		zoo.Score = 0;
	}
	
	// Booleans for button presses
	boolean pausePush = false;
	
	// Objects game has
	Island island; 
	AnimalHandler zoo; // Keeps track of animals within the island
	Font points; // displays points o screen
	Time timer;
	Font Timer;
	
	public void update(float deltaTime)
	{
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
	
	private void UpdateRunning(List<TouchEvent> touchEvents, float deltaTime)
	{
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
					state = GameState.Paused;
					pausePush = false;
				}
			}
		}
		
		MoveAnimals(); // updates animal movement
	}
	
	private void UpdatePaused(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size(); // length of touches array
	}
	
	private void UpdateGameOver(List<TouchEvent> touchEvents)
	{
		int len = touchEvents.size(); // length of touches array
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
    	g.drawPixmap(Assets.background, 0, 0);
    	g.drawPixmap(Assets.island, island.XPos, island.YPos);
    	g.drawPixmap(Assets.foreWater, 0, 0);
    }
    
    private void DrawReadyUI()
    {
    	
    }
    
    private void DrawGameUI()
    {    	
    	points = new Font(game, zoo.Score, 32, 32);
    	Timer = new Font(game, timer.TheTime, 544, 32);
    	
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
    
    private void DrawPausedUI()
    {
    	
    }
    
    private void DrawGameOverUI()
    {
    	
    }
    
    private void DrawAnimals() // draws each animal on the screen
    {
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.elephant)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.snake)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.tiger)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.zebra)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.giraffe)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.bear)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.gorilla)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.penguin)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.sheep)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    	
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		if(zoo.Pen.get(i).Type == creatures.kangaroo)
    		{
    			g.drawPixmap(Assets.animals, zoo.Pen.get(i).XPos, zoo.Pen.get(i).YPos, zoo.Pen.get(i).SpriteX, 0, zoo.Pen.get(i).Width, zoo.Pen.get(i).Height);
    		}
    	}
    }
    
    private void MoveAnimals()
    {
    	for(int i = 0 ; i < zoo.Pen.size() ; ++i)
    	{
    		zoo.Pen.get(i).Move(zoo);
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