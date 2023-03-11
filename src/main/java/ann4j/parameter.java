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

   /**
    * This function returns the bias learning rate
    * 
    * @return The bias learning rate.
    */
    public static double getBiasLearningRate() {
        return biasLearningRate;
    }

    /**
     * Get the value of epsillion for relevance propagation
     * 
     * @return The value of the epsillion variable.
     */
    public static double getEpsillion() {
        return epsillion;
    }

   /**
    * This function sets the epsillion value to the value passed in
    * 
    * @param epsillion The value of the epsillion variable to be set.
    */
    public static void setEpsillion(double epsillion) {
        parameter.epsillion = epsillion;
    }

   /**
    * This function sets the learning rate for the bias
    * 
    * @param biasLearningRate The learning rate for the bias.
    */
    public static void setBiasLearningRate(double biasLearningRate) {
        parameter.biasLearningRate = biasLearningRate;
    }

    /**
     * This function sets the rectification function to be used in the network
     * 
     * @param rectificationFunction The rectification function to use.
     */
    public static void setRectificationFunction(String rectificationFunction) {
        parameter.rectificationFunction = rectificationFunction;
    }

    /**
     * This function returns the trainingFileReader object
     * 
     * @return The trainingFileReader object.
     */
    public static InputFileReader getTrainingFileReader() {
        return trainingFileReader;
    }

   /**
    * This function returns the testingFileReader object
    * 
    * @return The InputFileReader object (testing file reader) that was created in the static block.
    */
    public static InputFileReader getTestingFileReader() {
        return testingFileReader;
    }

    /**
     * It sets the testing file reader to the required file reader. This needs to be overridden for adding custom file reader types.
     * 
     * @param fileName The name of the file to be read
     * @param type The type of file you are reading. Currently, only "mnist" is supported.
     */
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

    /**
     * This function sets the learning rate of the neural network
     * 
     * @param learningRate The learning rate of the neural network.
     */
    public static void setLearningRate(double learningRate) {
        parameter.learningRate = learningRate;
    }
   // This function is used to set the training file reader to the input file reader passed in.
    public static void setTrainingFileReader(InputFileReader inputFileReader){
        parameter.trainingFileReader = inputFileReader;
    }

   // This function is used to set the testing file reader to the input file reader passed in.
    public static void setTestingFileReader(InputFileReader inputFileReader){
        parameter.testingFileReader = inputFileReader;
    }
   // This function is used to set the training file reader to the input file reader passed in.
    public static void setTrainingFileReader(String fileName, String type) {
        try {
            if (type == "mnist") {
                parameter.trainingFileReader = new MNISTDataBaseFileReader(fileName, layerArray[layerArray.length - 1]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

   // This is used to set the number of neurons to be tested in relevance propagation.
    public static void setNeuronNumberToBeTestedinRelavancePropagation(
            double NeuronNumberToBeTestedinRelavancePropagation) {
        LayerManager.setNeuronNumberToBeTestedinRelavancePropagation(NeuronNumberToBeTestedinRelavancePropagation);
    }

     // This is the rectification function. It is used to rectify the output of the
        // neuron.
        // The rectification function will rectify according to the string provided. The
        // functions fromn NN module will be utilized for this.
        public static double rectify(double numToBeRectified) {
       
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

        //add custom function here...
        return NN.sigmoid(numToBeRectified); 
    }
    /*future scope
    Add custom rectification function functionality. Need to see how.

    */


   // Setting the number of input and output neurons.
    public static void setLayerArray(int... LayerArray) {
        parameter.numberOfInputNeurons = LayerArray[0];
        parameter.numberOfOutputNeurons = LayerArray[LayerArray.length-1];
        parameter.layerArray = LayerArray;
    }

    // Returning the learning rate of the neural network.
    public static double getLearningRate() {
        return learningRate;
    }

    // Returning the layer array.
    public static int[] getLayerArray() {
        return layerArray;
    }

   // Returning the batch size.
    public static int getBatchsize() {
        return batchsize;
    }

    // Setting the batch size.
    public static void setBatchsize(int batchsize) {
        parameter.batchsize = batchsize;
    }

   /**
    * > The function `getModelEvaluator()` returns a new instance of the class `ModelEvaluator`
    * 
    * @return A new instance of the ModelEvaluator class.
    */
    public static ModelEvaluator getModelEvaluator() {
        return new ModelEvaluator();
    }

   /**
    * It sets the output file and enables/disables printing in console.
    * 
    * @param string The name of the file to write to.
    * @param printInConsoleEnabled If true, the output will be printed in the console.
    */
    public static void setOutputFile(String string, boolean printInConsoleEnabled) {
        Writer.setFile(string);
        Writer.setPrintInConsoleEnabled(printInConsoleEnabled);
    }
    /**
     * Ends the display of the current page.
     */
    public static void display(){
        Writer.end();
    }
}

/*
 * 
 * How to add a new rectification function in this class.
 * Step 1) Write your function It must be a static function. It must take in a double value as input and return a double value as output.
 * Example 
 * public static double relu(double numToBeRectified) {
        if (numToBeRectified > 0) { //logic of code
            return numToBeRectified;
        }
        return 0;
    }

 * Step 2) Add the following snippet to the rectify functiuon of this class before return statement
 * 
 *  if (rectificationFunction == "Your function name") {
            return fun(numToBeRectified);
        }
 * 
 * For more information on the rectification functions please visit https://en.wikipedia.org/wiki/Rectifier_(neural_networks)
 * 
 */
