package ann4j;
import java.util.ArrayList;

 public class ModelEvaluator {
    // Used to evaluate the predictions of the model, example pricision, accuracy
    // and confusion matrix.

    private int correctCounter;
    private int turnsCounter;
    private double trainingAccuracy;
    private double testingAccuracy;
    public ArrayList<Double[]> confusionMatrix = new ArrayList<Double[]>();

    /**
     * This function updates the confusion matrix by comparing the expected output array with the
     * predicted output array
     * 
     * @param expectedOutputArray The expected output of the neural network.
     * @param predictedNeuronNum The index of the neuron that was predicted to be the correct one.
     */
    public void updateConfusionMatrix(ArrayList<Double> expectedOutputArray, int predictedNeuronNum) {
        ArrayList<Double> temp = new ArrayList<Double>();
        for (int i = 0; i <= parameter.numberOfOutputNeurons; i++) {
            if (i == predictedNeuronNum) {
                temp.add(i, 1.0);
            } else {
                temp.add(i, 0.0);
            }
        }
        updateConfusionMatrix(expectedOutputArray, temp);
    }

    /**
     * This function takes in two arrays, one of expected outputs and one of actual
     * outputs, and updates
     * the confusion matrix accordingly
     * 
     * __ __ __ __
     * |TP FP
     * | 0 1
     * |FN TN
     * | 2 3
     * 
     * @param expectedOutputArray The expected output of the neural network
     * @param actualOutputArray   The actual output of the neural network
     */
    public void updateConfusionMatrix(ArrayList<Double> expectedOutputArray, ArrayList<Double> actualOutputArray) {
        // This is checking if the confusion matrix has been initialized, if it has not
        // been
        // initialized, it initializes it.
        if (confusionMatrix.size() != parameter.numberOfOutputNeurons) {// arraylist has not been initialized
            initializeList(parameter.numberOfOutputNeurons);
        }
        for (int i = 0; i < parameter.numberOfOutputNeurons; i++) {

            // This is updating the confusion matrix.
            if (expectedOutputArray.get(i) == 1.0 & actualOutputArray.get(i) == 1.0) { // True positives
                confusionMatrix.get(i)[0] += 1.0;
            } else if (expectedOutputArray.get(i) == 0.0 & actualOutputArray.get(i) == 0.0) { // True negatives
                confusionMatrix.get(i)[3] += 1.0;
            } else if (expectedOutputArray.get(i) == 0.0 & actualOutputArray.get(i) == 1.0) { // false positive
                confusionMatrix.get(i)[1] += 1.0;
            } else if (expectedOutputArray.get(i) == 1.0 & actualOutputArray.get(i) == 0.0) { // false negatives
                confusionMatrix.get(i)[2] += 1.0;
            }
        }
    }

   /**
    * Initialize the confusion matrix to all zeros
    * 
    * @param size The number of classes in the dataset.
    */
    public void initializeList(int size) {
        confusionMatrix.clear();
        for (int i = 0; i < size; i++) {
            // Initializing the arraylist to have 4 elements.
            confusionMatrix.add(i, new Double[4]);
            // Initializing the confusion matrix to all zeros.
            confusionMatrix.get(i)[0] = 0.0;
            confusionMatrix.get(i)[1] = 0.0;
            confusionMatrix.get(i)[2] = 0.0;
            confusionMatrix.get(i)[3] = 0.0;
        }

    }

  /**
   * This function updates the prediction data
   * 
   * @param prediction The prediction of the model.
   * @param label The actual label of the data.
   * @param confidance The confidance of the prediction.
   */
    public void updatePredictionData(double prediction, double label, double confidance) {
        // Updating the prediction data.
        turnsCounter++;
        if (prediction == label) {
            correctCounter++;
        }

    }

    /**
     * This function resets the confusion matrix, the correct counter, and the turns counter
     */
    public void reset() {
        correctCounter = 0;
        turnsCounter = 0;
        resetConfusionMatrix();
    }

    public void resetConfusionMatrix() {
        confusionMatrix.clear();
    }

/**
 * It returns the accuracy of the player as a percentage
 * 
 * @return The accuracy of the player.
 */
    public double getAccuracy() {
        return (double) 100 * correctCounter / turnsCounter; // be careful for zero turns!

    }

    /**
     * This function sets the training accuracy of the model
     * 
     * @param accuracy The accuracy of the model on the training data.
     */
    public void setTrainingaccuracy(double accuracy) {
        this.trainingAccuracy = accuracy;
    }

   /**
    * This function returns the training accuracy of the model
    * 
    * @return The training accuracy of the model.
    */
    public double getTrainingAccuracy() {
        return trainingAccuracy;
    }

    /**
     * This function sets the testing accuracy of the model
     * 
     * @param accuracy The accuracy of the model on the test data.
     */
    public void setTestingaccuracy(double accuracy) {
        this.testingAccuracy = accuracy;
    }

   /**
    * This function returns the testing accuracy of the model
    * 
    * @return The testing accuracy of the model.
    */
    public double getTestingAccuracy() {
        return testingAccuracy;
    }

    /**
     * It prints the confusion matrix
     * 
     * @param fileReader This is the object of the InputFileReader class.
     */
    public void printConfusionMatrix(InputFileReader fileReader) {
        // Printing the confusion matrix.
        for (int i = 0; i < confusionMatrix.size(); i++) {
            Writer.writeln("Label:" + fileReader.getPredictionFromNeuronNum(i));
            Writer.writeln("True Positive :" + confusionMatrix.get(i)[0]);
            Writer.writeln("True Negative :" + confusionMatrix.get(i)[3]);
            Writer.writeln("False Positive :" + confusionMatrix.get(i)[1]);
            Writer.writeln("False Negative :" + confusionMatrix.get(i)[2]);
            Writer.writeln("\n");
        }
    }
}
