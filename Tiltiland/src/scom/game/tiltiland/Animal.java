package scom.game.tiltiland;

public class Animal 
{
	public Animal(String type, char gender, int spriteX, int motherX, int motherY, int width, int height)
	{
		Type = type;
		Weight = width * height;
		Gender = gender;
		Age = 0;
		SpriteX = spriteX;
		Width = width;
		Height = height;
		XPos = motherX;
		YPos = motherY;
	}
	
	public int XPos; // animals POS in x
	public int YPos; // animals POS in y
	
	// Attributes: probably make all private then make getters
	public String Type; // what kind of animal. eg. elephant, penguin, or bear
	public int Weight; // how much animal weighs and also how much score its worth
	public char Gender;
	
	private int Age; // how old the animal is in seconds
	
	
	public int SpriteX; // X POS on sprite sheet
	public int Width; // sprite width
	public int Height; // sprite height
	
	public void Birthday()
	{
		Age += 1;
	}
	
	public void Wander()
	{
		
	}
}
