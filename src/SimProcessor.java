
public class SimProcessor implements IProcessor{

	private IRandomValueGenerator rand;
	private IProcess currentProcess;
	private int[] registers;
	private int currInstruction;
	
	SimProcessor(IRandomValueGenerator gen, IProcess process){
		rand= gen;
		currentProcess = process;
		registers = new int[4];
		currInstruction = 0;
		
	}
	
	public void setRegisterValue(int i , int val){}
	public int getRegisterValue(int i){
		return rand.getNextInt();
	}
	
	public int getCurrentInstruction(){
		return currInstruction;
	}
	public void setCurrentInstruction(int i){
		currInstruction = i;
	}
	
	public ProcessState executeNextInstruction()
	{
		ProcessState ps = currentProcess.execute(currInstruction);
		currInstruction++;
		return ps;
	}

	@Override
	public IProcess getCurrentProcess() {
		// TODO Auto-generated method stub
		return currentProcess;
	}

	@Override
	public void setCurrentProcess(IProcess process) {
		// TODO Auto-generated method stub
		currentProcess = process;
		
	}

	
}
