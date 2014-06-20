package scom.game.tiltiland;

import scom.game.tiltiland.AnimalHandler.creatures;

public class Animal 
{
	public Animal(creatures type, char gender, int spriteX, int motherX, int motherY, int width, int height, int id)
	{
		Type = type;
		Weight = width * height;
		Gender = gender;
		ID = id;
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
		chosen = false;
	}
	
	public int XPos; // animals POS in x
	public int YPos; // animals POS in y
	
	// Attributes: probably make all private then make getters
	public creatures Type; // what kind of animal. eg. elephant, penguin, or bear
	public int Weight; // how much animal weighs and also how much score its worth
	public char Gender;
	public int ID;
	
	public int Age; // how old the animal is in seconds
	
	
	public int SpriteX; // X POS on sprite sheet
	public int Width; // sprite width
	public int Height; // sprite height
	
	private int fertile; 
	private boolean InHeat;
	private boolean bred; 
	
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
	
	int mateID; // save mate that was chosen
	boolean chosen;
	private int GetMatePos(AnimalHandler zoo) // returns mates pos
	{
		int POS = 0;
		for( int i = 0 ; i < zoo.Pen.size() ; ++i )
		{
			if(mateID == zoo.Pen.get(i).ID)
			{
				POS = zoo.Pen.get(i).XPos;
			}
		}
		return POS;
	}
	
	public void Breed(AnimalHandler zoo) // decides to breed
	{
		if (chosen == false && InHeat == true && bred == false) // every 10 seconds will choose mate if in heat
		{
			chosen = true;
			mateID = zoo.ChooseMate(this);
		}
		else
		{
			InHeat = false;
		}
	}
	
	private boolean direction; // what direction they are traveling. true = right, false = left
	
	
	
	public void Move(AnimalHandler zoo) // makes animals walk back and forth
	{
		if(chosen == true && XPos == GetMatePos(zoo)) // birth when at mate
		{
			fertile = 0;
			InHeat = false;
			bred = true;
			chosen = false;
			zoo.Birth(Type, XPos, YPos);
		}
		
		if(InHeat == true && bred == false) // move to mate
		{
			if(XPos < GetMatePos(zoo))
			{
				direction = true;
			}
			else
			{
				direction = false;
			}
		}
		
		if(XPos <= 133 || XPos + Width - 1 >= 635 ) // change direction when reach edge
		{
			direction = !direction;
		}
		
		if(direction == true) // move based on direction
		{
			if(InHeat == true && bred == false)
			{
				XPos += 2;
			}
			else
			{
				XPos += 1;
			}
		}
		else
		{
			if(InHeat == true && bred == false)
			{
				XPos -= 2;
			}
			else
			{
				XPos -= 1;
			}
		}
	}
}
