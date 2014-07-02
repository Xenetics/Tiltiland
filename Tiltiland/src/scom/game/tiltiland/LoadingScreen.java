package scom.game.tiltiland;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
/*
import com.game.tiltiland.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
*/

import android.content.Context;

public class LoadingScreen extends Screen 
{
	public LoadingScreen(Game game, Context ctx)
	{
		super(game);
		context = ctx;
	}
	
	Context context;
	
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		
		// loading image assets
		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.title = g.newPixmap("title.png", PixmapFormat.ARGB4444);
		Assets.stats = g.newPixmap("stats.png", PixmapFormat.ARGB4444);
		Assets.foreWater = g.newPixmap("foreWater.png", PixmapFormat.ARGB4444);
		Assets.shroud = g.newPixmap("shroud.png", PixmapFormat.ARGB4444);
		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
		Assets.animals = g.newPixmap("animals.png", PixmapFormat.ARGB4444);
		Assets.island = g.newPixmap("island.png", PixmapFormat.ARGB4444);
		Assets.screens = g.newPixmap("screens.png", PixmapFormat.ARGB4444);

		// loading sound assets
		Assets.click = game.getAudio().newSound("button.ogg");
		Assets.music = game.getAudio().newMusic("RollinE.ogg");
		
		//load font
		Assets.font = g.newPixmap("Font.png", PixmapFormat.ARGB4444);
		
		Assets.fontBitmap = Assets.font.getBitmap();
		
		Settings.load(game.getFileIO()); // loads saved settings file
		
		//Ad(game); // Ads
		
		game.setScreen(new MainMenuScreen(game)); // creates and sets up main menu
	}
	
    public void present(float deltaTime)
    {
    	
    }

    public void pause()
    {
    	
    }

    public void resume()
    {
    	
    }

    public void dispose()
    {
    	
    }
    
    /*
    private AdView mAdView;
    
    public void Ad(Game game)
    {
         mAdView = new AdView(context);
         mAdView.setAdUnitId(context.getResources().getString(R.string.ad_unit_id));
         mAdView.setAdSize(AdSize.BANNER);
         mAdView.setAdListener(new ToastAdListener(context));
         mAdView.loadAd(new AdRequest.Builder().build());
    }
    */
}
