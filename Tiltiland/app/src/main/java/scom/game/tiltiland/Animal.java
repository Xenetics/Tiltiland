package scom.game.tiltiland;

import scom.game.tiltiland.AnimalHandler.creatures;

import java.util.Random;

public class Animal 
{
	public Animal(creatures type, char gender, int spriteX, int motherX, int motherY, int width, int height, int id)
	{
		SpriteX = spriteX;
		Type = type;
		Weight = width * height;
		Gender = gender;
		ID = id;
		Age = 0;
		Width = width;
		Height = height;
		XPos = motherX;
		YPos = motherY;
		onGround = true;
		Randoms(); // determines starting
		chosen = false;
		state = AnimalState.Normal;
		ReserveState = AnimalState.Normal;
	}
	
	enum AnimalState //state machine
    {
        Normal, InHeat, Panic
    }
	    
    private AnimalState state;
    private AnimalState ReserveState;
    
    // Attributes
	private int XPos; // animals POS in x
	private int YPos; // animals POS in y
	private creatures Type; // what kind of animal. eg. elephant, penguin, or bear
	private int Weight; // how much animal weighs and also how much score its worth
	private char Gender;
	private int ID;
	private boolean onGround;
	
	private int Age; // how old the animal is in seconds
	private int MaxAge; // how old they will live to
	
	private int SpriteX; // X POS on sprite sheet
	private int Width; // sprite width
	private int Height; // sprite height
	
	private int fertile; 
	private boolean direction; // what direction they are traveling. true = right, false = left
	private int DirChange; // count for change direction
	private double ChangeChance; // % chance for the animal to change direction ever second
	private int mateID; // save mate that was chosen
	private boolean chosen;
	
	private void Randoms()
	{
		double Random = Math.random();
		if(Random > 0.50)
		{
			direction = true;
		}
		else
		{
			direction = false;
		}
		
		Random generator = new Random();
		int Random2 = generator.nextInt(80 - 50) + 50;
		MaxAge = Random2; // make random between 50 and 80
		
		Random generator2 = new Random();
		int Random3 = generator2.nextInt(7 - 2) + 2;
		fertile = Random3; // make random between 2 and 7
		
		double range = (0.50 - 0.10) + 0.10;
		double Random4 = Math.random() * range + 0.10;
		ChangeChance = Random4; // make random between 0.10 and 0.50
	}
	
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
		Age += 1;
		
		if (Gender == 'f')
		{
			if(fertile == 10)
			{
				state = AnimalState.InHeat;
				fertile = 0;
			}
			
			if(state == AnimalState.Normal)
			{
				fertile += 1;
			}
		
			if(chosen == true)
			{
				for(int i = 0; i < zoo.Pen.size(); ++i)
				{
					if (zoo.Pen.get(i).ID == mateID)
					{
						break;
					}
					else if(i == zoo.Pen.size() - 1)
					{
						chosen = false;
					}
				}
			}
			
			if (chosen == false && state == AnimalState.InHeat) // every 10 seconds will choose mate if in heat
			{
				mateID = zoo.ChooseMate(this);
				if(mateID != -1)
				{
					chosen = true;
				}
			}
		}
	}
	
	public void Panicker() // called to make animal panic
	{
		ReserveState = state;
		state = AnimalState.Panic;
	}
	
	public void Calmer() // called to calm animal down
	{
		state = ReserveState;
	}
	
	public void Move(AnimalHandler zoo, Double tilt) // makes animals walk back and forth
	{
		if(onGround)
		{
			if(tilt > 22)
			{
				XPos += 0.5 + ((tilt * tilt)* 0.005) ;
			}
			else if(tilt < -22)
			{
				XPos -= 0.5 + ((tilt * tilt)* 0.005) ;
			}
			else
			{	
				if(Gender == 'f')
				{
					if(state == AnimalState.InHeat && chosen == true && (XPos - 2 <= GetMatePos(zoo) && XPos + 2 >= GetMatePos(zoo)) && onGround == true) // birth when at mate
					{
						zoo.Birth(Type, XPos, YPos);
						state = AnimalState.Normal;
						chosen = false;
						mateID = -1;
						direction = !direction;
					}
					
					if(state == AnimalState.InHeat) // move to mate
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
				}
				
				if(state != AnimalState.InHeat) // Random Wander with critical mass
				{
					DirChange += 1;
					if(DirChange >= 60)
					{
						double Random = Math.random();
						if(Random < ChangeChance)
						{
							direction = !direction;
							ChangeChance = 0.10;
						}
						else
						{
							ChangeChance += 0.10;
						}
						DirChange = 0;
					}
				}
				
				if(direction == true) // move based on direction
				{
					if(state == AnimalState.InHeat)
					{
						XPos += 3;
					}
					else if(state == AnimalState.Panic)
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
					if(state == AnimalState.InHeat)
					{
						XPos -= 3;
					}
					else if(state == AnimalState.Panic)
					{
						XPos -= 2;
					}
					else
					{
						XPos -= 1;
					}
				}
			}
			
			if(YPos < 380)
			{
				YPos = 380;
			}
			
			if(XPos <= 134 || XPos + Width - 1 >= 634 ) // change direction when reach edge
			{
				direction = !direction;
			}
			
			if(XPos <= 130 || XPos + Width - 1 >= 638 )
			{
				onGround = false;
				zoo.TakeCensus(Type, Gender, false);
				double globalY =  Math.abs((384 - XPos)) * Math.tan(Math.toRadians(tilt));
				YPos = (int) Math.round(globalY + 512 + 128);
			}

		}
		else
		{
			if( YPos < 512)
			{
				YPos += 6;
			}
			else
			{
				YPos += 2;
			}
		}
	}
	
	// Getters
	public int GetXPos()
	{
		return XPos;
	}
	public int GetYPos()
	{
		return YPos;
	}
	public creatures GetType()
	{
		return Type;
	}
	public int GetWeight()
	{
		return Weight;
	}
	public char GetGender()
	{
		return Gender;
	}
	public int GetID()
	{
		return ID;
	}
	public boolean GetOnGround()
	{
		return onGround;
	}
	public int GetAge()
	{
		return Age;
	}
	public int GetMaxAge()
	{
		return MaxAge;
	}
	public int GetSpriteX()
	{
		return SpriteX;
	}
	public int GetWidth()
	{
		return Width;
	}
	public int GetHeight()
	{
		return Height;
	}
	// Setters
	
	public void SetOnGround(boolean set)
	{
		onGround = set;
	}
	public void SetYPos(int set)
	{
		YPos = set;
	}
}
