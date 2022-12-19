package ann4j;
import java.util.ArrayList;

 public class MeanSquaredErrorCalculator {

    /**
     * The function takes in an output layer and an array list of expected outputs. It then calculates
     * the mean squared error between the output layer and the expected output array list
     * 
     * @param outputLayer The output layer of the neural network
     * @param expectedOutputArrayList The expected output of the neural network.
     * @return The mean squared error.
     */
    public static double calculateMSE(OutputLayer outputLayer, ArrayList<Double> expectedOutputArrayList) {

        if (outputLayer.getSize() != expectedOutputArrayList.size()) {
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }

        // Mean squared error
        double[] sum = new double[outputLayer.getListOfNeurons().size()];
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            sum[i] = Math.pow((outputLayer.getListOfNeurons().get(i).getActivation() - expectedOutputArrayList.get(i)),
                    2);

        }

        return NN.average(sum);
    }

}
