import java.io.FileNotFoundException;

public class InputFileReader {
    public String filename;

    InputFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
    }
    // must be overriden by the base methods

    public double[] getExpectedOutputArray() {
        return null;
    }

    public double[] getInputArray() {
        return null;
    }

    public void next() {
    }

    public double getLabel() {
        return 0;
    }
}
