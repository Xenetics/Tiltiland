package scom.game.tiltiland;


import java.util.ArrayList;
import java.util.List;

public class AnimalHandler 
{
	public AnimalHandler()
	{
		
	}
	
	public String[] Types = { "elephant", "giraffe", "tiger", "zebra", "alligator", "gorilla", "penguin", "bear", "sheep", "kangaroo" }; // animal type
	public int[][] Sprites = { {0,8,8}, {9,3,11}, {12,7,3}, {19,7,3}, {26,12,2}, {38,5,5}, {43,3,3}, {46,8,4}, {54,4,3}, {58,3,4}}; // sprite sheet XPOS,Width,Height
	
	public List<Animal> Pen = new ArrayList<Animal>();
	//private Animal[] Pen; // holds all the animals in existence
	
	public void Birth(String type, int x, int y)//handles a request to give birth
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
	
	public Animal ChooseMate() // returns best possible mate
	{
		return Pen.get(0); // placeholder
	}
	
	public int Locate() // return xPOS of chosen mate
	{
		return 0; // placeholder
	}
}
