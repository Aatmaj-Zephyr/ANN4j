import java.util.ArrayList;

public class MeanSquaredErrorCalculator {

    protected static double calculateMSE(OutputLayer outputLayer, ArrayList<Double> expectedOutputArrayList) {

        if (outputLayer.getSize() != expectedOutputArrayList.size()) {
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }

        // Mean squared error
        double[] sum = new double[outputLayer.getListOfNeurons().size()];
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            sum[i] = Math.pow((outputLayer.getListOfNeurons().get(i).getActivation() - expectedOutputArrayList.get(i)), 2);

        }

        return NN.average(sum);
    }

}
