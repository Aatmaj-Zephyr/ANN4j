package ann; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MNISTDataBaseFileReader extends InputFileReader {
    // This class is custom made for the MNIST database Dataset
    protected ArrayList<Double> expectedOutputArray = new ArrayList<Double>();;
    protected ArrayList<Double> inputArray = new ArrayList<Double>();
    protected double label;
    protected BufferedReader singleFileReader;
    protected int outputLayerLength;

    MNISTDataBaseFileReader(String filename, int outputLayerLength) throws FileNotFoundException {
        super(filename);
        this.outputLayerLength = outputLayerLength;
        this.singleFileReader = new BufferedReader(new FileReader(filename));
    }

    protected double getLabel() {
        // this function is responsible for setting the labels of the data. This depends
        // on the data set
        return label;
    }

    protected void next() {
        // Reading the next line of the file and converting it into a two double arrays.
        ArrayList<Double> array;
        try {
            array = readLineToDoubleArray();
            label = array.get(0);
            expectedOutputArray = generateExpectedOutputArrayFromLabel();
            inputArray = generateInputFromBigArray(array);

        } catch (IOException ex) {
        }
    }

    protected ArrayList<Double> readLineToDoubleArray() throws IOException {
        // Reading the next line of the file and converting it into a double array.
        String line = singleFileReader.readLine();
        String[] arrayOfStrings = line.split(","); // CSV files are seperate by Commas
        return convertStringArrayToDoubleArray(arrayOfStrings);
    }

    private ArrayList<Double> convertStringArrayToDoubleArray(String[] arrayOfStrings) {
        // Converting the string array to a double array.
        ArrayList<Double> arrayOfDouble = new ArrayList<Double>();
        for (int i = 0; i < arrayOfStrings.length; i++) {
            arrayOfDouble.add(i,Double.parseDouble(arrayOfStrings[i]));
        }
        return arrayOfDouble;
    }

    protected ArrayList<Double> getExpectedOutputArray() {
        return expectedOutputArray;

    }

    protected ArrayList<Double> getInputArray() {
        //
        return inputArray;
    }

    protected ArrayList<Double> generateExpectedOutputArrayFromLabel() {
        // this needs to be overridden for changing the dataset
        // responsible for generating the output neurons.
        ArrayList<Double> expectedOutputArray = new ArrayList<Double>();
        for (int i = 0; i < 10; i++) {
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

    protected ArrayList<Double> generateInputFromBigArray(ArrayList<Double> array) {
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
    public double getPredictionFromNeuronNum(int mostSignificantNeuronNumAsPrediction) {
        return mostSignificantNeuronNumAsPrediction;
        //in this case as every input is mapped with same neuron it is the same. But it needs to be overridden for different datasets
        // Example for letters, change to 0 for a, 26 for b, etc.
    }

}

/**Note

Predicted neuron and prediction are different. 

Predicted neuron is the neuron which is most significant. The prediction is the value corresponding to that neuron.

Example if the neuron 4 is most significant (glows brightest)  and it corresponds to label D then the predicted neuron is 4 and prediction is D.
**/