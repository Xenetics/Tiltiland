package scom.game.tiltiland;


import java.util.ArrayList;
import java.util.List;

public class AnimalHandler 
{
	public AnimalHandler()
	{
		
	}
	
	private String[] Types = { "elephant", "giraffe", "tiger", "zebra", "alligator", "gorilla", "penguin", "bear", "sheep", "kangaroo" }; // animal type
	private int[][] Sprites = { {0,8,8}, {9,3,11}, {12,7,3}, {19,7,3}, {26,12,2}, {38,5,5}, {43,3,3}, {46,8,4}, {54,4,3}, {58,3,4}}; // sprite sheet XPOS,Width,Height
	
	private List<Animal> Pen = new ArrayList<Animal>();
	//private Animal[] Pen; // holds all the animals in existence
	
	public void Birth(String type, int x, int y)
	{
		int weight;
		char gender;
		int sprite;
		for(int i = 0 ; i < Types.length ; ++i )
		{
			if(type == Types[i])
			{
				weight = Sprites[i][1] * Sprites[i][2];
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
				Pen.add(new Animal( type, weight, gender, sprite, x, y));
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
