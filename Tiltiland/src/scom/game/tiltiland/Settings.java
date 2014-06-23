package scom.game.tiltiland;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.badlogic.androidgames.framework.FileIO;

public class Settings 
{
    public static boolean MusicEnabled = true;
    public static boolean SFXEnabled = true;
    public static int[] highscores = new int[] { 2500, 2000, 1500, 1000, 500 };
    
    public static void load(FileIO files) 
    {
        BufferedReader in = null;
        try 
        {
	        in = new BufferedReader(new InputStreamReader(files.readFile(".tiltiland")));
	        MusicEnabled = Boolean.parseBoolean(in.readLine());
	        SFXEnabled = Boolean.parseBoolean(in.readLine());
        
	        for (int i = 0; i < 5; i++) 
	        {
	            highscores[i] = Integer.parseInt(in.readLine());
	        }
     	} 
        catch (IOException e) 
    	{
	           
        } 
        catch (NumberFormatException e) 
        {
	            
        } 
        finally 
        {
            try 
            {
	            if (in != null)
	            {
	                in.close();
	            }
            } 
	        catch (IOException e) 
	        {
	        	
	        }
        }
    }
    
    public static void save(FileIO files) 
    {
        BufferedWriter out = null;
        try 
        {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile("tiltiland")));
            out.write(Boolean.toString(MusicEnabled));
            out.write(Boolean.toString(SFXEnabled));
            
            for (int i = 0; i < 5; i++) 
            {
                out.write(Integer.toString(highscores[i]));
            }
        } 
        catch (IOException e) 
        {
        	
        } 
        finally 
        {
            try 
            {
                if (out != null)
                    out.close();
            } 
            catch (IOException e) 
            {
            	
            }
        }
    }

    public static void addScore(int score) 
    {
        for (int i = 0; i < 5; i++) 
        {
            if (highscores[i] < score) 
            {
                for (int j = 4; j > i; j--)
                {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = score;
                break;
            }
        }
    }
}
