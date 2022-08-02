/**
 * This class is the Passenger class and is used to simulate a passenger in the
 * LIRR simulation.
 */
public class Passenger {
    
    private int id;
    private int arrivalTime;
    private boolean isFirstClass;
    /**
     * The Passenger constructor that sets the id, arrivaltime, and whether it
     * is in the first class.
     * @param id
     *      The id of the passenger.
     * @param arrivaltime
     *      The arrivaltime of the passenger.
     * @param isFirstClass
     *      If the passenger is in first class. A boolean value.
     */
    public Passenger(int id, int arrivaltime, boolean isFirstClass) {
        this.id = id;
        this.arrivalTime = arrivaltime;
        this.isFirstClass = isFirstClass;
    }
    /**
     * The getter method for the id of the Passenger object.
     * @return
     *      The id.
     */
    public int getId() {
        return id;
    }
    /**
     * The setter method for the Passenger object.
     * @param id
     *      The given id int to be set to the Passenger object.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * The getter method for the arrivalTime of the Passenger object.
     * @return
     *      The arrivalTime of the Passenger object.
     */
    public int getArrivalTime() {
        return arrivalTime;
    }
    /**
     * The setter method for the arrivalTime of the Passenger object.
     * @param arrivalTime
     *      The arrivalTime of the Passenger object.
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    /**
     * The getter method for isFirstClass.
     * @return
     *      The isFirstClass boolean variable.
     */
    public boolean getIsFirstClass() {
        return isFirstClass;
    }
    /**
     * The setter method for isFirstClass.
     * @param isFirstClass
     *      if the Passenger object is in first class or not. Boolean value.
     */
    public void setIsFirstClass(boolean isFirstClass) {
        this.isFirstClass = isFirstClass;
    }
    /**
     * The toString method for the Passenger object.
     * @return
     *      The string of the Passenger object.
     */
    public String toString() {
        return "P" + id + "@T" + arrivalTime;
    }

}
