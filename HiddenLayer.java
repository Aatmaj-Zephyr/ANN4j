public class HiddenLayer extends Layer {
    HiddenLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
    public void calculateDelta(Neuron i) {
        // Calculating the delta for the output layer.
        double activation = i.getActivation();
        double weightedSum = 0;
        for (Connection j : i.rightConnections) {
            weightedSum += j.getBackwardWeightedSum();
        }
        i.delta= weightedSum * activation * (1 - activation);
    }

}
