package Oving2.P2;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
    private int maxLength;
    private Gui gui;

    private Customer[] queue;
    private int curLength;
    private int startPos;
    private int endPos;

	/**
	 * Creates a new customer queue. Make sure to save these variables in the class.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
        this.maxLength = queueLength;
        this.gui = gui;

        this.queue = new Customer[maxLength];
        this.curLength = 0;
        this.startPos = 0;
        this.endPos = 0;
	}

	public synchronized void add(Customer customer) {
        /**
         * Using the current length of the queue simplifies matters
         * compared to checking edgecases with the position variables
         */
        if(curLength < maxLength - 1){
            queue[endPos] = customer;
            gui.fillLoungeChair(endPos, customer);

            endPos++;
            if(endPos == maxLength) endPos = 0;
            curLength++;
        }
	}

	public synchronized Customer next() {
		if(curLength == 0) return null;

        int oldPos = startPos;
        Customer cust = queue[oldPos];
        queue[oldPos] = null;
        startPos++;

        if(startPos == maxLength) startPos = 0;
        curLength--;

        return cust;
	}

	// Add more methods as needed

    // It's either this, or having a bunch of function calls that do nothing
    public Boolean isFull(){
        return (curLength >= (maxLength - 1));
    }
}
