package scom.game.tiltiland;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.Rect;
import android.widget.TextView;


public class MainMenuScreen extends Screen 
{
	Context context;
	Paint paint;
	Canvas canvas;
	Typeface font;
	Rect bounds = new Rect();
	
	public MainMenuScreen(Game game)
	{
		super(game);
		paint = new Paint();
		canvas = new Canvas();
		font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
	}
	
	// Booleans for button presses
	boolean playPush = false;
	boolean scorePush = false;
	boolean instructPush = false;
	boolean optionPush = false;
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
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
					if(Settings.soundEnabled)
					{
						Assets.push.play(1);
					}
				}
				
				if(inBounds(event, 256, 448, 256, 128)) // high score
				{
					scorePush = true;
					if(Settings.soundEnabled)
					{
						Assets.push.play(1);
					}
				}
				
				if(inBounds(event, 256, 640, 256, 128)) // instructions
				{
					instructPush = true;
					if(Settings.soundEnabled)
					{
						Assets.push.play(1);
					}
				}
				
				if(inBounds(event, 256, 832, 256, 128)) // options
				{
					optionPush = true;
					if(Settings.soundEnabled)
					{
						Assets.push.play(1);
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
				if(inBounds(event, 256, 256, 256, 128)) // play
				{
					playPush = false;
				}
				
				if(inBounds(event, 256, 448, 256, 128)) // high score
				{
					scorePush = false;
				}
				
				if(inBounds(event, 256, 640, 256, 128)) // instructions
				{
					instructPush = false;
				}
				
				if(inBounds(event, 256, 832, 256, 128)) // options
				{
					optionPush = false;
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
    	g.drawPixmap(Assets.shroud, 0, 0);
    	
    	canvas.drawRGB(0, 0, 0);
    	paint.setColor(Color.YELLOW);
    	paint.setTypeface(font);
    	paint.setTextSize(28);
    	paint.setTextAlign(Paint.Align.CENTER);
    	canvas.drawText("This is a test!", canvas.getWidth() / 2, 100,
    	paint);

    	
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
