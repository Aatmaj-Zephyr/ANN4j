public class InputLayer extends Layer {

    InputLayer(int numOfNeurons) {
        super(numOfNeurons);

    }

    public void setInput(double[] inputLayerArray) {
        // This is setting the activation of each neuron in the input layer to the
        // corresponding value
        // in the input array.
        for (int i = 0; i <= inputLayerArray.length - 1; i++) {
            listOfNeurons.get(i).setActivation(inputLayerArray[i]);
        }
    }

    @Override
    public double calculateDelta(Neuron i) {
        // This is calculating the delta of the input layer.
        double activation = i.getActivation();
        double weightedSum = 0;
        for (Connection j : i.rightConnections) {
            weightedSum += j.getBackwardWeightedSum();
        }
        return weightedSum * activation * (1 - activation);
    }

}
