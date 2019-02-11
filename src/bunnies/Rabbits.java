package bunnies;
/*
This program calculates how long will it take just a few rabbits to outnumber the human population 2:1.
Rabbits become fertile at the age of 4 months and never stop being fertile.
Rabbits will die at the age of 8 years (96 months)
Female rabbits will give birth once a month. The offspring will be the following each time (5 males and 9 females)
We shall start with one female and one male rabbit both being the age of 2 months
We must pretend that the human population is 350 million people since int can't handle billions
*/

public class Rabbits
{
	public int maxAge = 96; //Maximum age is set to 96 months --> 8 years
	public int humanPopulation = 350000000;
	public int goal = 700000000;
	public int colonyAmount = 2;
	public int timePassed = 0;
	public int maturity = 4;
	public int femaleAmount = 1;
	public int maleAmount = 1;
	public int[] femaleRabbits = new int[96];
	public int[] maleRabbits = new int[96];
	
	public Rabbits ()
	{
		femaleRabbits[3] = 3;
		maleRabbits[3] = 3;
	}
	
	private void progress()
	{
		if (timePassed > maturity)
		{
			//At this time every rabbit at the age of 8 years will die and cannot reproduce
			femaleRabbits[maxAge-1] = 0;
			maleRabbits[maxAge-1] = 0;
			
			//Going over the table and making the rabbits reproduce
			for (int i = maxAge-1; i > 0; i--)
			{
				//Checking if there are rabbits in each age group to reproduce
				//and making sure each rabbit to reproduce is at least 4 months old
				if (femaleRabbits[i] >= 1 && i > maturity-1)
				{
					femaleRabbits[0] = femaleRabbits[0] + femaleRabbits[i] *9;
					maleRabbits[0] = maleRabbits[0] + femaleRabbits[i] *5;
				}
			}
			
			//The rabbits shall now age one month
			for (int i = maxAge -1; i > 0; i--)
			{
				femaleRabbits[i] = femaleRabbits[i-1];
				maleRabbits [i] = maleRabbits[i-1];
			}
			
			//As the rabbits have aged there should be no rabbits at the age of 0 months
			femaleRabbits[0] = 0;
			maleRabbits[0] = 0;
			
			timePassed++;
			//Printing the colony
			for(int i = 0; i < maxAge; i++)
			{
				colonyAmount = colonyAmount + femaleRabbits[i] + maleRabbits[i];
			}
			
			//Keeping track of the new amounts for females and males
			for (int i = 0; i < maxAge; i++)
			{
				femaleAmount = femaleAmount +femaleRabbits[i];
				maleAmount = maleAmount + maleRabbits[i];
			}
			
			System.out.println("\n------------------------------------------------------------");
			System.out.println(timePassed + " months has passed!");
			System.out.println("The number of female rabbits: " + femaleAmount);
			System.out.println("The number of male rabbits: " + maleAmount);
			System.out.println("The number of rabbits in the colony: " + colonyAmount);
			System.out.println("------------------------------------------------------------");
			return;
		}
		else
		{
			timePassed++;
			System.out.println("\n------------------------------------------------------------");
			System.out.println(timePassed + " months has passed!");
			System.out.println("The number of female rabbits: " + femaleAmount);
			System.out.println("The number of male rabbits: " + maleAmount);
			System.out.println("The number of rabbits in the colony: " + colonyAmount);
			System.out.println("------------------------------------------------------------");
			return;
		}
	}
	
	
	
	public static void main (String[] args)
	{
		Rabbits colony = new Rabbits();
		while (colony.colonyAmount < 2*colony.humanPopulation)
		{
			colony.progress();
		}
		
		System.out.println("It took the rabbits " + colony.timePassed + " months to take over the world!");
	}
	
}

