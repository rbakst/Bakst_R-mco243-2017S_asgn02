
public interface IProcessor {

	public IProcess getCurrentProcess();
	public void setCurrentProcess(IProcess process);
	public int getCurrentInstruction();
	public void setCurrentInstruction(int instruction);
	public ProcessState executeNextInstruction();
	public void setRegisterValue(int i, int val);
	public int getRegisterValue(int i);
	
}
