package Oving2.P2;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 * One doorman instance corresponds to one producer in
 * the producer/consumer problem.
 */
public class Doorman implements Runnable {
    private Boolean stop;

    private CustomerQueue queue;
    private Gui gui;

    /**
	 * Creates a new doorman. Make sure to save these variables in the class.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
        this.queue = queue;
        this.gui = gui;
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
        // Incomplete?
        while(!stop){
            // We don't want people loitering around, now do we?
            while(queue.isFull()){
                try {
                    // Wait a little while before checking again
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Magically find a new customer, and tell them to wait for the barber
            System.out.println("Dørvakt slipper inn kunde");
            Customer newCustomer = new Customer();
            queue.add(newCustomer);

            // Sleep for semi-random amount of time
            int sleepRand = Constants.MIN_DOORMAN_SLEEP +
                    (int)(Math.random()*(Constants.MAX_DOORMAN_SLEEP - Constants.MIN_DOORMAN_SLEEP + 1));
            try {
                System.out.println("Dørvakt tar pause");
                Thread.sleep(sleepRand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}


	/**
	 * Starts the doorman running as a separate thread. Make
	 * sure to create the thread and start it.
	 */
	public void startThread() {
        stop = false;
        Thread t = new Thread(this);
        t.start();
	}

	/**
	 * Stops the doorman thread. Use Thread.join() for stopping
	 * a thread.
	 */
	public void stopThread() {
		stop = true;
	}

	// Add more methods as needed
}
