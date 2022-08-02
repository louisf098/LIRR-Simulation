import java.util.LinkedList;
/**
 * The PassengerQueue class that extends a LinkedList of Passenger. Used to
 * simulate a queue of passengers.
 */
public class PassengerQueue extends LinkedList<Passenger>{
    /**
     * This method adds a Passenger object to the end of the queue.
     * @param p
     *      The Passenger object.
     */
    public void enqueue(Passenger p) {
        try {
            add(p);
        } catch (Exception e) {
            System.out.println("Can not be added");
        }
    }
    /**
     * This method removes the head of the queue and returns it.
     * @return
     *      The removed Passenger object at the head of the queue.
     */
    public Passenger dequeue() {
        Passenger removed = get(0);
        remove(0);
        return removed;
    }
    /**
     * The toString method for the PassengerQueue class. Returns the passenger
     * queue as a string.
     * @return
     *      The strings of the Passengers in the queue.
     */
    public String toString() {
        String output = "";
        for (Passenger p : this) {
            output += p.toString();
            if (LIRRSimulator.time != 0) {
                output += ", ";
            }
        }
        return output;
    }
    /**
     * This method returns the Passenger object at the head of the queue.
     * @return
     *      The Passenger object at the head of the queue.
     */
    public Passenger peek() {
        if (isEmpty()) return null;
        else {
            return get(0);
        }
    }
    /**
     * This method returns a boolean value of whether the queue is empty or not.
     * @return
     *      Boolean value depending on if the queue is empty.
     */
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
}
