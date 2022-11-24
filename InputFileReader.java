import java.io.FileNotFoundException;

public class InputFileReader {
    protected String filename;

    InputFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
    }
    // must be overriden by the base methods

    protected double[] getExpectedOutputArray() {
        return null;
    }

    protected double[] getInputArray() {
        return null;
    }

    protected void next() {
    }

    protected double getLabel() {
        return 0;
    }
}
