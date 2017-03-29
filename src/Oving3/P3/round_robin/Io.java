package Oving3.P3.round_robin;

import java.util.LinkedList;

/**
 * This class implements functionality associated with
 * the I/O device of the simulated system.
 */
public class Io {
    private Process activeProcess = null;

    private LinkedList<Process> queue;
    private long IOtime;
    private Oving3.P3.round_robin.Statistics stats;
    /**
     * Creates a new I/O device with the given parameters.
     * @param ioQueue		The I/O queue to be used.
     * @param avgIoTime		The average duration of an I/O operation.
     * @param statistics	A reference to the statistics collector.
     */
    public Io(LinkedList<Process> ioQueue, long avgIoTime, Oving3.P3.round_robin.Statistics statistics) {
        // Complete?
        this.queue = ioQueue;
        this.IOtime = avgIoTime;
        this.stats = statistics;
    }

    /**
     * Adds a process to the I/O queue, and initiates an I/O operation
     * if the device is free.
     * @param requestingProcess	The process to be added to the I/O queue.
     * @param clock				The time of the request.
     * @return					The event ending the I/O operation, or null
     *							if no operation was initiated.
     */
    public Event addIoRequest(Process requestingProcess, long clock) {
        // Incomplete
        queue.add(requestingProcess);
        Event e = startIoOperation(clock); //event ending I/O maybe type 5?
        return e;
    }

    /**
     * Starts a new I/O operation if the I/O device is free and there are
     * processes waiting to perform I/O.
     * @param clock		The global time.
     * @return			An event describing the end of the I/O operation that was started,
     *					or null	if no operation was initiated.
     */
    public Event startIoOperation(long clock) {
        // Incomplete
        Process ret = queue.pollFirst();
        if( null == ret )return null;
        activeProcess = ret;
        //update statistic?


        return new Event(4, clock); //if i understand correctly
    }

    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        // Complete?
        this.IOtime += timePassed; // if it translates from the cpu like that ?
        this.IOtime /= 2;
    }

    /**
     * Removes the process currently doing I/O from the I/O device.
     * @return	The process that was doing I/O, or null if no process was doing I/O.
     */
    public Process removeActiveProcess() {
        // !Incomplete
        Process temp = getActiveProcess();
        if ( null != temp ) activeProcess = null;
        return temp;
    }

    public Process getActiveProcess() {
        return activeProcess;
    }
}
