import java.util.Iterator;
import java.util.LinkedList;

public class Simulation {
	
	public static void main (String [] args)
	{
		IRandomValueGenerator rand = new RandomValueGenerator();
		IProcessor processor;
		
		int pid = 0;
		String procName = "process";
		int totalInstructions = 100;
		
		//SimProcess[] processes = new SimProcess[10];
		ProcessControlBlock[] pcbs = new ProcessControlBlock[10];
		LinkedList<ProcessControlBlock> ready = new LinkedList<ProcessControlBlock>();
		LinkedList<ProcessControlBlock> blocked = new LinkedList<ProcessControlBlock>();
		
		IProcess process;
		for (int i =0; i<pcbs.length;i++)
		{
			totalInstructions = rand.getNextInt(300,101);
			process = new SimProcess(++pid, procName+pid, totalInstructions, rand );
			pcbs[i] = new ProcessControlBlock(process, 0);
			ready.addLast(pcbs[i]);
		}
		
		final int QUANTUM = 5;
		int quantumCounter = 0;
		ProcessControlBlock pcb = ready.removeFirst();
		processor = new SimProcessor(rand, pcb.getProcess());
		ProcessState procState; 
		boolean needsContextSwitch = false;
		
		for(int i = 1; i <= 3000; i++)
		{
			System.out.print("Step " + i + " ");
			
			if (needsContextSwitch)
			{
				if (!ready.isEmpty()){
					pcb = contextSwitch(processor, pcb, ready);
					quantumCounter = 0;
					needsContextSwitch = false;
				}
				else
				{
					System.out.println("No processes currently ready");
				}
					
			}
			else{
				procState = processor.executeNextInstruction();
				quantumCounter++;
				
				if (quantumCounter >= QUANTUM)
				{
					System.out.println("\n***Quantum Expired***\n");
					ready.addLast(pcb);
					needsContextSwitch = true;
				}
				else{
					switch(procState)
					{
					
					case BLOCKED:
						System.out.println("\n***Process Blocked***\n");
						blocked.addLast(pcb);
						needsContextSwitch = true;
						break;
										
					case FINISHED:
						System.out.println("\n***Process Completed***\n");
						needsContextSwitch = true;
						break;
						
					}
				}
				
			}
			
			if(!blocked.isEmpty())
				{wakeUpProcessThirtyPercent(rand, blocked, ready);}
			
		}
	
	}
	
	public static ProcessControlBlock contextSwitch(IProcessor processor, ProcessControlBlock pcb, LinkedList<ProcessControlBlock> ready){
		
		
			System.out.print("Context Switch: ");
			System.out.println("\n\tSaving Process: " + pcb.getProcess().getPid());
			System.out.print("\tInstruction: " + processor.getCurrentInstruction());
			pcb.setCurrInstruction(processor.getCurrentInstruction());
			
			System.out.print(" R1: " + processor.getRegisterValue(0) +",");
			pcb.setRegister0(processor.getRegisterValue(0));
			System.out.print(" R2: " + processor.getRegisterValue(1) +",");
			pcb.setRegister1(processor.getRegisterValue(1));
			System.out.print(" R3: " + processor.getRegisterValue(2) +",");
			pcb.setRegister2(processor.getRegisterValue(2));
			System.out.print(" R4: " + processor.getRegisterValue(3) +",\n");
			pcb.setRegister3(processor.getRegisterValue(3));
			
		
			
			pcb = ready.removeFirst();
			
			System.out.println("\tRestoring Process: " + pcb.getProcess().getPid());
			processor.setCurrentProcess(pcb.getProcess());
			
			System.out.print("\tInstruction: " + pcb.getCurrInstruction());
			processor.setCurrentInstruction(pcb.getCurrInstruction());
			
			processor.setRegisterValue(0, pcb.getRegister0());
			System.out.print(" R1: " + processor.getRegisterValue(0) +",");
			
			processor.setRegisterValue(1, pcb.getRegister1());
			System.out.print(" R2: " + processor.getRegisterValue(1) +",");
			
			processor.setRegisterValue(2, pcb.getRegister2());
			System.out.print(" R3: " + processor.getRegisterValue(2) +",");
			
			processor.setRegisterValue(3, pcb.getRegister3());
			System.out.print(" R4: " + processor.getRegisterValue(3) +",\n");
			
			
			
		
		return pcb; 
		}
	
	public static void wakeUpProcessThirtyPercent(IRandomValueGenerator rand, LinkedList<ProcessControlBlock> blocked, LinkedList<ProcessControlBlock> ready)
	{
		/*for(ProcessControlBlock p: blocked)
		{
			if(rand.getTrueWithProbability(.3))
			{

				ready.addLast(p);
				blocked.remove(p);
			}
		}*/
		
		Iterator<ProcessControlBlock> iter = blocked.iterator();
		ProcessControlBlock p;
		while (iter.hasNext())
		{
			p=iter.next();
			if(rand.getTrueWithProbability(.3))
			{
				iter.remove();
				ready.addLast(p);
			}
				
			
		}
	}

}
