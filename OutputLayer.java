public class OutputLayer extends Layer {

    OutputLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
    public double calculateDelta(Neuron i) {
        double expectedValue = LayerManager.ExpectedOutputArray[i.getNeuronNum()];
        double activation = i.getActivation();
        return activation * (1 - activation) * (expectedValue - activation);
    }

}
