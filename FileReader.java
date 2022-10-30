import java.io.File;
import java.io.FileNotFoundException;

public abstract class FileReader {
    String filename;
    FileReader(String filename) throws FileNotFoundException{
        this.filename = filename;
    }
    public abstract double [] getExpectedOutputArray();
    public abstract double [] getInputArray();
}
