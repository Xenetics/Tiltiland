package scom.game.tiltiland;

public class Island 
{
	public Island(int x, int y)
	{
		zoo = new AnimalHandler(); // creates animal handler
		XPos = x;
		YPos = y;
		rotation = 0;
	}
	
	// Objects Within Island
	AnimalHandler zoo;
	
	// Position on screen
	public int XPos;
	public int YPos;
	public double rotation;
}
