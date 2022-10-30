import java.io.FileNotFoundException;
import java.io.IOException;

public class MNISTDataBaseFileReader extends CSVFileREader {
//This class is custom made for the MNIST database Dataset

    MNISTDataBaseFileReader(String filename) throws FileNotFoundException {
        super(filename);
        
    }
   public double[] readLineToDoubleArray() throws IOException{
       String line=  singleFileReader.readLine();
       String [] arrayOfStrings = line.split(","); //CSV files are seperate dby Commas
       return convertStringArrayToDoubleArray(arrayOfStrings);
   }
   private double [] convertStringArrayToDoubleArray(String [] arrayOfStrings){
      double[] arrayOfDouble= new double[arrayOfStrings.length];
      for(int i = 0; i < arrayOfStrings.length; i++){
        arrayOfDouble[i] = Double.parseDouble(arrayOfStrings[i]);
      }
      return arrayOfDouble;
   }

    public double[] getExpectedOutputArray() {
        return null;
        
    }
    
    public double[] generateExpectedOutputArrayFromLabel(int label){
        return null;
      
}
   
    
}
