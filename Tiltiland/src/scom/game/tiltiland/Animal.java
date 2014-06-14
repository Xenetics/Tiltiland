package scom.game.tiltiland;

public class Animal 
{
	public Animal(String type, int weight, char gender, int spriteX, int motherX, int motherY)
	{
		Type = type;
		Weight = weight;
		Gender = gender;
		Age = 0;
		SpriteX = spriteX;
		XPos = motherX;
		YPos = motherY;
	}
	
	public int XPos; // animals POS in x
	public int YPos; // animals POS in y
	
	// Attributes: probably make all private then make getters
	public String Type; // what kind of animal. eg. elephant, penguin, or bear
	public int Weight; // how much animal weighs and also how much score its worth
	public char Gender;
	public int Age; // how old the animal is in seconds
	
	
	private int SpriteX; // X POS on sprite sheet
	
	public void Animate()
	{
		
	}
}
