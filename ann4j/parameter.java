package ann4j;
import java.io.FileNotFoundException;

 public class parameter {
    // This class is used for setting the parameters of the model.
    // There is no point in passing its object to the layerManager or Trainer
    // classes so it is static class.
    private static int[] layerArray; // used only by trainer class. Can be made non static and object of this class
                                     // can be passed if required.
    private static InputFileReader trainingFileReader; // used only by trainer class. Can be made non static and object
                                                       // of this class can be passed if required.
    private static double learningRate; // static because it is used by many classes.
    private static String rectificationFunction; // static because it is used by many classes.
    private static InputFileReader testingFileReader;
    private static int batchsize;
    private static double biasLearningRate;
    private static double epsillion;
  // Used to store the number of input and output neurons.
    public static int numberOfOutputNeurons;
    public static int numberOfInputNeurons;

    public static double getBiasLearningRate() {
        return biasLearningRate;
    }

    public static double getEpsillion() {
        return epsillion;
    }

    public static void setEpsillion(double epsillion) {
        parameter.epsillion = epsillion;
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
            if (type == "mnist") {
                parameter.testingFileReader = new MNISTDataBaseFileReader(fileName, layerArray[layerArray.length - 1]);
                // The layer array must be initialized before this always
            }
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        }
    }

    public static void setLearningRate(double learningRate) {
        parameter.learningRate = learningRate;
    }
    public static void setTrainingFileReader(InputFileReader inputFileReader){
        parameter.trainingFileReader = inputFileReader;
    }

    public static void setTestingFileReader(InputFileReader inputFileReader){
        parameter.testingFileReader = inputFileReader;
    }
    public static void setTrainingFileReader(String fileName, String type) {
        try {
            if (type == "mnist") {
                parameter.trainingFileReader = new MNISTDataBaseFileReader(fileName, layerArray[layerArray.length - 1]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void setNeuronNumberToBeTestedinRelavancePropagation(
            double NeuronNumberToBeTestedinRelavancePropagation) {
        LayerManager.setNeuronNumberToBeTestedinRelavancePropagation(NeuronNumberToBeTestedinRelavancePropagation);
    }

    public static double rectify(double numToBeRectified) {
        // This is the rectification function. It is used to rectify the output of the
        // neuron.
        // The rectification function will rectify according to the string provided. The
        // functions fromn NN module will be utilized for this.
        if (rectificationFunction == "relu") {
            return NN.relu(numToBeRectified);
        }
        if (rectificationFunction == "tanh") {
            return NN.tanh(numToBeRectified);
        }
        if (rectificationFunction == "softplus") {
            return NN.softplus(numToBeRectified);
        }
        if (rectificationFunction == "leakyrelu") {
            return NN.leakyrelu(numToBeRectified);
        }
        if (rectificationFunction == "sigmoid") {
            return NN.sigmoid(numToBeRectified);
        }

        return NN.sigmoid(numToBeRectified); //add custom function here...
    }

    public static void setLayerArray(int... LayerArray) {
        parameter.numberOfInputNeurons = LayerArray[0];
        parameter.numberOfOutputNeurons = LayerArray[LayerArray.length-1];
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

    public static ModelEvaluator getModelEvaluator() {
        return new ModelEvaluator();
    }

    public static void setOutputFile(String string, boolean printInConsoleEnabled) {
        Writer.setFile(string);
        Writer.setPrintInConsoleEnabled(printInConsoleEnabled);
    }
    public static void display(){
        Writer.end();
    }
}
