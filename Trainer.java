import java.util.ArrayList;

public class Trainer {
    // template pattern

    protected LayerManager myLayerManager;
    protected ArrayList<Double> expectedLayer;
    protected InputFileReader trainingFileReader;
    protected InputFileReader testingFileReader;
    private ModelEvaluator myModelEvaluator;

    ArrayList<Double> inputLayer;
    double label;
    private double prediction;
    private double confidence;

    Trainer() {
        this.myLayerManager = new LayerManager(parameter.getLayerArray());
        this.trainingFileReader = parameter.getTrainingFileReader();
        this.testingFileReader = parameter.getTestingFileReader();
        this.myModelEvaluator = parameter.getModelEvaluator();

    }

    public LayerManager getLayerManager() {
        return myLayerManager;
    }

    protected void train(int noOfSamples, int epochs) {
        for(int j = 0; j < epochs; j++){

            parameter.setTrainingFileReader("mnist_train.csv", "mnist");
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

                prediction = myLayerManager.getMostSignificantNeuronAsPrediction();

   
                myModelEvaluator.updatePredictionData(prediction, label, confidence);

            }
        myModelEvaluator.setTrainingaccuracy(myModelEvaluator.getAccuracy());
        System.out.println("Training accuracy in epoch "+j+" is " + myModelEvaluator.getTrainingAccuracy());

        myModelEvaluator.reset();

        }
    }

    protected void test(int noOfSamples) {
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
            prediction = myLayerManager.getMostSignificantNeuronAsPrediction();

            
            myModelEvaluator.updatePredictionData(prediction, label, confidence);
            //System.out.println("Digit " + label + " is predicted as " + prediction + " with confidence " + confidence);
        }
        myModelEvaluator.setTestingaccuracy(myModelEvaluator.getAccuracy());

        System.out.println("Testing accuracy " + myModelEvaluator.getTestingAccuracy());
    }


    protected void forwardPropagatewithExclusionInputLayerOnKSamples(int noOfSamples) {
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

    protected void relevancePropagate(int layerNumber, int neuronNumber) {

        myLayerManager.relevancePropagate(layerNumber, neuronNumber);
    }

    private void test() {
        myLayerManager.setInputLayer(inputLayer);
        myLayerManager.setExpectedOutputArray(expectedLayer);
        myLayerManager.forwardPropagate();

    }

    protected void train() {
        // Heart of the code

        myLayerManager.setInputLayer(inputLayer);

        myLayerManager.setExpectedOutputArray(expectedLayer);

        myLayerManager.forwardPropagate();

        myLayerManager.backwardPropagate();

        prediction = myLayerManager.getMostSignificantNeuronAsPrediction();

        // System.out.println(" prediction " + prediction);
    }

}
