import java.util.Scanner;
/**
 * This is the Station class and it is used to simulate a train station that
 * contains a queue of first and second class passengers.
 */
public class Station {
    private String name;
    
    private PassengerQueue firstClass;
    private int firstServed = 0;
    private int firstAvgWaitTime = 0; //before dividing by firstServed
    private int firstLeftWithout = 0;

    private PassengerQueue secondClass;
    private int secondServed = 0;
    private int secondAvgWaitTime = 0;
    private int secondLeftWithout = 0;

    private BooleanSource firstArrival;
    private boolean firstArrives = false;
    private BooleanSource secondArrival;
    private boolean secondArrives = false;

    Scanner sc = new Scanner(System.in);
    /**
     * The Station class constructor that takes a String "name" and creates a 
     * Station with the passed string.
     * @param name
     *      The name of the Station object.
     */
    public Station(String name) {
        this.name = name;
        firstClass = new PassengerQueue();
        secondClass = new PassengerQueue();
    }
    /**
     * The getter method for the PassengerQueue of the first class.
     * @return
     *      The PassengerQueue of the first class.
     */
    public PassengerQueue getFirstClass() {
        return firstClass;
    }
    /**
     * The getter method for the PassengerQueue of the second class.
     * @return
     *      The PassengerQueue of the first class.
     */
    public PassengerQueue getSecondClass() {
        return secondClass;
    }
    /**
     * The getter method for the name of the station.
     * @return
     *      The name as a string of the station.
     */
    public String getName() {
        return name;
    }
    /**
     * This method increments the firstServed variable by 1.
     */
    public void incrementFirstServed() {
        firstServed++;
    }
    /**
     * This method increments the secondServed variable by 1.
     */
    public void incrementSecondServed() {
        secondServed++;
    }
    /**
     * This method adds the given int, time, to firstAvgWaitTime.
     * @param time
     *      The time of how long the Passenger had to wait to be picked up.
     */
    public void addFirstAvgWaitTime(int time) {
        firstAvgWaitTime += time;
    }
    /**
     * This method adds the given int, time, to secondAvgWaitTime.
     * @param time
     *      The time of how long the Passenger had to wait to be picked up.
     */
    public void addSecondAvgWaitTime(int time) {
        secondAvgWaitTime += time;
    }
    /**
     * This method adds the given int, amount, to firstLeftWithout.
     * @param amount
     *      The int amount to be added.
     */
    public void firstLeftWithoutAdd(int amount) {
        firstLeftWithout += amount;
    }
    /**
     * This method adds the given int, amount, to secondLeftWithout.
     * @param amount
     *      The int amount to be added.
     */
    public void secondLeftWithoutAdd(int amount) {
        secondLeftWithout += amount;
    }
    /**
     * This is the toString method that returns the string of the Station
     * object. Contains the string of the queue of the station and if passengers
     * arrive at the station.
     * @return 
     *      The string of the Station object.
     */
    public String toString() {
        String output = name + ":\n"; 
        if (LIRRSimulator.time <= LIRRSimulator.lastArrivalTime) {
            if (firstArrives) {
                output += "First class pasesenger ID " 
                + firstClass.getLast().getId();
                output += " arrives\n";
            }
            else if (firstArrives == false) {
                output += "No first class passenger arrives\n";
            }
            if (secondArrives) {
                output += "Second class pasesenger ID " 
                + secondClass.getLast().getId();
                output += " arrives\n";
            }
            else if (secondArrives == false) {
                output += "No second class passenger arrives\n";
            }
            output += "Queues:\n";
            if (!firstClass.isEmpty()) {
                output += "First [" + firstClass.toString();
            }
            else if (firstClass.isEmpty()) {
                output += "First [empty]";
            }
            output += "\n";
            if (!secondClass.isEmpty()) {
                output += "Second [" + secondClass.toString();
            }
            else if (secondClass.isEmpty()) {
                output += "Second [empty]";
            }
        }
        else if (LIRRSimulator.time > LIRRSimulator.lastArrivalTime) {
            output += "No first class passenger arrives\n"
            + "No second class passenger arrives\n";
            output += "Queues:\n";
            if (!firstClass.isEmpty()) {
                output += "First [" + firstClass.toString();
            }
            else if (firstClass.isEmpty()) {
                output += "First [empty]";
            }
            output += "\n";
            if (!secondClass.isEmpty()) {
                output += "Second [" + secondClass.toString();
            }
            else if (secondClass.isEmpty()) {
                output += "Second [empty]";
            }
        }
        return output;
    }
    /**
     * This method is used to set the probabilities of arriving passengers for
     * the station.
     */
    public void askForProbabilities() {
        System.out.println(name + ":");
        System.out.println("Please enter first class arrival probability: ");
        try {
            firstArrival = new BooleanSource(sc.nextDouble());
            if (firstArrival.occurs()) {
                Passenger passenger = 
                new Passenger(LIRRSimulator.passengerNum++ + 1, LIRRSimulator.time, true);
                firstClass.enqueue(passenger);
                firstArrives = true;
            }
        } catch (IllegalArgumentException e) {
            firstArrival = new BooleanSource(0.0); //if user enters erraneous
            System.out.println(e);
        }
        System.out.println("Please enter second class arrival probability: ");
        try {
            secondArrival = new BooleanSource(sc.nextDouble());
            if (secondArrival.occurs()) {
                Passenger passenger = 
                new Passenger(LIRRSimulator.passengerNum++ + 1, LIRRSimulator.time, false);
                secondClass.enqueue(passenger);
                secondArrives = true;
            }
        } catch (IllegalArgumentException e) {
            secondArrival = new BooleanSource(0.0); //if user enters erraneous
            System.out.println(e);
        }
    }
    /**
     * This method is used to simulate the station as the station is 
     * incremented for each time that passes.
     */
    public void simulateTimeStep() {
        if (LIRRSimulator.time != 0) {
            firstArrives = false; //for printing
            secondArrives = false;
            try {
                if (firstArrival.occurs()) {
                    Passenger passenger = 
                    new Passenger(LIRRSimulator.passengerNum++ + 1, LIRRSimulator.time, true);
                    firstClass.enqueue(passenger);
                    firstArrives = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
            try {
                if (secondArrival.occurs()) {
                    Passenger passenger = 
                    new Passenger(LIRRSimulator.passengerNum++ + 1, LIRRSimulator.time, true);
                    secondClass.enqueue(passenger);
                    secondArrives = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }        
        }
    }
    /**
     * This method is used for the simulation of the station after the last
     * arrival of passengers occur. No more passengers is added to the station
     * PassengerQueue.
     */
    public void simulationAfterLastArrival() {
        firstArrives = false; //for printing
        secondArrives = false;
        return; //no longer adds passengers to queue
    }
    /**
     * This method is for displaying all the statistic at the end of the
     * simulation.
     */
    public void endOfSimulation() {
        if (firstServed > 0 && secondServed > 0) {
            System.out.println("\nAt " + name + " " + firstServed + " first class"
            + " passengers were served with an average wait time of "
            + (firstAvgWaitTime / firstServed) + " min.");
            System.out.println("" + secondServed + " second class passengers were served"
            + " with an average wait time of "
            + (secondAvgWaitTime / secondServed) + " min.");
        }
        else if (firstServed > 0 && secondServed <= 0) {
            System.out.println("\nAt " + name + " " + firstServed + " first class"
            + " passengers were served with an average wait time of "
            + (firstAvgWaitTime / firstServed) + " min.");
            System.out.println("" + secondServed + " second class passengers were served"
            + " with an average wait time of 0 min.");
        }
        else if (firstServed <= 0 && secondServed > 0) {
            System.out.println("\nAt " + name + " " + firstServed + " first class"
            + " passengers were served with an average wait time of 0 min.");
            System.out.println("" + secondServed + " second class passengers were served"
            + " with an average wait time of "
            + (secondAvgWaitTime / secondServed) + " min.");
        } 
        else {
            System.out.println("\nAt " + name + " " + firstServed + " first class"
            + " passengers were served with an average wait time of 0 min.");
            System.out.println("" + secondServed + " second class passengers were served"
            + " with an average wait time of 0 min.");
        }

        System.out.println(firstLeftWithout + " first class passengers and "
        + secondLeftWithout + " were left without a seat.");
    }
    
}
