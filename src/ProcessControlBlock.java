
public class ProcessControlBlock {

	private IProcess process;
	private int currInstruction;
	private int register0;
	private int register1;
	private int register2;
	private int register3;
	
	public int getRegister0() {
		return register0;
	}

	public void setRegister0(int register0) {
		this.register0 = register0;
	}

	public int getRegister1() {
		return register1;
	}

	public void setRegister1(int register1) {
		this.register1 = register1;
	}

	public int getRegister2() {
		return register2;
	}

	public void setRegister2(int register2) {
		this.register2 = register2;
	}

	public int getRegister3() {
		return register3;
	}

	public void setRegister3(int register3) {
		this.register3 = register3;
	}

	public ProcessControlBlock(IProcess proc, int currInstr){
		process = proc;
		currInstruction = currInstr;
	}
	
	public IProcess getProcess()
	{
		return process;
	}
	
	public int getCurrInstruction(){
		return currInstruction;
	}
	
	public void setCurrInstruction(int i){
		currInstruction = i;
	}
	
}
