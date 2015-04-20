package scom.game.tiltiland;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.androidgames.framework.impl.AndroidAudio;
import com.badlogic.androidgames.framework.impl.AndroidFastRenderView;
import com.badlogic.androidgames.framework.impl.AndroidFileIO;
import com.badlogic.androidgames.framework.impl.AndroidGame;
import com.badlogic.androidgames.framework.impl.AndroidGraphics;
import com.badlogic.androidgames.framework.impl.AndroidInput;
import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;
import com.appfireworks.android.listener.AppModuleListener;
import com.appfireworks.android.track.AppTracker;
import com.tcerkrmloelh.AdController;

public class Tiltiland extends Activity implements Game
{
	AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    public Screen screen;
    WakeLock wakeLock;

    @Override
	protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 1024 : 768;
        int frameBufferHeight = isLandscape ? 768 : 1024;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = new LoadingScreen(this, this, this);
        setContentView(renderView);
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
        MakePowMan();
        //setContentView(R.layout.activity_tiltiland);
        //initializeLeadBolt();
    }

    @Override
    public void onResume() 
    {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
        //AppTracker.resume(getApplicationContext());
    }

    @Override
    public void onPause() 
    {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
        {
            screen.dispose();
            //AppTracker.pause(getApplicationContext());
        }
    }

    public Input getInput() 
    {
        return input;
    }

    public FileIO getFileIO() 
    {
        return fileIO;
    }

    public Graphics getGraphics() 
    {
        return graphics;
    }

    public Audio getAudio() 
    {
        return audio;
    }

    public void setScreen(Screen screen) 
    {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    public Screen getCurrentScreen() 
    {
        return screen;
    }
    
	public void MakePowMan() 
	{
		  Assets.PowMan = (PowerManager) getSystemService(Context.POWER_SERVICE);
	}
    /*
	private AdController interstitial;
    private AdController audio2;
    private Activity act = this;
    
    public void initializeLeadBolt() 
    {
    	audio2 = new AdController(act, "148319933");
    	audio2.loadAudioAd(); 
        AppTracker.startSession(act, "SLocIBP8SUuxyIP4f4E5oseGcNaR2nVB", new AppModuleListener() 
        {
            @Override
            public void onModuleLoaded() {}
            @Override
            public void onModuleFailed() 
            {
                interstitial = new AdController(act, "150365981");
                interstitial.loadAd();
            }
            @Override
            public void onModuleClosed() {}
            @Override
            public void onModuleCached() {}
        });
    }
    
	public Screen getStartScreen()
	{
		MakePowMan();
		setContentView(R.layout.activity_tiltiland);
		return new LoadingScreen(this, this, this);
	}
	
	private AdController interstitial;
    private AdController audio2;
    private Activity act = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiltiland);
        initializeLeadBolt();
    }
 	
    public void initializeLeadBolt() {
    	audio2 = new AdController(act, "148319933");
    	audio2.loadAudioAd(); 
        AppTracker.startSession(act, "SLocIBP8SUuxyIP4f4E5oseGcNaR2nVB", new AppModuleListener() 
        {
            @Override
            public void onModuleLoaded() {}
            @Override
            public void onModuleFailed() 
            {
                interstitial = new AdController(act, "150365981");
                interstitial.loadAd();
            }
            @Override
            public void onModuleClosed() {}
            @Override
            public void onModuleCached() {}
        });
    }
 
    public void onPause() {
        super.onPause();
        if (!isFinishing()) {
            AppTracker.pause(getApplicationContext());
        }
    }
 
    public void onResume() {
        super.onResume();
        AppTracker.resume(getApplicationContext());
    }
    */
}
