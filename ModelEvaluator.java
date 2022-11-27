import java.util.ArrayList;

public class ModelEvaluator {
    // Used to evaluate the predictions of the model, example pricision, accuracy
    // and confusion matrix.

    private int correctCounter;
    private int turnsCounter;
    private double trainingAccuracy;
    private double testingAccuracy;
    protected ArrayList<Double[]> confusionMatrix = new ArrayList<Double[]>();

    protected void updateConfusionMatrix(ArrayList<Double> expectedOutputArray, int predictedNeuronNum) {
        ArrayList<Double> temp = new ArrayList<Double>();
        for (int i = 0; i <= expectedOutputArray.size(); i++) {
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

    protected void updateConfusionMatrix(ArrayList<Double> expectedOutputArray, ArrayList<Double> actualOutputArray) {
        // This is checking if the confusion matrix has been initialized, if it has not
        // been
        // initialized, it initializes it.
        if (confusionMatrix.size() != expectedOutputArray.size()) {// arraylist has not been initialized
            initializeList(expectedOutputArray.size());
        }
        for (int i = 0; i < expectedOutputArray.size(); i++) {

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

    protected void initializeList(int size) {
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

    protected void updatePredictionData(double prediction, double label, double confidance) {
        // Updating the prediction data.
        turnsCounter++;
        if (prediction == label) {
            correctCounter++;
        }

    }

    protected void reset() {
        correctCounter = 0;
        turnsCounter = 0;
        resetConfusionMatrix();
    }

    public void resetConfusionMatrix() {
        confusionMatrix.clear();
    }

    protected double getAccuracy() {
        return (double) 100 * correctCounter / turnsCounter; // be careful for zero turns!

    }

    protected void setTrainingaccuracy(double accuracy) {
        this.trainingAccuracy = accuracy;
    }

    protected double getTrainingAccuracy() {
        return trainingAccuracy;
    }

    protected void setTestingaccuracy(double accuracy) {
        this.testingAccuracy = accuracy;
    }

    protected double getTestingAccuracy() {
        return testingAccuracy;
    }

    protected void printConfusionMatrix(InputFileReader fileReader) {
        // Printing the confusion matrix.
        for (int i = 0; i < confusionMatrix.size(); i++) {
            System.out.println("Label:" + fileReader.getPredictionFromNeuronNum(i));
            System.out.println("True Positive :" + confusionMatrix.get(i)[0]);
            System.out.println("True Negative :" + confusionMatrix.get(i)[3]);
            System.out.println("False Positive :" + confusionMatrix.get(i)[1]);
            System.out.println("False Negative :" + confusionMatrix.get(i)[2]);
            System.out.println("\n");
        }
    }
}
