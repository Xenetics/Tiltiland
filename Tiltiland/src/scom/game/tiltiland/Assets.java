package scom.game.tiltiland;

import java.util.List;

import android.graphics.Bitmap;
import android.os.PowerManager;

import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.Music;

public class Assets 
{
	//Graphics
	public static Pixmap splash;
	public static Pixmap layers;
	public static Pixmap title;
	public static Pixmap stats;
	public static Pixmap messages;
	public static Pixmap buttons;
	
	public static Pixmap animals;
	
	public static Pixmap island;
	public static Pixmap cloud;
	public static List<Cloud> Clouds;
	public static CloudManager cloudManager;
	
	// Instruction screen
	public static Pixmap screens;
	
	//Sound
	public static Sound click;
	public static Music music;
	
	// Font
	public static Pixmap font;
	public static Bitmap fontBitmap;
	
	public static PowerManager PowMan;

    public static void playMusic() 
    {
        if(Settings.MusicEnabled && Settings.Music)
        {
        	music.play();
        	music.setLooping(true);
        }
        else
        {
        	music.stop();
        	music.setLooping(false);
        }
    }

    public static void playSound(Sound sound) 
    {
        if(Settings.SFXEnabled)
        {
            sound.play(1);
        }
    }
    
    public static boolean isOn()
    {
    	return Assets.PowMan.isScreenOn();
    }
}
