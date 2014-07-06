package scom.game.tiltiland;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalHandler 
{
	enum creatures
	{
		elephant, giraffe, tiger, zebra, snake, gorilla, penguin, bear, sheep, kangaroo
	}
	
	private int UniqueID;
	private char Gender;
	private int Score;
	
	public AnimalHandler()
	{
		Genesis();
		UniqueID = 0;
	}
	
	public int GiveUniqueID()
	{
		return UniqueID++;
	}
	
	public creatures[] Types = { creatures.elephant, creatures.giraffe, creatures.tiger, creatures.zebra, creatures.snake, creatures.gorilla, creatures.penguin, creatures.bear, creatures.sheep, creatures.kangaroo }; // animal type
	private int[][] Sprites = { {0,8,8,382}, {9,3,11,384}, {12,7,3,386}, {19,7,3,388}, {26,12,2,390}, {38,5,5,392}, {43,3,3,394}, {46,8,4,396}, {54,4,3,398}, {58,3,4,400}}; // sprite sheet XPOS,Width,Height
	
	public List<Animal> Pen = new ArrayList<Animal>();
	public int[][] Census = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}}; // array of ints for gender amounts of animals  M/F
	
	public void TakeCensus(creatures type, char gender, boolean LD) // Adds or removes number of animals of a gender to Census
	{
		for(int i = 0; i < Types.length; ++i)
		{
			if(type == Types[i])
			{
				if(LD == true)
				{
					if(gender == 'm')
					{
						Census[i][0] += 1;
					}
					else
					{
						Census[i][1] += 1;
					}
				}
				else
				{
					if(gender == 'm')
					{
						if(Census[i][0] < 0)
						{
							Census[i][0] = 0;
						}
						else
						{
							Census[i][0] -= 1;
						}
					}
					else
					{
						if(Census[i][1] < 0)
						{
							Census[i][1] = 0;
						}
						else
						{
							Census[i][1] -= 1;
						}
					}
				}
				break;
			}
		}
	}
	
	public void AcurateCount()
	{
		int[][] AccuracyCensus = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}}; // array of int's for gender amounts of animals  M/F
		
		for(int i = 0; i < Types.length; ++i)
		{
			for(int j = 0; j < Pen.size(); ++j)
			{
				if(Pen.get(j).GetType() == Types[i] && Pen.get(j).GetOnGround() == true)
				{
					if(Pen.get(j).GetGender() == 'm')
					{
						AccuracyCensus[i][0] += 1;
					}
					else
					{
						AccuracyCensus[i][1] += 1;
					}
				}
			}
		}
		
		Census = AccuracyCensus;
	}
	
	public void Birth(creatures type, int x, int y)//handles a request to give birth
	{
		char gender;
		int sprite;
		int w;
		int h;
		int id;
		for(int i = 0 ; i < Types.length ; ++i )
		{
			if(type == Types[i])
			{
				double Random = Math.random();
				if(Random > 0.52)
				{
					gender = 'm';
				}
				else
				{
					gender = 'f';
				}
				sprite = Sprites[i][0];
				w = Sprites[i][1];
				h = Sprites[i][2];
				id = GiveUniqueID();
				Pen.add(new Animal( type, gender, sprite, x, y, w, h, id));
				Score = Score + (w * h);
				TakeCensus(type, gender, true);
				break;
			}
		}
	}
		
	public int ChooseMate(Animal mate) // returns best possible mate
	{
		Animal temp = Pen.get(0);
		int id = -1;
		
		for(int i = 0 ; i < Pen.size(); ++i)
		{
			if(Pen.get(i).GetType() == mate.GetType() && Pen.get(i).GetGender() == 'm' && Pen.get(i).GetOnGround() == true)
			{
				if(id == -1)
				{
					temp = Pen.get(i);
					id = temp.GetID();
				}
				
				if(Math.abs(Pen.get(i).GetXPos() - mate.GetXPos()) < Math.abs(temp.GetXPos() - mate.GetXPos()))
				{
					temp = Pen.get(i);
					id = Pen.get(i).GetID();
				}
			}
		}
		return id;
	}
	
	private void Genesis() // creates the initial animals for the game
	{
		Random generator = new Random();
		for(int i = 0 ; i < 10 ; ++i)//make it back to ten
		{
			Gender = 'm';
			for(int j = 0 ; j < 3 ; ++j)
			{
				int X = generator.nextInt(621 - 133) + 133;
				Birth( Types[i], X, Sprites[i][3], Gender);
				Gender = 'f';
			}
		}
	}
	
	private void Slaughter()
	{
		for(int i = 0; i < Pen.size() ; ++i)
		{
			if(Pen.get(i).GetAge() >= Pen.get(i).GetMaxAge())
			{
				TakeCensus(Pen.get(i).GetType(), Pen.get(i).GetGender(), false);
				Score = Score + Pen.get(i).GetWeight();
				Pen.remove(i);
			}
		}
	}
	
	public void Murder(int i)
	{
		Pen.remove(i);
	}
	
	public void Birthdays() // ages and make animal breed in needed
	{
		for(int i = 0 ; i < Pen.size(); ++i)
		{
			Pen.get(i).Breed(this);
		}
		
		Slaughter();
	}
	
	public void Birth(creatures type, int x, int y, char Gender) //Overloaded birth used for Genesis
	{
		int sprite;
		int w;
		int h;
		int id;
		for(int i = 0 ; i < Types.length ; ++i )
		{
			if(type == Types[i])
			{
				sprite = Sprites[i][0];
				w = Sprites[i][1];
				h = Sprites[i][2];
				id = GiveUniqueID();
				Pen.add(new Animal(type, Gender, sprite, x, y, w, h, id));
				TakeCensus(type, Gender, true);
				break;
			}
		}
	}
	
	// Getters
	public int GetScore()
	{
		return Score;
	}
	
	// Setters
	public void SetScore(int set)
	{
		Score = set;
	}
}
