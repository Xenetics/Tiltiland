package scom.game.tiltiland;

import scom.game.tiltiland.AnimalHandler.creatures;

public class Animal 
{
	public Animal(creatures type, char gender, int spriteX, int motherX, int motherY, int width, int height)
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
		
		double Random = Math.random();
		if(Random > 0.50)
		{
			direction = true;
		}
		else
		{
			direction = false;
		}
	}
	
	public int XPos; // animals POS in x
	public int YPos; // animals POS in y
	
	// Attributes: probably make all private then make getters
	public creatures Type; // what kind of animal. eg. elephant, penguin, or bear
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
	
	private boolean direction;
	
	public void Wander()
	{
		if (XPos <= 133 || XPos + Width >= 635 ) // change direction when reach edge
		{
			direction = !direction;
		}
		
		if(direction == true) // move based on direction
		{
			XPos += 1;
		}
		else
		{
			XPos -= 1;
		}
	}
}
