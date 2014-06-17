package scom.game.tiltiland;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class AnimalHandler 
{
	public AnimalHandler()
	{
		Genesis();
	}
	enum creatures
	{
		elephant, giraffe, tiger, zebra, snake, gorilla, penguin, bear, sheep, kangaroo
	}
	
	public creatures[] Types = { creatures.elephant, creatures.giraffe, creatures.tiger, creatures.zebra, creatures.snake, creatures.gorilla, creatures.penguin, creatures.bear, creatures.sheep, creatures.kangaroo }; // animal type
	public int[][] Sprites = { {0,8,8,382}, {9,3,11,384}, {12,7,3,386}, {19,7,3,388}, {26,12,2,390}, {38,5,5,392}, {43,3,3,394}, {46,8,4,396}, {54,4,3,398}, {58,3,4,400}}; // sprite sheet XPOS,Width,Height
	
	public List<Animal> Pen = new ArrayList<Animal>();
	//private Animal[] Pen; // holds all the animals in existence
	
	public void Birth(creatures type, int x, int y)//handles a request to give birth
	{
		char gender;
		int sprite;
		int w;
		int h;
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
				Pen.add(new Animal( type, gender, sprite, x, y, w, h));
				break;
			}
		}
	}
		
	public Animal ChooseMate(Animal mate) // returns best possible mate
	{
		Animal temp = Pen.get(0);
		for(int i = 0 ; i < Pen.size(); ++i)
		{
			if(Pen.get(i).Type == mate.Type)
			{
				if(Math.abs(Pen.get(i).XPos - 384) < Math.abs(temp.XPos - 384))
				{
					temp = Pen.get(i);
				}
			}
		}
		return temp; // placeholder
	}
	
	public int Locate() // return xPOS of chosen mate
	{
		return 0; // placeholder
	}
	
	private char Gender;
	
	private void Genesis() // creates the initial animals for the game
	{
		Gender = 'm';
		Random generator = new Random();
		for(int i = 0 ; i < 10 ; ++i)
		{
			for(int j = 0 ; j < Types.length ; ++j)
			{
				int X = generator.nextInt(621 - 133) + 133;
				Birth( Types[i], X, Sprites[i][3], Gender);
				Gender = 'f';
			}
		}
	}
	
	public void Birth(creatures type, int x, int y, char Gender)//Overloaded birth used for genesis
	{
		char gender;
		int sprite;
		int w;
		int h;
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
				Pen.add(new Animal( type, gender, sprite, x, y, w, h));
				break;
			}
		}
	}
}