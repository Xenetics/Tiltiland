package scom.game.tiltiland;

import android.util.Log;
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
		fertile = 0;
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
	
	private int fertile; // ==========================================remember to set to 0 when give birth
	private boolean InHeat; // is animal ready to mate =============== remember to set false when give birth
	private boolean bred; // ================================== remember to set to true when gave birth
	
	public void Birthday() // ages animal
	{
		Age += 1;
		
		if(fertile == 10)
		{
			InHeat = true;
			bred = false;
		}
		if(!InHeat)
		{
			fertile += 1;
		}
	}
	
	Animal mate; // save mate that was chosen
	
	public void Breed(AnimalHandler zoo) // decides to breed
	{
		if (InHeat == true && bred == false) // every 10 seconds will choose mate if in heat
		{
			mate = zoo.ChooseMate(this);
		}
		else
		{
			InHeat = false;
		}
	}
	
	private boolean direction;
	
	public void Move() // makes animals walk back and forth
	{
		if(InHeat == true && bred == false)
		{
			if(XPos < mate.XPos)
			{
				direction = true;
			}
			else
			{
				direction = false;
			}
		}
		else if (XPos <= 133 || XPos + Width >= 635 ) // change direction when reach edge
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
	
	public void Reproduce() // when reaches make will call birth and all that
	{
		
	}
}
