import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;  
import java.io.IOException;  
public abstract class CSVFileREader extends FileReader {
    //Here we are using two seperate scanners one for the expected outputs (labels) and one for the inputs.
    BufferedReader expectedOutputArrayScanner;
    BufferedReader inputArrayScanner;
    // This one is for single input file
    BufferedReader singleFileReader;
    CSVFileREader(String filename) throws FileNotFoundException {
        super(filename);
        this.expectedOutputArrayScanner = new BufferedReader(new FileReader(filename));  
        this.inputArrayScanner = new BufferedReader(new FileReader(filename));
        this.singleFileReader = new BufferedReader(new FileReader(filename));
    }

   
    
}
