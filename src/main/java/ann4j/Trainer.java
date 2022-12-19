package ann4j;
import java.util.ArrayList;

 public class Trainer {
    // template pattern

    public LayerManager myLayerManager;
    public ArrayList<Double> expectedLayer;
    public InputFileReader trainingFileReader;
    public InputFileReader testingFileReader;
    private ModelEvaluator myModelEvaluator;

    ArrayList<Double> inputLayer;
    double label;
    private double prediction;
    private double confidence;
   /**
    * This function returns the model evaluator
    * 
    * @return The ModelEvaluator object.
    */
    public ModelEvaluator getModelEvaluator() {
        return myModelEvaluator;
    }
    // This is the constructor of the Trainer class. It is initializing the LayerManager,
    // trainingFileReader, testingFileReader and myModelEvaluator.
    public Trainer() {
        this.myLayerManager = new LayerManager(parameter.getLayerArray());
        this.trainingFileReader = parameter.getTrainingFileReader();
        this.testingFileReader = parameter.getTestingFileReader();
        this.myModelEvaluator = parameter.getModelEvaluator();

    }

    /**
     * Returns the LayerManager object that is used to manage the layers in this map.
     * 
     * @return The LayerManager object.
     */
    public LayerManager getLayerManager() {
        return myLayerManager;
    }



    /**
     * The function trains the neural network by reading the training data from the mnist database and
     * updating the weights and biases of the neural network
     * 
     * @param noOfSamples The number of samples to train on.
     * @param epochs Number of times the training data is to be trained.
     */
    public void train(int noOfSamples, int epochs) {
        for(int j = 0; j < epochs; j++){

            this.trainingFileReader = parameter.getTrainingFileReader();
            trainingFileReader.restart();
            for (int i = 0; i < noOfSamples; i++) {

                // Getting the next image from the mnist database.
                trainingFileReader.next();

                // Getting the expected output array from the mnist database.
                expectedLayer = trainingFileReader.getExpectedOutputArray();

                // Getting the input array from the mnist database.
                inputLayer = trainingFileReader.getInputArray();

                // Getting the label of the image from the mnist database.
                label = trainingFileReader.getLabel();
                train();
                confidence = myLayerManager.getconfidence(); //get confidence after the training

                prediction = trainingFileReader.getPredictionFromNeuronNum(myLayerManager.getMostSignificantNeuronNumAsPrediction());


   
                myModelEvaluator.updatePredictionData(prediction, label, confidence);
                myModelEvaluator.updateConfusionMatrix(expectedLayer,myLayerManager.getMostSignificantNeuronNumAsPrediction());


            }
        myModelEvaluator.setTrainingaccuracy(myModelEvaluator.getAccuracy());
        Writer.writeln("Training accuracy in epoch "+j+" is " + myModelEvaluator.getTrainingAccuracy());

        myModelEvaluator.reset();

        }
    }

    /**
     * Used to test the model.
     * 
     * @param noOfSamples The number of samples to be tested.
     */
    public void test(int noOfSamples) {
        //no epochs in testing
        for (int i = 0; i < noOfSamples; i++) {
            // Getting the next image from the mnist database.
            testingFileReader.next();

            // Getting the expected output array from the mnist database.
            expectedLayer = testingFileReader.getExpectedOutputArray();

            // Getting the input array from the mnist database.
            inputLayer = testingFileReader.getInputArray();

            // Getting the label of the image from the mnist database.
            label = testingFileReader.getLabel();
            test();
            confidence = myLayerManager.getconfidence(); //get confidence after the testing
            // Getting the prediction from the predicted neuron number.
            prediction = testingFileReader.getPredictionFromNeuronNum(myLayerManager.getMostSignificantNeuronNumAsPrediction());

            
            myModelEvaluator.updatePredictionData(prediction, label, confidence);
            myModelEvaluator.updateConfusionMatrix(expectedLayer,myLayerManager.getMostSignificantNeuronNumAsPrediction());
            //Writer.writeln("Digit " + label + " is predicted as " + prediction + " with confidence " + confidence);
        }
        myModelEvaluator.setTestingaccuracy(myModelEvaluator.getAccuracy());

        Writer.writeln("Testing accuracy " + myModelEvaluator.getTestingAccuracy());
    }

    /**
     * This function prints the confusion matrix of the model
     */
    public void printConfusionMatrix(){
        myModelEvaluator.printConfusionMatrix(testingFileReader);
    
    }
   // This is used for calculating the relevance of the neurons in the hidden layers.
    public void forwardPropagatewithExclusionInputLayerOnKSamples(int noOfSamples) {
        for (int i = 0; i < noOfSamples; i++) {
            // Getting the next image from the mnist database.
            testingFileReader.next();

            // Getting the expected output array from the mnist database.
            expectedLayer = testingFileReader.getExpectedOutputArray();

            // Getting the input array from the mnist database.
            inputLayer = testingFileReader.getInputArray();

            // Getting the label of the image from the mnist database.
            label = testingFileReader.getLabel();

            forwardPropagatewithExclusionInputLayer();
        }
    }

  /**
   * This function is used to forward propagate the network, but it excludes the input layer
   */
    private void forwardPropagatewithExclusionInputLayer() {
        myLayerManager.forwardPropagate(); // for calculation of MSE
        myLayerManager.forwardPropagatewithExclusionInputLayer();

    }

    /**
     * This function propagates relevance from the output layer to the input layer
     * 
     * @param layerNumber The layer number of the neuron you want to propagate relevance from.
     * @param neuronNumber The neuron number in the layer.
     */
    public void relevancePropagate(int layerNumber, int neuronNumber) {

        myLayerManager.relevancePropagate(layerNumber, neuronNumber);
    }

    /**
     * The function takes in an input layer and an expected output layer, and then it runs the forward
     * propagation algorithm on the input layer, and then it compares the output layer to the expected
     * output layer
     */
    private void test() {
        myLayerManager.setInputLayer(inputLayer);
        myLayerManager.setExpectedOutputArray(expectedLayer);
        myLayerManager.forwardPropagate();

    }

    /**
     * The function takes in an input layer and an expected output layer, and then it uses the input
     * layer to predict the expected output layer
     */
    public void train() {
        // Heart of the code

        myLayerManager.setInputLayer(inputLayer);

        myLayerManager.setExpectedOutputArray(expectedLayer);

        myLayerManager.forwardPropagate();

        myLayerManager.backwardPropagate();

        prediction = myLayerManager.getMostSignificantNeuronNumAsPrediction();

        // Writer.writeln(" prediction " + prediction);
    }

}
