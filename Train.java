import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * This Train class is used to simulate a Train that passes through the each
 * of the stations.
 */
public class Train {
    private int trainId;
    private int firstCapacity;
    private int secondCapacity;
    private int timeUntilNextArrival;

    private int numOfFirstClass = 0;
    private int numOfSecondClass = 0;
    private boolean firstFull = false;
    private boolean secondFull = false;

    private int totalFirstPassLeft = 0;
    private int totalSecondPassLeft = 0;

    Scanner sc = new Scanner(System.in);

    private Queue<Station> stations = new LinkedList<Station>(); //implement more next step
    private PassengerQueue firstClassPass = new PassengerQueue();
    private PassengerQueue secondClassPass = new PassengerQueue();
    /**
     * The getter method for the numOfFirstClass.
     * @return
     *      The numOfFirstClass.
     */
    public int getNumOfFirstClass() {
        return numOfFirstClass;
    }
    /**
     * The getter method for the numOfSecondClass.
     * @return
     *      The numOfSecondClass.
     */
    public int getNumOfSecondClass() {
        return numOfSecondClass;
    }
    /**
     * The setter method for the trainId.
     * @param id
     *      The given id int.
     */
    public void setTrainId(int id) {
        trainId = id;
    }
    /**
     * The getter method for the firstCapacity.
     * @return
     *      The firstCapacity.
     */
    public int getFirstCapacity() {
        return firstCapacity;
    }
    /**
     * The setter method for the firstCapacity.
     * @param firstCapacity
     *      The given firstCapacity int.
     */
    public void setFirstCapcity(int firstCapacity) {
        this.firstCapacity = firstCapacity;
    }
    /**
     * The getter method for the secondCapacity.
     * @return
     *      The secondCapacity.
     */
    public int getSecondCapacity() {
        return secondCapacity;
    }
    /**
     * The setter method for the secondCapacity.
     * @param secondCapcity
     *      The given secondCapacity int.
     */
    public void setSecondCapacity(int secondCapcity) {
        this.secondCapacity = secondCapcity;
    }
    /**
     * The getter method for the Queue of Station.
     * @return
     *      The stations queue.
     */
    public Queue<Station> getStations() {
        return stations;
    }
    /**
     * The setter method for the timeUntilNextArrival.
     * @param time
     *      The given int time.
     */
    public void setTimeUntilNextArrival(int time) {
        timeUntilNextArrival = time;
    }
    /**
     * This method is used to ask and set the default capacities and other
     * variables for the Train class.
     */
    public void trainAsk() {

        do {
            System.out.println("Please enter first class capacity: ");
            firstCapacity = sc.nextInt();
        } while (firstCapacity < 0);

        do {
            System.out.println("Please enter second class capacity: ");
            secondCapacity = sc.nextInt();
        } while (secondCapacity < 0);

        do {
            System.out.println("Please enter number of trains: ");
            LIRRSimulator.numTrains = sc.nextInt();
        } while (LIRRSimulator.numTrains < 0);

        do {
            System.out.println("Please enter last arrtival time of passengers: ");
            LIRRSimulator.lastArrivalTime = sc.nextInt();
        } while (LIRRSimulator.lastArrivalTime > ((LIRRSimulator.numTrains - 1) * 5)
        || LIRRSimulator.lastArrivalTime < 0);

    }
    /**
     * This method is used to simulate the train for each time step.
     */
    public void simulateTimeStep() {
        int prevFirstLeft = 0;
        int prevSecondLeft = 0;
        
        if (LIRRSimulator.time > (LIRRSimulator.numTrains - 1) * 5 + 15) {
            return;
        }
        if (stations.peek() == null) {
            return;
        }

        if (timeUntilNextArrival == 0 && stations.peek() != null
        && LIRRSimulator.time <= (LIRRSimulator.numTrains - 1) * 5 + 15
        && LIRRSimulator.time <= LIRRSimulator.lastArrivalTime) {
            //simulated so that all passengers are planning on going to Mineola
            if (stations.peek().getName().equals("Mineola")) {
                firstClassPass = new PassengerQueue();
                secondClassPass = new PassengerQueue();
                System.out.println("Train " + trainId + " has stopped picking up passengers.");
                try {
                    stations.remove();
                } catch (Exception e) {
                    System.out.println(e);
                }
                return;
            }
            else {
                System.out.println("Train " + trainId
                + " arrives at " + stations.peek().getName() + ", There are "
                + numOfFirstClass + " passengers in first class and "
                + numOfSecondClass + " in second class.");
            }

            //Passengers entering the train and leaving from the station queue
            if (!stations.peek().getFirstClass().isEmpty()) {
                System.out.println("Passengers embarking in first class: ");
                if (firstFull) { //if train is full
                    System.out.println("none");
                }
                while (numOfFirstClass < firstCapacity
                && !stations.peek().getFirstClass().isEmpty()) {
                    System.out.print("P" + stations.peek().getFirstClass().peek().getId() + ",");
                    Passenger passenger = stations.peek().getFirstClass().dequeue();
                    firstClassPass.enqueue(passenger);
                    stations.peek().addFirstAvgWaitTime(LIRRSimulator.time);
                    stations.peek().incrementFirstServed();
                    numOfFirstClass++;
                    if (numOfFirstClass == firstCapacity) firstFull = true;
                }
                
            }
            if (!stations.peek().getSecondClass().isEmpty()) {
                System.out.println("\nPassengers embarking in second class: ");
                if (secondFull) { //if train is full
                    System.out.println("none");
                }
                if (numOfSecondClass < secondCapacity
                && !stations.peek().getFirstClass().isEmpty()) {
                    while (numOfSecondClass < secondCapacity
                    && !stations.peek().getFirstClass().isEmpty()) { //first class entering second class seat
                        System.out.print("P" + stations.peek().getFirstClass().peek().getId() + ",");
                        Passenger passenger = stations.peek().getFirstClass().dequeue();
                        secondClassPass.enqueue(passenger);
                        numOfSecondClass++;
                        stations.peek().addFirstAvgWaitTime(LIRRSimulator.time);
                        stations.peek().incrementFirstServed();
                        if (numOfSecondClass == secondCapacity) secondFull = true;
                    }
                }
                while (numOfSecondClass < secondCapacity
                && !stations.peek().getSecondClass().isEmpty()) {
                    System.out.print("P" + stations.peek().getSecondClass().peek().getId() + ",");
                    Passenger passenger = stations.peek().getSecondClass().dequeue();
                    secondClassPass.enqueue(passenger);  
                    numOfSecondClass++;
                    stations.peek().addSecondAvgWaitTime(LIRRSimulator.time);
                    stations.peek().incrementSecondServed();
                    if (numOfSecondClass == secondCapacity) secondFull = true;
                }


            }
            System.out.println();

            //dequeue station upon arrival at station
            try {
                if (trainId == LIRRSimulator.numTrains) {
                    
                    for (Passenger p : stations.peek().getFirstClass()) {
                        totalFirstPassLeft++;
                        prevFirstLeft++;
                    }
                    stations.peek().firstLeftWithoutAdd(prevFirstLeft);
                    for (Passenger p : stations.peek().getSecondClass()) {
                        totalSecondPassLeft++;
                        prevSecondLeft++;
                    }
                    stations.peek().secondLeftWithoutAdd(prevSecondLeft);
                }
                stations.remove();
                timeUntilNextArrival = 5;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (LIRRSimulator.time < (LIRRSimulator.numTrains - 1) * 5 + 15 //second part
        && LIRRSimulator.time > LIRRSimulator.lastArrivalTime
        && timeUntilNextArrival == 0) {
            System.out.println("Train " + trainId
            + " arrives at " + stations.peek().getName() + ", There are "
            + numOfFirstClass + " passengers in first class and "
            + numOfSecondClass + " in second class.");
            if (!stations.peek().getFirstClass().isEmpty()) {
                System.out.println("Passengers embarking in first class: ");
                if (firstFull) { //if train is full
                    System.out.println("none");
                }
                while (numOfFirstClass < firstCapacity
                && !stations.peek().getFirstClass().isEmpty()) {
                    System.out.print("P" + stations.peek().getFirstClass().peek().getId() + ",");
                    Passenger passenger = stations.peek().getFirstClass().dequeue();
                    firstClassPass.enqueue(passenger);  
                    numOfFirstClass++;
                    stations.peek().addFirstAvgWaitTime(LIRRSimulator.time);
                    stations.peek().incrementFirstServed();
                    if (numOfFirstClass == firstCapacity) firstFull = true;
                }
            }
            if (!stations.peek().getSecondClass().isEmpty()) {
                System.out.println("\nPassengers embarking in second class: ");
                if (secondFull) { //if train is full
                    System.out.println("none");
                }
                if (numOfSecondClass < secondCapacity
                && !stations.peek().getFirstClass().isEmpty()) {
                    while (numOfSecondClass < secondCapacity
                    && !stations.peek().getFirstClass().isEmpty()) { //first class entering second class seat
                        System.out.print("P" + stations.peek().getFirstClass().peek().getId() + ",");
                        Passenger passenger = stations.peek().getFirstClass().dequeue();
                        secondClassPass.enqueue(passenger);
                        numOfSecondClass++;
                        stations.peek().addFirstAvgWaitTime(LIRRSimulator.time);
                        stations.peek().incrementFirstServed();
                        if (numOfSecondClass == secondCapacity) secondFull = true;
                    }
                }
                while (numOfSecondClass < secondCapacity
                && !stations.peek().getSecondClass().isEmpty()) {
                    System.out.print("P" + stations.peek().getSecondClass().peek().getId() + ",");
                    Passenger passenger = stations.peek().getSecondClass().dequeue();
                    secondClassPass.enqueue(passenger);  
                    numOfSecondClass++;
                    stations.peek().addSecondAvgWaitTime(LIRRSimulator.time);
                    stations.peek().incrementSecondServed();
                    if (numOfSecondClass == secondCapacity) secondFull = true;
                }

            }
            System.out.println();
            //dequeue station upon arrival at station
            try {
                if (trainId == LIRRSimulator.numTrains) {
                    for (Passenger p : stations.peek().getFirstClass()) {
                        totalFirstPassLeft++;
                        prevFirstLeft++;
                    }
                    stations.peek().firstLeftWithoutAdd(prevFirstLeft);
                    for (Passenger p : stations.peek().getSecondClass()) {
                        totalSecondPassLeft++;
                        prevSecondLeft++;
                    }
                    stations.peek().secondLeftWithoutAdd(prevSecondLeft);
                }
                stations.remove();
                timeUntilNextArrival = 5;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            if (stations.peek() != null) {
                if (stations.peek().getName().equals("Mineola")
                && LIRRSimulator.time == 30) {
                    if (trainId == LIRRSimulator.numTrains) {
                        for (Passenger p : stations.peek().getFirstClass()) {
                            totalFirstPassLeft++;
                            prevFirstLeft++;
                        }
                        stations.peek().firstLeftWithoutAdd(prevFirstLeft);
                        for (Passenger p : stations.peek().getSecondClass()) {
                            totalSecondPassLeft++;
                            prevSecondLeft++;
                        }
                        stations.peek().secondLeftWithoutAdd(prevSecondLeft);
                    }
                    System.out.println("All trains have finished their schedule");
                }
                else {
                    System.out.println("Train " + trainId + " will arrive at "
                    + stations.peek().getName() + " in " + timeUntilNextArrival
                    + " minutes.");
                }
            }   
        }
        timeUntilNextArrival -= 1;
    }
    /**
     * This method is used to display the amount of first and second class
     * passengers that are left without seats at the end of simulation.
     */
    public void endOfSimulation() {
        System.out.println("" + totalFirstPassLeft + " first class passengers "
        + "were left without a seat, " + totalSecondPassLeft
        + " second class passengers were left without a seat.");
    }

}
