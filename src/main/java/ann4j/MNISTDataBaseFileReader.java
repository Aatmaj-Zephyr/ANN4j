package ann4j; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
MNIST database format
File type CSV consisting of the following

- 1 Label (Expected number)
- n pixel weights n must match number of input neurons.

 Some datasets to test the package on (without extending mnist file reader)

1) MNIST Handwritten database
2) MNIST extended chracter database
3) MNIST fashion data set


For other datasets, you will need to extend inputfilereader or MNISTDataBaseFileReader according to the data format

 */


public class MNISTDataBaseFileReader extends InputFileReader {
    // This class is custom made for the MNIST database Dataset
    public ArrayList<Double> expectedOutputArray = new ArrayList<Double>();;
    public ArrayList<Double> inputArray = new ArrayList<Double>();
    public double label;
    public BufferedReader singleFileReader;
    public int outputLayerLength;
    public String fileName;

    // This is the constructor of the class. It is used to initialize the variables.
    MNISTDataBaseFileReader(String filename, int outputLayerLength) throws FileNotFoundException {
        super(filename);
        
        this.outputLayerLength = outputLayerLength;
        this.singleFileReader = new BufferedReader(new FileReader(filename));
    }

    /**
     * This function is responsible for setting the labels of the data. This depends on the data set
     * 
     * @return The label of the data.
     */
    public double getLabel() {
        // this function is responsible for setting the labels of the data. This depends
        // on the data set
        return label;
    }

    /**
     * It reads the next line of the file, converts it into a two double arrays, and then sets the
     * label and expected output array
     */
    public void next() {
        // Reading the next line of the file and converting it into a two double arrays.
        ArrayList<Double> array;
        try {
            array = readLineToDoubleArray();
            label = array.get(0);
            expectedOutputArray = generateExpectedOutputArrayFromLabel();
            inputArray = generateInputFromBigArray(array);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * It reads the next line of the file, splits it into an array of strings, and then converts that
     * array of strings into an array of doubles
     * 
     * @return An ArrayList of Doubles.
     */
    public ArrayList<Double> readLineToDoubleArray() throws IOException {
        // Reading the next line of the file and converting it into a double array.
        String line = singleFileReader.readLine();
        String[] arrayOfStrings = line.split(","); // CSV files are seperate by Commas
        return convertStringArrayToDoubleArray(arrayOfStrings);
    }

   /**
    * Convert a string array to a double array
    * 
    * @param arrayOfStrings The string array that you want to convert to a double array.
    * @return The method is returning an ArrayList of Doubles.
    */
    private ArrayList<Double> convertStringArrayToDoubleArray(String[] arrayOfStrings) {
        // Converting the string array to a double array.
        ArrayList<Double> arrayOfDouble = new ArrayList<Double>();
        for (int i = 0; i < arrayOfStrings.length; i++) {
            arrayOfDouble.add(i,Double.parseDouble(arrayOfStrings[i]));
        }
        return arrayOfDouble;
    }

   /**
    * This function returns the expected output array
    * 
    * @return The expected output array.
    */
    public ArrayList<Double> getExpectedOutputArray() {
        return expectedOutputArray;

    }

    /**
     * This function returns the inputArray
     * 
     * @return The inputArray is being returned.
     */
    public ArrayList<Double> getInputArray() {
        //
        return inputArray;
    }

    /**
     * This function is responsible for generating the output neurons
     * 
     * @return The expected output array.
     */
    public ArrayList<Double> generateExpectedOutputArrayFromLabel() {
        // this needs to be overridden for changing the dataset
        // responsible for generating the output neurons.
        ArrayList<Double> expectedOutputArray = new ArrayList<Double>();
        for (int i = 0; i < parameter.numberOfOutputNeurons; i++) {
            if (i == label) {
                // Works only for 0-9 digits, 0 output neuron corresponds to 0, 1st to 1 etc.
                // But you can change this and jumble the order as required. This may be one of
                // the test cases for making the program free from dependancies.
                // Example for letters, change to 0 for a, 26 for b, etc.
                expectedOutputArray.add(i, 1.0);
            } else {
                expectedOutputArray.add(i, 0.0);
            }
        }
        // go to next line
        return expectedOutputArray;
    }

    /**
     * It takes the array of all the values in the dataset and returns an array of all the values
     * except the first one
     * 
     * @param array The array that contains the data.
     * @return The input array is being returned.
     */
    public ArrayList<Double> generateInputFromBigArray(ArrayList<Double> array) {
        // generates the input array from the total array , that is it excludes the
        // first element of the array

        // needs to be overridden depending on the dataset.
        // This is responsible for setting the input neurons.
        ArrayList<Double> inputArray = new ArrayList<Double>();
        for (int i = 0; i < array.size() - 1; i++) {
            inputArray.add(i,array.get(i + 1) / 256);
        }
        return inputArray;
    }
    @Override
// This is used to convert the neuron number to the corresponding label.
    public double getPredictionFromNeuronNum(int mostSignificantNeuronNumAsPrediction) {
        return mostSignificantNeuronNumAsPrediction;
        //in this case as every input is mapped with same neuron it is the same. But it needs to be overridden for different datasets
        // Example for letters, change to 0 for a, 26 for b, etc.
    }
    @Override 
// This is used to restart the file reader.
    public void restart(){
        try {
            this.singleFileReader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

/**Note

Predicted neuron and prediction are different. 

Predicted neuron is the neuron which is most significant. The prediction is the value corresponding to that neuron.

Example if the neuron 4 is most significant (glows brightest)  and it corresponds to label D then the predicted neuron is 4 and prediction is D.
**/