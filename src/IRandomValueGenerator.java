
public interface IRandomValueGenerator {

	public int getNextInt();
	public int getNextInt(int range, int add);
	public boolean getTrueWithProbability(double p);
}
