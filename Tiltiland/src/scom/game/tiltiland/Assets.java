package scom.game.tiltiland;

import android.graphics.Bitmap;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.Music;

public class Assets 
{
	//Graphics
	public static Pixmap background;
	public static Pixmap title;
	public static Pixmap stats;
	public static Pixmap foreWater;
	public static Pixmap shroud;
	public static Pixmap buttons;
	
	public static Pixmap animals;
	
	public static Pixmap island;
	
	// Instruction screen
	public static Pixmap instruction1;
	public static Pixmap instruction2;
	
	//Sound
	public static Sound click;
	public static Music music;
	
	// Font
	public static Pixmap font;
	public static Bitmap fontBitmap;
	
	
    public static void playMusic() 
    {
        if(Settings.MusicEnabled)
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
}
