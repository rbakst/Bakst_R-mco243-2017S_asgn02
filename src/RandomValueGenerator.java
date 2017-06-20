import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator{
	
	private Random rand; 
	public RandomValueGenerator(){
		rand = new Random(System.currentTimeMillis());
	}
	
	public int getNextInt(){
		return rand.nextInt();
	}
	
	public int getNextInt(int range, int add){
		return rand.nextInt(range) + add;
	}
	
	public boolean getTrueWithProbability(double p){
		
		p*=100;
		int val = rand.nextInt(100)+1;
		if(val <= p)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	

}
