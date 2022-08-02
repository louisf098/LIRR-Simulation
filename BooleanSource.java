/**
 * This is the BooleanSource class and it is used to check for given
 * probabilities and if (depending on the probability) the event occurs.
 */
public class BooleanSource {
    
    private double probability;
    /**
     * Constructor for the BooleanSource class. Used to create a BooleanSource
     * with the given probability value. 
     * @param p
     *      The given probability.
     * @throws IllegalArgumentException
     *      If the given probability value is less than 0.0 or greater than 1.0
     */
    public BooleanSource(double p) throws IllegalArgumentException {
        if (p < 0.0 || p > 1.0) 
            throw new IllegalArgumentException("Illegal probability");
        probability = p;
    }
    /**
     * This method returns a boolean value depending on if the object's
     * probability is greater than a random double value from 0 to 1.
     * @return
     *      True if the probability is higher, false otherwise.
     */
    public boolean occurs() {
        return (Math.random() < probability);
    }

}
