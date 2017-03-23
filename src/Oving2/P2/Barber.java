package Oving2.P2;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 * One barber instance corresponds to one consumer in
 * the producer/consumer problem.
 */
public class Barber implements Runnable {
    private Boolean stop;

    private CustomerQueue queue;
    private Gui gui;
    private int pos;

    /**
	 * Creates a new barber. Make sure to save these variables in the class.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
    public Barber(CustomerQueue queue, Gui gui, int pos) {
        this.queue = queue;
        this.gui = gui;
        this.pos = pos;
    }

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
        while(!stop){
            Customer newCust = queue.next();
            if(newCust == null){
                continue;
            }

            try {
                System.out.println("Frisør " + pos + " jobber med ny kunde");
                gui.fillBarberChair(pos, newCust);
                Thread.sleep(Globals.barberWork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gui.emptyBarberChair(pos);

            int sleepRand = Constants.MIN_BARBER_SLEEP +
                    (int)(Math.random()*(Constants.MAX_BARBER_SLEEP - Constants.MIN_BARBER_SLEEP + 1));
            try {
                System.out.println("Frisør " + pos + " sover");
                gui.barberIsSleeping(pos);
                Thread.sleep(sleepRand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Frisør " + pos + " våkner");
            gui.barberIsAwake(pos);
        }
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
        stop = false;
        Thread t = new Thread(this);
        t.start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
        stop = true;
	}

	// Add more methods as needed
}

