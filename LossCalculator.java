public class LossCalculator {
    public static double calculateLossfunctionByLayer(OutputLayer outputLayer, OutputLayer expectedLayer) {
        // Checking if the length of the output layer and the expected layer are the
        // same. If they are not, it throws an error.
        if (outputLayer.getListOfNeurons().size() != expectedLayer.getListOfNeurons().size()) {
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }

        // Mean squared error
        double[] sum = new double[outputLayer.getListOfNeurons().size()];
        // Calculating the mean squared error between the output layer and the expected
        // layer.
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            sum[i] = Math.pow((outputLayer.getListOfNeurons().get(i).activation
                    - expectedLayer.getListOfNeurons().get(i).activation), 2);
        }
        return NN.average(sum);
    }

    public static double calculateLossFunction(OutputLayer outputLayer, double[] expectedLayer) {

        // Overloaded for arrays as input

        if (outputLayer.getListOfNeurons().size() != expectedLayer.length) {
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }

        // Mean squared error
        double[] sum = new double[outputLayer.getListOfNeurons().size()];
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            sum[i] = Math.pow((outputLayer.getListOfNeurons().get(i).activation - expectedLayer[i]), 2);

        }

        return NN.average(sum);
    }

    public static double calculateDifference(OutputLayer outputLayer, double[] expectedLayer) {

        // Overloaded for arrays as input

        if (outputLayer.getListOfNeurons().size() != expectedLayer.length) {
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }

        // Mean squared error
        double[] sum = new double[outputLayer.getListOfNeurons().size()];
        for (int i = 0; i < outputLayer.getListOfNeurons().size(); i++) {
            sum[i] = outputLayer.getListOfNeurons().get(i).activation - expectedLayer[i];

        }
        // System.out.println(NN.average(sum));
        return NN.average(sum);
    }

}
