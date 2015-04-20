package scom.game.tiltiland;

import java.util.Random;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;

public class CloudManager 
{
	Graphics g;
	public CloudManager(Game game)
	{
		g = game.getGraphics();
		CloudCover();
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
    		Assets.Clouds.add(new Cloud(x, y, s));
    	}
    }
    
    public void ManageClouds()
    {
    	if(Assets.Clouds.size() < 10) // Makes new Clouds when there is not enough
    	{
    		Random generator = new Random();
    		int y = generator.nextInt(192 - (-64)) + (-64);
    		Random generator2 = new Random();
    		int x = generator2.nextInt(896 - 768) + 768;
    		Random generator3 = new Random();
    		int s = generator3.nextInt(3 - 1) + 1;
    		Assets.Clouds.add(new Cloud(x, y, s));
    	}
    	
    	for(int i = 0 ; i < Assets.Clouds.size() ; ++i) // Removes Clouds when off screen
    	{
    		if(Assets.Clouds.get(i).GetXPos() <= -256)
    		{
    			Assets.Clouds.remove(i);
    		}
    	}
    	
    	for(int i = 0 ; i < Assets.Clouds.size() ; ++i) // Moves In X
    	{
    		Assets.Clouds.get(i).SetXPos(Assets.Clouds.get(i).GetXPos() - Assets.Clouds.get(i).GetSpeed());
    	}
    	
    	for(int i = 0 ; i < Assets.Clouds.size() ; ++i) // Draws Clouds
    	{
    		g.drawPixmap(Assets.cloud, Assets.Clouds.get(i).GetXPos(), Assets.Clouds.get(i).GetYPos(), 0, 0, 256, 128);
    	}
    }
}
