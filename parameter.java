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
    // This is the rectification function. It is used to rectify the output of the neuron.
    //The rectification function will rectify according to the string provided. The functions fromn NN module will be utilized for this.
        if(rectificationFunction=="relu"){
            return NN.relu(numToBeRectified);
        }
        if(rectificationFunction=="tanh"){
            return NN.tanh(numToBeRectified);
        }
        if(rectificationFunction=="softplus"){
            return NN.softplus(numToBeRectified);
        }
        if(rectificationFunction=="leakyrelu"){
            return NN.leakyrelu(numToBeRectified);
        }

        return NN.sigmoid(numToBeRectified);
    }
    public static void setParameters(){
// This is setting the parameters of the model. The layerArray is the array of the number of neurons in
// each layer. The learning rate is the rate at which the model learns.
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
