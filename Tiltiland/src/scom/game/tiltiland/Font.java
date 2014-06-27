package scom.game.tiltiland;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class Font extends Screen
{
	Graphics g = game.getGraphics();
	
	public Font(Game game, String input, int x, int y, float scale)
	{
		super(game);
		Input = input;
		X = x;
		Y = y;
		Scale = scale;
		Collect();
	}
	
	public Font(Game game, int inputNumber, int x, int y, float scale)
	{
		super(game);
		Input = String.valueOf(inputNumber);
		X = x;
		Y = y;
		Scale = scale;
		Collect();
	}
	
	// For all
	String Input;
	public char Collection[] = new char[50];
	int X;
	int Y;
	
	// For scaling
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
	Bitmap Letter;
    Bitmap ScaledFont;
    Pixmap Final;
    float Scale;
    float ScaleX;
    float ScaleY;
	
	public void Collect() // Collects the chars in the string and places in an array
	{
		for (int i = 0 ; i < Input.length() ; ++i)
		{
			Collection[i] = Input.charAt(i);
		}
		
		Draw();
	}
	
	public void Draw() // Draws all letters in String
	{
		for (int i = 0 ; i < Collection.length; ++i) 
		{
			switch((int)Collection[i])
			{
				case 48: // 0
					DrawFont(X, Y, 416, 77, 48, 70);
					break;
				case 49: // 1
					DrawFont(X, Y, 0, 77, 32, 70);
					break;
				case 50: // 2
					DrawFont(X, Y, 32, 77, 48, 70);
					break;
				case 51: // 3
					DrawFont(X, Y, 80, 77, 48, 70);
					break;
				case 52: // 4
					DrawFont(X, Y, 128, 77, 48, 70);
					break;
				case 53: // 5
					DrawFont(X, Y, 176, 77, 48, 70);
					break;
				case 54: // 6
					DrawFont(X, Y, 224, 77, 48, 70);
					break;
				case 55: // 7
					DrawFont(X, Y, 272, 77, 48, 70);
					break;
				case 56: // 8
					DrawFont(X, Y, 320, 77, 48, 70);
					break;
				case 57: // 9
					DrawFont(X, Y, 368, 77, 48, 70);
					break;
				case 46: // .
					DrawFont(X, Y, 464, 77, 24, 70);
					break;
				case 58: // :
					DrawFont(X, Y, 488, 77, 24, 70);
					break;
				case 97: // A
					DrawFont(X, Y, 0, 0, 48, 70);
					break;
				case 98: // B
					DrawFont(X, Y, 48, 0, 48, 70);
					break;
				case 99: // C
					DrawFont(X, Y, 96, 0, 39, 70);
					break;
				case 100: // D	
					DrawFont(X, Y, 135, 0, 49, 70);
					break;
				case 101: // E
					DrawFont(X, Y, 184, 0, 48, 70);
					break;
				case 102: // F
					DrawFont(X, Y, 232, 0, 32, 70);
					break;
				case 103: // G
					DrawFont(X, Y, 264, 0, 48, 70);
					break;
				case 104: // H
					DrawFont(X, Y, 312, 0, 48, 70);
					break;
				case 105: // I
					DrawFont(X, Y, 360, 0, 24, 70);
					break;
				case 106: // J
					DrawFont(X, Y, 384, 0, 32, 70);
					break;
				case 107: // K
					DrawFont(X, Y, 416, 0, 48, 70);
					break;
				case 108: // L
					DrawFont(X, Y, 464, 0, 24, 70);
					break;
				case 109: // M
					DrawFont(X, Y, 488, 0, 72, 70);
					break;
				case 110: // N
					DrawFont(X, Y, 560, 0, 48, 70);
					break;
				case 111: // O
					DrawFont(X, Y, 608, 0, 48, 70);
					break;
				case 112: // P
					DrawFont(X, Y, 656, 0, 48, 70);
					break;
				case 113: // Q
					DrawFont(X, Y, 656, 0, 48, 70);
					break;
				case 114: // R
					DrawFont(X, Y, 752, 0, 40, 70);
					break;
				case 115: // S
					DrawFont(X, Y, 792, 0, 40, 70);
					break;
				case 116: // T
					DrawFont(X, Y, 832, 0, 32, 70);
					break;
				case 117: // U
					DrawFont(X, Y, 864, 0, 48, 70);
					break;
				case 118: // V
					DrawFont(X, Y, 912, 0, 48, 70);
					break;
				case 119: // W
					DrawFont(X, Y, 960, 0, 64, 70);
					break;
				case 120: // X
					DrawFont(X, Y, 1024, 0, 48, 70);
					break;
				case 121: // Y
					DrawFont(X, Y, 1072, 0, 48, 70);
					break;
				case 122: // Z
					DrawFont(X, Y, 1120, 0, 48, 70);
					break;
				default:
					X = X + (int)(48 * Scale);
			}
		}
	}
	
	public void DrawFont(int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) 
	{
		//this can be cleaned up
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        Letter = Bitmap.createBitmap(Assets.fontBitmap, srcX, srcY, srcWidth, srcHeight);
        
        ScaleX = Letter.getWidth() * Scale;
        ScaleY = Letter.getHeight() * Scale;
        
        ScaledFont = Bitmap.createScaledBitmap(Letter, (int)ScaleX, (int)ScaleY, false);
        Final = g.newPixmap(ScaledFont, PixmapFormat.ARGB4444);
        g.drawPixmap(Final, X, Y, 0, 0, (int)(srcWidth * Scale), (int)(srcHeight * Scale));
        X = X + (int)(srcWidth * Scale);
    }
	
	public void update(float deltaTime)
	{
		
	}
	
	public void present(float deltaTime)
	{
		
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
}