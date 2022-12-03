package src;
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

    public Trainer() {
        this.myLayerManager = new LayerManager(parameter.getLayerArray());
        this.trainingFileReader = parameter.getTrainingFileReader();
        this.testingFileReader = parameter.getTestingFileReader();
        this.myModelEvaluator = parameter.getModelEvaluator();

    }

    public LayerManager getLayerManager() {
        return myLayerManager;
    }

    public void train(int noOfSamples, int epochs) {
        for(int j = 0; j < epochs; j++){

            this.trainingFileReader = parameter.getTrainingFileReader();
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

    public void printConfusionMatrix(){
        myModelEvaluator.printConfusionMatrix(testingFileReader);
    
    }
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

    private void forwardPropagatewithExclusionInputLayer() {
        myLayerManager.forwardPropagate(); // for calculation of MSE
        myLayerManager.forwardPropagatewithExclusionInputLayer();

    }

    public void relevancePropagate(int layerNumber, int neuronNumber) {

        myLayerManager.relevancePropagate(layerNumber, neuronNumber);
    }

    private void test() {
        myLayerManager.setInputLayer(inputLayer);
        myLayerManager.setExpectedOutputArray(expectedLayer);
        myLayerManager.forwardPropagate();

    }

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
