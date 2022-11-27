import java.io.FileNotFoundException;
import java.util.ArrayList;

public class InputFileReader {
    protected String filename;

    InputFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
    }
    // must be overriden by the base methods

    protected ArrayList<Double> getExpectedOutputArray() {
        return null;
    }

    protected ArrayList<Double> getInputArray() {
        return null;
    }

    protected void next() {
    }

    protected double getLabel() {
        return 0;
    }
}
