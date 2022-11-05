import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MNISTDataBaseFileReader extends InputFileReader {
    // This class is custom made for the MNIST database Dataset
    public double[] expectedOutputArray;
    public double[] inputArray;
    public double label;
    public BufferedReader singleFileReader;

    MNISTDataBaseFileReader(String filename) throws FileNotFoundException {
        super(filename);
        this.singleFileReader = new BufferedReader(new FileReader(filename));
    }

    public double getLabel() {
        return label;
    }

    public void next() {
        double[] array;
        try {
            array = readLineToDoubleArray();
            label = array[0];
            expectedOutputArray = generateExpectedOutputArrayFromLabel();
            inputArray = generateInputFromBigArray(array);

        } catch (IOException ex) {
        }
    }

    public double[] readLineToDoubleArray() throws IOException {
        String line = singleFileReader.readLine();
        String[] arrayOfStrings = line.split(","); // CSV files are seperate dby Commas
        return convertStringArrayToDoubleArray(arrayOfStrings);
    }

    private double[] convertStringArrayToDoubleArray(String[] arrayOfStrings) {
        double[] arrayOfDouble = new double[arrayOfStrings.length];
        for (int i = 0; i < arrayOfStrings.length; i++) {
            arrayOfDouble[i] = Double.parseDouble(arrayOfStrings[i]);
        }
        return arrayOfDouble;
    }

    public double[] getExpectedOutputArray() {
        return expectedOutputArray;

    }

    public double[] getInputArray() {
        return inputArray;
    }

    public double[] generateExpectedOutputArrayFromLabel() {
        double[] expectedOutputArray = new double[10];
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

    public double[] generateInputFromBigArray(double[] array) {
        // generates the input array from the total array , that is it excludes the
        // first element of the array
        double[] inputArray = new double[784];
        for (int i = 0; i <= 783; i++) {
            inputArray[i] = array[i + 1] / 256;
        }
        return inputArray;
    }

}
