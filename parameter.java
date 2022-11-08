import java.io.FileNotFoundException;

public class parameter {
    //This class is used for setting the parameters of the model. 
    //There is no point in passing its object to the layerManager or Trainer classes so it is static class.
    private static int[] layerArray; //used only by trainer class. Can be made non static and object of this class can be passed if required.
    private static InputFileReader trainingFileReader; //used only by trainer class. Can be made non static and object of this class can be passed if required.
    private static double learningRate; //static because it is used by many classes.
    private static String rectificationFunction; //static because it is used by many classes.
    private static InputFileReader testingFileReader;
    private static int batchsize;
    private static double biasLearningRate;
    public static double getBiasLearningRate() {
        return biasLearningRate;
    }
    public static void setBiasLearningRate(double biasLearningRate) {
        parameter.biasLearningRate = biasLearningRate;
    }
    public static void setRectificationFunction(String rectificationFunction) {
        parameter.rectificationFunction = rectificationFunction;
    }
    public static InputFileReader getTrainingFileReader() {
        return trainingFileReader;
    }
    public static InputFileReader getTestingFileReader() {
        return testingFileReader;
    }
    
    public static void setTestingFileReader(String fileName, String type) {
        try {
        if(type=="mnist"){
            parameter.testingFileReader = new MNISTDataBaseFileReader(fileName);
        }
    } catch (FileNotFoundException ex) {
    }
    }
    public static void setLearningRate(double learningRate) {
        parameter.learningRate = learningRate;
    }
    public static void setTrainingFileReader(String fileName, String type) {
        try {
        if(type=="mnist"){
            parameter.trainingFileReader = new MNISTDataBaseFileReader(fileName);
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
    public static void setLayerArray(int...LayerArray){
        parameter.layerArray = LayerArray;
    }
    public static double getLearningRate() {
        return learningRate;
    }
    public static int[] getLayerArray() {
        return layerArray;
    }
    public static int getBatchsize() {
        return batchsize;
    }
    public static void setBatchsize(int batchsize) {
        parameter.batchsize = batchsize;
    }
}
