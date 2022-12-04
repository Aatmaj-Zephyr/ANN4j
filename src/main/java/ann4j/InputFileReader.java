package ann4j;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class InputFileReader {
    public String filename;

    InputFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
    }
    // must be overriden by the base methods

    public ArrayList<Double> getExpectedOutputArray() {
        return null;
    }

    public ArrayList<Double> getInputArray() {
        return null;
    }

    public void next() {
    }

    public double getLabel() {
        return 0;
    }

    public double getPredictionFromNeuronNum(int mostSignificantNeuronNumAsPrediction) {
        return 0;
    }

    public void restart(){
        
    }
}
