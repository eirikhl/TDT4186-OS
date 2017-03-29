package Oving3.P3.round_robin;

/**
 * This class contains data associated with processes,
 * and methods for manipulating this data as well as
 * methods for displaying a process in the GUI.
 *
 * You will probably want to add more methods to this class.
 */
public class Process {
	/** The ID of the next process to be created */
	private static long nextProcessId = 1;
	/** The ID of this process */
	private long processId;
	/** The amount of memory needed by this process */
    private long memoryNeeded;
	/** The amount of cpu time still needed by this process */
    private long cpuTimeNeeded;

	/** The average time between the need for I/O operations for this process */
    private long avgIoInterval;
	/** The time left until the next time this process needs I/O */
    private long timeToNextIoOperation = 0;
	/** The time needed for an I/O operation */
    private long ioTimeNeeded;

	/** The time that this process has spent waiting in the memory queue */
	private long timeSpentWaitingForMemory = 0;
	/** The time that this process has spent waiting in the CPU queue */
	private long timeSpentInReadyQueue = 0;
	/** The time that this process has spent processing */
    private long timeSpentInCpu = 0;
	/** The time that this process has spent waiting in the I/O queue */
    private long timeSpentWaitingForIo = 0;
	/** The time that this process has spent performing I/O */
	private long timeSpentInIo = 0;

	/** The number of times that this process has been placed in the CPU queue */
	private long nofTimesInReadyQueue = 0;
	/** The number of times that this process has been placed in the I/O queue */
	private long nofTimesInIoQueue = 0;

	/** The global time of the last event involving this process */
	private long timeOfLastEvent;

    /** The time at which the process got switched into the CPU */
    private long oldTime;



	/**
	 * Creates a new process with given parameters. Other parameters are randomly
	 * determined.
	 * @param memorySize	The size of the memory unit.
	 * @param creationTime	The global time when this process is created.
	 */
	public Process(long memorySize, long creationTime) {
		// Memory need varies from 100 kB to 25% of memory size
		memoryNeeded = 100 + (long)(Math.random()*(memorySize/4-100));
		// CPU time needed varies from 100 to 10000 milliseconds
		cpuTimeNeeded = 100 + (long)(Math.random()*9900);
		// Average interval between I/O requests varies from 1% to 25% of CPU time needed
		avgIoInterval = (1 + (long)(Math.random()*25))*cpuTimeNeeded/100;
		// Generate random I/O time required
		ioTimeNeeded = (1 +(long)(Math.random()*25))*cpuTimeNeeded/100;
		// The first and latest event involving this process is its creation
		timeOfLastEvent = creationTime;
		// Assign a process ID
		processId = nextProcessId++;
		nextIoTime();
	}

	/**
	 * This method is called when the process leaves the memory queue (and
	 * enters the cpu queue).
     * @param clock The time when the process leaves the memory queue.
     */
    public void leftMemoryQueue(long clock) {
		  timeSpentWaitingForMemory += clock - timeOfLastEvent;
		  timeOfLastEvent = clock;
    }

    /**
	 * Returns the amount of memory needed by this process.
     * @return	The amount of memory needed by this process.
     */
	public long getMemoryNeeded() {
		return memoryNeeded;
	}

    /**
	 * Updates the statistics collected by the given Statistic object, adding
	 * data collected by this process. This method is called when the process
	 * leaves the system.
     * @param statistics	The Statistics object to be updated.
     */
	public void updateStatistics(Oving3.P3.round_robin.Statistics statistics) {
		
		statistics.totalTimeSpentWaitingForMemory += timeSpentWaitingForMemory;
        statistics.totalTimeSpentInReadyQueue += timeSpentInReadyQueue;
        statistics.totalTimeSpentInCpu += timeSpentInCpu;
        statistics.totalTimeSpentWaitingForIo += timeSpentWaitingForIo;
        statistics.totalTimeSpentInIo += timeSpentInIo;

        statistics.totalNofTimesInReadyQueue += nofTimesInReadyQueue;
        statistics.totalNofTimesInIoQueue += nofTimesInIoQueue;
	}

	public long getProcessId() {
		return processId;
	}

	// Add more methods as needed

	public long getCpuTimeNeeded(){return cpuTimeNeeded;}

    /**
     * Updates the value of timeSpentInCpu
     * @param clock The time at which the Process was switched out of the CPU
     */
    public void updateTimeSpentInCpu(long clock){
        timeSpentInCpu += clock;
        cpuTimeNeeded -= clock;
    }

    public void updateTimeSpentWaiting(long clock){
        timeSpentInReadyQueue += clock;
    }

	public long getAvgIoInterval() {
		return avgIoInterval;
	}

	public static long getNextProcessId() {
		return nextProcessId;
	}

	public static void setNextProcessId(long nextProcessId) {
		Process.nextProcessId = nextProcessId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public void setMemoryNeeded(long memoryNeeded) {
		this.memoryNeeded = memoryNeeded;
	}

	public void setCpuTimeNeeded(long cpuTimeNeeded) {
		this.cpuTimeNeeded = cpuTimeNeeded;
	}

	public void setAvgIoInterval(long avgIoInterval) {
		this.avgIoInterval = avgIoInterval;
	}

	public void updateTimeToNextIoOperation(long timeToNextIoOperation) {
		this.timeToNextIoOperation -= timeToNextIoOperation;
	}

	public long getTimeSpentWaitingForMemory() {
		return timeSpentWaitingForMemory;
	}

	public void updateTimeSpentWaitingForMemory(long timeSpentWaitingForMemory) {
		this.timeSpentWaitingForMemory += timeSpentWaitingForMemory;
	}

	public long getTimeSpentInReadyQueue() {
		return timeSpentInReadyQueue;
	}

	public void updateTimeSpentInReadyQueue(long timeSpentInReadyQueue) {
		this.timeSpentInReadyQueue += timeSpentInReadyQueue;
	}

	public long getTimeSpentInCpu() {
		return timeSpentInCpu;
	}

//	public void updateTimeSpentInCpu(long timeSpentInCpu) {
//		this.timeSpentInCpu += timeSpentInCpu;
//	}

	public long getTimeSpentWaitingForIo() {
		return timeSpentWaitingForIo;
	}

	public void updateTimeSpentWaitingForIo(long timeSpentWaitingForIo) {
		this.timeSpentWaitingForIo += timeSpentWaitingForIo;
	}

	public long getTimeSpentInIo() {
		return timeSpentInIo;
	}

	public void updateTimeSpentInIo(long timeSpentInIo) {
		this.timeSpentInIo += timeSpentInIo;
		this.ioTimeNeeded -= timeSpentInIo;
	}

	public long getNofTimesInReadyQueue() {
		return nofTimesInReadyQueue;
	}

	public void updateNofTimesInReadyQueue() {
		this.nofTimesInReadyQueue++;
	}

	public long getNofTimesInIoQueue() {
		return nofTimesInIoQueue;
	}

	public void updateNofTimesInIoQueue() {
		this.nofTimesInIoQueue++;
	}

	public long getTimeOfLastEvent() {
		return timeOfLastEvent;
	}

	public void setTimeOfLastEvent(long timeOfLastEvent) {
		this.timeOfLastEvent = timeOfLastEvent;
	}

	public long getOldTime() {
		return oldTime;
	}

	public void setOldTime(long oldTime) {
		this.oldTime = oldTime;
	}

	public long getTimeToNextIoOperation() {
		return timeToNextIoOperation;
	}
	public void nextIoTime(){
		timeToNextIoOperation = (long) (avgIoInterval*0.75+Math.random()*0.5*avgIoInterval);
	}

}
