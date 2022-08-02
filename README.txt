# LIRR Simulation
This is a simulation built on Java OOP. The program utilizes a linked list that
works as a priority queue to emulate a first and second class system on the train.

## How it Works
The user is asked for the probabilities that first and second class passengers will
arrive at the given stations: Huntington, Hicksville, Syosset, and Mineola.
The user also inputs the first and second class capacities for all the trains as well
as the number of trains. The user is also asked for a last arrival time of the passengers.
The simulation then runs through each minute that passes and shows the stations with
their corresponding passengers(ID & time arrived) in the first and second class queues.

At the end of the simulation, information is given about the average wait time of
the first and second class lines as well as how much of each passenger type was served
on each station. Lastly, the program also says how much of each passenger type
were left without a seat due to train capacity.

### Last Arrival Time
Using an estimate that each train takes 5 minutes to arrive at the next station,
I created a condition for the last arrival time possible for all the trains.
condition: (lastArrivalTime > ((numTrains - 1) * 5) || lastArrivalTime < 0)
This condition is used to keep asking the user for a last arrival time if the
condition is satisfied. This condition is used because without it passengers may