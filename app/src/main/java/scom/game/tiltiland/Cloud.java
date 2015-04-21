package scom.game.tiltiland;

public class Cloud 
{
	public Cloud(int x, int y, int s)
	{
		XPos = x;
		YPos = y;
		Speed = s;
	}
	
	private int XPos;
	private int YPos;
	private int Speed;
	
	
	// Getters
	public int GetXPos()
	{
		return XPos;
	}
	public int GetYPos()
	{
		return YPos;
	}
	public int GetSpeed()
	{
		return Speed;
	}
	
	// Setters
	public void SetXPos(int x)
	{
		XPos = x;
	}
	public void SetYPos(int y)
	{
		YPos = y;
	}
}
