
public class SimProcess implements IProcess{

	private IRandomValueGenerator rand;
	private int pid;
	private String procName;
	private int totalInstructions;
	//private int currInstruction;
		
	public SimProcess(int pid, String procName, int totalInstructions, IRandomValueGenerator gen){
		
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
		rand = gen;
		//currInstruction = 0;
	}
	
	public ProcessState execute(int i){
		StringBuilder info = new StringBuilder();
		info.append("PID: ");
		info.append(pid);
		info.append(" Name: ");
		info.append(procName);
		info.append(" Instruction Number ");
		info.append(i);
		
		System.out.println(info.toString());
		
		if (i >= totalInstructions)
		{
			return ProcessState.valueOf("FINISHED");
		}
		
		boolean isBlocked = rand.getTrueWithProbability(.15);
		if (isBlocked){
			return ProcessState.valueOf("BLOCKED");
		}
		else{
			return ProcessState.valueOf("READY");				
			}
		
	}

	@Override
	public int getPid() {
		// TODO Auto-generated method stub
		return pid;
	}

	@Override
	public String getProcName() {
		// TODO Auto-generated method stub
		return procName;
	}
}
