/**
 * This class contains the main method and is used to simulate the entire
 * LIRR simulation.
 */
public class LIRRSimulator {
    public static int passengerNum = 0;
    public static int numTrains = 0;
    public static int time = 0;
    public static int lastArrivalTime = 0;
    public static boolean running = true;
    /**
     * The main method.
     * @param args
     *      The default args.
     */
    public static void main(String[] args) {

        Station Mineola = new Station("Mineola");
        Station Hicksville = new Station("Hicksville");
        Station Syosset = new Station("Syosset");
        Station Huntington = new Station("Huntington");

        System.out.println("Welcome to the LIRR Simulator, Leaving Irate Riders"
        + " Reguarly");
        Mineola.askForProbabilities();
        Hicksville.askForProbabilities();
        Syosset.askForProbabilities();
        Huntington.askForProbabilities();
        //first train to ask for UI
        Train train1 = new Train();
        train1.trainAsk();
        Train[] trains = new Train[numTrains];
        trains[0] = train1;
        trains[0].getStations().add(Huntington);
        trains[0].getStations().add(Syosset);  
        trains[0].getStations().add(Hicksville);
        trains[0].getStations().add(Mineola);
        trains[0].setTrainId(1);

        int timeUntilArrivalSetter = 0;
        trains[0].setTimeUntilNextArrival(timeUntilArrivalSetter);
        timeUntilArrivalSetter += 5;

        //creates array of num of trains
        for (int i = 1; i < trains.length; i++) {
            Train temp = new Train();
            temp.setTrainId(i + 1);
            trains[i] = temp;
            trains[i].getStations().add(Huntington);
            trains[i].getStations().add(Syosset);  
            trains[i].getStations().add(Hicksville);
            trains[i].getStations().add(Mineola);
            trains[i].setTimeUntilNextArrival(timeUntilArrivalSetter);
            trains[i].setFirstCapcity(train1.getFirstCapacity());
            trains[i].setSecondCapacity(train1.getSecondCapacity());
            timeUntilArrivalSetter += 5;
        }

        System.out.println("Begin Simulation:\n------------------------------");
        //for (int i = 0; i < LIRRSimulator.lastArrivalTime; i++) { //goes up to last time to add passengers
        while (running) {
            if (time == ((numTrains - 1) * 5) + 15) {
                running = false;
            }

            System.out.println("Time " + time + ":\n\nStation Overview:\n");
            if (time <= lastArrivalTime) {
                Mineola.simulateTimeStep();
                System.out.println(Mineola + "\n");
                Hicksville.simulateTimeStep();
                System.out.println(Hicksville + "\n");
                Syosset.simulateTimeStep();
                System.out.println(Syosset + "\n");
                Huntington.simulateTimeStep();
                System.out.println(Huntington + "\n");
            }
            else if (time > lastArrivalTime) {
                Mineola.simulationAfterLastArrival();
                System.out.println(Mineola + "\n");
                Hicksville.simulationAfterLastArrival();
                System.out.println(Hicksville + "\n");
                Syosset.simulationAfterLastArrival();
                System.out.println(Syosset + "\n");
                Huntington.simulationAfterLastArrival();
                System.out.println(Huntington + "\n");
            }

            System.out.println("Trains:\n");
            for (Train t : trains) {
                t.simulateTimeStep();
                System.out.println();
            }

            System.out.println("---------------");
            time++;
        }
        System.out.println("\nAt the end of the simulation:\n");
        int total = 0;
        for (int i = 0; i < trains.length; i++) {
            total += trains[i].getNumOfFirstClass() + trains[i].getNumOfSecondClass();
        }
        System.out.println("A total of " + total + " passengers were served");
        trains[trains.length - 1].endOfSimulation();
        
        Mineola.endOfSimulation();
        Hicksville.endOfSimulation();
        Syosset.endOfSimulation();
        Huntington.endOfSimulation();


    }

}
