import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MNISTDataBaseFileReader extends InputFileReader {
    // This class is custom made for the MNIST database Dataset
    protected double[] expectedOutputArray;
    protected double[] inputArray;
    protected double label;
    protected BufferedReader singleFileReader;
    protected int outputLayerLength;

    MNISTDataBaseFileReader(String filename,int outputLayerLength) throws FileNotFoundException {
        super(filename);
        this.outputLayerLength= outputLayerLength;
        this.singleFileReader = new BufferedReader(new FileReader(filename));
    }

    protected double getLabel() {
        return label;
    }

    protected void next() {
        double[] array;
        try {
            array = readLineToDoubleArray();
            label = array[0];
            expectedOutputArray = generateExpectedOutputArrayFromLabel();
            inputArray = generateInputFromBigArray(array);

        } catch (IOException ex) {
        }
    }

    protected double[] readLineToDoubleArray() throws IOException {
        String line = singleFileReader.readLine();
        String[] arrayOfStrings = line.split(","); // CSV files are seperate by Commas
        return convertStringArrayToDoubleArray(arrayOfStrings);
    }

    private double[] convertStringArrayToDoubleArray(String[] arrayOfStrings) {
        double[] arrayOfDouble = new double[arrayOfStrings.length];
        for (int i = 0; i < arrayOfStrings.length; i++) {
            arrayOfDouble[i] = Double.parseDouble(arrayOfStrings[i]);
        }
        return arrayOfDouble;
    }

    protected double[] getExpectedOutputArray() {
        return expectedOutputArray;

    }

    protected double[] getInputArray() {
        return inputArray;
    }

    protected double[] generateExpectedOutputArrayFromLabel() {
        double[] expectedOutputArray = new double[outputLayerLength];
        for (int i = 0; i < 10; i++) {
            if (i == label) {
                expectedOutputArray[i] = 1;
            } else {       
                expectedOutputArray[i] = 0;
            }
        }
        // go to next line
        return expectedOutputArray;
    }

    protected double[] generateInputFromBigArray(double[] array) {
        // generates the input array from the total array , that is it excludes the
        // first element of the array
        double[] inputArray = new double[array.length-1];
        for (int i = 0; i < array.length-1; i++) {
            inputArray[i] = array[i + 1] / 256;
        }
        return inputArray;
    }

}
