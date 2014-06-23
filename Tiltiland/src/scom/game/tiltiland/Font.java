package scom.game.tiltiland;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;

public class Font extends Screen
{
	Graphics g = game.getGraphics();
	
	public Font(Game game, String input, int x, int y)
	{
		super(game);
		Input = input;
		X = x;
		Y = y;
		Collect();
	}
	
	public Font(Game game, int inputNumber, int x, int y)
	{
		super(game);
		Input = String.valueOf(inputNumber);
		Y = y;
		Collect();
	}
	
	String Input;
	public char Collection[] = new char[50];
	int X;
	int Y;
	
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
					g.drawPixmap(Assets.font, X, Y, 416, 77, 48, 70);
					X = X + 48;
					break;
				case 49: // 1
					g.drawPixmap(Assets.font, X, Y, 0, 77, 32, 70);
					X = X + 32;
					break;
				case 50: // 2
					g.drawPixmap(Assets.font, X, Y, 32, 77, 48, 70);
					X = X + 48;
					break;
				case 51: // 3
					g.drawPixmap(Assets.font, X, Y, 80, 77, 48, 70);
					X = X + 48;
					break;
				case 52: // 4
					g.drawPixmap(Assets.font, X, Y, 128, 77, 48, 70);
					X = X + 48;
					break;
				case 53: // 5
					g.drawPixmap(Assets.font, X, Y, 176, 77, 48, 70);
					X = X + 48;
					break;
				case 54: // 6
					g.drawPixmap(Assets.font, X, Y, 224, 77, 48, 70);
					X = X + 48;
					break;
				case 55: // 7
					g.drawPixmap(Assets.font, X, Y, 272, 77, 48, 70);
					X = X + 48;
					break;
				case 56: // 8
					g.drawPixmap(Assets.font, X, Y, 320, 77, 48, 70);
					X = X + 48;
					break;
				case 57: // 9
					g.drawPixmap(Assets.font, X, Y, 368, 77, 48, 70);
					X = X + 48;
					break;
				case 46: // .
					g.drawPixmap(Assets.font, X, Y, 464, 77, 24, 70);
					X = X + 24;
					break;
				case 58: // :
					g.drawPixmap(Assets.font, X, Y, 488, 77, 24, 70);
					X = X + 24;
					break;
				case 97: // A
					g.drawPixmap(Assets.font, X, Y, 0, 0, 48, 70);
					X = X + 48;
					break;
				case 98: // B
					g.drawPixmap(Assets.font, X, Y, 48, 0, 48, 70);
					X = X + 48;
					break;
				case 99: // C
					g.drawPixmap(Assets.font, X, Y, 96, 0, 39, 70);
					X = X + 39;
					break;
				case 100: // D	
					g.drawPixmap(Assets.font, X, Y, 135, 0, 49, 70);
					X = X + 49;
					break;
				case 101: // E
					g.drawPixmap(Assets.font, X, Y, 184, 0, 48, 70);
					X = X + 48;
					break;
				case 102: // F
					g.drawPixmap(Assets.font, X, Y, 232, 0, 32, 70);
					X = X + 32;
					break;
				case 103: // G
					g.drawPixmap(Assets.font, X, Y, 264, 0, 48, 70);
					X = X + 48;
					break;
				case 104: // H
					g.drawPixmap(Assets.font, X, Y, 312, 0, 48, 70);
					X = X + 48;
					break;
				case 105: // I
					g.drawPixmap(Assets.font, X, Y, 360, 0, 24, 70);
					X = X + 24;
					break;
				case 106: // J
					g.drawPixmap(Assets.font, X, Y, 384, 0, 32, 70);
					X = X + 32;
					break;
				case 107: // K
					g.drawPixmap(Assets.font, X, Y, 416, 0, 48, 70);
					X = X + 48;
					break;
				case 108: // L
					g.drawPixmap(Assets.font, X, Y, 464, 0, 24, 70);
					X = X + 24;
					break;
				case 109: // M
					g.drawPixmap(Assets.font, X, Y, 488, 0, 72, 70);
					X = X + 72;
					break;
				case 110: // N
					g.drawPixmap(Assets.font, X, Y, 560, 0, 48, 70);
					X = X + 48;
					break;
				case 111: // O
					g.drawPixmap(Assets.font, X, Y, 608, 0, 48, 70);
					X = X + 48;
					break;
				case 112: // P
					g.drawPixmap(Assets.font, X, Y, 656, 0, 48, 70);
					X = X + 48;
					break;
				case 113: // Q
					g.drawPixmap(Assets.font, X, Y, 656, 0, 48, 70);
					X = X + 48;
					break;
				case 114: // R
					g.drawPixmap(Assets.font, X, Y, 752, 0, 40, 70);
					X = X + 48;
					break;
				case 115: // S
					g.drawPixmap(Assets.font, X, Y, 792, 0, 40, 70);
					X = X + 40;
					break;
				case 116: // T
					g.drawPixmap(Assets.font, X, Y, 832, 0, 32, 70);
					X = X + 32;
					break;
				case 117: // U
					g.drawPixmap(Assets.font, X, Y, 864, 0, 48, 70);
					X = X + 48;
					break;
				case 118: // V
					g.drawPixmap(Assets.font, X, Y, 912, 0, 48, 70);
					X = X + 48;
					break;
				case 119: // W
					g.drawPixmap(Assets.font, X, Y, 960, 0, 64, 70);
					X = X + 64;
					break;
				case 120: // X
					g.drawPixmap(Assets.font, X, Y, 1024, 0, 48, 70);
					X = X + 48;
					break;
				case 121: // Y
					g.drawPixmap(Assets.font, X, Y, 1072, 0, 48, 70);
					X = X + 48;
					break;
				case 122: // Z
					g.drawPixmap(Assets.font, X, Y, 1120, 0, 48, 70);
					X = X + 48;
					break;
				default:
					X = X + 48;
			}
		}
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