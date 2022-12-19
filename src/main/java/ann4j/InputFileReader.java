package ann4j;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class InputFileReader {
    public String filename;

    // A constructor.
    InputFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
    }
    // must be overriden by the base methods

   /**
    * This function returns an ArrayList of Doubles that represents the expected output of the neural
    * network
    * 
    * @return Nothing is being returned.
    */
    public ArrayList<Double> getExpectedOutputArray() {
        return null;
    }

   /**
    * This function returns an ArrayList of Doubles that represents the input layer of the neural
    * network
    * 
    * @return An ArrayList of Doubles
    */
    public ArrayList<Double> getInputArray() {
        return null;
    }

   /**
    * This function goes to next line of the inputfile and is responsible for setting all input parameters
    */
    public void next() {
    }

    /**
     * This function returns the label. One of the most important method.
     * 
     * @return The label of the instance.
     */
    public double getLabel() {
        return 0;
    }

    /**
     * > This function takes in the most significant neuron number and returns the prediction
     * 
     * @param mostSignificantNeuronNumAsPrediction The neuron number that has the highest activation
     * value.
     * @return The prediction from the most significant neuron number.
     */
    public double getPredictionFromNeuronNum(int mostSignificantNeuronNumAsPrediction) {
        return 0;
    }

   /**
    * This function restarts the file reader.
    */
    public void restart(){
        
    }
}
