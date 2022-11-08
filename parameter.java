import java.io.FileNotFoundException;

public class parameter {
    //This class is used for setting the parameters of the model. 
    //There is no point in passing its object to the layerManager or Trainer classes so it is static class.
    private static int[] layerArray; //used only by trainer class. Can be made non static and object of this class can be passed if required.
    private static InputFileReader myInputFileReader; //used only by trainer class. Can be made non static and object of this class can be passed if required.
    private static double learningRate; //static because it is used by many classes.
    private static String rectificationFunction; //static because it is used by many classes.
    public static void setRectificationFunction(String rectificationFunction) {
        parameter.rectificationFunction = rectificationFunction;
    }
    public static InputFileReader getMyInputFileReader() {
        return myInputFileReader;
    }
    public static void setMyInputFileReader(String fileName, String type) {
        try {
        if(type=="mnist"){
            parameter.myInputFileReader = new MNISTDataBaseFileReader(fileName);
        }
    } catch (FileNotFoundException ex) {
    }
    }
    public static double rectify(double numToBeRectified) {
        if(rectificationFunction=="relu"){
            return NN.relu(numToBeRectified);
        }

        return NN.sigmoid(numToBeRectified);
    }
    public static void setParameters(){
        layerArray = new int[]{ 784, 16, 16, 10 };
        learningRate = 1;
    }
    public static double getLearningRate() {
        return learningRate;
    }
    public static int[] getLayerArray() {
        return layerArray;
    }
}
